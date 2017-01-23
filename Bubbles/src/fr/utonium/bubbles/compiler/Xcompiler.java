package fr.utonium.bubbles.compiler;

import java.io.File;

/**
 * Created by francois on 22/01/2017.
 */
public class Xcompiler  implements Compiler{
    @Override
    public void setFiles(File classPath, File[] files) {

    }

    @Override
    public void compile() {

    }

    @Override
    public Object run(String clazz, String method, Object[] args) throws Exception {
        return 0;
    }

    @Override
    public Object run(String clazz, String method) throws Exception{
        return null;
    }

    @Override
    public void clean() {

    }
}
