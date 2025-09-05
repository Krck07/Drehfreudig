import Exeptions.FailedReadingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    @Test
    void testDemoFileString1() {
        assertEquals("(((()())(()())(()()))((()())(()()))((()()())(()()())))", Reader.readString("drehfreudig01.txt"));
    }

    @Test
    void testDemoFileString2() {
        assertEquals("((()())(()()()))", Reader.readString("drehfreudig02.txt"));
    }

    @Test
    void testMissingFileString() {
        Exception e = assertThrows(FailedReadingException.class, () -> Reader.readString("missing.txt"));
        assertEquals("This file might not exist. -- missing.txt",e.getMessage());
    }

    @Test
    void testUnwantedCharactersString1() {
        Exception e = assertThrows(FailedReadingException.class, () -> Reader.readString("error.txt"));
        assertEquals("This file contains unwanted characters. -- error.txt",e.getMessage());
    }

    @Test
    void testUnwantedCharactersString2() {
        assertEquals("(((()())(()())(()()))((()())(()()))((()()())(()()())))", Reader.readString("noError.txt"));
    }

    @Test
    void testDemoFileArr1() {
        assertArrayEquals("(((()())(()())(()()))((()())(()()))((()()())(()()())))".toCharArray(), Reader.readChars("drehfreudig01.txt"));
    }

    @Test
    void testDemoFileArr2() {
        assertArrayEquals("((()())(()()()))".toCharArray(), Reader.readChars("drehfreudig02.txt"));
    }

    @Test
    void testMissingFileArr() {
        Exception e = assertThrows(FailedReadingException.class, () -> Reader.readChars("missing.txt"));
        assertEquals("This file might not exist. -- missing.txt",e.getMessage());
    }

    @Test
    void testUnwantedCharactersArr1() {
        Exception e = assertThrows(FailedReadingException.class, () -> Reader.readChars("error.txt"));
        assertEquals("This file contains unwanted characters. -- error.txt",e.getMessage());
    }

    @Test
    void testUnwantedCharactersArr2() {
        assertArrayEquals("(((()())(()())(()()))((()())(()()))((()()())(()()())))".toCharArray(), Reader.readChars("noError.txt"));
    }
}