import Helper.InsSort;
import Helper.Pair;
import Helper.Panel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelTest {
    @Test
    void testChildrenFinder1(){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        list.add(new Pair<>(0, 7));
        list.add(new Pair<>(1, 2));
        list.add(new Pair<>(3, 4));
        list.add(new Pair<>(5, 6));

        List<Integer> l = Panel.findChildren(0,list);

        assertEquals(1, l.get(0));
        assertEquals(2, l.get(1));
        assertEquals(3, l.get(2));
    }
    @Test
    void testChildrenFinder2(){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        list.add(new Pair<>(0, 13));
        list.add(new Pair<>(1, 4));
        list.add(new Pair<>(2, 3));
        list.add(new Pair<>(5, 8));
        list.add(new Pair<>(6, 7));
        list.add(new Pair<>(9, 12));
        list.add(new Pair<>(10, 11));

        List<Integer> l = Panel.findChildren(0,list);

        assertEquals(1, l.get(0));
        assertEquals(3, l.get(1));
        assertEquals(5, l.get(2));
    }
    @Test
    void testNodeCollection1(){
        List<Pair<Integer,Integer>> l = Panel.collectNodes("(()())");

        assertEquals(new Pair<>(0, 5),l.get(0));
        assertEquals(new Pair<>(1, 2),l.get(1));
        assertEquals(new Pair<>(3, 4),l.get(2));
    }
    @Test
    void testNodeCollection2(){
        List<Pair<Integer,Integer>> l = Panel.collectNodes("((()())(()()))");

        assertEquals(new Pair<>(0, 13),l.get(0));
        assertEquals(new Pair<>(1, 6),l.get(1));
        assertEquals(new Pair<>(2, 3),l.get(2));
        assertEquals(new Pair<>(4, 5),l.get(3));
        assertEquals(new Pair<>(7, 12),l.get(4));
        assertEquals(new Pair<>(8, 9),l.get(5));
        assertEquals(new Pair<>(10, 11),l.get(6));
    }
}