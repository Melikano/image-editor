package ir.aut.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setJMenuBar(menuBar);
    }

    public class MenuEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

        }

    }
}


