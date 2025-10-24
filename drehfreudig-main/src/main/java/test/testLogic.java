package test;

import Exeptions.FailedReadingException;
import Helper.Pair;

import java.io.InvalidClassException;
import java.util.*;

public class testLogic {

    public static void collectNodes(List<Pair<Integer,Integer>> n, String s){
        int i = 0;
        int c = 0;
        Stack<Pair<Integer,Integer>> stack = new Stack<>();

        do {
            if (s.charAt(i) == '(') stack.push(new Pair<>(c++,i++));
            else if (s.charAt(i) == ')') {
                n.add(new Pair<>(stack.pop().getB(), i++));
                c--;
            }
            //todo replace with actual Exception
            else throw new FailedReadingException("The string is invalid");
        } while (c>0);

        //todo replace with better sort
        n = n.stream().sorted(Comparator.comparingInt(Pair::getA)).toList();

        System.out.println(n);
    }

    public static void main(String[] args) {
        try {
            collectNodes(new ArrayList<>(),"((()())(()()))");
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
