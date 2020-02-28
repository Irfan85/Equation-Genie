package com.company;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionPanel extends JPanel {
    private JTextField input;
    private static JTextArea output;
    private JButton computeButton;

    public InteractionPanel() {
        setLayout(new GridBagLayout());
        setBorder(new EtchedBorder());

        input = new JTextField(30);

        output = new JTextArea();
        output.setEditable(false);

        computeButton = new JButton("Compute");
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.process(LeftPanel.getOperation(), input.getText());
            }
        });

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;

        add(new JLabel("Input"), gc);

        gc.gridy = 1;
        add(input, gc);

        gc.gridy = 2;
        add(new JLabel("Output"), gc);

        gc.gridy = 3;
        gc.weighty = 1.2;
        gc.fill = GridBagConstraints.BOTH;
        add(output, gc);

        gc.gridy = 4;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(computeButton, gc);

        this.setVisible(true);
    }

    public static void showOutput(String s) {
        output.setText("");
        output.setText(s);
    }

}
