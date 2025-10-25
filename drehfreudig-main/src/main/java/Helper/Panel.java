package Helper;

import Exeptions.InvalidStringException;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Panel extends JPanel {

    private final int maxDepth;
    private final String tree;

    public Panel(int maxDepth, String tree){
        this.setPreferredSize(new Dimension(1000, 1000));
        this.maxDepth = maxDepth;
        this.tree = tree;
    }

    //todo this is hella complex, bet I can fix that?
    private List<Integer> findChildren(int nodeInd, List<Pair<Integer,Integer>> nodes) {
        List<Integer> c = new ArrayList<>();
        //I am your Father
        Pair<Integer,Integer> father = nodes.get(nodeInd);
        int open = father.getA();
        int closed = father.getB();

        for (int i = 0; i < nodes.size(); i++) {
            if (i == nodeInd) continue;

            Pair<Integer,Integer> candidate = nodes.get(i);
            int o = candidate.getA();
            int cl = candidate.getB();

            if (o > open && cl < closed) {
                boolean b = true;
                for (int j = 0; j < nodes.size(); j++) {
                    if (j == nodeInd || j == i) continue;

                    Pair<Integer,Integer> inters = nodes.get(j);
                    int intOpen = inters.getA();
                    int intClose = inters.getB();

                    if (intOpen < o && intClose > cl &&
                            intOpen > open && intClose < closed) {
                        b = false;
                        break;
                    }
                }

                if (b) {
                    c.add(i);
                }
            }
        }

        return c;
    }

    private void rec(Graphics2D g, int x, int y, int width,
                     Pair<Integer,Integer> parentCenter,
                     List<Pair<Integer,Integer>> nodes, int nodeIdx,
                     int currentDepth, int maxDepth) {

        int boxHeight = 50;

        List<Integer> children = findChildren(nodeIdx, nodes);
        boolean isLeaf = children.isEmpty();

        // Calculate rectangle height
        int rectHeight;
        if (isLeaf) {
            // Extend to max depth
            int remainingLevels = maxDepth - currentDepth;
            rectHeight = boxHeight + (remainingLevels * boxHeight);
        } else {
            rectHeight = boxHeight;
        }

        // Draw rectangle
        Rectangle r = new Rectangle(x, y, width, rectHeight);
        g.setColor(Color.yellow);
        g.fill(r);
        g.setColor(Color.black);
        g.draw(r);

        // Draw node circle at the top of the rectangle
        int centerX = x + width / 2;
        int centerY = y + boxHeight / 2; // Circle stays at top
        g.fillOval(centerX - 10, centerY - 10, 20, 20);

        // Draw line to parent
        if (parentCenter != null) {
            g.drawLine(parentCenter.getA(), parentCenter.getB(), centerX, centerY);
        }

        // Draw children (only if not a leaf)
        if (!isLeaf) {
            int childWidth = width / children.size();
            int childY = y + boxHeight;

            for (int i = 0; i < children.size(); i++) {
                int childX = x + (i * childWidth);
                rec(g, childX, childY, childWidth,
                        new Pair<>(centerX, centerY),
                        nodes, children.get(i),
                        currentDepth + 1, maxDepth);
            }
        }
    }

    public static List<Pair<Integer,Integer>> collectNodes(String s){
        List<Pair<Integer,Integer>> n = new ArrayList<>();
        int c = 0;
        Stack<Pair<Integer,Integer>> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(new Pair<>(c++, i));
            else if (s.charAt(i) == ')') {
                Pair<Integer,Integer> st = stack.pop();
                n.add(new Pair<>(st.getB(), i));
                c--;
            }
            else throw new InvalidStringException("The string is invalid");
        }

        //todo better sorting bc I still can't handle streams :c
        n.sort(Comparator.comparingInt(Pair::getA));

        return n;
    }

    @Override
    public void paint(Graphics gx) {
        super.paint(gx);
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Pair<Integer,Integer>> nodes = collectNodes(tree);

        if (!nodes.isEmpty()) {
            //todo make the height stuff dynamic, this is just temporary
            int rootInd = 0;
            int treeHeight = 400;
            int gap = 20;

            rec(g, 50, 50, getWidth() - 100, null, nodes, rootInd, 1, maxDepth);

            AffineTransform originalTransform = g.getTransform();

            int rotY = 50 + treeHeight + gap;
            int rotX = getWidth() / 2;

            g.translate(rotX, rotY);
            g.rotate(Math.PI);
            g.translate(-rotX, -50);

            rec(g, 50, 50, getWidth() - 100, null, nodes, rootInd, 1, maxDepth);

            g.setTransform(originalTransform);
        }
    }
}