package fr.utonium.bubbles.compiler;

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


public class JCompiler implements Compiler {

    File root;
    Iterable<? extends JavaFileObject> unit;
    JavaCompiler compiler;
    StandardJavaFileManager fileManager;
    static JCompiler INSTANCE = null;

    private JCompiler() {
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = compiler.getStandardFileManager(null, null, null);
    }

    public static JCompiler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new JCompiler();
        }
        return INSTANCE;
    }

    @Override
    public void setFiles(File classPath, File[] files) {
        root = classPath;
        unit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files));

    }
    @Override
    public void compile() {
        compiler.getTask(null, fileManager, null, null, null, unit).call();
    }

    @Override
    public Object run(String clazz, String method, Object[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {root.toURI().toURL()});
        Class<?> cls = Class.forName(clazz, true, classLoader);

        Class[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++){
            types [i] = args[i].getClass();
            System.out.println(types[i]);
        }

        Method hand = cls.getMethod(method, types);
        Object ret = hand.invoke(null, args);

        return ret;
    }

    @Override
    public Object run(String clazz, String method) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {root.toURI().toURL()});
        Class<?> cls = Class.forName(clazz, true, classLoader);
        Method hand = cls.getMethod(method);
        Object ret = hand.invoke(null, new Object[0]);

        return ret;
    }

    // TODO
    @Override
    public void clean() {
    }

}
