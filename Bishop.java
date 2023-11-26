public class Bishop extends Piece{
    public Bishop(int color, Square location) {
        super(color, location);
    }

    public boolean canMove(String loc){
        return canItMoveDiagonally(loc);
    }


    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "B" : "b";
    }

}