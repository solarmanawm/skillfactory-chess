package practice.Chess;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
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

        int[][] jumps = {
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2},
                {-2, -1},
                {-2, 1},
                {-1, 2}
        };

        for (int i = 0; i < jumps.length; i++) {
            int newline = column + jumps[i][0];
            int newColumn = column + jumps[i][1];

            if (newline == toLine && newColumn == toColumn) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    protected boolean isValidPoint(int line, int column) {
        return false;
    }
}
