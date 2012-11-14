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
    }

    public void testPlaceMark() throws Exception {
        Board board = new Board();
        assertTrue(board.PlaceMark(1,'X'));
        assertFalse(board.PlaceMark(1,'X'));
    }

    public void testMark() throws Exception {
            assertTrue(true);
    }
}
