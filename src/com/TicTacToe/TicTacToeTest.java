package com.TicTacToe;

import com.TicTacToe.TicTacToe;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 13.11.2012
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeTest {
    @Test
    public void play_marks_correct_spot() throws Exception {
        TicTacToe TestTTT = new TicTacToe('X');
        char PlayTest = 'X';
        TestTTT.play(0,0,'X');


        assert(PlayTest == TestTTT.mark[0][0]);

    }
}
