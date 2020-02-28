package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Equation Genie");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new LeftPanel(), BorderLayout.WEST);

        this.add(new InteractionPanel(), BorderLayout.CENTER);

        this.setVisible(true);
    }
}
