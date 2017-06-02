package ir.aut.app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CropImage extends JComponent {

    private BufferedImage image;


    CropImage(){
    }

    BufferedImage crop(int startW, int startH, int endW, int endH){
        try {
            image = image.getSubimage(startW, startH, endW - startW, endH - startH);
        }catch (NullPointerException e){
            System.err.println("unable to crop");
        }
        return image;
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        try
        {
            Graphics2D g2D = (Graphics2D) graphics;
            g2D.translate(this.getSize().width/2,this.getSize().height/2);
            g2D.drawImage(image, -image.getWidth()/2, -image.getHeight()/2, null);
        }catch (NullPointerException e){
            System.out.println("no image is");
        }

    }
    void setImage(BufferedImage image) {
        this.image = image;
    }
}
