package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        final int WIDTH = 1920;
        final int HEIGHT = 1080;

        int seed = new Random().nextInt(100000);
        Random rand = new Random(seed);

//
//
//
//      int gerne = rand.nextInt(3);
        int gerne = 1;

        // Background or say empty canva for image
        BufferedImage image =
                new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // pen for drawing on empty canva
        Graphics2D g = image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0 , WIDTH, HEIGHT);


        if(gerne == 0){
            for(int i = 0; i < 250; i++){
                int x = rand.nextInt(WIDTH);
                int y = rand.nextInt(HEIGHT);
                int size = rand.nextInt(200);

                Color c = new Color(
                        rand.nextInt(256),
                        rand.nextInt(256),
                        rand.nextInt(256),
                        150
                );

                g.setColor(c);
                g.fill3DRect(x, y, size, size, true);
            }

            g.dispose();

        }

        if(gerne == 1) {
            for (int i = 0; i < 300; i++) {
                int x = rand.nextInt(WIDTH);
                int y = rand.nextInt(HEIGHT);
                int size = rand.nextInt(200);

                Color c = new Color(
                        rand.nextInt(256),
                        rand.nextInt(256),
                        rand.nextInt(256),
                        150
                );

                g.setColor(c);
                g.fillOval(x, y, size, size);
            }

            g.dispose();
        }


        if(gerne == 2){
            int gap = 60;
            double frequency = 0.05;

            Color c = new Color(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    150
            );

            for (int x = 0; x < WIDTH; x += gap) {
                for (int y = 0; y < HEIGHT; y += gap) {

                    double wave = Math.sin(x * frequency);
                    int offsetY = (int) (wave * 30);

                    g.setColor(c);
                    g.fillRect(x, y + offsetY, 40, 40);
                }
            }

        }


        if(gerne == 3){
            int size = 40;
            double angleStep = 0.1;

            Color c = new Color(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    150
            );

            for (int x = 0; x < WIDTH; x += 80) {
                for (int y = 0; y < HEIGHT; y += 80) {

                    double angle = Math.sin((x + y) * angleStep);

                    g.translate(x + size / 2, y + size / 2);
                    g.rotate(angle);

                    g.setColor(c);
                    g.fillRect(-size / 2, -size / 2, size, size);

                    g.rotate(-angle);
                    g.translate(-(x + size / 2), -(y + size / 2));
                }
            }
        }

        if(gerne == 4) {
            int centerX = WIDTH / 2;
            int centerY = HEIGHT / 2;

            Color c = new Color(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    150
            );

            for (int x = 0; x < WIDTH; x += 60) {
                for (int y = 0; y < HEIGHT; y += 60) {

                    double dx = x - centerX;
                    double dy = y - centerY;

                    double distance = Math.sqrt(dx * dx + dy * dy);
                    double wave = Math.sin(distance * 0.05);

                    int offset = (int) (wave * 25);

                    g.setColor(c);
                    g.fillRect(x + offset, y + offset, 35, 35);
                }
            }
        }

        if(gerne == 5) {
            double angle = Math.PI / 4;
            Color c = new Color(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    150
            );

            for (int x = 0; x < WIDTH; x += 70) {
                for (int y = 0; y < HEIGHT; y += 70) {

                    g.translate(x, y);
                    g.rotate(angle);

                    g.setColor(c);
                    g.fillRect(0, 0, 40, 40);

                    g.rotate(-angle);
                    g.translate(-x, -y);
                }
            }
        }

        if(gerne == 6){
            Color c = new Color(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    150
            );

            for (int y = 0; y < HEIGHT; y += 30) {
                for (int x = 0; x < WIDTH; x += 5) {

                    double wave = Math.sin(x * 0.02 + y * 0.05);
                    int offset = (int) (wave * 25);

                    g.setColor(c);
                    g.fillOval(x, y + offset, 4, 4);
                }
            }

        }


        // saving in file
        File dir = new File("output");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Use seed do that image can't be overriden
        String fileName = "wallpaper_seed_" + seed + ".png";
        File output = new File(dir, fileName);

        try {
            ImageIO.write(image, "png", output);
            System.out.println("Wallpaper saved successfully!");
            System.out.println("Saved at: " + output.getAbsolutePath());
            System.out.println("Seed used: " + seed);
        } catch (IOException e) {
            System.err.println("Failed to save wallpaper");
            e.printStackTrace();
        }
    }
}
