import junit.framework.Assert;
import junit.framework.TestCase;

import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/22/12
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayTTTTest extends TestCase {
    public void testRead() throws Exception {
        DisplayTTT ttt = new DisplayTTT();
        char charSent ='1';
        ttt.outBuffer[0] = charSent;
        ttt.available.countDown();
        Scanner in = new Scanner(ttt);
        int val = in.nextInt();
        assertEquals(1,val);
    }
}
