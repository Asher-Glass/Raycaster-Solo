package main;

import entities.*;
import utils.*;
import entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int[][] worldMap;
    public static ArrayList<Rectangle> walls = new ArrayList<>();

    public static boolean renderMap2D = true;

    public static KeyHandler keyHandler = new KeyHandler();


    public static void main(String[] args) {
        new Window();
        genMap();

        Player.position = new Vector2D(
                ((double) worldMap.length / 2) * Constants.TILE_SIZE,
                ((double) worldMap.length / 2) * Constants.TILE_SIZE);
    }

    public static void genMap() {
        List<String> map = MapLoader.loadMap("src/utils/map.txt");
        worldMap = new int[map.size()][map.getFirst().split(" ").length];

        for (int y = 0; y < map.size(); y++) {

            String[] row = map.get(y).split(" ");
            for (int x = 0; x < row.length; x++) {
                worldMap[y][x] = Integer.parseInt(row[x]);

                if (worldMap[y][x] == 1)
                    walls.add(new Rectangle(
                            x * Constants.TILE_SIZE,
                            y * Constants.TILE_SIZE,
                            Constants.TILE_SIZE,
                            Constants.TILE_SIZE));
            }
        }
    }

    public static void update() {
        Player.update();
    }

    public static void paint(Graphics2D g) {

        // Render Pseudo-3D Rays
        for (int i = 0; i < Player.Ray.xLines.size(); i++) {
            if (Player.Ray.heights.isEmpty()) break;

            int xLine = Player.Ray.xLines.get(i);
            double height = Player.Ray.heights.get(i);

            // TODO: add side colours etc
            g.setColor(Color.getHSBColor(.66F, 1F, 1.9607F));
            g.drawLine(xLine, (int) (((double) Constants.SCREEN_HEIGHT / 2) - (height / 2)),
                    xLine + Constants.RES, (int) (((double) Constants.SCREEN_HEIGHT / 2) + (height / 2)));
        }


        // Render Minimap
        // Walls
        if (Main.renderMap2D && Main.worldMap != null) {
            g.setColor(Color.darkGray);

            for (int row = 0; row < Main.worldMap.length; row++) {
                for (int col = 0; col < Main.worldMap[0].length; col++) {

                    if (Main.worldMap[row][col] == 1)
                        g.fillRect(
                                (col * Constants.TILE_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                                (row * Constants.TILE_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                                Constants.TILE_SIZE / Constants.MINIMAP_SIZE_DIVIDER,
                                Constants.TILE_SIZE / Constants.MINIMAP_SIZE_DIVIDER);
                }
            }
        }

        // Player
        g.setColor(Color.yellow);
        g.fillOval(
                (int) (Player.position.x() - (double) Constants.ENTITY_RADIUS) / Constants.MINIMAP_SIZE_DIVIDER,
                (int) (Player.position.y() - (double) Constants.ENTITY_RADIUS) / Constants.MINIMAP_SIZE_DIVIDER,
                (Constants.ENTITY_RADIUS * 2) / Constants.MINIMAP_SIZE_DIVIDER,
                (Constants.ENTITY_RADIUS * 2) / Constants.MINIMAP_SIZE_DIVIDER);
    }
}
