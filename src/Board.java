/**
 * Created with IntelliJ IDEA.
 * User: Ella
 * Date: 11/14/12
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    protected
        char board[];
    public Board() {
        board = new char[9];
        for (int i=0; i<9; i++) {
            board[i] = (char)('1' + i);
        }
    }
    protected int Two2One(int x, int y) {
       return  (x-1) + 3*(y-1) +1;
    }
    public boolean  Empty(int x, int y) {
        return Empty(Two2One(x,y));
    }
    public boolean  Empty(int x) {
        return (board[x-1] > '0' && board[x-1] <= '9');
    }
    public boolean PlaceMark(int x, int y, char mark) {
        return PlaceMark(Two2One(x,y),mark);
    }
    public boolean PlaceMark(int x, char mark) {
        if (Empty(x)) {
            board[x-1] = mark;
            return true;
        }
        return false;

    }
    public char GetMark(int x) {
        return board[x-1];
    }
    public char GetMark(int x, int y) {
        return board[Two2One(x,y)-1];
    }
}
