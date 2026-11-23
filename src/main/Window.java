package main;

import entities.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

// Desktop window handler
public class Window extends JPanel {

    public int displayFPS; // Count how many frames passed
    public BufferedImage image;

    public Window() {
        image = new BufferedImage(
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_ARGB
        );

        JFrame frame = new JFrame(Constants.TITLE); // Make screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop program when window closes
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); // Fullscreen
        frame.setUndecorated(true); // Remove the menu bar (proper fullscreen)
        frame.setResizable(false); // Can't resize window (if enabled will cause issues when resized)
        frame.setLocationRelativeTo(null); // Centre window
        frame.add(this); // The desktop window will be affected by changes to the class

        frame.addKeyListener(Main.keyHandler); // Can handle key presses however I please

        frame.setVisible(true); // Make sure the desktop window is visible

        // Get fps
        new Thread(() -> {

            final double TARGET_UPS = 60.0;
            final double NS_PER_UPDATE = 1_000_000_000.0 / TARGET_UPS;
            // Ensures running at minimum 60fps and calculates 60fps = ~16.67ms

            long lastTime = System.nanoTime(); // The time elapsed since program start (used for duration measurement)
            long fpsTimer = System.nanoTime();
            int frameCount = 0; // How many frames passed


            double delta = 0;
            // Is used to count how many fractions of an update in game logic are lost

            while (true) {

                long now = System.nanoTime();

                delta += (now - lastTime) / NS_PER_UPDATE;
                // Converts time into "updates owed" for fixed timestep

                lastTime = now;

                // Catches up game logic if owes an update
                for (; delta >= 1; delta--) Main.update();

                // Requests a repaint; GUI thread decides when to perform it
                repaint();
                frameCount++;

                if (System.nanoTime() - fpsTimer >= 1_000_000_000) { // Runs every second
                    fpsTimer += 1_000_000_000; // Changes reference point to reset the second-timer

                    displayFPS = frameCount; // Set the displayed fps to the fps

                    frameCount = 0; // Reset count so it doesn't show 60fps -> 120fps -> 180fps etc
                }

                // Give game a delay so as not to overload the CPU
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start(); // Start the timer
    }

    // Paint the whole screen and call Main.paint() which holds other paint methods
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D g = (Graphics2D) G.create();

        Main.paint();
        g.drawImage(image, 0, 0, null);
    }
}
