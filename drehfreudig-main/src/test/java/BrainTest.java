import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainTest {
    @Test
    void testEmptyString() {
        assertFalse(Brain.isDrehfreudig(""));
    }
    @Test
    void testRootOnly() {
        assertTrue(Brain.isDrehfreudig("()"));
    }
    @Test
    void testList() {
        assertTrue(Brain.isDrehfreudig("((((((())))))"));
    }
    @Test
    void testSimpleTrue() {
        assertTrue(Brain.isDrehfreudig("(()())"));
        assertTrue(Brain.isDrehfreudig("((())(()))"));
        assertTrue(Brain.isDrehfreudig("((()())(()()))"));
        assertTrue(Brain.isDrehfreudig("((()()())(()()()))"));
        assertTrue(Brain.isDrehfreudig("(((()()())(()()()))((()())(()())(()())))"));
    }
    @Test
    void testSimpleFalse() {
        assertFalse(Brain.isDrehfreudig("((()()())(()()))"));
        assertFalse(Brain.isDrehfreudig("(()(()()))"));
        assertFalse(Brain.isDrehfreudig("(((()())(()()()))((()())(()())(()())))"));
        assertFalse(Brain.isDrehfreudig("(()()()()()()(()()))"));
        assertFalse(Brain.isDrehfreudig("(((()())(()))((()())(()())))"));
    }
    @Test
    void testEdgeCaseFalse() {
        assertFalse(Brain.isDrehfreudig("((())())"));
        assertFalse(Brain.isDrehfreudig("(()(((()))))"));
        assertFalse(Brain.isDrehfreudig("(((()())(()))(((()())(()))))"));
        assertFalse(Brain.isDrehfreudig("((())(()())((())))"));
    }
    @Test
    void testEdgeCaseTrue() {
        assertTrue(Brain.isDrehfreudig("((()())()(()()))"));
        assertTrue(Brain.isDrehfreudig("((()())(())(()()))"));
        assertTrue(Brain.isDrehfreudig("((()())(()())(()())(())(()())(()())(()()))"));
    }
    @Test
    void testWithReader1() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig01.txt")));
    }
    @Test
    void testWithReader2() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig02.txt")));
    }
    @Test
    void testWithReader3() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig03.txt")));
    }
    @Test
    void testWithReader4() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig04.txt")));
    }
    @Test
    void testWithReader5() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig05.txt")));
    }
    @Test
    void testWithReader6() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig06.txt")));
    }
    @Test
    void testWithReader7() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig07.txt")));
    }
    @Test
    void testWithReader8() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig08.txt")));
    }
    @Test
    void testWithReader9() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig09.txt")));
    }
    @Test
    void testWithReader10() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig10.txt")));
    }
    @Test
    void testWithReader11() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig11.txt")));
    }
    @Test
    void testWithReader12() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig12.txt")));
    }
    @Test
    void testWithReader13() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig13.txt")));
    }
    @Test
    void testWithReader14() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig14.txt")));
    }
    @Test
    void testWithReader15() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig15.txt")));
    }
}