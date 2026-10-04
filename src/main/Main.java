package main;

import entities.*;
import utils.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Launcher, basically
public class Main {
    public static int[][] worldMap; // Will be a matrix array of the map
    public static ArrayList<Rectangle> walls = new ArrayList<>(); // Will be a list of the coords of walls

    // Whether to render the minimap
    public static boolean renderMap2D = true;

    // Variable to be used for handling key input
    public static KeyHandler keyHandler = new KeyHandler();

    // main method
    public static void main(String[] args) {

        // Start up the desktop window
        Window window = new Window();
        genMap(); // Load the map

        Player.position = new Vector2D(
                ((double) worldMap.length / 2) * Constants.TILE_SIZE,
                ((double) worldMap.length / 2) * Constants.TILE_SIZE);
    }

    public static void genMap() {
        worldMap = MapLoader.loadMap("src/utils/world.txt");
    }

    public static void update() {
        Player.update();
    }

    public static void paint(Graphics2D g) {

        // Render Minimap if enabled
        if (Main.renderMap2D && Main.worldMap != null) {

            // Walls
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

            // Player
            g.setColor(Color.yellow);
            g.fillOval(
                    (int) (Player.position.x() - (double) Constants.ENTITY_RADIUS) / Constants.MINIMAP_SIZE_DIVIDER,
                    (int) (Player.position.y() - (double) Constants.ENTITY_RADIUS) / Constants.MINIMAP_SIZE_DIVIDER,
                    (Constants.ENTITY_RADIUS * 2) / Constants.MINIMAP_SIZE_DIVIDER,
                    (Constants.ENTITY_RADIUS * 2) / Constants.MINIMAP_SIZE_DIVIDER);
        }
    }
}
