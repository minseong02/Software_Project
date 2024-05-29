package User_Main;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class HotelBookingFormBuilder {
    private JPanel panel;

    public HotelBookingFormBuilder() {
        panel = new JPanel();
        panel.setLayout(null); // Using null layout for absolute positioning
        panel.setPreferredSize(new Dimension(700, 1200)); // 크기 조정
    }

    public HotelBookingFormBuilder addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        panel.add(label);
        return this;
    }

    public HotelBookingFormBuilder addTextField(String name, int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setName(name);
        textField.setBounds(x, y, width, height);
        panel.add(textField);
        return this;
    }

    public HotelBookingFormBuilder addComboBox(String name, String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setName(name);
        comboBox.setBounds(x, y, width, height);
        panel.add(comboBox);
        return this;
    }

    public HotelBookingFormBuilder addRadioButton(String name, String text, int x, int y, ButtonGroup group) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setName(name);
        radioButton.setBounds(x, y, 100, 25);
        group.add(radioButton);
        panel.add(radioButton);
        return this;
    }

    public HotelBookingFormBuilder addButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(actionListener);
        panel.add(button);
        return this;
    }

    public JPanel build() {
        return panel;
    }
}
