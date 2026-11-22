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
    public boolean WAS_SPACE_PRESSED = false;
    public boolean NUMPAD_UP = false;
    public boolean NUMPAD_DOWN = false;
    public boolean NUMPAD_LEFT = false;
    public boolean WAS_NUMLEFT_PRESSED = false;
    public boolean NUMPAD_RIGHT = false;
    public boolean WAS_NUMRIGHT_PRESSED = false;

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

            case KeyEvent.VK_NUMPAD8 -> NUMPAD_UP = true;

            case KeyEvent.VK_NUMPAD2 -> NUMPAD_DOWN = true;

            case KeyEvent.VK_NUMPAD4 -> NUMPAD_LEFT = true;

            case KeyEvent.VK_NUMPAD6 -> NUMPAD_RIGHT = true;
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
                if (WAS_SPACE_PRESSED) WAS_SPACE_PRESSED = false;
            }

            case KeyEvent.VK_NUMPAD8 -> NUMPAD_UP = false;

            case KeyEvent.VK_NUMPAD2 -> NUMPAD_DOWN = false;

            case KeyEvent.VK_NUMPAD4 -> {
                NUMPAD_LEFT = false;
                if (WAS_NUMLEFT_PRESSED) WAS_NUMLEFT_PRESSED = false;
            }

            case KeyEvent.VK_NUMPAD6 -> {
                NUMPAD_RIGHT = false;
                if (WAS_NUMRIGHT_PRESSED) WAS_NUMRIGHT_PRESSED = false;
            }
        }
    }
}
