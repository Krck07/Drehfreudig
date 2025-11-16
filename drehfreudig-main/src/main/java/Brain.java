import Helper.Pair;
import Helper.Panel;
import Helper.Rational;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Brain {
    public static Pair<Boolean,Integer> isDrehfreudig(String tree){
        if (tree == null) return new Pair<>(false,-1);
        if (tree.isEmpty()) return new Pair<>(false,-1);
        if (tree.equals("()")) return new Pair<>(true,1);

        List<Pair<Rational,Integer>> info = calcInfo(tree);

        int i = info.size();
        for (int j = 0; j < i/2; j++) {
            if (!info.get(j).a().equals(info.get(i-1-j).a()) || !Objects.equals(info.get(j).b(), info.get(i - 1 - j).b())){
                return new Pair<>(false,-1);
            }
        }

        int depth = 1;
        for (Pair<Rational,Integer> p:info) if (p.b()> depth) depth = p.b();

        return new Pair<>(true, depth);
    }

    private static List<Pair<Rational,Integer>> calcInfo(String tree){
        List<Pair<Rational,Integer>> info = new ArrayList<>();
        calcInfoRec(tree, 0, tree.length()-1, new Rational(1,1),1, info);
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
            calcInfoRec(tree, p.a(), p.b(), childWidth, depth+1, info);
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

    public static void main(String[] args) {
        String tree = Reader.readString(args[0]);
        Pair<Boolean,Integer> res = isDrehfreudig(tree);
        if (res.a()){
        try{ImageExport.exportToFile(new Panel(res.b(),tree), "drehfreudig-main/src/main/resources/output/tree_output.png");}
        catch (Exception e){ throw new RuntimeException();}}
        else System.out.println(false);
    }
}