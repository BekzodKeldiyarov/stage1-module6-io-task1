package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = null;
        try (InputStream inputStream =
                     new FileInputStream(file)) {
            byte[] bytes = inputStream.readAllBytes();
            String s = new String(bytes);
            Map<String, String> data = new HashMap<>();
            String[] line = s.split("\n");
            for (String i : line) {
                String[] keyValue = i.split(": ");
                data.put(keyValue[0].toLowerCase(), keyValue[1]);
            }

            profile = new Profile(data.get("name"), Integer.parseInt(data.get("age")), data.get("email"), Long.parseLong(data.get("phone")));
            return profile;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
