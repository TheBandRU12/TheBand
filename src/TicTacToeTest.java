import org.junit.Test;

/**
 * Tests for TicTacToe.java
 */


public class TicTacToeTest {

    TicTacToe TestTTT = new TicTacToe('X');
    /**
     * profar hvort play() merkir rettan stad i mark[][]
     */
    @Test
    public void play_not_overwrites() {
        TestTTT.reset();
        TestTTT.play(2,2,TestTTT.player);
        TestTTT.play(2,2,'O');
        assert (TestTTT.mark[2][2] == TestTTT.player);
    }
    @Test
    public void play_marks_correct_spot() throws Exception {
        TestTTT.reset();
        char PlayTest = 'X';
        TestTTT.play(0,0,'X');
        assert(PlayTest == TestTTT.mark[0][0]);
    }
    @Test
    public void computer_play_makes_move() {
        TestTTT.reset();
        char[][] compare = new char[2][2];
        TestTTT.play(0,0,TestTTT.player);

        for(int i = 0; i<2; i++)
            for(int j = 0; j<2; j++)
                compare[i][j] = TestTTT.mark[i][j];


        TestTTT.computerplay();
        assert (compare != TestTTT.mark);

    }

}
