package main;

import entities.*;
import utils.*;

import java.awt.*;
import java.util.ArrayList;

// Launcher, basically
public class Main {
    public static int[][] worldMap; // Will be a matrix array of the map
    public static ArrayList<Rectangle> walls = new ArrayList<>(); // Will be a list of the coords of walls

    // Whether to render the minimap
    public static boolean renderMap2D = true;

    // Variable to be used for handling key input
    public static KeyHandler keyHandler = new KeyHandler();

    // main method
    static void main() {

        // Start up the desktop window
        new Window();
        genMap(); // Load the map

        // Initiate player into middle of map
        Player.position = new Vector2D(
                ((double) worldMap.length / 2) * Constants.TILE_SIZE,
                ((double) worldMap.length / 2) * Constants.TILE_SIZE);

        // Instantiate player's rays
        for (int i = 0; i < Player.rays.length; i++) Player.rays[i] = new Player.Ray();
    }

    // Method to generate map
    public static void genMap() {
        worldMap = MapLoader.loadMap("src/utils/map.txt");
    }

    // Method to update all entities
    public static void update() {
        Player.update();
    }

    // Paint screen (will probably be replaced with BufferedImage later)
    public static void paint(Graphics2D g) {

        // Render pseudo-3D world!
        g.setColor(Color.darkGray);
        for (int i = 0; i < Player.rays.length; i++) {
            g.fillRect(
                    Constants.SCREEN_WIDTH - (i * Constants.RES),
                    (int) (Constants.SCREEN_HEIGHT - Player.rays[i].height) / 2,
                    Constants.RES,
                    (int) Player.rays[i].height
            );
        }

        // Render Minimap if enabled
        if (Main.renderMap2D && Main.worldMap != null) {

            // Walls
            g.setColor(Color.lightGray);

            for (int row = 0; row < Main.worldMap.length; row++) {
                for (int col = 0; col < Main.worldMap[0].length; col++) {

                    if (Main.worldMap[row][col] == 1) {
                        g.fillRect(
                                (col * Constants.TILE_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                                (row * Constants.TILE_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                                Constants.TILE_SIZE / Constants.MINIMAP_SIZE_DIVIDER,
                                Constants.TILE_SIZE / Constants.MINIMAP_SIZE_DIVIDER);
                    }
                }
            }

            // Player
            if (Player.position != null) {
                g.setColor(Color.yellow);
                g.fillOval(
                        (int) (Player.position.x() - (double) Constants.RAY_MAP_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                        (int) (Player.position.y() - (double) Constants.RAY_MAP_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                        Constants.RAY_MAP_SIZE * 2,
                        Constants.RAY_MAP_SIZE * 2);
            }

            // Rays (what the player sees)
            g.setColor(Color.cyan);
            for (Player.Ray ray : Player.rays) {
                if (ray != null && ray.launched) {
                    g.fillOval(
                            (int) (ray.position.x() - Constants.RAY_MAP_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                            (int) (ray.position.y() - Constants.RAY_MAP_SIZE) / Constants.MINIMAP_SIZE_DIVIDER,
                            (Constants.RAY_MAP_SIZE * 2) / Constants.MINIMAP_SIZE_DIVIDER,
                            (Constants.RAY_MAP_SIZE * 2) / Constants.MINIMAP_SIZE_DIVIDER);
                }
            }
        }
    }
}
