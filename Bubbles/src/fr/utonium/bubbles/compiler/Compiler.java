package fr.utonium.bubbles.compiler;

import java.io.File;

public interface Compiler {

    void setFiles(File classPath, File[] files);

    void compile();

    Object run(String clazz, String method, Object[] args) throws Exception;

    Object run(String clazz, String method) throws Exception;

    void clean();

}
