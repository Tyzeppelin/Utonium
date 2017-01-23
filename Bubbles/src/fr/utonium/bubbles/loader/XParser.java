package fr.utonium.bubbles.loader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class XParser {

    private JSONObject file;

    public XParser(String path){

        JSONTokener jsonparser = null;
        try {
            jsonparser = new JSONTokener(new FileReader(path));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file = new JSONObject(jsonparser);
    }

    public String getCompile() {
        return new String(file.getString("compile"));
    }

    public List<HashMap<String, String>> getExpected() {

        JSONArray arr = file.getJSONArray("expected");

        List<HashMap<String, String>> exp = new ArrayList();

        int len = arr.length();

        for(int i = 0; i < len; i++) {
            HashMap<String, String> item = new HashMap<>();
            // get class, method and expected return value
            item.put("call", arr.getJSONObject(i).getString("call"));
            item.put("return", arr.getJSONObject(i).getString("return"));
            exp.add(item);
        }
        return exp;
    }

}
