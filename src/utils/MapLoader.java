package utils;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static main.Main.walls;

// Whole class made to load maps from .txt files
public class MapLoader {

    public static int[][] loadMap(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) lines.add(line);
        } catch(IOException e) {
            e.printStackTrace();
        }

        int[][] map = new int[lines.size()][lines.getFirst().split(" ").length];

        for (int y = 0; y < lines.size(); y++) {

            String[] row = lines.get(y).split(" ");
            for (int x = 0; x < row.length; x++) {
                map[y][x] = Integer.parseInt(row[x]);

                if (map[y][x] == 1)
                    walls.add(new Rectangle(
                            x * Constants.TILE_SIZE,
                            y * Constants.TILE_SIZE,
                            Constants.TILE_SIZE,
                            Constants.TILE_SIZE));
            }
        }

        return map;
    }
}
