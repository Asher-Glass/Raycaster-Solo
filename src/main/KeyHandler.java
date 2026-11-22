package main;

import java.awt.event.*;

public class KeyHandler extends KeyAdapter {

    public boolean UP = false;
    public boolean DOWN = false;
    public boolean STRAFE_L = false;
    public boolean STRAFE_R = false;
    public boolean LEFT = false;
    public boolean RIGHT = false;
    public boolean SPACE = false;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> System.exit(0);

            case KeyEvent.VK_UP, KeyEvent.VK_W -> UP = true;

            case KeyEvent.VK_DOWN, KeyEvent.VK_S ->  DOWN = true;

            case KeyEvent.VK_A -> STRAFE_L = true;

            case KeyEvent.VK_D -> STRAFE_R = true;

            case KeyEvent.VK_LEFT -> LEFT = true;

            case KeyEvent.VK_RIGHT -> RIGHT = true;

            case KeyEvent.VK_SPACE -> SPACE = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> System.exit(0);

            case KeyEvent.VK_UP, KeyEvent.VK_W -> UP = false;

            case KeyEvent.VK_DOWN, KeyEvent.VK_S ->  DOWN = false;

            case KeyEvent.VK_A -> STRAFE_L = false;

            case KeyEvent.VK_D -> STRAFE_R = false;

            case KeyEvent.VK_LEFT -> LEFT = false;

            case KeyEvent.VK_RIGHT -> RIGHT = false;

            case KeyEvent.VK_SPACE -> {
                SPACE = false;
                if (Main.wasSpacePressed) Main.wasSpacePressed = false;
            }
        }
    }
}
