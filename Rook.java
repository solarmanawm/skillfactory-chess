package practice.Chess;

public class Rook extends ChessPiece {
    public Rook(String _color) {
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

        return line == toLine || column == toColumn;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
