package ir.aut.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class SaveImage extends JComponent{

    private BufferedImage image;

    SaveImage(BufferedImage image){
        this.image = image;
    }

    void write() throws IOException {

        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                ImageIO.write(image, "jpg", file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    BufferedImage getImage(){
        return image;
    }
}
