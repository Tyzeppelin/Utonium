package fr.utonium.blossom.compiler

import java.io.File

/** 
 * Created by francois on 11/25/16.
 */
interface Compiler {
    def void setFiles(File classPath, File[] files)

    def void compile()

    def int run() throws Exception

    def void clean()

}
