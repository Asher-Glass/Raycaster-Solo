package entities;

import main.Main;
import utils.Constants;
import utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    // Dictates what the player sees when 1st person
    public static class Ray {

        public static double midDirection = Player.direction + 90;
        public static double direction = midDirection;
        public static boolean hasLaunched = false;
        public static boolean hasHit = false;
        public static Vector2D midPosition;
        public static Vector2D position;
        public static double distance;
        public static int scanLines;
        public static double dir;
        public static ArrayList<Integer> xLines =  new ArrayList<>();
        public static ArrayList<Double> heights = new ArrayList<>();

        public static void rayCast() {
            direction = Player.direction;

            dir = direction;
            xLines.clear();
            heights.clear();
            xLines.add((Constants.RES / 2) - Constants.SCREEN_WIDTH);
            direction -= (double) Constants.FOV / 2;
            scanLines = (Constants.SCREEN_WIDTH * 2) / Constants.RES;

            for (int i = 0; i < scanLines; i++) {
                singleRay();
                direction += (double) Constants.FOV / scanLines;
                if (i > 0)
                    xLines.add(i, xLines.get(i - 1) + Constants.RES);

                else xLines.set(0, xLines.getFirst() + Constants.RES);
            }

            if (direction == midDirection) midPosition = position;
        }

        public static void singleRay() {
            hasHit = false;
            hasLaunched = true;
            position = Player.position;

            Vector2D unitDirection = Vector2D.directionFromDegrees(direction);

            outer:
            for (Rectangle wall : Main.walls) {
                double r = Constants.RAY_RADIUS / 2;
                while (!(
                        wall.contains(position.x() - r, position.y() - r) ||
                        wall.contains(position.x() + r, position.y() - r) ||
                        wall.contains(position.x() - r, position.y() + r) ||
                        wall.contains(position.x() + r, position.y() + r)
                )) {
                    position = position.add(unitDirection.multiply(Constants.RAY_SPEED));

                    if (
                            wall.contains(position.x() - r, position.y() - r) ||
                            wall.contains(position.x() + r, position.y() - r) ||
                            wall.contains(position.x() - r, position.y() + r) ||
                            wall.contains(position.x() + r, position.y() + r)
                    ) {
                        hasHit = true;
                        break outer;
                    }
                }
                while (
                        wall.contains(position.x() - r, position.y() - r) ||
                        wall.contains(position.x() + r, position.y() - r) ||
                        wall.contains(position.x() - r, position.y() + r) ||
                        wall.contains(position.x() + r, position.y() + r)
                ) {
                    position = position.subtract(unitDirection.multiply(Constants.RAY_BACK_SPEED));
                }
            }

            distance = position.distanceTo(Player.position) * Math.cos(direction - dir);
            heights.add(Constants.WALL_HEIGHT / distance);
        }

        public static void update() {

            if (!hasLaunched) {
                position = Player.position;
                midDirection = Player.direction + 90;
                direction = midDirection;
                hasLaunched = true;
                hasHit = false;
            }

            if (hasHit) {
                position = Player.position;
                midDirection = Player.direction + 90;
            } else {
                rayCast();
            }
        }
    }

    public static Vector2D position;
    public static double direction = 180;

    public static void update() {
        move();
        Ray.update();
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
                position = position.add(-dx, -dy);
            }
        }
    }
}