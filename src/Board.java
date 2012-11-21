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
    private FieldChosen fieldChosen = null;
    void NotifyPlacement(FieldChosen fc){
        System.out.print("Notify in the house ");
        fieldChosen = fc;
        System.out.println(fieldChosen);
    }
    protected int Two2One(int x, int y) {
       return  3*(x) + (y) +1;
    }
    public boolean  Empty(int x, int y) {
        return Empty(Two2One(x,y));
    }
    public boolean  Empty(int x) {
        return (board[x-1] > '0' && board[x-1] <= '9');
    }
    public boolean PlaceMark(int x, int y, char mark) {
        int field = Two2One(x,y);
        System.out.println("Would notify");
        if (null == fieldChosen) {
            return PlaceMark(field, mark);
        }
        System.out.println("Success");
        fieldChosen.squarePressed(field, mark);
        return PlaceMark(field, mark);
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
