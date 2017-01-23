package fr.utonium.bubbles.loader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.*;

public class Parser {

    private JSONObject file;

    public Parser(String path){

        JSONTokener jsonparser = null;
        try {
            jsonparser = new JSONTokener(new FileReader(path));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file = new JSONObject(jsonparser);
    }

    public File getRootDirectory() {
        return new File(file.getString("root_dir"));
    }

    public File[] getFiles() {
        List<File> l = new ArrayList<>();
        for(Iterator<Object> it = file.getJSONArray("files").iterator(); it.hasNext();){
            l.add(new File((String)it.next()));
        }
        return l.toArray(new File[0]);
    }

    public List<HashMap<String, Object>> getExpected() {

        JSONArray arr = file.getJSONArray("expected");

        List<HashMap<String, Object>> exp = new ArrayList();

        int len = arr.length();

        for(int i = 0; i < len; i++) {
            HashMap<String, Object> item = new HashMap<>();
            // get class, method and expected return value
            item.put("class", arr.getJSONObject(i).getString("class"));
            item.put("method", arr.getJSONObject(i).getString("method"));
            item.put("return", arr.getJSONObject(i).getString("return"));

            // if there is parameters
            if (arr.getJSONObject(i).has("params")) {
                List params = new LinkedList();
                JSONArray prms = arr.getJSONObject(i).getJSONArray("params");
                for(Iterator<Object> p = prms.iterator(); p.hasNext();) {
                    params.add(p.next());
                }
                item.put("params", params.toArray());
            }
            exp.add(item);
        }
        return exp;
    }

}
