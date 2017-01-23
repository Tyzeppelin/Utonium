package fr.utonium.bubbles.main;

import fr.utonium.bubbles.loader.Check;
import fr.utonium.bubbles.loader.XParser;

import java.util.HashMap;
import java.util.List;

public class Bubbles {

    public static void main(String[] args) {

        if (args.length == 1){
            XParser p = new XParser(args[0]);

            String compilationCall = p.getCompile();
            List<HashMap<String, String>> tests = p.getExpected();

            Check ch = Check.getInstance();

            ch.generate(compilationCall);
            ch.checkAll(tests);
        } else {
            System.out.println("Bubbles project.");
            System.out.println("Usage: java -jar Bubbles.jar file.json");
        }
    }
}
