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
    char[][] mark;
    int count;
    char player;      // human player er alltaf 1 player í version 0.01
    char computer;

    TicTacToe(char playa) {
        mark = new char[3][3];
        for(int i = 0, k = 49; i < 3; i++)
            for( int j = 0; j < 3; j++, k++)
                mark[i][j] = (char)k;
        count = 0;
        player = playa;

        if(player == 'X')
            computer = 'O';
        else computer = 'X';
    }


    // framkvæma einn leik
    void play(int a, int b, char marking){
        if(mark[a][b] != 'O' && mark[a][b] != 'X'){
            this.mark[a][b] = marking;
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
                        if(mark[i][j] != 'X' && mark[i][j] != 'O'){
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
                if(this.mark[i][0] == this.mark[i][1]  && this.mark[i][0] == this.mark[i][2])
                    victor = this.mark[i][2];
            }

            for(int i = 0; i<3; i++){   // athuga sigur í lodrettum linum
                if(this.mark[0][i] == this.mark[1][i]  && this.mark[0][i] == this.mark[2][i])
                {victor = this.mark[2][i];
                    System.out.println("victor = " + victor);}
            }

            //athuga sigur a ská frá hægra efra horni
            if(this.mark[0][0] == this.mark[1][1]  && this.mark[1][1] == this.mark[2][2])
                victor = this.mark[1][1];

            //athuga sigur a ská frá vinstra efra horni
            if(this.mark[0][2] == this.mark[1][1]  && this.mark[1][1] == this.mark[2][0])
                victor = this.mark[1][1];


            if(victor == this.player)      // human vinnur
                winner = 2;

            if(victor == this.computer)     // tölva vinnur
                winner = 3;


        }
        if(count >= 8)      // jafntefliu
            winner = 1;

        return winner;
    }

    TicTacToe reset(){
        return new TicTacToe(this.player);
    }

    private void printBoard(){
        //System.out.println("printboard");
        String board = "";

        for(int i= 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                board += "[" + mark[i][j] + "]";//sb.append(mark[i][j]);
                if(j == 2)
                    board += "\n";//sb.append("\n");

                //System.out.println("i = " + i + " j = " + j + "board = " + board);
            }

        System.out.println(board);//sb.toString();
    }

    //Til ad nota medan forrit er enn i DOS
    private void GetabAndPlay(int answer, char playa)  {
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


    public static void main(String[] args){

        char playa;
        Scanner in = new Scanner(System.in);


        //System.out.println("args = " + args[0].charAt(0));

        if(args[0].charAt(0) == 'X' || args[0].charAt(0) == 'O')
            playa = args[0].charAt(0);
        else playa = 'X';

        //System.out.println("player = " + playa);
        TicTacToe TTT = new TicTacToe(playa);
        System.out.println("Velkomin i TicTackToe\n thu spilar "+ TTT.player + "\ncomputer = " + TTT.computer);
        TTT.printBoard();
        while(TTT.win() < 1) {
            System.out.println("Sladu inn numer reitar sem thu vilt merkja med " + TTT.player);
            int answer = in.nextInt();
            if(answer == 0) TTT = TTT.reset();
            if(answer != 0)  {
                TTT.GetabAndPlay(answer, TTT.player);

                if(TTT.win() < 1)
                    TTT.computerplay();
            }


            // ef leik er lokid med sigri eða jaftnefli
            if(TTT.win() > 0){
                if(TTT.win() == 1)
                    System.out.println("Vid hofum janftefli\n\n");
                if(TTT.win() == 2)
                    System.out.println("Til hamingju\n\n   Thu vannst :-)\n\n");
                if(TTT.win() == 3)
                    System.out.println("HEHEEH \n Eg RUSTADI THER ;-)\n\n");

                // byrja nyan leik eda haetta
                System.out.println("Sladu inn 1 til ad halda afram\n 2 til ad haetta");
                answer = in.nextInt();
                if(answer == 1) {TTT = TTT.reset(); TTT.printBoard();}
                if(answer != 1) answer = 2;
            }
            //System.out.println("win = " + TTT.win() + " count = " + TTT.count);

        }



    }
}
