package fr.utonium.blossom.main

import fr.utonium.blossom.compiler.Compiler
import fr.utonium.blossom.compiler.JCompiler
import java.io.File

/** 
 * Created by francois on 11/25/16.
 */
class Blossom {
    def static void main(String[] args) {
        // root
        var File root = new File("res/tests-classes/")
        // open source file.
        var File[] sourceFile = #[new File("res/tests-classes/Test.java")]
        var Compiler co = JCompiler.getInstance()
        co.setFiles(root, sourceFile)
        co.compile()
        var int c = 1
        try {
            c = co.run()
        } catch(Exception e) {
            e.printStackTrace()
        }

        System.out.println(c)
    }
}
