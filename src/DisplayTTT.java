import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class DisplayTTT extends JDialog implements FieldChosen, Readable  {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton a6Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a1Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JTextField messageField;

    final char[] outBuffer = new char[1];
    CountDownLatch available = new CountDownLatch(1);

    @Override
    public void squarePressed(int f, char x) {
        switch (f) {
            case 1:
                a1Button.setLabel(String.valueOf(x));
                break;
            case 2:
                a2Button.setLabel(String.valueOf(x));
                break;
            case 3:
                a3Button.setLabel(String.valueOf(x));
                break;
            case 4:
                a4Button.setLabel(String.valueOf(x));
                break;
            case 5:
                a5Button.setLabel(String.valueOf(x));
                break;
            case 6:
                a6Button.setLabel(String.valueOf(x));
                break;
            case 7:
                a7Button.setLabel(String.valueOf(x));
                break;
            case 8:
                a8Button.setLabel(String.valueOf(x));
                break;
            case 9:
                a9Button.setLabel(String.valueOf(x));
                break;
        }
    }

    @Override
    public void message(String message) {
        messageField.setText(message);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        System.out.println("Waiting for input");
        try {
            available.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        available = new CountDownLatch(1);
        System.out.println("Returning: " + outBuffer[0]);

        cb.append(outBuffer[0]);
        cb.append("\n") ;
        return 1;
    }
    public DisplayTTT() {
        class ButtonPressed implements ActionListener {
            int button;
            JButton copy;
            ButtonPressed(int n, JButton b) {
                button = n;
                copy = b;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.Empty(button)) {
                    return;
                }
                board.PlaceMark(button,'X');

                outBuffer[0] = (char)('0' + button) ;
                System.out.println("You Chose: " + outBuffer[0]);
                available.countDown();
                copy.setLabel("X");
            }
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        a1Button.addActionListener(new ButtonPressed(1,a1Button));
        a2Button.addActionListener(new ButtonPressed(2,a2Button));
        a3Button.addActionListener(new ButtonPressed(3,a3Button));
        a4Button.addActionListener(new ButtonPressed(4,a4Button));
        a5Button.addActionListener(new ButtonPressed(5,a5Button));
        a6Button.addActionListener(new ButtonPressed(6,a6Button));
        a7Button.addActionListener(new ButtonPressed(7,a7Button));
        a8Button.addActionListener(new ButtonPressed(8,a8Button));
        a9Button.addActionListener(new ButtonPressed(9,a9Button));
        //buttonOK.addActionListener();
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outBuffer[0] = '0' ;
                System.out.println("You Chose: Reset");
                available.countDown();
                a6Button.setLabel("6");
                a3Button.setLabel("3");
                a2Button.setLabel("2");
                a1Button.setLabel("1");
                a5Button.setLabel("5");
                a4Button.setLabel("4");
                a7Button.setLabel("7");
                a8Button.setLabel("8");
                a9Button.setLabel("9");

            }
        });

    }

    private Board board;
    private void SetBoard(Board theBoard) {
        board = theBoard;
        board.NotifyPlacement(this);
    }

    public static void main(String[] args) {
        DisplayTTT dialog = new DisplayTTT();
        Board board = new Board();
        dialog.SetBoard(board);
        board.NotifyPlacement(dialog);

        TicTacToe ttt = new TicTacToe('X');
        ttt.SetBoard(board, dialog);

        dialog.pack();
        dialog.setModal(false);
        dialog.setVisible(true);
        ttt.Run(new Scanner(dialog));
        System.exit(0);
    }
}