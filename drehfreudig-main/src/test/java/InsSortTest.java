import Helper.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Helper.InsSort.sort;
import static org.junit.jupiter.api.Assertions.*;

class InsSortTest {
    @Test
    void testEmptyList() {
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        sort(list);
        assertTrue(list.isEmpty());
    }
    @Test
    void testSimpleList1(){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        list.add(new Pair<>(5, 10));
        sort(list);
        assertEquals(1, list.size());
        assertEquals(5, list.get(0).a());
    }
    @Test
    void testSimpleList2(){
        //sorted
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        list.add(new Pair<>(1, 100));
        list.add(new Pair<>(2, 200));
        list.add(new Pair<>(3, 300));
        list.add(new Pair<>(4, 400));

        sort(list);

        assertEquals(1, list.get(0).a());
        assertEquals(2, list.get(1).a());
        assertEquals(3, list.get(2).a());
        assertEquals(4, list.get(3).a());

        //reverse
        List<Pair<Integer,Integer>> list2 = new ArrayList<>();
        list2.add(new Pair<>(4, 400));
        list2.add(new Pair<>(3, 300));
        list2.add(new Pair<>(2, 200));
        list2.add(new Pair<>(1, 100));

        sort(list2);

        assertEquals(1, list2.get(0).a());
        assertEquals(2, list2.get(1).a());
        assertEquals(3, list2.get(2).a());
        assertEquals(4, list2.get(3).a());
    }
    @Test
    void testSimpleList3(){
        //random
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        list.add(new Pair<>(5, 50));
        list.add(new Pair<>(2, 20));
        list.add(new Pair<>(8, 80));
        list.add(new Pair<>(1, 10));
        list.add(new Pair<>(9, 90));

        sort(list);

        assertEquals(1, list.get(0).a());
        assertEquals(2, list.get(1).a());
        assertEquals(5, list.get(2).a());
        assertEquals(8, list.get(3).a());
        assertEquals(9, list.get(4).a());

        //random with high b
        List<Pair<Integer,Integer>> list2 = new ArrayList<>();
        list2.add(new Pair<>(3, 999));
        list2.add(new Pair<>(1, 1));
        list2.add(new Pair<>(2, 500));

        sort(list2);

        assertEquals(1, list2.get(0).a());
        assertEquals(999, list2.get(2).b());
        assertEquals(2, list2.get(1).a());
        assertEquals(3, list2.get(2).a());
    }
}