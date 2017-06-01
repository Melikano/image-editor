package ir.aut.app;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RotateImage extends JComponent {

    private BufferedImage image;
    private double angle;

    RotateImage() {
    }

    void rotateImage(int rotateAngle){
        angle = rotateAngle * 3.6;
        repaint();
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        if(image != null){
            Graphics2D g2D = (Graphics2D)graphics;
            g2D.translate(this.getSize().width/2,this.getSize().height/2);
            g2D.rotate(Math.toRadians(angle));
            g2D.drawImage(image, -image.getWidth()/2, -image.getHeight()/2,null);
        }
    }

    void setImage(BufferedImage image) {
        this.image = image;
    }
}
