import Helper.Panel;

import javax.swing.*;

public class Visualizer extends JFrame {
    private final Panel p;

    public Visualizer(String tree, int maxDepth) {
        p = new Panel(maxDepth, tree);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(p);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Visualizer t = new Visualizer("((()())(()())(()())(())(()())(()())(()()))", 3);
    }
}
