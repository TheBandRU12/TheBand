import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class DisplayTTT extends JDialog implements FieldChosen, Readable  {
    protected JPanel contentPane;
    protected JButton buttonOK;
    protected JButton a6Button;
    protected JButton a3Button;
    protected JButton a2Button;
    protected JButton a1Button;
    protected JButton a5Button;
    protected JButton a4Button;
    protected JButton a7Button;
    protected JButton a8Button;
    protected JButton a9Button;
    protected JTextField messageField;

    final char[] outBuffer = new char[1];
    CountDownLatch available = new CountDownLatch(1);

    /*
    protected JButton retrieveButton(int f) {

    }
    */
    @Override
    public void squarePressed(int f, char x) {
        retrieveButton(f).setText(String.valueOf(x));
    }

    protected JButton retrieveButton(int f) {
        switch (f) {
            case 1:
                return a1Button;
            case 2:
                return a2Button;
            case 3:
                return a3Button;
            case 4:
                return a4Button;
            case 5:
                return a5Button;
            case 6:
                return a6Button;
            case 7:
                return a7Button;
            case 8:
                return a8Button;
            case 9:
                return a9Button;
        }
        return null;
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
                copy.setText("X");
            }
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        // TODO: loop this:
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
                a6Button.setText("6");
                a3Button.setText("3");
                a2Button.setText("2");
                a1Button.setText("1");
                a5Button.setText("5");
                a4Button.setText("4");
                a7Button.setText("7");
                a8Button.setText("8");
                a9Button.setText("9");
                available.countDown();
            }
        });

    }

    private Board board;
    public void SetBoard(Board theBoard) {
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