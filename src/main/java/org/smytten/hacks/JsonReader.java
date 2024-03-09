package org.smytten.hacks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JsonReader {
    public static void main(String[] args) throws FileNotFoundException {
        FileReaders s = new FileReaders();
        String str = s.getString();

        try {
            JSONArray jsonArray = new JSONArray(str);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String name = jsonObj.getString("name");
                System.out.println(name);
            }
            System.out.println("----------------------------------------------------------------------");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String sku = jsonObj.getString("sku");
                System.out.println(sku);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class FileReaders{
    public String getString() {
        String filePath = "/Users/Vignesh/Desktop/search.txt";

        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}
