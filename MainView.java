package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    public static void createAndShowGUI() {
        Catalog c = new Catalog();
        FileIO fileIO = new FileIO();

        JFrame frame = new JFrame("Setlist Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        //Border
        Border space = BorderFactory.createEmptyBorder(5,5,5,5);

        //Icons!!!
        ImageIcon editIcon = new ImageIcon("EditSong.png");
        Image editImg = editIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH);
        editIcon = new ImageIcon(editImg);
        ImageIcon finalEditIcon = editIcon;
        ImageIcon addIcon = new ImageIcon("AddSong.png");
        Image addImg = addIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(addImg);
        ImageIcon finalAddIcon = addIcon;

        //Button Border
        Border buttonBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        //Buttons!!!
        JButton addSong = new JButton("Add Song");
        addSong.setBorder(buttonBorder);
        JButton addFromFile = new JButton("Add Song(s) from File");
        addFromFile.setBorder(buttonBorder);
        JButton exportCatalog = new JButton("Export Catalog");
        exportCatalog.setBorder(buttonBorder);
        JButton generate = new JButton("Generate Setlist");
        generate.setBorder(buttonBorder);
        JButton settings = new JButton("Setlist Settings");
        settings.setBorder(buttonBorder);
        JButton exportSetlist = new JButton("Export Setlist");
        exportSetlist.setBorder(buttonBorder);

        ListView catalogList = new ListView();
        JScrollPane catalogScroll = new JScrollPane(catalogList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        catalogScroll.setBorder(space);

        ListView catalogButtons = new ListView();
        catalogButtons.setBorder(space);
        catalogButtons.addListElement(addSong);
        SongPropertiesView addSongView = new SongPropertiesView();
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongView.changeSong(new Song());
                int r = JOptionPane.showConfirmDialog(frame, addSongView, "Add Song", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, finalAddIcon);
                if (r == 0) {
                    String p1 = addSongView.getTitle();
                    String p2 = addSongView.getComposer();
                    String p3 = addSongView.getKey();
                    String p4 = addSongView.getGenre();
                    int p5 = addSongView.getLength();
                    int p6 = addSongView.getTempo();
                    int p7 = addSongView.getIntro();
                    boolean p8 = addSongView.getArchive();
                    if (p1.equals("")) {
                        p1 = " ";
                    }
                    if (p2.equals("")) {
                        p2 = " ";
                    }
                    if (p3.equals("")) {
                        p3 = " ";
                    }
                    if (p4.equals("")) {
                        p4 = " ";
                    }
                    Song songToAdd = new Song(p1, p2, p3, p4, p5, p6, p7, p8);
                    c.addSong(songToAdd);
                    SongPropertiesView elementView = new SongPropertiesView(addSongView);
                    catalogList.addListElement(new SongButtonView(songToAdd, elementView, c, catalogList));
                }
            }
        });
        catalogButtons.addListElement(addFromFile);
        addFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int r = fileChooser.showOpenDialog(frame);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    fileIO.openCatalog(filename, c);
                    Song fileSong;
                    int i = 0;
                    while ((fileSong = c.reviewSong(i)) != null) {
                        catalogList.addListElement(new SongButtonView(fileSong, new SongPropertiesView(fileSong), c, catalogList));
                        ++i;
                    }
                }
            }
        });
        catalogButtons.addListElement(exportCatalog);
        exportCatalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int r = fileChooser.showSaveDialog(frame);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    fileIO.writeCatalog(c, filename);
                }
            }
        });

        PageView catalogPage = new PageView(catalogScroll, catalogButtons);

        //------------------------------------------------------------------------

        ListView setlistList = new ListView();
        JScrollPane setlistScroll = new JScrollPane(setlistList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setlistScroll.setBorder(space);

        ListView setlistButtons = new ListView();
        setlistButtons.setBorder(space);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Song cSong;
                int i = 0;
                while ((cSong = c.reviewSong(i)) != null) {
                    setlistList.addListElement(new SongView(cSong));
                    ++i;
                }
            }
        });
        setlistButtons.addListElement(generate);
        Setlist setlist = new Setlist();
        SettingsView settingsView = new SettingsView(setlist);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = JOptionPane.showConfirmDialog(frame, settingsView, "Setlist Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (r == 0) {
                    setlist.setLength(settingsView.getSetLength());
                    System.out.println(settingsView.getSetLength());
                    setlist.setBreakLength(settingsView.getBreakLength());
                    setlist.setBreakCount(settingsView.getBreakAmount());
                }
            }
        });
        setlistButtons.addListElement(settings);
        exportSetlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int r = fileChooser.showSaveDialog(frame);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString();
                    fileIO.writeCatalog(c, filename);
                }
            }
        });
        setlistButtons.addListElement(exportSetlist);

        PageView setlistPage = new PageView(setlistScroll, setlistButtons);

        //--------------------------------------------------------------------------

        tabbedPane.add("Catalog", catalogPage);
        tabbedPane.add("Setlist", setlistPage);
        tabbedPane.setPreferredSize(new Dimension(950, 500));

        frame.add(tabbedPane);

        frame.pack();
        frame.setLocation(100, 100);
        frame.setVisible(true);

    }
}
