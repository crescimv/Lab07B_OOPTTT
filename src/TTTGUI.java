import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TTTGUI extends JFrame {
    private TTTTileButton[][] buttons;
    private JTextArea gameHistory;
    private TTTGame game;

    public TTTGUI(TTTGame game) {
        this.game = game;
        setTitle("TicTacToe");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new TTTTileButton[3][3];
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TTTTileButton(row, col);
                buttons[row][col].addActionListener(new MyActionListener());
                buttonPanel.add(buttons[row][col]);
            }
        }

        gameHistory = new JTextArea();
        gameHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameHistory);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TTTTileButton buttonClicked = (TTTTileButton) e.getSource();
            int row = buttonClicked.getRow();
            int col = buttonClicked.getCol();

            String result = game.makeMove(row, col);
            buttonClicked.setText(String.valueOf(game.getCurrentPlayer()));
            if (result != null) {
                gameHistory.append(result + "\n");
                updateGameHistory(game.getWinsX(), game.getWinsO());
                game.resetGame();
            }
        }
    }

    public void updateGameHistory(int winsX, int winsO) {
        gameHistory.append("X Wins: " + winsX + ", O Wins: " + winsO + "\n");
    }
}