public class TTTGame {
    private char[][] board;
    private char currentPlayer;
    private int winsX;
    private int winsO;
    private int moveCount;

    public static void main(String[] args) {
        TTTGame game = new TTTGame();
        TTTGUI gui = new TTTGUI(game);
        gui.setVisible(true);
    }

    public TTTGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        winsX = 0;
        winsO = 0;
        moveCount = 0;
    }

    public String makeMove(int row, int col) {
        if (board[row][col] == '\0') {
            board[row][col] = currentPlayer;
            moveCount++;
            if (checkWin(row, col)) {
                if (currentPlayer == 'X') {
                    winsX++;
                    return "Player X wins!";
                } else {
                    winsO++;
                    return "Player O wins!";
                }
            } else if (moveCount == 9) {
                return "It's a tie!";
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        return null;
    }

    public boolean checkWin(int row, int col) {
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            return true;
        }
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            return true;
        }
        if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public void resetGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        moveCount = 0;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public int getWinsX() {
        return winsX;
    }

    public int getWinsO() {
        return winsO;
    }
}