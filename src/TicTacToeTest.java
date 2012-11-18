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

    @Test
    public void test_win_diagonal_from_top_left(){
        TestTTT.reset();
        TestTTT.count = 5;
        TestTTT.mark[0][0] = TestTTT.player;
        TestTTT.mark[1][1] = TestTTT.player;
        TestTTT.mark[2][2] = TestTTT.player;
        assert (TestTTT.win() == 2);

    }
    @Test
    public void test_win_diagonal_from_top_right(){
        TestTTT.reset();
        TestTTT.count = 5;
        TestTTT.mark[0][2] = TestTTT.player;
        TestTTT.mark[1][1] = TestTTT.player;
        TestTTT.mark[2][0] = TestTTT.player;

        assert (TestTTT.win() == 2);
    }
    @Test
    public void test_win_horizontal(){
        TestTTT.reset();
        TestTTT.count = 5;
        TestTTT.mark[1][0] = TestTTT.player;
        TestTTT.mark[1][1] = TestTTT.player;
        TestTTT.mark[1][2] = TestTTT.player;

        assert (TestTTT.win() == 2);
    }


    @Test
    public void test_GetABandplay_array_selections1(){
        TestTTT.reset();
        char[][] testArray = new char[3][3];
        testArray[0][0] = 'X';
        TestTTT.GetabAndPlay(1,TestTTT.player);
        assert (testArray[0][0] == TestTTT.mark[0][0]);
    }
    @Test
    public void test_GetABandplay_array_selections4(){
        char[][] testArray = new char[3][3];
        testArray[1][0] = 'X';
        TestTTT.GetabAndPlay(4,TestTTT.player);
        assert (testArray[1][0] == TestTTT.mark[1][0]);
    }
    @Test
    public void test_GetABandplay_array_selections9(){
        char[][] testArray = new char[3][3];
        testArray[2][2] = 'X';
        TestTTT.GetabAndPlay(9,TestTTT.player);
        assert (testArray[2][2] == TestTTT.mark[2][2]);
    }


}
