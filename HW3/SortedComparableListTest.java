import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SortedComparableListTest {
	public SortedComparableList listTest;
    
    @Test 
    public void testFunctions() {
	    listTest = new SortedComparableList(1, null);
            assertEquals(listTest.head, 1);
            assertEquals(listTest.tail, null);
		
	    listTest.insert(3);
	    listTest.insert(7);
	    listTest.insert(-1);
	    listTest.insert(6);
	    System.out.printf(listTest.toString());

	    Comparable getC = listTest.get(4);
            assertEquals(getC, 7);
    }
    
    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(SortedComparableListTest.class);
    }       
}
