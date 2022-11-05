package practice.Chess;

public abstract class ChessPiece {
    protected String color;
    public boolean check = true;

    public ChessPiece(String _color) {
        this.color = _color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    protected boolean isNewPositionOnBoard(ChessBoard chessBoard, int line, int column) {
        if (line < 0 || column < 0) {
            return false;
        }

        int lines = chessBoard.board.length;
        int columns = chessBoard.board[0].length;

        return line < lines && column < columns;
    }
}