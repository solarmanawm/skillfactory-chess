package practice.Chess;

public class King extends ChessPiece {
    public King(String _color) {
        super(_color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!this.isNewPositionOnBoard(chessBoard, toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int dX = Math.abs(toLine - line);
        int dY = Math.abs(toColumn - column);

        if (dX > 1 || dY > 1) {
            return false;
        }

        return dX == dY || line == toLine || column == toColumn;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (!this.isNewPositionOnBoard(board, line, column)) {
            return false;
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board.board[i][j] != null) {
                    if (!board.board[i][j].getColor().equals(color) && board.board[i][j].canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
