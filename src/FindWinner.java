/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/25/12
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindWinner {
    protected Board board;
    public FindWinner(Board board) {
        this.board = board;
    }
    public char WinnerIs() {
        for (int i= 0; i < 3; i++) {
            char win = rowWin(i);
            if (win != ' ') {
                return win;
            }
            win = colWin(i);
            if (win != ' ') {
                return win;
            }
        }
        char win = tlDiagWin();
        if (win != ' ')
            return  win;
        return trDiagWin();
    }

    protected char rowWin(int row) {
        int start = 1 + row * 3;
        char f=board.GetMark(start);
        for (int i =start +1; i < start +3; i++) {
            if (f != board.GetMark(i)) {
                return ' ';
            }
        }
        return f;
    }
    protected char colWin(int col) {
        int start = col + 1;
        char f=board.GetMark(start);
        for (int i =start +3; i < start +9; i+=3) {
            if (f != board.GetMark(i)) {
                return ' ';
            }
        }
        return f;
    }
    protected char tlDiagWin() {
        char f=board.GetMark(1);
        for (int i =1; i < 3; i++) {
            if (f != board.GetMark(i,i)) {
                return ' ';
            }
        }
        return f;
    }
    protected char trDiagWin() {
        final char f=board.GetMark(3);
        if (f != board.GetMark(5) || f != board.GetMark(7))
            return ' ';
        return f;
    }
}
