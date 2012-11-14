import junit.framework.Assert;
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

    public void testPlaceMark() throws Exception {
        Board board = new Board();
        assertTrue(board.PlaceMark(1,'X'));
        assertEquals('X', board.board[0]);
        assertFalse(board.PlaceMark(1,'X'));
        assertEquals('X', board.GetMark(1));
    }

    public void testGetMark() throws Exception {
        Board board = new Board();
        board.board[0] = 'X';
        assertEquals('X', board.GetMark(1));
    }
}
