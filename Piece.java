public abstract class Piece {
    int color;
    Square location;

    public Piece(int color, Square location){
        this.color = color;
        this.location = location;
    }

    public int getColor(){
        return color;
    }

    public void move(String to){
        Square newLocation = location.getBoard().getSquareAt(to);
        newLocation.setPiece(this);
        location.clear();
        location = newLocation;
    }

    public abstract boolean canMove(String loc);

    public boolean canItMoveDiagonally(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);


        if (this.location.isAtSameDiagonal(targetLocation)){
            int arrayLen=location.getBoard().getDiagSquaresBetween(location,targetLocation).length;

            if (color == Chessboard.WHITE && (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == Chessboard.BLACK)  ) {
                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getDiagSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove=false;
                        break;
                    }
                }
            }
            else if (color == Chessboard.BLACK && (targetLocation.isEmpty() || targetLocation.getPiece().getColor() == Chessboard.WHITE)  ) {
                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getDiagSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove=false;
                        break;
                    }
                }
            }
        }

        return validMove;
    }

    public boolean canItMoveHorizontally(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);

        if (this.location.isAtSameRow(targetLocation)){
            if (color == Chessboard.WHITE && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.BLACK) ){
                validMove = isValidMove(targetLocation);

            }
            else if (color == Chessboard.BLACK && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.WHITE) ){
                validMove = isValidMove(targetLocation);
            }
        }
        return validMove;
    }

    private boolean isValidMove(Square targetLocation) {
        boolean validMove;
        int arrLength=location.getBoard().getColSquaresBetween(location,targetLocation).length;
        validMove=true;
        for (int i = 0; i <arrLength ; i++) {
            if (!location.getBoard().getColSquaresBetween(location, targetLocation)[i].isEmpty()){
                validMove = false;
                break;
            }
        }
        return validMove;
    }

    public boolean canItMoveVertically(String to){
        boolean validMove =false;
        Square targetLocation=location.getBoard().getSquareAt(to);

        if (this.location.isAtSameColumn(targetLocation)){
            int arrayLen= location.getBoard().getRowSquaresBetween(location, targetLocation).length;

            if (color == Chessboard.WHITE && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.BLACK)  ) {
                validMove =true;

                for (int i = 0; i < arrayLen; i++) {
                    if ( !(location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty() )){
                        validMove = false;
                        break;
                    }
                }
            }
            else if (color==Chessboard.BLACK && (targetLocation.isEmpty()|| targetLocation.getPiece().getColor() == Chessboard.WHITE) ) {

                validMove =true;
                for (int i = 0; i < arrayLen; i++) {
                    if (!location.getBoard().getRowSquaresBetween(location, targetLocation)[i].isEmpty()){
                        validMove = false;
                        break;
                    }
                }
            }

        }
        return validMove;
    }

    public String toString(){
        return " ";
    }
}