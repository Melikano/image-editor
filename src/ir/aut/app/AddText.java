package ir.aut.app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AddText extends JComponent {

    BufferedImage image;
    AddText(BufferedImage image){
        this.image = image;
    }

    BufferedImage mergeImageAndText(String text, Point textPosition) throws IOException {
        Graphics2D g2 = image.createGraphics();
        g2.drawString(text, textPosition.x, textPosition.y);
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
            System.out.println("no image here");
        }

        repaint();
    }

    void setImage(BufferedImage image) {
        this.image = image;
    }

}
