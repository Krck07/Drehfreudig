package test;

import javax.swing.*;

public class test extends JFrame {
    testPane panel;

    public test(){
        panel = new testPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
