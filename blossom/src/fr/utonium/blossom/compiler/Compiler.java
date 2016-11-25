package fr.utonium.blossom.compiler;

import java.io.File;

/**
 * Created by francois on 11/25/16.
 */
public interface Compiler {


    public void setFiles(File classPath, File[] files);

    public void compile();

    public int run() throws Exception;

    public void clean();
}
