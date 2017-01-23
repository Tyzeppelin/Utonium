package fr.utonium.bubbles.loader;


import fr.utonium.bubbles.compiler.XCompiler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Check {

    XCompiler compiler;
    private static Check INSTANCE;

    private Check() {
        compiler = XCompiler.getInstance();
    }

    public static Check getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new Check();
        }
        return INSTANCE;

    }

    public void generate(String compilerCall) {
        try {
            compiler.run(compilerCall);
        } catch (IOException e){
            System.err.println("Compilation error: call: " + compilerCall);
            e.printStackTrace();
        }
    }

    public void checkAll(List<HashMap<String, String>> tests) {
        int len = tests.size(), counter = 0;
        for (HashMap<String, String> test: tests) {
            try {
                String r = compiler.run(test.get("call"));

                if (r.equals(test.get("return"))) {
                    System.out.println("["+ counter++ + "/" + len + "] : OK");
                }
                else {
                    System.out.println("["+ counter++ + "/" + len + "] : FAIL");
                    System.out.println("\t expected : " + test.get("return") + " but got : " + r);
                }

                //System.out.println(r + " " + test.get("return"));

            } catch (IOException e) {
                System.err.println("Can't test: call: " + test.get("call"));
                e.printStackTrace();
            }

        }
    }

}
