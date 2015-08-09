import static org.junit.Assert.*;

import org.junit.Test;

/** Perform tests of the Piece class
 */

public class TestBoard{
	public Board b = new Board(false);

    /** Tests pieceAt method of Board */
    @Test
    public void testPieceAt() {
        Piece p1 = b.pieceAt(0, 0); 
       	assertEquals(b.pieces[0][0], p1);
        
        Piece p2 = b.pieceAt(1,0); 
       	assertEquals(null, p2);
	
	Piece p3 = b.pieceAt(4, 3); 
       	assertEquals(null, p3);
	
	Piece p4 = b.pieceAt(3, 1); 
       	assertEquals(b.pieces[3][1], p4);
    }
    
    /** Tests canSelect method of canSelect */
    @Test
    public void testCanSelect() {
	b.fireTurn = true;
       	assertTrue(b.canSelect(0, 0));
       	assertTrue(!b.canSelect(1, 9));
       	assertTrue(!b.canSelect(0, 1));

	b.selectPiece = b.pieceAt(2,2);
       	assertTrue(b.canSelect(0, 0));
       	assertTrue(!b.canSelect(2, 3));
       	assertTrue(b.canSelect(3, 3));
       	assertTrue(b.canSelect(5, 5));
    }

    /** Tests select method of canSelect */
    @Test
    public void testSelect() {
	b.fireTurn = true;
       	assertTrue(b.canSelect(2, 2));
	b.select(2, 2);
       	assertTrue(b.canSelect(5, 5));
	b.select(5, 5);
       	assertTrue(b.canSelect(6, 6));
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
    }
}
