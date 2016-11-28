package fr.utonium.blossom.compiler

import javax.tools.JavaCompiler
import javax.tools.JavaFileObject
import javax.tools.StandardJavaFileManager
import javax.tools.ToolProvider
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.net.MalformedURLException
import java.net.URLClassLoader
import java.util.Arrays

/** 
 * Created by francois on 11/25/16.
 */
class JCompiler implements Compiler {
    File root
    Iterable<? extends JavaFileObject> unit
    JavaCompiler compiler
    StandardJavaFileManager fileManager
    static JCompiler INSTANCE = null

    private new() {
        compiler = ToolProvider.getSystemJavaCompiler()
        fileManager = compiler.getStandardFileManager(null, null, null)
    }

    def static JCompiler getInstance() {
        if(INSTANCE === null) {
            INSTANCE = new JCompiler()
        }
        return INSTANCE
    }

    override void setFiles(File classPath, File[] files) {
        root = classPath
        unit = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files))

    }

    override void compile() {
        compiler.getTask(null, fileManager, null, null, null, unit).call()
    }

    // TODO: Override stdin @runtime to check values
    override int run() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        var URLClassLoader classLoader = URLClassLoader.newInstance(#[root.toURI().toURL()])
        var Class<?> cls = Class.forName("Test", true, classLoader)
        var String[] params = #[]
        var Method hand = cls.getMethod("main", typeof(String[]))
        hand.invoke(null, (params as Object))
        return 0
    }

    // TODO
    override void clean() {
    }
}
