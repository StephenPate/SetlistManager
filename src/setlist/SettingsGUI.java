package setlist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Interface to manipulate Setlist Settings
 */
public class SettingsGUI extends JPanel {
    private final JTextField setLengthTextField = new JTextField();
    private final JTextField breakLengthTextField = new JTextField();
    private final JTextField breakAmountTextField = new JTextField();
    private final JTextField genreRestrictTextField = new JTextField();

    /**
     * Parameterized constructor
     * @param s Setlist with which to interface
     */
    public SettingsGUI(Setlist s) {
        setLayout(new GridLayout(4, 2));

        JLabel setLengthLabel = new JLabel("Set Length:");
        JLabel breakLengthLabel = new JLabel("Break Length:");
        JLabel breakAmountLabel = new JLabel("Number of Breaks:");
        JLabel genreRestrictLabel = new JLabel("Genre(s) (separate with commas):");

        setLengthTextField.setText(String.valueOf(s.getLength()));
        breakLengthTextField.setText(String.valueOf(s.getBreakLength()));
        breakAmountTextField.setText(String.valueOf(s.getBreakCount()));
        ArrayList<String> genreRestrict = s.getGenreRestrict();
        StringBuilder genreRestrictString = new StringBuilder();
        for (int i = 0; i < genreRestrict.size(); ++i) {
            if (i != genreRestrict.size()-1) {
                genreRestrictString.append(genreRestrict.get(i)).append(", ");
            } else {
                genreRestrictString.append(genreRestrict.get(i));
            }
        }
        genreRestrictTextField.setText(genreRestrictString.toString());


        add(setLengthLabel);
        add(setLengthTextField);
        add(breakLengthLabel);
        add(breakLengthTextField);
        add(breakAmountLabel);
        add(breakAmountTextField);
        add(genreRestrictLabel);
        add(genreRestrictTextField);
    }

    /**
     * Populates interface with data from a Setlist
     * @param s Specified Setlist
     */
    public void update(Setlist s) {
        setLengthTextField.setText(String.valueOf(s.getLength()));
        breakLengthTextField.setText(String.valueOf(s.getBreakLength()));
        breakAmountTextField.setText(String.valueOf(s.getBreakCount()));
        ArrayList<String> genreRestrict = s.getGenreRestrict();
        StringBuilder genreRestrictString = new StringBuilder();
        for (int i = 0; i < genreRestrict.size(); ++i) {
            if (i != genreRestrict.size()-1) {
                genreRestrictString.append(genreRestrict.get(i)).append(", ");
            } else {
                genreRestrictString.append(genreRestrict.get(i));
            }
        }
        genreRestrictTextField.setText(genreRestrictString.toString());
    }

    /**
     * Converts setLengthTextField to an integer
     * @return Length of the set
     */
    public int getSetLength() {
        return Integer.parseInt(setLengthTextField.getText());
    }
    /**
     * Converts breakLengthTextField to an integer
     * @return Length of breaks
     */
    public int getBreakLength() {
        return Integer.parseInt(breakLengthTextField.getText());
    }
    /**
     * Converts breakAmountTextField to an integer
     * @return Number of breaks
     */
    public int getBreakAmount() {
        return Integer.parseInt(breakAmountTextField.getText());
    }

    /**
     * Gets text from genreRestrictTextField
     * @return Text in genreRestrictTextField
     */
    public String getGenreRestrict() {
        return genreRestrictTextField.getText();
    }
}
