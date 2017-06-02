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
    private BufferedImage image;
    private ImageIcon img = new ImageIcon();
    private RotateImage rotateImage = new RotateImage();
    private CropImage cropImage = new CropImage();
    private JPanel rotationPanel = new JPanel(new BorderLayout());
    private JPanel cropPanel = new JPanel(new BorderLayout());

    Menu() {
        initialMenu();
    }

    private void initialMenu() {

        createMenuBar();

        setTitle("Image Editor");
        setSize(1500, 1000);
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

        setJMenuBar(menuBar);


    }

    public class MenuEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            if(event.getSource() == fNew){
                image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = image.createGraphics();
                graphics.setPaint ( new Color (255, 249, 241) );
                graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
                img.setImage(image);
                showingImage = new JLabel(img, JLabel.CENTER);
                add(showingImage);
                setVisible(true);
            }

            if (event.getSource() == fOpen) {
                OpenImage openImage = new OpenImage(image);
                openImage.read();
                rotationPanel.setVisible(false);
                cropPanel.setVisible(false);
                image = openImage.getImage();
                img.setImage(image);
                showingImage = new JLabel(img, JLabel.CENTER);
                add(showingImage);
                setVisible(true);
                System.out.println(image.getWidth()+" "+image.getHeight());
            }
            if (event.getSource() == eRotate) {
                showingImage.setVisible(true);
                cropPanel.setVisible(false);
                JSlider slider = new JSlider(0,100);
                rotationPanel.add(showingImage);
                rotationPanel.add(slider, BorderLayout.NORTH);
                add(rotationPanel);
                rotationPanel.setVisible(true);
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        showingImage.setVisible(false);
                        rotateImage.setImage(image);
                        rotateImage.rotateImage(slider.getValue());
                        rotationPanel.add(rotateImage);
                        add(rotationPanel);
                        setVisible(true);
                    }
                });
                img.setImage(image);
                showingImage.setIcon(img);
            }
            if (event.getSource() == eCrop) {
                showingImage.setVisible(true);
                rotationPanel.setVisible(false);

                JPanel Text = new JPanel(new GridLayout(1,9));
                JTextArea startW = new JTextArea("start width :");
                JTextArea endW = new JTextArea("end width :");
                JTextArea startH = new JTextArea("start height : ");
                JTextArea endH = new JTextArea("end height : ");

                JTextField startWidth = new JTextField();
                JTextField endWidth = new JTextField();
                JTextField startHeight = new JTextField();
                JTextField endHeight = new JTextField();

                JButton cropButton = new JButton("Crop");

                Text.add(startW);
                Text.add(startWidth);
                Text.add(endW);
                Text.add(endWidth);
                Text.add(startH);
                Text.add(startHeight);
                Text.add(endH);
                Text.add(endHeight);
                Text.add(cropButton);

                cropPanel.add(Text, BorderLayout.NORTH);
                cropPanel.add(showingImage);

                cropPanel.setVisible(true);
                add(cropPanel);
                setVisible(true);

                cropButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int sw = Integer.parseInt(startWidth.getText());
                        int ew = Integer.parseInt(endWidth.getText());
                        int sh = Integer.parseInt(startHeight.getText());
                        int eh = Integer.parseInt(endHeight.getText());
                        showingImage.setVisible(false);
                        cropImage.setImage(image);
                        image = cropImage.crop(sw, sh, ew, eh);
                        cropPanel.add(cropImage);
                        add(cropPanel);
                        setVisible(true);
                    }
                });
                img.setImage(image);
                showingImage.setIcon(img);
            }
            if(event.getSource() == fClose){
                dispose();
            }


        }
    }
}


