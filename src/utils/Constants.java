package utils;

import java.awt.*;

public class Constants {
    // Screen Constants
    public static final String TITLE = "Solo Raycaster"; // What to call the window
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width; // Width of window
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; // Height of window

    // Game Constants
    public static final int TILE_SIZE = 32; // Measurement of one tile

    // Entity Constants
    public static final int ENTITY_RADIUS = 8; // How big the entities are (tile size / 4)
    public static final int ENTITY_SPEED = 6; // How fast entities move (might separate player to entity)

    // RayCasting-Specific Constants
    public static final int RES = 16; // How many rays to cast (higher -> slow but high quality)
    public static final int FOV = 60; // The range of how far spread to shoot the rays (higher -> zoomed out)

    // Minimap Constants
    public static final int MINIMAP_SIZE_DIVIDER = 2; // tile size / this = how many pixels^2 for minimap tile
}
