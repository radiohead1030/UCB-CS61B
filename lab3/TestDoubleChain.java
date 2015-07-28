import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the DoubleChain class
 */

public class TestDoubleChain {

    /** Tests the constructor of DoubleChain */
    @Test
    public void testConstructor() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(5,d.getFront().val, 1e-6);
        assertEquals(null, d.getFront().prev);
        assertEquals(null, d.getFront().next);
    }

    /** Tests some basic DoubleChain operations. */
    @Test
    public void testBasicOperations() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(5, d.getFront().val, 1e-11);
        assertEquals(5, d.getBack().val, 1e-11);

        d.insertFront(2);
        d.insertFront(1);
        d.insertBack(7);
        d.insertBack(8);
        assertEquals(1, d.getFront().val, 1e-11);
        assertEquals(8, d.getBack().val, 1e-11);
    }

    /** Tests toString() method */
    @Test
    public void testToString() {
	DoubleChain d = new DoubleChain(1.0);
        d.insertFront(2.1);
	String result = new String("<[2.1, 1.0]>");
        
	assertTrue(result.equals(d.toString()));
    }
    
    /** Tests deletion operations */
    @Test
    public void testDeletion() {
	DoubleChain d = new DoubleChain(1.0);
        d.insertBack(2.1);
        d.insertBack(3.3);
        d.insertBack(4.6);
        d.insertBack(5.2);

        assertEquals(5.2, d.deleteBack().val, 1e-11);
        assertEquals(4.6, d.getBack().val, 1e-11);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestDoubleChain.class);
    }
}
