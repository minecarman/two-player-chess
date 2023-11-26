public class Pawn extends Piece {
    boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square newLocation = location.getBoard().getSquareAt(to);
        int rowDistance = newLocation.getRowDistance(location);

        if (this.location.isAtSameColumn(newLocation)){ /// checks if the target and existing location is at same column
            if (color == Chessboard.WHITE && rowDistance > 0 && rowDistance <= 2){
                if(rowDistance == 2){
                    if(initialLocation){

                        validMove = newLocation.isEmpty() && location.getBoard().getRowSquaresBetween(location, newLocation)[0].isEmpty();
                    }
                }

                else{
                    validMove = newLocation.isEmpty();
                }

            }

            else if(color ==Chessboard.BLACK && rowDistance < 0 && rowDistance >= -2){
                if(rowDistance == -2){
                    if (initialLocation)
                        validMove = newLocation.isEmpty() && location.getBoard().getRowSquaresBetween(newLocation, location)[0].isEmpty();
                }

                else
                    validMove = newLocation.isEmpty();
            }

        }

        else if (this.location.isNeighborColumn(newLocation)) {
            if(color == Chessboard.WHITE && rowDistance == 1)
                validMove = !newLocation.isEmpty() && newLocation.getPiece().getColor() == Chessboard.BLACK;


            else if (color == Chessboard.BLACK && rowDistance == -1)
                validMove = !newLocation.isEmpty() && newLocation.getPiece().getColor() == Chessboard.WHITE;

        }

        return validMove;
    }

    @Override
    public void move(String to) {
        Square newLocation=location.getBoard().getSquareAt(to);

        if (newLocation.isAtLastRow(color)){
            newLocation.putNewQueen(color);
        }
        else {
            this.initialLocation= false;
            newLocation.setPiece(this);
        }
        location.clear();
        location = newLocation;

    }

    @Override
    public String toString() {
        return color == Chessboard.WHITE ? "P" : "p";
    }
}