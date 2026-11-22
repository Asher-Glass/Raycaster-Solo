package entities;

import main.*;
import utils.*;

import java.awt.*;

public class Player {

    public static class Ray {
        private static double offset;

        public final double offsetFinal;
        public Vector2D position;
        public double direction;
        public boolean launched;
        public Vector2D hitPos;
        public double height;

        static {
            offset = -((double) Constants.FOV / 2);
        }

        public Ray() {
            offsetFinal = offset;

            position = Player.position;
            direction = Player.direction + offsetFinal;
            launched = false;
            hitPos = null;

            offset += Constants.RAY_INCREMENT;
        }

        public void update() {

            position = Player.position;
            direction = Player.direction + offsetFinal;

            if (hitPos == null) {
                while (hitPos == null) move();
            } else hitPos = null;
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

            if (tile == Constants.WALL_ID) {
                do {
                    position = position.subtract(
                            Constants.RAY_BACK_SPEED * Math.sin(dirRad),
                            Constants.RAY_BACK_SPEED * Math.cos(dirRad)
                    );
                } while (MapLoader.binMap
                        [(int) (position.y() / Constants.TILE_SIZE)]
                        [(int) (position.x() / Constants.TILE_SIZE)] == Constants.WALL_ID);
                hitPos = position;
                double distance = hitPos.distanceTo(Player.position) * Math.cos(Math.toRadians(offsetFinal));
                height = Constants.WALL_HEIGHT / distance;
            }
        }
    }

    public static Ray[] rays = new Ray[Constants.RAY_AMOUNT];

    public static Vector2D position;
    public static double direction = 180;

    public static void update() {
        move();
        for (Ray ray : rays) if (ray != null) ray.update();
    }

    public static void move() {

        double dirRad = Math.toRadians(direction);

        if (Main.keyHandler.UP)
            tryMove(Constants.PLAYER_SPEED * Math.sin(dirRad), Constants.PLAYER_SPEED * Math.cos(dirRad));

        if (Main.keyHandler.DOWN)
            tryMove(-Constants.PLAYER_SPEED * Math.sin(dirRad), -Constants.PLAYER_SPEED * Math.cos(dirRad));

        if (Main.keyHandler.STRAFE_L) {
            dirRad = Math.toRadians(direction + 90);
            tryMove(Constants.PLAYER_SPEED * Math.sin(dirRad), Constants.PLAYER_SPEED * Math.cos(dirRad));
        }
        if (Main.keyHandler.STRAFE_R) {
            dirRad = Math.toRadians(direction - 90);
            tryMove(Constants.PLAYER_SPEED * Math.sin(dirRad), Constants.PLAYER_SPEED * Math.cos(dirRad));
        }

        if (Main.keyHandler.LEFT) {
            direction += Constants.PLAYER_TURN_SPEED;
            if (direction >= 360) direction = 0;
        }
        if (Main.keyHandler.RIGHT) {
            direction -= Constants.PLAYER_TURN_SPEED;
            if (direction <= -360) direction = 0;
        }
    }

    public static void tryMove(double dx, double dy) {

        position = position.add(dx, 0);

        int row = (int) (position.y() / Constants.TILE_SIZE);
        int col = (int) (position.x() / Constants.TILE_SIZE);
        int tile = MapLoader.binMap[row][col];

        while (tile == Constants.WALL_ID) {
            position = position.subtract(dx, 0);

            row = (int) (position.y() / Constants.TILE_SIZE);
            col = (int) (position.x() / Constants.TILE_SIZE);
            tile = MapLoader.binMap[row][col];
        }

        position = position.add(0, dy);
        row = (int) (position.y() / Constants.TILE_SIZE);
        col = (int) (position.x() / Constants.TILE_SIZE);
        tile = MapLoader.binMap[row][col];

        while (tile == Constants.WALL_ID) {
            position = position.subtract(0, dy);

            row = (int) (position.y() / Constants.TILE_SIZE);
            col = (int) (position.x() / Constants.TILE_SIZE);
            tile = MapLoader.binMap[row][col];
        }
    }
}