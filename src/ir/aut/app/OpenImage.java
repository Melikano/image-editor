package ir.aut.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class OpenImage extends JComponent{

    private BufferedImage image;

    OpenImage(BufferedImage image){
        this.image = image;
    }

    void read() {

        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                image = ImageIO.read(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    BufferedImage getImage(){
        return image;
    }
}
