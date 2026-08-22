package utils;

import java.awt.*;

public class Constants {
    // Screen Constants
    public static final String TITLE = "Solo Raycaster";
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    // Game Constants
    public static final int TILE_SIZE = 32;

    // Player Constants
    public static final int ENTITY_RADIUS = 8;
    public static final int ENTITY_SPEED = 6;

    // Ray Constants
    public static final double RAY_SPEED = 4d;
    public static final double RAY_BACK_SPEED = .5d;
    public static final double RAY_RADIUS = 1;

    // Player x Ray Constants
    public static int WALL_HEIGHT = 4_000;
    public static int FOV = 60;
    public static int RES = 16;

    // Minimap Constants
    public static final int MINIMAP_SIZE_DIVIDER = 2;
}
