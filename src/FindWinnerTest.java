/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/25/12
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/14/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindWinnerTest extends TestCase {
    protected Board board = new Board();
    @Test
    public void testNoWinner() throws Exception {
        FindWinner noWin = new FindWinner(board);
        org.junit.Assert.assertEquals(' ', noWin.WinnerIs());
    }
    public void testFindTopRowWin() throws Exception {
        board.reset();
        FindWinner xWins = new FindWinner(board);
        for (int i =1; i < 4; i++)
            board.PlaceMark(i,'X');
        org.junit.Assert.assertEquals('X', xWins.rowWin(0));
        org.junit.Assert.assertEquals('X', xWins.WinnerIs());
    }
    public void testFindMiddleRowWin() throws Exception {
        board.reset();
        FindWinner xWins = new FindWinner(board);
        for (int i =4; i < 7; i++)
            board.PlaceMark(i,'X');
        org.junit.Assert.assertEquals('X', xWins.rowWin(1));
        org.junit.Assert.assertEquals('X', xWins.WinnerIs());
    }
    public void testFindBottomRowWin() throws Exception {
        board.reset();
        FindWinner xWins = new FindWinner(board);
        for (int i =7; i < 10; i++)
            board.PlaceMark(i,'X');
        org.junit.Assert.assertEquals('X', xWins.rowWin(2));
        org.junit.Assert.assertEquals('X', xWins.WinnerIs());
    }
    public void testColWins() throws Exception {
        for (int c = 0; c <3; c++) {
            board.reset();
            FindWinner xWins = new FindWinner(board);
            for (int i =1+c; i < 10; i++)
                board.PlaceMark(i,'X');
            org.junit.Assert.assertEquals('X', xWins.colWin(c));
            org.junit.Assert.assertEquals('X', xWins.WinnerIs());
        }
    }
    public void testTLDiagWins() throws Exception {
        board.reset();
        FindWinner xWins = new FindWinner(board);
        for (int i=0; i < 3; i++) {
            board.PlaceMark(i,i,'X');
        }
        org.junit.Assert.assertEquals('X', xWins.tlDiagWin());
        org.junit.Assert.assertEquals('X', xWins.WinnerIs());
    }
    public void testTRDiagWins() throws Exception {
        board.reset();
        FindWinner xWins = new FindWinner(board);
        board.PlaceMark(3,'X');
        board.PlaceMark(5,'X');
        board.PlaceMark(7,'X');
        org.junit.Assert.assertEquals('X', xWins.trDiagWin());
        org.junit.Assert.assertEquals('X', xWins.WinnerIs());
    }

}