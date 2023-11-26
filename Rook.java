public class Rook extends Piece {

    public Rook(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String loc) {
        return canItMoveVertically(loc) || canItMoveHorizontally(loc);
    }



    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "R" : "r";
    }

}