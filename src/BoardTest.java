import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/14/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest extends TestCase {
    public void testEmpty() throws Exception {
        Board board = new Board();

        assertTrue(board.Empty(1));
        board.board[0] = 'X';
        assertFalse(board.Empty(1));
        board.board[2] = 'O';
        assertFalse(board.Empty(3));
        assertTrue(board.Empty(9));
        board.board[8] = 'X';
        assertFalse(board.Empty(9));
    }
    public void testEmptyTwo() throws Exception {
        Board board = new Board();

        assertTrue(board.Empty(1,1));
        board.board[2] = 'X';
        assertFalse(board.Empty(0,2));
    }

    public void testPlaceMark() throws Exception {
        Board board = new Board();
        assertTrue(board.PlaceMark(1,'X'));
        assertEquals('X', board.board[0]);
        assertFalse(board.PlaceMark(1,'X'));
        assertEquals('X', board.GetMark(1));
    }

    public void testPlaceMarkTwo() throws Exception {
        Board board = new Board();
        assertTrue(board.PlaceMark(0,0,'X'));
        assertEquals('X', board.board[0]);
        assertFalse(board.PlaceMark(0,0,'X'));
        assertEquals('X', board.GetMark(1));
    }

    public void testGetMark() throws Exception {
        Board board = new Board();
        board.board[0] = 'X';
        assertEquals('X', board.GetMark(1));
    }

    public void testGetMarkTwo() throws Exception {
        Board board = new Board();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                assertEquals((char)( 3*i+j+(int)'1'), board.GetMark(i,j));
            }
        }
        assertEquals('1', board.GetMark(0,0));
        board.board[0] = 'X';
        assertEquals("Top left should be X",'X', board.GetMark(0,0));
        assertEquals('5', board.GetMark(1,1));
        board.board[4] = 'X';
        assertEquals('X', board.GetMark(1,1));
        board.board[8] = 'X';
        assertEquals('X', board.GetMark(2,2));
    }
}
