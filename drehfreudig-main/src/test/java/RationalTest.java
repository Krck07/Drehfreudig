import Exeptions.DivByZeroException;
import Helper.Rational;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    @Test
    void testValidFractionCreation() {
        assertEquals("3/4", new Rational(3, 4).toString());
    }

    @Test
    void testFractionReduction() {
        assertEquals("3/4", new Rational(6, 8).toString());
        assertEquals("2/3", new Rational(10, 15).toString());
    }

    @Test
    void testNegativeDenominatorNormalization() {
        assertEquals("-3/4", new Rational(3, -4).toString());
        assertEquals("3/4", new Rational(-3, -4).toString());
    }

    @Test
    void testWholeNumberRepresentation() {
        assertEquals("5", new Rational(5, 1).toString());
        assertEquals("4", new Rational(12, 3).toString());
    }

    @Test
    void testZeroNumerator() {
        assertEquals("0", new Rational(0, 5).toString());
        assertEquals("0", new Rational(0, -3).toString());
    }

    @Test
    void testZeroDenominatorException() {
        Exception e = assertThrows(DivByZeroException.class, () -> new Rational(1, 0));
    }

    @Test
    void testZeroZeroDenominatorException() {
        Exception e = assertThrows(DivByZeroException.class, () -> new Rational(0, 0));
    }

    @Test
    void testNegativeZeroDenominatorException() {
        Exception e = assertThrows(DivByZeroException.class, () -> new Rational(-5, 0));
    }

    @Test
    void testBasicMultiplicationInstance() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(3, 4);
        assertEquals("1/2", r1.multiply(r2).toString());
    }

    @Test
    void testBasicMultiplicationStatic() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(3, 4);
        assertEquals("1/2", Rational.multiply(r1, r2).toString());
    }

    @Test
    void testMultiplicationByZero() {
        Rational r1 = new Rational(5, 7);
        Rational zero = new Rational(0, 1);
        assertEquals("0", r1.multiply(zero).toString());
    }

    @Test
    void testMultiplicationByOne() {
        Rational r1 = new Rational(3, 7);
        Rational one = new Rational(1, 1);
        assertEquals("3/7", r1.multiply(one).toString());
    }

    @Test
    void testMultiplicationWithNegatives() {
        Rational r1 = new Rational(-2, 3);
        Rational r2 = new Rational(4, 5);
        assertEquals("-8/15", r1.multiply(r2).toString());

        Rational r3 = new Rational(-2, 3);
        Rational r4 = new Rational(-4, 5);
        assertEquals("8/15", r3.multiply(r4).toString());
    }

    @Test
    void testMultiplicationResultingInWholeNumber() {
        Rational r1 = new Rational(3, 4);
        Rational r2 = new Rational(8, 6);
        assertEquals("1", r1.multiply(r2).toString());
    }

    @Test
    void testBasicDivisionByPositiveInteger() {
        Rational r = new Rational(3, 4);
        assertEquals("3/8", r.divide(2).toString());
    }

    @Test
    void testDivisionByNegativeInteger() {
        Rational r = new Rational(3, 4);
        assertEquals("-3/8", r.divide(-2).toString());
    }

    @Test
    void testDivisionByOne() {
        Rational r = new Rational(5, 7);
        assertEquals("5/7", r.divide(1).toString());
    }

    @Test
    void testDivisionByNegativeOne() {
        Rational r = new Rational(5, 7);
        assertEquals("-5/7", r.divide(-1).toString());
    }

    @Test
    void testDivisionResultingInReduction() {
        Rational r = new Rational(6, 8);
        assertEquals("1/4", r.divide(3).toString());
    }

    @Test
    void testDivisionByZeroException() {
        Rational r = new Rational(3, 4);
        Exception e = assertThrows(DivByZeroException.class, () -> r.divide(0));
    }

    @Test
    void testZeroDividedByInteger() {
        Rational zero = new Rational(0, 1);
        assertEquals("0", zero.divide(5).toString());
    }

    @Test
    void testEqualFractionsSameRepresentation() {
        Rational r1 = new Rational(3, 4);
        Rational r2 = new Rational(3, 4);
        assertEquals(r1, r2);
        assertTrue(Rational.equals(r1, r2));
    }

    @Test
    void testEqualFractionsDifferentRepresentation() {
        Rational r1 = new Rational(3, 4);
        Rational r2 = new Rational(6, 8);
        assertEquals(r1, r2);
        assertTrue(Rational.equals(r1, r2));
    }

    @Test
    void testEqualFractionsNegativeRepresentation() {
        Rational r1 = new Rational(-3, 4);
        Rational r2 = new Rational(3, -4);
        assertEquals(r1, r2);
        assertTrue(Rational.equals(r1, r2));
    }

    @Test
    void testUnequalFractions() {
        Rational r1 = new Rational(3, 4);
        Rational r2 = new Rational(2, 3);
        assertNotEquals(r1, r2);
        assertFalse(Rational.equals(r1, r2));
    }

    @Test
    void testSelfEquality() {
        Rational r = new Rational(3, 4);
        assertEquals(r, r);
        assertTrue(Rational.equals(r, r));
    }

    @Test
    void testNullEquality() {
        Rational r = new Rational(3, 4);
        assertNotEquals(null, r);
    }

    @Test
    void testZeroEquality() {
        Rational zero1 = new Rational(0, 1);
        Rational zero2 = new Rational(0, 5);
        assertEquals(zero1, zero2);
        assertTrue(Rational.equals(zero1, zero2));
    }

    @Test
    void testWholeNumberEquality() {
        Rational r1 = new Rational(5, 1);
        Rational r2 = new Rational(10, 2);
        assertEquals(r1, r2);
        assertTrue(Rational.equals(r1, r2));
    }

    @Test
    void testProperFractionToString() {
        assertEquals("3/4", new Rational(3, 4).toString());
    }

    @Test
    void testImproperFractionToString() {
        assertEquals("7/3", new Rational(7, 3).toString());
    }

    @Test
    void testWholeNumberToString() {
        assertEquals("5", new Rational(5, 1).toString());
    }

    @Test
    void testZeroToString() {
        assertEquals("0", new Rational(0, 7).toString());
    }

    @Test
    void testNegativeFractionToString() {
        assertEquals("-3/4", new Rational(-3, 4).toString());
    }

    @Test
    void testNegativeWholeNumberToString() {
        assertEquals("-5", new Rational(-5, 1).toString());
    }

    @Test
    void testReducedFractionToString() {
        assertEquals("2/3", new Rational(6, 9).toString());
    }

    @Test
    void testChainedOperations() {
        Rational r1 = new Rational(2, 3);
        Rational r2 = new Rational(3, 4);
        assertEquals("1/4", r1.multiply(r2).divide(2).toString());
    }

    @Test
    void testOperationsPreserveImmutability() {
        Rational original = new Rational(3, 4);
        String originalString = original.toString();
        original.multiply(new Rational(2, 1));
        original.divide(3);
        assertEquals(originalString, original.toString());
    }

    @Test
    void testGCDReduction() {
        assertEquals("8/3", new Rational(48, 18).toString());
        assertEquals("17/19", new Rational(17, 19).toString());
        assertEquals("4", new Rational(100, 25).toString());
    }

    @Test
    void testMultipleEquivalentRepresentations() {
        Rational half1 = new Rational(1, 2);
        Rational half2 = new Rational(2, 4);
        Rational half3 = new Rational(-1, -2);
        assertEquals(half1, half2);
        assertEquals(half2, half3);
        assertEquals(half1, half3);
    }
}