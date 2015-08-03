import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the Piece class
 */

public class TestPiece {

    /** Tests the constructor of Piece */
    @Test
    public void testConstructor() {
	Board b = new Board();
	Piece pawn = new Piece(true, b, 1, 1, "pawn");
        assertEquals(true, pawn.isFire);
        assertEquals(1, pawn.x);
        assertEquals(1, pawn.y);
	assertTrue(pawn.type.equals("pawn"));
    }

    /** Tests methods of Piece */
    @Test
    public void testMethods() {
	Board b = new Board();
	Piece pawn = new Piece(true, b, 1, 1, "pawn");
	Piece bomb = new Piece(false, b, 1, 2, "bomb");
	Piece shield = new Piece(false, b, 2, 2, "shield");
       
    	/** Tests isFire() */
       	assertEquals(true, pawn.isFire());
        assertEquals(false, bomb.isFire());
        
    	/** Tests side() */
	assertEquals(0, pawn.side());
	assertEquals(1, bomb.side());
    	
	/** Tests isKing() */
	assertEquals(false, pawn.isKing());
	assertEquals(false, bomb.isKing());
	
	/** Tests isBomb() */
	assertEquals(false, pawn.isBomb());
	assertEquals(true, bomb.isBomb());
	
	/** Tests isShield() */
	assertEquals(false, pawn.isShield());
	assertEquals(true, shield.isShield());
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestPiece.class);
    }
}
