package Helper;

import java.util.List;
import java.util.ArrayList;

public class Node {
    private double x;
    private double y;
    private Rational width;
    private double height;
    private int depth;
    private boolean leaf;
    private List<Integer> childInd;

    public Node(Rational width, int depth) {
        this.width = width;
        this.depth = depth;
        this.leaf = true;
        this.childInd = new ArrayList<>();
        this.x = 0.0;
        this.y = 0.0;
        this.height = 0.0;
    }

    public Node(Rational width, int depth, List<Integer> childInd) {
        this.width = width;
        this.depth = depth;
        this.leaf = false;
        this.childInd = childInd != null ? new ArrayList<>(childInd) : new ArrayList<>();
        this.x = 0.0;
        this.y = 0.0;
        this.height = 0.0;
    }

    public Node(double x, double y, Rational width, double height, int depth, boolean leaf, List<Integer> childInd) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.leaf = leaf;
        this.childInd = childInd != null ? new ArrayList<>(childInd) : new ArrayList<>();
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Rational getWidth() { return width; }
    public double getHeight() { return height; }
    public int getDepth() { return depth; }
    public boolean isLeaf() { return leaf; }
    public List<Integer> getChildInd() { return childInd; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setWidth(Rational width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }
    public void setDepth(int depth) { this.depth = depth; }
    public void setLeaf(boolean leaf) { this.leaf = leaf; }
    public void setChildInd(List<Integer> childInd) {
        this.childInd = childInd != null ? new ArrayList<>(childInd) : new ArrayList<>();
    }

    public void addChild(int childIndex) {
        this.childInd.add(childIndex);
    }

    public double getWidthAsDouble() {
        return width.getDecimal();
    }
}