/**
* HUGB TheBand haust 2012
* Date: 11.11.2012
* dependencies: java.util.scanner
*
* Til að þjappa: javac TicTacToe.java
* Til að keyra: java TicTacToe X ( eða O )
*/

import java.util.Scanner;

public class TicTacToe {
    // frumstilla breytur
    //char[][] mark;
    Board board;
    FieldChosen message;
    int count;
    char player;      // human player er alltaf 1 player í version 0.01
    char computer;

    public void SetBoard(Board b, FieldChosen f) {
        board = b;
        message=f;
    }

    TicTacToe(char playa) {
        board = new Board();
        message = null;
        count = 0;
        player = playa;

        if(player == 'X')
            computer = 'O';
        else computer = 'X';
    }
    protected void messageUser(String m) {
        if (message != null)
        {
            message.message(m);
        }
        System.out.println(m);
    }


    // framkvæma einn leik
    void play(int a, int b, char marking){
        if(board.Empty(a,b)){
            board.PlaceMark(a, b , marking);

        }

        count++;

        //if(win(a,b,player) > 0); // ath hvort þessi leikur hafi skilað úrslitum
        //    computerplay();
    }

    void computerplay(){
             //TODO gera random generator sem skrifar computer í tóman reit
        int a = 0, b = 0;

        ut:
        for(int i = 0; i < 3; i++)
            ytri:
                    for(int j = 0; j<3; j++)
                        if(board.Empty(i,j)){
                            play(i,j,computer);
                            System.out.println("play computer");
                            break ut;
                        }

        this.printBoard();

        //if(win(a,b,'O')>0)

    }

    // skilar 0 ef enginn sigurvergari,
    // 2 fyrir human, 3 fyrir tolvu og 1 fyrir jafntefli
    Integer win() {
        int winner = 0;
        char victor = '-';
        if(count > 4){
            FindWinner win = new FindWinner(board);
            char winMark = win.WinnerIs();
            if (player == winMark)
                winner =2;
            else if (computer == winMark)
                winner = 3;
        }
        if(count > 8)      // jafntefli
            winner = 1;

        return winner;
    }

    void reset(){
        board.reset();
        this.count = 0;
    }

    void printBoard(){
        //System.out.println("printboard");
        String boardString = "";

        for(int i= 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                boardString += "[" + board.GetMark(i,j) + "]";//sb.append(mark[i][j]);
                if(j == 2)
                    boardString += "\n";//sb.append("\n");

                //System.out.println("i = " + i + " j = " + j + "board = " + board);
            }

        System.out.println(boardString);//sb.toString();
    }


    public void Run(Scanner in) {
        messageUser("Velkomin i TicTackToe\n thu spilar "+ this.player + "\ncomputer = " + this.computer);
        this.printBoard();
        while(this.win() < 1) {
            System.out.println("Sladu inn numer reits sem thu vilt merkja med " + this.player);
            int answer = in.nextInt();
            if(answer == 0) this.reset();
            if(answer != 0)  {
                if (!board.Empty(answer)) {
                    board.PlaceMark(answer,player);
                    count ++;
                }

                if(this.win() < 1)
                    this.computerplay();
            }


            // ef leik er lokid med sigri eða jaftnefli
            if(this.win() > 0){
                if(this.win() == 1)
                    messageUser("Thetta er janftefli\n\n");
                if(this.win() == 2)
                    messageUser("Til hamingju\n\n   Thu vannst :-)\n\n");
                if(this.win() == 3)
                    messageUser("HEHEEH \n Eg RUSTADI THER ;-)\n\n");

                // byrja nyjan leik eda haetta
                System.out.println("Sladu inn 1 til ad halda afram\n 2 til ad haetta");
                answer = in.nextInt();
                if(answer == 1) {this.reset(); this.printBoard();}
                if(answer != 1) answer = 2;
            }
            //System.out.println("win = " + TTT.win() + " count = " + TTT.count);

        }
    }

    public static void main(String[] args){

        char playa;
        Scanner in = new Scanner(System.in);

        //System.out.println("args = " + args[0].charAt(0));

        //if(args[0].charAt(0) == 'X' || args[0].charAt(0) == 'O')
        //    playa = args[0].charAt(0);
        //else
        playa = 'X';

        //System.out.println("player = " + playa);
        TicTacToe TTT = new TicTacToe(playa);

        TTT.Run(in);
     }


}
