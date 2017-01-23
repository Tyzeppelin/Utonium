package fr.utonium.bubbles.main;

import fr.utonium.bubbles.loader.Check;
import fr.utonium.bubbles.loader.XParser;

import java.util.HashMap;
import java.util.List;

public class Bubbles {

    public static void main(String[] args) {
        /*// root
        File root = new File("res/tests-classes/");
        // open source file.
        File[] sourceFiles = new File[]{new File("res/tests-classes/Test.java"), new File("res/tests-classes/Fun.java")};

        Compiler co = JCompiler.getInstance();
        co.setFiles(root, sourceFiles);
        co.compile();
        Object c = 1;
        try {
            c = co.run("Test", "main", new Object[]{new String[0]});
        } catch(Exception e) {
            e.printStackTrace();
        }

        Object ret1 = null, ret2 = null, ret3 = null;

        try {
            ret1 = co.run("Fun", "met");
            ret2 = co.run("Fun", "ho");
            ret3 = co.run("Fun", "de", new Object[]{1f});
        }catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(c);

        System.out.println(ret1 + " " + ret2 + " " + ret3);
        */

        XParser p = new XParser("res/sample.json");

        String compilationCall = p.getCompile();
        List<HashMap<String, String>> tests = p.getExpected();

        Check ch = Check.getInstance();

        ch.generate(compilationCall);
        System.out.println("Done compiling");

        ch.checkAll(tests);
    }
}
