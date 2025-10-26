package Helper;

import java.util.ArrayList;
import java.util.List;

public class InsSort {
    public static void sort(List<Pair<Integer,Integer>> l){
        int ln = l.size();

        for (int i = 1; i < ln; i++) {
            Pair<Integer,Integer> k = l.get(i);
            int j = i-1;

            while (j>=0 && l.get(j).a()>k.a()){
                l.set(j+1,l.get(j));
                j--;
            }
            l.set(j+1,k);
        }
    }
}
