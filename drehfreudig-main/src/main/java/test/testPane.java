package test;

import Helper.Pair;
import Helper.Rational;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class testPane extends JPanel {

    testPane(){
        this.setPreferredSize(new Dimension(500,500));
    }

    private void rec(Graphics2D g, Pair<Integer,Integer> topLeft,
                     Rational fraction, Pair<Integer,Integer> parentCenter,
                     List<Pair<Integer,Integer>> nodes, int nodeInd){
        //todo make the sides dynamic
        Rectangle r = new Rectangle(new Dimension(fraction.multiply(1024),50));
        r.setLocation(topLeft.getA(),topLeft.getB());
        g.draw(r);
        g.drawOval((int)r.getCenterX()-10,(int)r.getCenterY()-10,20,20);
        if(parentCenter!=null)g.drawLine(parentCenter.getA(), parentCenter.getB(), (int)r.getCenterX(), (int)r.getCenterY());
        int end = findEnd(nodeInd,nodes);
        for (int i = nodeInd; i<end; i++){
            Rational childFraction = Rational.multiply(fraction,new Rational(1,end-nodeInd));
            //todo fix this xD
            rec(g,new Pair<>(topLeft.getA()+50,topLeft.getB()+childFraction.multiply(1024)*i),childFraction,new Pair<>((int)r.getCenterX(),(int)r.getCenterY()),nodes,i);
        }
    }

    private int findEnd(int start, List<Pair<Integer,Integer>> nodes){
        for (int i = start; i < nodes.size(); i++){
            if (nodes.get(i).getB() < nodes.get(i+1).getA()) return i+1;
        }
        return -1;
    }

    @Override
    public void paint(Graphics gx) {
        Graphics2D g = (Graphics2D) gx;
        ArrayList<Pair<Integer,Integer>> n = new ArrayList<>();
        testLogic.collectNodes(n,"((()())(()()))");
        rec(g,new Pair<>(0,0),new Rational(1,1),null,n,0);
        //todo make the print function from paper
    }
}
