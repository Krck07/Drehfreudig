package test;

import Helper.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class testPane extends JPanel {

    private List<Pair<Integer,Integer>> nodes;

    testPane(){
        this.setPreferredSize(new Dimension(1000, 1000));
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
                     List<Pair<Integer,Integer>> nodes, int nodeIdx) {

        int boxHeight = 50;

        Rectangle r = new Rectangle(x, y, width, boxHeight);
        g.setColor(Color.yellow);
        g.fill(r);
        g.setColor(Color.black);
        g.draw(r);

        int centerX = (int)r.getCenterX();
        int centerY = (int)r.getCenterY();
        g.fillOval(centerX - 10, centerY - 10, 20, 20);

        if (parentCenter != null) g.drawLine(parentCenter.getA(), parentCenter.getB(), centerX, centerY);

        List<Integer> children = findChildren(nodeIdx, nodes);

        if (!children.isEmpty()) {
            int childWidth = width / children.size();
            int childY = y + boxHeight;

            for (int i = 0; i < children.size(); i++) {
                int childX = x + (i * childWidth);
                rec(g, childX, childY, childWidth,
                        new Pair<>(centerX, centerY),
                        nodes, children.get(i));
            }
        }
    }

    @Override
    public void paint(Graphics gx) {
        super.paint(gx);
        Graphics2D g = (Graphics2D) gx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        nodes = testLogic.collectNodes("((()()())((()())(()()))(()()()())(()()()))");

        if (!nodes.isEmpty()) {
            //todo make the height stuff dynamic, this is just temporary
            int rootInd = 0;
            int treeHeight = 400;
            int gap = 20;

            rec(g, 50, 50, getWidth() - 100, null, nodes, rootInd);

            AffineTransform originalTransform = g.getTransform();

            int rotY = 50 + treeHeight + gap;
            int rotX = getWidth() / 2;

            g.translate(rotX, rotY);
            g.rotate(Math.PI);
            g.translate(-rotX, -50);

            rec(g, 50, 50, getWidth() - 100, null, nodes, rootInd);

            g.setTransform(originalTransform);
        }
    }
}