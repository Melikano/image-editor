package ir.aut.app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorImage extends JComponent {

    private BufferedImage image;


    ColorImage() {
    }

    public BufferedImage changeBlue(int blue) {

        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int p = image.getRGB(i, j);
                int a = (p >> 24) & 0xff;
                int red = (p >> 16) & 0xff;
                int green = (p >> 8) & 0xff;
                p = (a << 24) | (red << 16) | (green << 8) | (blue);
                image.setRGB(i, j, p);
            }
        }

        return image;
    }

    public BufferedImage changeRed(int red) {

        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int p = image.getRGB(i, j);
                int a = (p >> 24) & 0xff;
                int green = (p >> 8) & 0xff;
                int blue = p & 0xff;
                p = (a << 24) | (red << 16) | (green << 8) | (blue);
                image.setRGB(i, j, p);
            }
        }

        return image;
    }

    public BufferedImage changeGreen(int green) {

        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int p = image.getRGB(i, j);
                int a = (p >> 24) & 0xff;
                int red = (p >> 16) & 0xff;
                int blue = p & 0xff;
                p = (a << 24) | (red << 16) | (green << 8) | (blue);
                image.setRGB(i, j, p);
            }
        }

        return image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            Graphics2D g2D = (Graphics2D) g;
            g2D.translate(this.getSize().width / 2, this.getSize().height / 2);
            g2D.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2, null);
        } catch (NullPointerException e) {
            System.out.println("no image");
        }

        repaint();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
