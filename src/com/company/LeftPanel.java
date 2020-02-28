package com.company;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private static JRadioButton infToPos;
    private static JRadioButton posToInf;
    private static JRadioButton eval;
    private ButtonGroup bg;

    public LeftPanel() {
        Dimension dimension = this.getPreferredSize();
        dimension.width = 200;
        this.setPreferredSize(dimension);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEtchedBorder());

        infToPos = new JRadioButton("Infix to Postfix");
        posToInf = new JRadioButton("Postfix to Infix");
        eval = new JRadioButton("Evaluate");

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;

        this.add(infToPos, gc);

        gc.gridy = 1;

        this.add(posToInf, gc);

        gc.gridy = 2;

        this.add(eval, gc);

        bg = new ButtonGroup();
        bg.add(infToPos);
        bg.add(posToInf);
        bg.add(eval);

        this.setVisible(true);

    }

    public static String getOperation() {
        if (infToPos.isSelected())
            return "infToPos";
        else if (posToInf.isSelected())
            return "posToInf";
        else if (eval.isSelected())
            return "eval";
        else
            return null;
    }
}
