import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainTest {
    @Test
    void testEmptyString() {
        assertFalse(Brain.isDrehfreudig("").getA());
    }
    @Test
    void testRootOnly() {
        assertTrue(Brain.isDrehfreudig("()").getA());
        assertEquals(1,Brain.isDrehfreudig("()").getB());
    }
    @Test
    void testList() {
        assertTrue(Brain.isDrehfreudig("((((((()))))))").getA());
        assertEquals(7,Brain.isDrehfreudig("((((((()))))))").getB());
    }
    @Test
    void testSimpleTrue() {
        assertTrue(Brain.isDrehfreudig("(()())").getA());
        assertTrue(Brain.isDrehfreudig("((())(()))").getA());
        assertTrue(Brain.isDrehfreudig("((()())(()()))").getA());
        assertTrue(Brain.isDrehfreudig("((()()())(()()()))").getA());
        assertTrue(Brain.isDrehfreudig("(((()()())(()()()))((()())(()())(()())))").getA());
    }
    @Test
    void testSimpleFalse() {
        assertFalse(Brain.isDrehfreudig("((()()())(()()))").getA());
        assertFalse(Brain.isDrehfreudig("(()(()()))").getA());
        assertFalse(Brain.isDrehfreudig("(((()())(()()()))((()())(()())(()())))").getA());
        assertFalse(Brain.isDrehfreudig("(()()()()()()(()()))").getA());
        assertFalse(Brain.isDrehfreudig("(((()())(()))((()())(()())))").getA());
    }
    @Test
    void testEdgeCaseFalse() {
        assertFalse(Brain.isDrehfreudig("((())())").getA());
        assertFalse(Brain.isDrehfreudig("(()(((()))))").getA());
        assertFalse(Brain.isDrehfreudig("(((()())(()))(((()())(()))))").getA());
        assertFalse(Brain.isDrehfreudig("((())(()())((())))").getA());
    }
    @Test
    void testEdgeCaseTrue() {
        assertTrue(Brain.isDrehfreudig("((()())()(()()))").getA());
        assertTrue(Brain.isDrehfreudig("((()())(())(()()))").getA());
        assertTrue(Brain.isDrehfreudig("((()())(()())(()())(())(()())(()())(()()))").getA());
    }
    @Test
    void testWithReader1() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig01.txt")).getA());
    }
    @Test
    void testWithReader2() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig02.txt")).getA());
    }
    @Test
    void testWithReader3() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig03.txt")).getA());
    }
    @Test
    void testWithReader4() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig04.txt")).getA());
    }
    @Test
    void testWithReader5() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig05.txt")).getA());
    }
    @Test
    void testWithReader6() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig06.txt")).getA());
    }
    @Test
    void testWithReader7() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig07.txt")).getA());
    }
    @Test
    void testWithReader8() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig08.txt")).getA());
    }
    @Test
    void testWithReader9() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig09.txt")).getA());
    }
    @Test
    void testWithReader10() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig10.txt")).getA());
    }
    @Test
    void testWithReader11() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig11.txt")).getA());
    }
    @Test
    void testWithReader12() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig12.txt")).getA());
    }
    @Test
    void testWithReader13() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig13.txt")).getA());
    }
    @Test
    void testWithReader14() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig14.txt")).getA());
    }
    @Test
    void testWithReader15() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig15.txt")).getA());
    }
}