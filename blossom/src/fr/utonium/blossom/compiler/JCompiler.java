package fr.utonium.blossom.compiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by francois on 11/25/16.
 */
public class JCompiler implements Compiler{

    private File root;
    private Iterable<? extends JavaFileObject> unit;

    private JavaCompiler compiler;
    private StandardJavaFileManager fileManager;

    private static JCompiler INSTANCE = null;

    private JCompiler(){

        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = compiler.getStandardFileManager(null, null, null);
    }

    public static JCompiler getInstance() {
        if (INSTANCE == null){
            INSTANCE = new JCompiler();
        }
        return INSTANCE;
    }

    @Override
    public void setFiles(File classPath, File[] files) {
        root = classPath;
        unit= fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files));
    }

    @Override
    public void compile() {
        compiler.getTask(null, fileManager, null, null, null, unit).call();
    }

    //TODO: Override stdin @runtime to check values
    @Override
    public int run() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
        Class<?> cls = Class.forName("Test", true, classLoader);

        String params[] = {};
        Method hand = cls.getMethod("main", String[].class);

        hand.invoke(null, (Object) params);

        return 0;
    }

    //TODO
    @Override
    public void clean() {}
}
