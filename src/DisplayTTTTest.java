import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/22/12
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayTTTTest {
    @Test
    public void testRead() throws Exception {
        DisplayTTT ttt = new DisplayTTT();
        char charSent ='1';
        ttt.outBuffer[0] = charSent;
        ttt.available.countDown();
        Scanner in = new Scanner(ttt);
        int val = in.nextInt();
        org.junit.Assert.assertEquals(1, val);
    }
    @Test
    public void testSquaredPressed() throws  Exception {
        DisplayTTT board = new DisplayTTT();

        board.squarePressed(1, 'X');
        org.junit.Assert.assertEquals("X", board.a1Button.getText());

        board.squarePressed(3, 'A');
        org.junit.Assert.assertEquals("A", board.a3Button.getText());

        board.squarePressed(2, 'F');
        org.junit.Assert.assertEquals("F", board.a2Button.getText());

        board.squarePressed(4, 'D');
        org.junit.Assert.assertEquals("D", board.a4Button.getText());

        board.squarePressed(5, 'E');
        org.junit.Assert.assertEquals("E", board.a5Button.getText());

        board.squarePressed(6, 'M');
        org.junit.Assert.assertEquals("M", board.a6Button.getText());

        board.squarePressed(7, 'a');
        org.junit.Assert.assertEquals("a", board.a7Button.getText());

        board.squarePressed(8, 'L');
        org.junit.Assert.assertEquals("L", board.a8Button.getText());

        board.squarePressed(9, 'I');
        org.junit.Assert.assertEquals("I", board.a9Button.getText());
    }
    @Test
    public void testPressingResetButton() throws Exception {
        DisplayTTT display = new DisplayTTT();
        Board b = new Board();
        display.SetBoard(b);
        display.a4Button.doClick();
        org.junit.Assert.assertEquals("X", display.a4Button.getText());
        for (int i= 1; i<10; i++) {
            // fjordi takkinn er med Xi i sleppa honum
            if ( i == 4 )
                continue;
            org.junit.Assert.assertEquals((char)('0' +i), b.GetMark(i));
        }
        // remember board starts at 0; marks at 1. Below is actually square 4
        b.board[3] = 'O';
        display.a4Button.doClick();
        org.junit.Assert.assertEquals("X", display.a4Button.getText());
        char m = b.GetMark(4);
        org.junit.Assert.assertEquals('O', m);
    }
    @Test
    public  void testRetrieveButton() {
        DisplayTTT board = new DisplayTTT();
        org.junit.Assert.assertEquals( board.a1Button, board.retrieveButton(1));
        org.junit.Assert.assertEquals( null, board.retrieveButton(10));

    }
    @Test
    public void testReset() throws  Exception {
        DisplayTTT board = new DisplayTTT();

        for (int i= 1; i<10; i++) {
            board.squarePressed(i, 'X');
        }
        org.junit.Assert.assertEquals("X", board.a2Button.getText());
        board.buttonOK.doClick();
        org.junit.Assert.assertEquals("2", board.a2Button.getText());
    }
    @Test
    public void testMessage() throws  Exception {
        DisplayTTT display = new DisplayTTT();
        String message = new String("TheMessageIs");
        display.message(message);
        org.junit.Assert.assertEquals(message, display.messageField.getText());
    }
}