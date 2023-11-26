public class King extends Piece {
    public King(int color,Square location) {
        super(color,location);
    }

    @Override
    public boolean canMove(String loc) {
        boolean validMove = false;
        Square to = location.getBoard().getSquareAt(loc);
        int colDistance = to.getColDistance(location);
        int rowDistance = to.getRowDistance(location);
        boolean validDistance = (colDistance <= 1) && (colDistance >= -1) && (rowDistance <= 1) && (rowDistance >= -1);

        if(color == Chessboard.WHITE && validDistance ){
            if(to.getPiece() == null || to.getPiece().getColor() == Chessboard.BLACK ) {
                validMove = true;
            }
        }

        else if (color == Chessboard.BLACK && validDistance) {
            if(to.getPiece() == null || to.getPiece().getColor() == Chessboard.WHITE ) {
                validMove = true;
            }
        }

        return validMove;
    }


    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "K" : "k";
    }

}