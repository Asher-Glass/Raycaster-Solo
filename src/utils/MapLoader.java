package utils;

import java.io.*;
import java.util.*;

public class MapLoader {

    public static List<String> loadMap(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) lines.add(line);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
