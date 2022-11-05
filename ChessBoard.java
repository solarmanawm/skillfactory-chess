package practice.Chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                if (board[startLine][startColumn] instanceof King || board[startLine][startColumn] instanceof Rook) {
                    board[startLine][startColumn].check = false;
                }

                return true;
            } else return false;
        } else return false;
    }

    private boolean checkPiecesForExistence(int lineIndex, int rookIndex) {
        return board[lineIndex][rookIndex] != null && board[lineIndex][4] != null;
    }

    private boolean checkEmpty(int lineIndex, int[] indexes) {
        boolean areFieldsEmpty = true;

        for (int i = 0; i < indexes.length; i++) {
            areFieldsEmpty = areFieldsEmpty && board[lineIndex][1] == null;
        }

        return areFieldsEmpty;
    }

    private boolean doCastling(int lineIndex, int rookIndex) {
        int[] emptyIndexes = rookIndex == 0 ? new int[]{1, 2, 3} : new int[]{5, 6};
        if (
                board[lineIndex][rookIndex].getSymbol().equals("R")
                && board[lineIndex][4].getSymbol().equals("K")
                && checkEmpty(lineIndex, emptyIndexes)
        ) {
            int newKingTargetPosition = rookIndex == 0 ? 2 : 6;
            if (
                    board[lineIndex][rookIndex].getColor().equals(nowPlayer)
                    && board[lineIndex][4].getColor().equals(nowPlayer)
                    && board[lineIndex][rookIndex].check
                    && board[lineIndex][4].check
                    && !new King(nowPlayer).isUnderAttack(this, lineIndex, newKingTargetPosition)
            ) {
                board[lineIndex][4] = null;
                board[lineIndex][newKingTargetPosition] = new King(nowPlayer);
                board[lineIndex][newKingTargetPosition].check = false;
                board[lineIndex][rookIndex] = null;
                int newRookTargetPosition = rookIndex == 0 ? 3 : 5;
                board[lineIndex][newRookTargetPosition] = new Rook(nowPlayer);
                board[lineIndex][newRookTargetPosition].check = false;
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            } else return false;
        } else return false;
    }

    public boolean castling0() {
        int lineIndex = nowPlayer.equals("White") ? 0 : 7;
        boolean arePiecesExist = this.checkPiecesForExistence(lineIndex, 0);

        if (!arePiecesExist) {
            return false;
        }

        return doCastling(lineIndex, 0);
    }


    public boolean castling7() {
        int lineIndex = nowPlayer.equals("White") ? 0 : 7;
        boolean arePiecesExist = this.checkPiecesForExistence(lineIndex, 7);

        if (!arePiecesExist) {
            return false;
        }

        return doCastling(lineIndex, 7);
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
