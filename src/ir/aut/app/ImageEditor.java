package ir.aut.app;

import com.jhlabs.image.*;
import com.jhlabs.image.GrayFilter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


class ImageEditor extends JFrame {

    private JMenuItem fNew;
    private JMenuItem fOpen;
    private JMenuItem fClose;
    private JMenuItem fSaveAs;
    private JMenuItem eRotate;
    private JMenuItem eCrop;
    private JMenuItem eColor;
    private JMenuItem eAddText;
    private JMenuItem eAddSticker;
    private JMenuItem eFilter;
    private BufferedImage image;
    private ImageIcon img = new ImageIcon();
    private JLabel showingImage;
    private RotateImage rotateImage = new RotateImage();
    private CropImage cropImage = new CropImage();
    private ColorImage colorImage = new ColorImage();
    private JPanel rotationPanel = new JPanel(new BorderLayout());
    private JPanel cropPanel = new JPanel(new BorderLayout());
    private JPanel colorPanel = new JPanel(new BorderLayout());
    private JPanel addTextPanel = new JPanel(new BorderLayout());
    private JPanel Filter = new JPanel(new BorderLayout());

    ImageEditor() {
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
        eFilter = new JMenuItem("Filter");
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

            if (event.getSource() == fNew) {
                image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = image.createGraphics();
                graphics.setPaint(new Color(255, 249, 241));
                graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
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
                System.out.println(image.getWidth() + " " + image.getHeight());
            }
            if (event.getSource() == eRotate) {
                showingImage.setVisible(true);
                cropPanel.setVisible(false);
                colorPanel.setVisible(false);
                addTextPanel.setVisible(false);
                Filter.setVisible(false);

                JButton rotateButton = new JButton("rotate");

                JPanel Text = new JPanel(new GridLayout(1, 3));
                JTextArea enterAngle = new JTextArea("enter angle : ");
                JTextField angle = new JTextField();

                Text.add(enterAngle);
                Text.add(angle);
                Text.add(rotateButton);

                rotationPanel.add(showingImage);
                rotationPanel.add(Text, BorderLayout.NORTH);
                add(rotationPanel);
                rotationPanel.setVisible(true);

                rotateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        rotateImage.setImage(image);
                        rotateImage.rotateImage(Float.parseFloat(angle.getText()));
                        image = rotateImage.rotate();
                        rotationPanel.add(rotateImage);
                        rotationPanel.setVisible(true);
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
                colorPanel.setVisible(false);
                addTextPanel.setVisible(false);
                Filter.setVisible(false);

                JPanel Text = new JPanel(new GridLayout(1, 9));
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
                showingImage.setVisible(true);
            }

            if (event.getSource() == eColor) {
                showingImage.setVisible(true);
                rotationPanel.setVisible(false);
                cropPanel.setVisible(false);
                addTextPanel.setVisible(false);
                Filter.setVisible(false);

                JSlider blueSlider = new JSlider(0, 255);
                JSlider redSlider = new JSlider(0, 255);
                JSlider greenSlider = new JSlider(0, 255);
                JPanel sliders = new JPanel(new GridLayout(3, 1));
                redSlider.setMajorTickSpacing(10);
                redSlider.setMinorTickSpacing(1);
                redSlider.setPaintTicks(true);
                redSlider.setPaintLabels(true);
                redSlider.setForeground(Color.red);

                blueSlider.setMajorTickSpacing(10);
                blueSlider.setMinorTickSpacing(1);
                blueSlider.setPaintTicks(true);
                blueSlider.setPaintLabels(true);
                blueSlider.setForeground(Color.blue);

                greenSlider.setMajorTickSpacing(10);
                greenSlider.setMinorTickSpacing(1);
                greenSlider.setPaintTicks(true);
                greenSlider.setPaintLabels(true);
                greenSlider.setForeground(Color.green);

                sliders.add(redSlider);
                sliders.add(greenSlider);
                sliders.add(blueSlider);

                colorPanel.add(sliders, BorderLayout.NORTH);
                colorPanel.add(showingImage);
                colorPanel.setVisible(true);

                add(colorPanel);
                setVisible(true);

                redSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        showingImage.setVisible(false);
                        colorImage.setImage(image);
                        image = colorImage.changeRed(redSlider.getValue());
                        colorPanel.add(colorImage);
                        add(colorPanel);
                        setVisible(true);
                    }
                });

                greenSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        showingImage.setVisible(false);
                        colorImage.setImage(image);
                        image = colorImage.changeGreen(greenSlider.getValue());
                        colorPanel.add(colorImage);
                        add(colorPanel);
                        setVisible(true);
                    }
                });

                blueSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        showingImage.setVisible(false);
                        colorImage.setImage(image);
                        image = colorImage.changeBlue(blueSlider.getValue());
                        colorPanel.add(colorImage);
                        add(colorPanel);
                        setVisible(true);
                    }
                });

                img.setImage(image);
                showingImage.setIcon(img);

            }
            if (event.getSource() == eFilter) {

                showingImage.setVisible(true);
                rotationPanel.setVisible(false);
                cropPanel.setVisible(false);
                colorPanel.setVisible(false);
                addTextPanel.setVisible(false);


                JPanel filtersPanel = new JPanel(new GridLayout(1, 9));

                JButton twirlFilter = new JButton("TwirlFilter");
                JButton invertFilter = new JButton("InvertFilter");
                JButton crystallizeFilter = new JButton("CrystallizeFilter");
                JButton waterFilter = new JButton("waterFilter");
                JButton unsharpFilter = new JButton("unsharpFilter");
                JButton shadowFilter = new JButton ("shadowFilter");
                JButton offsetFilter = new JButton("offsetFilter");
                JButton grayFilter = new JButton("grayFilter");
                JButton fadeFilter = new JButton("fadeFilter ");

                filtersPanel.add(twirlFilter);
                filtersPanel.add(invertFilter);
                filtersPanel.add(crystallizeFilter);
                filtersPanel.add(waterFilter);
                filtersPanel.add(unsharpFilter);
                filtersPanel.add(shadowFilter);
                filtersPanel.add(offsetFilter);
                filtersPanel.add(grayFilter);
                filtersPanel.add(fadeFilter);

                Filter.add(filtersPanel, BorderLayout.NORTH);
                Filter.add(showingImage);
                Filter.setVisible(true);
                add(Filter);
                setVisible(true);

                twirlFilter.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        showingImage.setVisible(false);
                        TwirlFilter twfilter = new TwirlFilter();
                        twfilter.setAngle(45);
                        twfilter.setRadius(300);
                        image = twfilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });
                invertFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        InvertFilter invFilter = new InvertFilter();
                        invFilter.filterRGB(100, 100, 10);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });

                crystallizeFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        CrystallizeFilter crysFilter = new CrystallizeFilter();
                        crysFilter.setEdgeColor(10);
                        crysFilter.setEdgeThickness(20);
                        crysFilter.setAngle(45);
                        image = crysFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });

                waterFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        WaterFilter wtrFilter = new WaterFilter();
                        wtrFilter.setRadius(300);
                        image = wtrFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });


                unsharpFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        UnsharpFilter unsFilter = new UnsharpFilter();
                        image = unsFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });

                shadowFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        ShadowFilter shdFilter = new ShadowFilter();
                        image = shdFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });

                offsetFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        OffsetFilter ofsFilter = new OffsetFilter();
                        image = ofsFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });


                grayFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        GrayFilter gryFilter = new GrayFilter();
                        image = gryFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });

                fadeFilter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        FadeFilter fdFilter = new FadeFilter();
                        image = fdFilter.filter(image, image);
                        img.setImage(image);
                        showingImage.setIcon(img);
                        showingImage.setVisible(true);
                    }
                });
            }


            if (event.getSource() == eAddText)

            {
                rotationPanel.setVisible(false);
                cropPanel.setVisible(false);
                colorPanel.setVisible(false);
                Filter.setVisible(false);
                showingImage.setVisible(true);

                JPanel textPanel = new JPanel(new GridLayout(1, 8));
                JTextArea textArea = new JTextArea("enter your text : ");
                JTextArea posArea = new JTextArea("enter your position : ");
                JTextArea xArea = new JTextArea("x : ");
                JTextArea yArea = new JTextArea("y : ");
                JTextField text = new JTextField();
                JTextField x = new JTextField();
                JTextField y = new JTextField();
                JButton add = new JButton("add");

                textPanel.add(textArea);
                textPanel.add(text);
                textPanel.add(posArea);
                textPanel.add(xArea);
                textPanel.add(x);
                textPanel.add(yArea);
                textPanel.add(y);
                textPanel.add(add);

                addTextPanel.add(textPanel, BorderLayout.NORTH);
                addTextPanel.add(showingImage);
                addTextPanel.setVisible(true);
                add(addTextPanel);
                setVisible(true);

                AddText addText = new AddText(image);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showingImage.setVisible(false);
                        try {
                            addText.mergeImageAndText(text.getText(), new Point(Integer.parseInt(x.getText()), Integer.parseInt(x.getText())));
                        } catch (IOException err) {
                            err.printStackTrace();
                        }
                        addTextPanel.add(addText);
                        addTextPanel.setVisible(true);
                        setVisible(true);
                    }
                });
                img.setImage(image);
                showingImage.setIcon(img);
            }

            if (event.getSource() == fClose)

            {
                dispose();
            }
            if (event.getSource() == fSaveAs)

            {
                SaveImage saveImage = new SaveImage(image);
                try {
                    saveImage.write();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}



