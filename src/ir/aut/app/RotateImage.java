package ir.aut.app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RotateImage extends JComponent {

    private BufferedImage image;
    private double angle;


    RotateImage() {
    }

    void rotateImage(double rotateAngle) {
        angle = (rotateAngle * 3.14) / 180;
    }

    @Override
    public void paint(Graphics graph) {
        repaint();
        super.paint(graph);
        try {
            Graphics2D g2D = (Graphics2D) graph;
            g2D.translate(this.getSize().width / 2, this.getSize().height / 2);
            g2D.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2, null);
        } catch (NullPointerException e) {
            System.out.println("no image entered");
        }


    }

    BufferedImage rotate() {

        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }


    void setImage(BufferedImage image) {
        this.image = image;
    }
}
