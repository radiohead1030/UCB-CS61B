import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        //tester = new StaffCalculator(); // Comment me out to test your Calculator
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE
    @Test 
    public void testSumTwoNeg() {
        assertEquals(-5, tester.add(-1, -4));
    }
    
    @Test 
    public void testSumTwoPos() {
        assertEquals(5, tester.add(1, 4));
    }
    
    @Test 
    public void testSumOnePosOneNeg() {
        assertEquals(3, tester.add(-1, 4));
        assertEquals(-3, tester.add(1, -4));
    }

    @Test 
    public void testSumWithZero() {
        assertEquals(3, tester.add(3, 0));
        assertEquals(-1, tester.add(-1, 0));
        assertEquals(0, tester.add(-4, 4));
    }
    
    @Test 
    public void testMultTwoNeg() {
        assertEquals(4, tester.multiply(-1, -4));
    }
    
    @Test 
    public void testMultTwoPos() {
        assertEquals(4, tester.multiply(1, 4));
    }
    
    @Test 
    public void testMultOnePosOneNeg() {
        assertEquals(-4, tester.multiply(-1, 4));
    }

    @Test 
    public void testMultWithZero() {
        assertEquals(0, tester.multiply(3, 0));
        assertEquals(0, tester.multiply(-1, 0));
    }
    
    /*@Test 
    public void testGetBit() {
        assertEquals(1, tester.getBit(9, 0));
        assertEquals(1, tester.getBit(15, 3));
    }

    @Test 
    public void testSetBit() {
        assertEquals(28, tester.setBit(20, 3));
        assertEquals(13, tester.setBit(9, 2));
    }*/
    
    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}
