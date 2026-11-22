package utils;

import java.awt.*;

public class Constants {
    // Screen Constants
    public static final String TITLE = "Solo Raycaster"; // What to call the window
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; // Width of window
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; // Height of window

    // Map/Tile Constants
    public static final int TILE_SIZE = 16; // Measurement of one tile
    public static final int WALL_ID = 1;
    public static final int WALL_HEIGHT = 50_000;

    // Entity Constants
    public static final int ENTITY_RADIUS = 4; // How big the entities are (tile size / 4)

    // Player Constants
    public static final int PLAYER_SPEED = 3;// How fast entities move (might separate player to entity)
    public static final int PLAYER_TURN_SPEED = 2;

    // RayCasting-Specific Constants
    public static final int RES = 4; // Sort of inverse resolution
    public static final int FOV = 120; // The range of how far spread to shoot the rays (higher -> zoomed out)

    public static final int RAY_SPEED = 1; // How fast the ray moves (faster makes it skip corners)
    public static final int RAY_BACK_SPEED = 1; // How fast ray moves to edge of wall for more precise casting

    public static final int RAY_AMOUNT = SCREEN_WIDTH / RES; // Amount of rays to be made
    public static final double RAY_INCREMENT = (double) FOV / RAY_AMOUNT;

    // Minimap Constants
    public static final int MINIMAP_SIZE_DIVIDER = 2; // tile size / this = how many pixels^2 for minimap tile
    public static final int RAY_MAP_SIZE = ENTITY_RADIUS / 2;
}
