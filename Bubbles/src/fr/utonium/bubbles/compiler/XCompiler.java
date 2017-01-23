package fr.utonium.bubbles.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by francois on 22/01/2017.
 */
public class XCompiler {

    static XCompiler INSTANCE = null;
    Runtime runtime;


    private XCompiler() {
        runtime = Runtime.getRuntime();
    }

    public static XCompiler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XCompiler();
        }
        return INSTANCE;
    }

    public String run(String command) throws IOException {
        Process current_p = runtime.exec(command);
        BufferedReader out = new BufferedReader(new InputStreamReader(current_p.getInputStream()));
        String res = "";
        String line;
        try {
            while ((line = out.readLine()) != null){
                res += "\n"+ line;
            }
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
