package utils;

import java.awt.*;

public class Constants {
    // Screen Constants
    public static final String TITLE = "Solo Raycaster"; // What to call the window
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; // Width of window
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; // Height of window

    // Map/Tile Constants
    public static final int TILE_SIZE = 32; // Measurement of one tile

    public static final int WALL_ID = 1; // ID for walls
    public static final int WALL_HEIGHT = 70_000; // Height of a wall (allows for taller or shorter tiles later)
    public static final float[] WALL_COLOUR_HSB = Color.RGBtoHSB(64, 64, 64, null);
    public static final float WALL_MAX_BRIGHTNESS = .25f;

    // Minimap Constants
    // Desired amount of pixels the minimap should take up (dynamic)
    public static final int MINIMAP_DESIRED_PIXELS_SIZE = 256;

    // Colour Constants (0xAARRGGBB)
    public static final int COLOUR_BG = 0xFF000000;             // Black
    public static final int COLOUR_MINI_WALL = 0xFFC0C0C0;      // Light Grey
    public static final int COLOUR_MINI_PLAYER = 0xFFFFFF00;    // Yellow
    public static final int COLOUR_FPS = 0xFF00FF00;            // Green

    // Entity Constants
    public static final int ENTITY_RADIUS = 4; // How big the entities are (tile size / 4)

    // Player Constants
    public static final int PLAYER_SPEED = 3;// How fast entities move (might separate player to entity)
    public static final int PLAYER_TURN_SPEED = 2; // How fast the player turns

    public static final int TORCH_FACTOR = 1_500; // How fast the tiles fade around the player

    // RayCasting-Specific Constants
    public static final int RES = 4; // Sort of inverse resolution
    public static final int FOV = 90; // The range of how far spread to shoot the rays (higher -> zoomed out)

    public static final int RAY_SPEED = 3; // How fast the ray moves (faster makes it skip corners)
    public static final int RAY_BACK_SPEED = 1; // How fast ray moves to edge of wall for more precise casting

    public static final int RAY_AMOUNT = (SCREEN_WIDTH / RES) + 1; // Amount of rays to be made
    public static final double RAY_INCREMENT = (double) FOV / RAY_AMOUNT;
}
