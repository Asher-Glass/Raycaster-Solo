package main;

import javax.swing.*;
import java.awt.*;

import static utils.Constants.*;

public class Window extends JPanel {

    public Window() {
        this.setBackground(Color.black);

        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.add(this);

        frame.addKeyListener(Main.keyHandler);

        frame.setVisible(true);

        new Thread(() -> {

            final double TARGET_UPS = 60.0;
            final double NS_PER_UPDATE = 1_000_000_000.0 / TARGET_UPS;

            long lastTime = System.nanoTime();
            long fpsTimer = System.currentTimeMillis();

            double delta = 0;

            while (true) {

                long now = System.nanoTime();

                delta += (now - lastTime) / NS_PER_UPDATE;
                lastTime = now;

                // Fixed-rate game updates
                while (delta >= 1) {
                    Main.update();
                    delta--;
                }

                this.repaint();

                if (System.currentTimeMillis() - fpsTimer >= 1000) {
                    fpsTimer += 1000;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D g = (Graphics2D) G.create();

        Main.paint(g);
    }
}
