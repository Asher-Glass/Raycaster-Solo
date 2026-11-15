package entities;

import main.*;
import utils.*;

import java.awt.*;

public class Player {

    public static class Ray {
        public Vector2D position;
        public double direction;
        public boolean launched;
        public Vector2D hitPos;

        public Ray() {
            position = Player.position;
            direction = Player.direction;
            launched = false;
            hitPos = null;
        }

        public void update() {
            if (launched && hitPos == null) {
                while (hitPos == null) move();
            } else if (launched) {
                launched = false;
                hitPos = null;
            }
            else {
                position = Player.position;
                direction = Player.direction;
                if (Main.keyHandler.SPACE) launched = true;
            }
        }

        public void move() {

            double dirRad = Math.toRadians(direction);
            position = position.add(
                    Constants.RAY_SPEED * Math.sin(dirRad),
                    Constants.RAY_SPEED * Math.cos(dirRad)
            );

            int row = (int) (position.y() / Constants.TILE_SIZE);
            int col = (int) (position.x() / Constants.TILE_SIZE);
            int tile = MapLoader.binMap[row][col];

            if (tile == 1) {
                hitPos = position;
            }
        }
    }

    public static Ray[] rays = new Ray[1]; // TODO: set to new Ray[Constants.RES] when done with testing etc

    public static Vector2D position;
    public static double direction = 180;

    public static void update() {
        move();
        for (Ray ray : rays) if (ray != null) ray.update();
    }

    public static void move() {

        double dirRad = Math.toRadians(direction);

        if (Main.keyHandler.UP)
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), Constants.ENTITY_SPEED * Math.cos(dirRad));

        if (Main.keyHandler.DOWN)
            tryMove(-Constants.ENTITY_SPEED * Math.sin(dirRad), -Constants.ENTITY_SPEED * Math.cos(dirRad));

        if (Main.keyHandler.STRAFE_L) {
            dirRad = Math.toRadians(direction + 90);
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), Constants.ENTITY_SPEED * Math.cos(dirRad));
        }
        if (Main.keyHandler.STRAFE_R) {
            dirRad = Math.toRadians(direction - 90);
            tryMove(Constants.ENTITY_SPEED * Math.sin(dirRad), Constants.ENTITY_SPEED * Math.cos(dirRad));
        }

        if (Main.keyHandler.LEFT) {
            direction += Constants.ENTITY_SPEED;
            if (direction >= 360) direction = 0;
        }
        if (Main.keyHandler.RIGHT) {
            direction -= Constants.ENTITY_SPEED;
            if (direction <= -360) direction = 0;
        }
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