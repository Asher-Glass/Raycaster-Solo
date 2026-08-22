package entities;

import main.Main;
import utils.Constants;
import utils.Vector2D;

import java.awt.*;

public class Player {

    public static class Ray {
        public Vector2D position;

    }

    public static Vector2D position;
    public static double direction = 180;

    public static void update() {
        move();
    }

    public static void move() {

        double dirRad = Math.toRadians(direction);

        if (Main.keyHandler.UP) {
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), 0);
            tryMove(0, Constants.ENTITY_SPEED * Math.cos(dirRad));
        } else if (Main.keyHandler.DOWN) {
            tryMove(-Constants.ENTITY_SPEED * Math.sin(dirRad), 0);
            tryMove(0, -Constants.ENTITY_SPEED * Math.cos(dirRad));
        }

        if (Main.keyHandler.STRAFE_L) {
            dirRad = Math.toRadians(direction + 90);
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), 0);
            tryMove(0, Constants.ENTITY_SPEED * Math.cos(dirRad));
        } else if (Main.keyHandler.STRAFE_R) {
            dirRad = Math.toRadians(direction - 90);
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), 0);
            tryMove(0, Constants.ENTITY_SPEED * Math.cos(dirRad));
        }

        if (Main.keyHandler.LEFT) direction += Constants.ENTITY_SPEED;

        else if (Main.keyHandler.RIGHT) direction -= Constants.ENTITY_SPEED;
    }

    public static void tryMove(double dx, double dy) {
        position = position.add(dx, dy);

        for (Rectangle wall : Main.walls) {
            double r = Constants.ENTITY_RADIUS;

            if (
                wall.contains(position.x() - r, position.y() - r) ||
                wall.contains(position.x() + r, position.y() - r) ||
                wall.contains(position.x() - r, position.y() + r) ||
                wall.contains(position.x() + r, position.y() + r)
            ) {
                position = position.subtract(dx, dy);
            }
        }
    }
}