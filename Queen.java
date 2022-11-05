package practice.Chess;

public class Queen extends ChessPiece {
    public Queen(String _color) {
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

        return dX == dY || line == toLine || column == toColumn;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
