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
    int count;
    char player;      // human player er alltaf 1 player í version 0.01
    char computer;

    FieldChosen message;
    public void SetBoard(Board b, FieldChosen m) {
        board = b;
        message = m;
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


    // framkvæma einn leik
    void play(int a, int b, char marking) {
        if(board.Empty(a,b)) {
            board.PlaceMark(a, b , marking);
        }

        count++;

        //if(win(a,b,player) > 0); // ath hvort þessi leikur hafi skilað úrslitum
        //    computerplay();
    }

    void computerplay(){
        //TODO gera random generator sem skrifar computer Ã­ tÃ³man reit
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
            for(int i = 0; i<3; i++){   // athuga sigur í larettum linum
                if(board.GetMark(i,0) == board.GetMark(i,1)  && board.GetMark(i,0) == board.GetMark(i, 2))
                    victor = board.GetMark(i,2);
            }

            for(int i = 0; i<3; i++){   // athuga sigur í lodrettum linum
                if(board.GetMark(0,i) == board.GetMark(1,i)  && board.GetMark(0,i) == board.GetMark(2,i)) {
                    victor = board.GetMark(2, i);
                    message("victor = " + victor);
                }
            }

            //athuga sigur a ská frá hægra efra horni
            if(board.GetMark(0,0) == board.GetMark(1,1)  && board.GetMark(1,1) == board.GetMark(2,2))
                    victor = board.GetMark(1, 1);

            //athuga sigur a ská frá vinstra efra horni
            if(board.GetMark(0,2) == board.GetMark(1,1)  && board.GetMark(1,1) == board.GetMark(2,0))
                    victor = board.GetMark(1, 1);


            if(victor == this.player)      // human vinnur
                winner = 2;

            if(victor == this.computer)     // tölva vinnur
                winner = 3;


        }
        if(count >= 8)      // jafntefliu
            winner = 1;

        return winner;
    }

    private void message(String s) {
        System.out.println(s);
        if (null != message ) {
            message.message(s);
        }
    }

    void reset(){
        board = new Board();
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

    //Velja reit út frá völdu númeri
    public void GetabAndPlay(int answer, char playa)  {
        if(answer == 1)
            this.play(0,0,playa);
        if(answer == 2)
            this.play(0,1,playa);
        if(answer == 3)
            this.play(0,2,playa);
        if(answer == 4)
            this.play(1,0,playa);
        if(answer == 5)
            this.play(1,1,playa);
        if(answer == 6)
            this.play(1,2,playa);
        if(answer == 7)
            this.play(2,0,playa);
        if(answer == 8)
            this.play(2,1,playa);
        if(answer == 9)
            this.play(2,2,playa);

    }
    public void Run(Scanner in) {
        message("Velkomin i TicTackToe\n thu spilar "+ this.player + "\ncomputer = " + this.computer);
        this.printBoard();
        while(this.win() < 1) {
            System.out.println("Sladu inn numer reits sem thu vilt merkja med " + this.player);
            int answer = in.nextInt();
            System.out.println("Reitur valinn " + answer);
            if(answer == 0) this.reset();
            if(answer != 0)  {
                this.GetabAndPlay(answer, this.player);

                if(this.win() < 1)
                    this.computerplay();
            }


            // ef leik er lokid med sigri eða jaftnefli
            if(this.win() > 0){
                if(this.win() == 1)
                    message("Thetta er janftefli\n\n");
                if(this.win() == 2)
                    message("Til hamingju\n\n   Thu vannst :-)\n\n");
                if(this.win() == 3)
                    message("HEHEEH \n Eg RUSTADI THER ;-)\n\n");

                // byrja nyjan leik eda haetta
                System.out.println("Sladu inn 1 til ad halda afram\n 2 til ad haetta");
                answer = in.nextInt();
                if(answer == 1) {this.reset(); this.printBoard();}
                if(answer != 1) answer = 2;
            }
            //System.out.println("win = " + TTT.win() + " count = " + TTT.count);

        }
    }
    //*
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
     }             // */


}
