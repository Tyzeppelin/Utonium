package fr.utonium.blossom.main;

import fr.utonium.blossom.compiler.Compiler;
import fr.utonium.blossom.compiler.JCompiler;

import java.io.File;

/**
 * Created by francois on 11/25/16.
 */
public class Blossom {


    public static void main(String[] args) {

        // root
        File root = new File("res/tests-classes/");
        // open source file.
        File sourceFile[] = new File[] {new File("res/tests-classes/Test.java")};

        Compiler co = JCompiler.getInstance();

        co.setFiles(root, sourceFile);
        co.compile();
        int c = 1;
        try {
            c = co.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(c);

    }
}
