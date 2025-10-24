package test;

import Exeptions.FailedReadingException;
import Helper.Pair;

import java.util.*;

public class testLogic {

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
            } //todo real exception maybe?
            else throw new FailedReadingException("The string is invalid");
        }

        //todo better sorting bc I still can't handle streams :c
        n.sort(Comparator.comparingInt(Pair::getA));

        System.out.println(n);
        return n;
    }

    public static void main(String[] args) {
        try {
            List<Pair<Integer,Integer>> nodes = collectNodes("((()())(()()))");
            System.out.println("Nodes: " + nodes);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}