package fr.utonium.bubbles.main;

import fr.utonium.bubbles.compiler.Compiler;
import fr.utonium.bubbles.compiler.JCompiler;
import fr.utonium.bubbles.loader.Parser;

import java.io.File;
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

        //TODO: check args

        Parser p = new Parser("res/sample.json");

        Compiler com = JCompiler.getInstance();
        com.setFiles(p.getRootDirectory(), p.getFiles());

        com.compile();

        System.out.println("Done");

        List<HashMap<String, Object>> exp = p.getExpected();
        System.out.println(exp);
        for (HashMap<String, Object> c: exp) {
            System.out.println(c);
            if (c.keySet().contains("params")){
                try {
                    Object ret = com.run((String) c.get("class"), (String) c.get("method"), (Object[])c.get("params"));
                    System.out.println(ret + "==" + c.get("return"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Object ret = com.run((String) c.get("class"), (String) c.get("method"));
                    System.out.println(ret + "==" + c.get("return"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("YOLO");
    }
}
