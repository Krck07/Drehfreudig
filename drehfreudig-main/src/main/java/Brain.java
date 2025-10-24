import Exeptions.TreeTooLargeException;
import Helper.Node;
import Helper.Pair;
import Helper.Rational;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Brain {
    public static boolean isDrehfreudig(String tree){
        if (tree == null) return false;
        if (tree.isEmpty()) return false;
        if (tree.equals("()")) return true;

        final int maxWidth = 1920;
        final int maxHeight = 1080;
        final int spacing = 60;

        List<Node> info = calcInfo(tree);
        List<Node> leafs = info.stream().filter(Node::isLeaf).toList();

        int i = leafs.size();
        for (int j = 0; j < i/2; j++) {
            if (!leafs.get(j).getWidth().equals(leafs.get(i-1-j).getWidth()) || !Objects.equals(leafs.get(j).getDepth(), leafs.get(i - 1 - j).getDepth())){
                return false;
            }
        }

        // start

        double currX = 0.0;
        for (Node n : leafs) {
            n.setX(currX);
            if (n.getDepth()*spacing>maxHeight) throw new TreeTooLargeException("The Tree is too large to display");
            n.setY(n.getDepth()*spacing);
            n.setHeight(spacing);
            currX += n.getWidthAsDouble() * maxWidth;
        }

        for (Node n : info){
            if (!n.isLeaf()){
                double maxLeftX = Double.MAX_VALUE;
                double maxRightX = Double.MIN_VALUE;

                for (int ind : n.getChildInd()){
                    Node child = info.get(ind);
                    maxLeftX = Math.min(maxLeftX,child.getX());
                    maxRightX = Math.max(maxRightX, child.getX()+child.getWidthAsDouble()*maxWidth);
                }

                n.setX((maxLeftX+maxRightX)/2);
                n.setY(n.getDepth()*spacing);
                n.setHeight(spacing);
            }
        }

        //end

        return true;
    }

    private static List<Node> calcInfo(String tree){
        List<Node> info = new ArrayList<>();
        calcInfoRec(tree, 0, tree.length()-1, new Rational(1,1),0, info);
        return info;
    }

    private static void calcInfoRec(String tree, int start, int end, Rational width, int depth, List<Node> info){
        if (start >= end) return;

        if (end-start == 1 && tree.charAt(start) == '(' && tree.charAt(end) == ')'){
            info.add(new Node(width,depth));
            return;
        }

        List<Pair<Integer,Integer>> children = findChildren(tree, start, end);

        if (children.isEmpty()) return;

        List<Integer> childInd = new ArrayList<>();
        Rational childWidth = width.divide(children.size());

        for (Pair<Integer,Integer> p : children) {
            int cInd = info.size();
            calcInfoRec(tree, p.getA(), p.getB(), childWidth, depth+1, info);

            if (info.size()>cInd){
                for (int i = cInd; i < info.size();i++){
                    if (info.get(i).getDepth() == depth+1){
                        childInd.add(i);
                        break;
                    }
                }
            }
        }

        info.add(new Node(width,depth,childInd));
    }

    private static List<Pair<Integer,Integer>> findChildren(String tree, int start, int end){
        List<Pair<Integer,Integer>> range = new ArrayList<>();

        int pos = start+1;
        while (pos < end) {
            if (tree.charAt(pos) == '('){
                int child = pos;
                int depth = 1;
                pos++;

                while (pos < end && depth > 0) {
                    if (tree.charAt(pos) == '(') depth++;
                    else depth--;
                    pos++;
                }

                range.add(new Pair<>(child, pos - 1));
            }
            else {
                pos++;
            }
        }
        return range;
    }
}