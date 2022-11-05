package practice.Chess;

public class Pawn extends ChessPiece {
    public Pawn(String _color) {
        super(_color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isWhite = this.color.equals("White");
        boolean isBlack = this.color.equals("Black");
        boolean isWhiteFirstMove = isWhite && line == 1;
        boolean isBlackFirstMove = isBlack && line == 6;

        return (isWhiteFirstMove && (toLine == line + 2 || toLine == line + 1)
                || isBlackFirstMove && (toLine == line - 2 || toLine == line - 1)
                || isWhite && toLine == line + 1
                || isBlack && toLine == line - 1) && toColumn == column;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
