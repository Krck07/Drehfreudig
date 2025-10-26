package Helper;

import Exeptions.InvalidStringException;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Panel extends JPanel {

    private final int maxDepth;
    private final String tree;
    private final int maxWidth;

    public Panel(int maxDepth, String tree){
        maxWidth = Math.min((tree.length()/2)*50,1280);
        this.setPreferredSize(new Dimension(maxWidth+100, Math.min(maxDepth*50*2+200,1280)));
        this.maxDepth = maxDepth;
        this.tree = tree;

    }

    public static List<Integer> findChildren(int nodeInd, List<Pair<Integer,Integer>> nodes) {
        List<Integer> c = new ArrayList<>();

        if (nodes.size() <= nodeInd + 1) return c;

        //I am your father again :3
        Pair<Integer,Integer> father = nodes.get(nodeInd);
        int a = father.a();
        int b = father.b();

        int skip = a;

        for (int i = nodeInd + 1; i < nodes.size(); i++) {
            Pair<Integer,Integer> node = nodes.get(i);
            int open = node.a();
            int close = node.b();

            if (open >= b) break;

            if (open < skip) continue;

            c.add(i);

            skip = close;
        }

        return c;
    }

    private void rec(Graphics2D g, int x, int y, Rational width,
                     Pair<Integer,Integer> parentCenter,
                     List<Pair<Integer,Integer>> nodes, int nodeIdx,
                     int currentDepth, int maxDepth) {

        int boxHeight = 50;

        List<Integer> children = findChildren(nodeIdx, nodes);
        boolean isLeaf = children.isEmpty();

        int rectHeight;
        if (isLeaf) {
            int remainingLevels = maxDepth - currentDepth;
            rectHeight = boxHeight + (remainingLevels * boxHeight);
        } else {
            rectHeight = boxHeight;
        }

        Rectangle r = new Rectangle(x, y, width.multiply(maxWidth), rectHeight);
        g.setColor(Color.yellow);
        g.fill(r);
        g.setColor(Color.black);
        g.draw(r);

        int centerX = x + width.multiply(maxWidth) / 2;
        int centerY = y + boxHeight / 2;
        g.fillOval(centerX - 10, centerY - 10, 20, 20);

        if (parentCenter != null) {
            g.drawLine(parentCenter.a(), parentCenter.b(), centerX, centerY);
        }

        if (!isLeaf) {
            Rational childWidth = width.divide(children.size());
            int childY = y + boxHeight;

            for (int i = 0; i < children.size(); i++) {
                int childX = x + childWidth.multiply(maxWidth)*i;
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
                n.add(new Pair<>(st.b(), i));
                c--;
            }
            else throw new InvalidStringException("The string is invalid");
        }

        InsSort.sort(n);

        return n;
    }

    @Override
    public void paint(Graphics gx) {
        super.paint(gx);
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Pair<Integer,Integer>> nodes = collectNodes(tree);

        if (!nodes.isEmpty()) {
            int rootInd = 0;
            int treeHeight = maxDepth*50*2;
            int gap = 20;

            rec(g, 50, 50, new Rational(1,1), null, nodes, rootInd, 1, maxDepth);

            AffineTransform originalTransform = g.getTransform();

            int rotY = 50 + treeHeight + gap;
            int rotX = getWidth() / 2;

            g.translate(rotX, rotY);
            g.rotate(Math.PI);
            g.translate(-rotX, -50);

            rec(g, 50, 50, new Rational(1,1), null, nodes, rootInd, 1, maxDepth);

            g.setTransform(originalTransform);
        }
    }
}