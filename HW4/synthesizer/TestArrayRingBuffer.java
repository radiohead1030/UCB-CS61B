package synthesizer;

import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);
	arb.enqueue(3.1);
	arb.enqueue(4.1);
	arb.enqueue(5.1);
	arb.enqueue(6.1);
	arb.enqueue(7.1);
	assertEquals(3.1, arb.dequeue(), 1e-11);
	assertEquals(4.1, arb.dequeue(), 1e-11);
	assertEquals(5.1, arb.peek(), 1e-11);
	assertEquals(5.1, arb.dequeue(), 1e-11);
    }
    
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
