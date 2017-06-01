package ir.aut.app;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


class Menu extends JFrame {

    private JMenuItem fNew;
    private JMenuItem fOpen;
    private JMenuItem fClose;
    private JMenuItem fSaveAs;
    private JMenuItem eRotate;
    private JMenuItem eCrop;
    private JMenuItem eFilter;
    private JMenuItem eColor;
    private JMenuItem eAddText;
    private JMenuItem eAddSticker;
    private JLabel showingImage;
    private JSlider slider;
    private JPanel panel;
    private BufferedImage image;
    private ImageIcon img = new ImageIcon();
    private RotateImage rotateImage = new RotateImage();


    Menu() {
        initialMenu();
    }

    private void initialMenu() {

        createMenuBar();

        setTitle("Image Editor");
        setSize(500, 500);
        setLocation(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu FileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");

        MenuEventHandler handler = new MenuEventHandler();

        fNew = new JMenuItem("New");
        fNew.addActionListener(handler);
        fOpen = new JMenuItem("Open");
        fOpen.addActionListener(handler);
        fClose = new JMenuItem("Close");
        fClose.addActionListener(handler);
        fSaveAs = new JMenuItem("Save As");
        fSaveAs.addActionListener(handler);

        eRotate = new JMenuItem("Rotate");
        eRotate.addActionListener(handler);
        eCrop = new JMenuItem("Crop");
        eCrop.addActionListener(handler);
        eFilter = new JMenu("Filter");
        eFilter.addActionListener(handler);
        eColor = new JMenuItem("Color");
        eColor.addActionListener(handler);
        eAddText = new JMenuItem("Add Text");
        eAddText.addActionListener(handler);
        eAddSticker = new JMenuItem("Add Sticker");
        eAddSticker.addActionListener(handler);

        FileMenu.add(fNew);
        FileMenu.add(fOpen);
        FileMenu.add(fClose);
        FileMenu.add(fSaveAs);

        EditMenu.add(eRotate);
        EditMenu.add(eCrop);
        EditMenu.add(eFilter);
        EditMenu.add(eColor);
        EditMenu.add(eAddText);
        EditMenu.add(eAddSticker);

        menuBar.add(FileMenu);
        menuBar.add(EditMenu);

        slider = new JSlider(0, 100);
        panel = new JPanel(new BorderLayout());


        setJMenuBar(menuBar);


    }

    public class MenuEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == fOpen) {
                OpenImage openImage = new OpenImage(image);
                openImage.read();
                image = openImage.getImage();
                img.setImage(image);
                showingImage = new JLabel(img, JLabel.CENTER);
                panel.add(showingImage);
                add(panel);
                setVisible(true);

            }
            if (event.getSource() == eRotate) {
                panel.add(slider, BorderLayout.NORTH);
                add(panel);
                setVisible(true);
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        showingImage.setVisible(false);
                        rotateImage.setImage(image);
                        rotateImage.rotateImage(slider.getValue());
                        panel.add(rotateImage);
                        add(panel);
                        setVisible(true);
                    }
                });

            }

        }
    }
}


