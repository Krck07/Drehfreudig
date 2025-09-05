import Helper.Pair;
import Helper.Rational;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Brain {
    public static boolean isDrehfreudig(String tree){
        if (tree == null) return false;
        if (tree.equals("()")) return true;

        List<Pair<Rational,Integer>> info = calcInfo(tree);

        int i = info.size();
        for (int j = 0; j < i/2; j++) {
            if (!info.get(j).getA().equals(info.get(i-1-j).getA()) && !Objects.equals(info.get(j).getB(), info.get(i - 1 - j).getB())){
                return false;
            }
        }
        return true;
    }

    private static List<Pair<Rational,Integer>> calcInfo(String tree){
        List<Pair<Rational,Integer>> info = new ArrayList<>();
        calcInfoRec(tree, 0, tree.length()-1, new Rational(1,1),0, info);
        return info;
    }

    private static void calcInfoRec(String tree, int start, int end, Rational width, int depth, List<Pair<Rational,Integer>> info){
        if (start >= end) return;

        if (end-start == 1 && tree.charAt(start) == '(' && tree.charAt(end) == ')'){
            info.add(new Pair<>(width,depth));
            return;
        }

        List<Pair<Integer,Integer>> children = findChildren(tree, start, end);

        if (children.isEmpty()) return;

        Rational childWidth = width.divide(children.size());

        for (Pair<Integer,Integer> p : children) {
            calcInfoRec(tree, p.getA(), p.getB(), childWidth, depth+1, info);
        }
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

    public static void explainExample(String brackets) {
        System.out.println("\n=== Analyzing: " + brackets + " ===");

        List<Pair<Rational, Integer>> leaves = calcInfo(brackets);
        System.out.println("Leaves info:");
        for (int i = 0; i < leaves.size(); i++) {
            System.out.println("  Leaf " + i + ": width=" + leaves.get(i).getA() + ", depth=" + leaves.get(i).getB());
        }

        // Check if all leaves at same depth
        boolean sameDepth = true;
        if (!leaves.isEmpty()) {
            int expectedDepth = leaves.get(0).getB();
            for (Pair<Rational, Integer> leaf : leaves) {
                if (leaf.getB() != expectedDepth) {
                    sameDepth = false;
                    System.out.println("✗ Not all leaves at same depth!");
                    break;
                }
            }
        }

        if (sameDepth && !leaves.isEmpty()) {
            System.out.println("✓ All leaves at depth " + leaves.get(0).getB());
        }

        // Check palindrome of widths
        boolean isPalindrome = true;
        int n = leaves.size();
        for (int i = 0; i < n / 2; i++) {
            if (!leaves.get(i).getA().equals(leaves.get(n - 1 - i).getA())) {
                isPalindrome = false;
                System.out.println("✗ Width mismatch: position " + i + " (" + leaves.get(i).getA() +
                        ") vs position " + (n-1-i) + " (" + leaves.get(n-1-i).getA() + ")");
                break;
            }
        }

        if (isPalindrome && n > 1) {
            System.out.println("✓ Widths form palindrome");
        }

        boolean isDrehfreudig = sameDepth && isPalindrome;
        System.out.println("Therefore drehfreudig: " + isDrehfreudig);

        if (isDrehfreudig) {
            System.out.println("✓ Middle band leaf rectangles will coincide exactly!");
        } else {
            System.out.println("✗ Cannot achieve perfect overlap in middle band");
        }
    }

    public static void main(String[] args) {
        // Test the problematic example you mentioned
        explainExample("((())(()))"); // Should be false - different depths

        // Test other cases
        explainExample("((()())(()()()))");
        explainExample("((())())");
        explainExample("((())(()))");//True case
        explainExample("(()())"); //Simple true case
        explainExample("((()())())"); //False case
    }
}
