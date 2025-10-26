import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainTest {
    @Test
    void testEmptyString() {
        assertFalse(Brain.isDrehfreudig("").a());
    }
    @Test
    void testRootOnly() {
        assertTrue(Brain.isDrehfreudig("()").a());
        assertEquals(1,Brain.isDrehfreudig("()").b());
    }
    @Test
    void testList() {
        assertTrue(Brain.isDrehfreudig("((((((()))))))").a());
        assertEquals(7,Brain.isDrehfreudig("((((((()))))))").b());
    }
    @Test
    void testSimpleTrue() {
        assertTrue(Brain.isDrehfreudig("(()())").a());
        assertTrue(Brain.isDrehfreudig("((())(()))").a());
        assertTrue(Brain.isDrehfreudig("((()())(()()))").a());
        assertTrue(Brain.isDrehfreudig("((()()())(()()()))").a());
        assertTrue(Brain.isDrehfreudig("(((()()())(()()()))((()())(()())(()())))").a());
    }
    @Test
    void testSimpleFalse() {
        assertFalse(Brain.isDrehfreudig("((()()())(()()))").a());
        assertFalse(Brain.isDrehfreudig("(()(()()))").a());
        assertFalse(Brain.isDrehfreudig("(((()())(()()()))((()())(()())(()())))").a());
        assertFalse(Brain.isDrehfreudig("(()()()()()()(()()))").a());
        assertFalse(Brain.isDrehfreudig("(((()())(()))((()())(()())))").a());
    }
    @Test
    void testEdgeCaseFalse() {
        assertFalse(Brain.isDrehfreudig("((())())").a());
        assertFalse(Brain.isDrehfreudig("(()(((()))))").a());
        assertFalse(Brain.isDrehfreudig("(((()())(()))(((()())(()))))").a());
        assertFalse(Brain.isDrehfreudig("((())(()())((())))").a());
    }
    @Test
    void testEdgeCaseTrue() {
        assertTrue(Brain.isDrehfreudig("((()())()(()()))").a());
        assertTrue(Brain.isDrehfreudig("((()())(())(()()))").a());
        assertTrue(Brain.isDrehfreudig("((()())(()())(()())(())(()())(()())(()()))").a());
    }
    @Test
    void testWithReader1() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig01.txt")).a());
    }
    @Test
    void testWithReader2() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig02.txt")).a());
    }
    @Test
    void testWithReader3() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig03.txt")).a());
    }
    @Test
    void testWithReader4() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig04.txt")).a());
    }
    @Test
    void testWithReader5() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig05.txt")).a());
    }
    @Test
    void testWithReader6() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig06.txt")).a());
    }
    @Test
    void testWithReader7() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig07.txt")).a());
    }
    @Test
    void testWithReader8() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig08.txt")).a());
    }
    @Test
    void testWithReader9() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig09.txt")).a());
    }
    @Test
    void testWithReader10() {
        assertTrue(Brain.isDrehfreudig(Reader.readString("drehfreudig10.txt")).a());
    }
    @Test
    void testWithReader11() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig11.txt")).a());
    }
    @Test
    void testWithReader12() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig12.txt")).a());
    }
    @Test
    void testWithReader13() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig13.txt")).a());
    }
    @Test
    void testWithReader14() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig14.txt")).a());
    }
    @Test
    void testWithReader15() {
        assertFalse(Brain.isDrehfreudig(Reader.readString("drehfreudig15.txt")).a());
    }
}