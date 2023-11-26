import java.util.HashMap;
class Chessboard{

    public static final int BLACK = 1;
    public static final int WHITE = 0;

    Square[][] squares = new Square[8][8];
    private boolean whitePlaying = true;
    char [] column={'a','b','c','d','e','f','g','h'};
    HashMap<Character,Integer> columnKey = new HashMap<>();

    public Chessboard(){
        initialize();
        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4);
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);
    }

    public void printBoard(){
        String letters ="    A   B   C   D   E   F   G   H   \n";
        String line = "  ---------------------------------  ";
        System.out.println(letters+line);

        for (int i = 7; i >= 0; i--) {
            System.out.print(i+1+" | ");

            for (int j = 0; j <8 ; j++) {
                System.out.print(squares[i][j]);
                System.out.print(" | ");
            }
            System.out.println(i+1);
        }
        System.out.println(line+"\n"+letters);
    }

    public void initialize(){
        for (int i=0;i<8;i++){ // Creates 64 squares.
            for (int j = 0; j < 8; j++) {
                squares[i][j]= new Square(i+1,column[j],this);
            }

        }
        squares[0][0].setPiece(new Rook(0,squares[0][0]));  // Sets all pieces on board.
        squares[0][1].setPiece(new Knight(0,squares[0][1]));
        squares[0][2].setPiece(new Bishop(0,squares[0][2]));
        squares[0][3].setPiece(new Queen(0,squares[0][3]));
        squares[0][4].setPiece(new King(0,squares[0][4]));
        squares[0][5].setPiece(new Bishop(0,squares[0][5]));
        squares[0][6].setPiece(new Knight(0,squares[0][6]));
        squares[0][7].setPiece(new Rook(0,squares[0][7]));

        squares[7][0].setPiece(new Rook(1,squares[7][0]));
        squares[7][1].setPiece(new Knight(Chessboard.BLACK,squares[7][1] ));
        squares[7][2].setPiece(new Bishop(1,squares[7][2]));
        squares[7][3].setPiece(new Queen(1,squares[7][3]));
        squares[7][4].setPiece(new King(1,squares[7][4]));
        squares[7][5].setPiece(new Bishop(1,squares[7][5]));
        squares[7][6].setPiece(new Knight(1,squares[7][6]));
        squares[7][7].setPiece(new Rook(1,squares[7][7]));

        for (int i = 0; i <8 ; i++) {
            squares[6][i].setPiece(new Pawn(1,squares[6][i]));
            squares[1][i].setPiece(new Pawn(0,squares[1][i]));
        }
    }

    public boolean isGameEnded(){
        boolean whiteFound=false;
        boolean blackFound=false;

        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (squares[i][j].getPiece() != null ){
                    if(( squares[i][j].getPiece().getColor()==WHITE )) {
                        whiteFound= true;
                    }

                    if (squares[i][j].getPiece().getColor()==BLACK) {
                        blackFound= true;
                    }
                }
            }
        }
        return !(whiteFound&&blackFound);
    }

    public boolean isWhitePlaying(){
        return whitePlaying;
    }

    public Piece getPieceAt(String location){
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);
        return squares[row-1][columnKey.get(column)].getPiece();
    }

    public Square getSquareAt(String location){
        char column = location.charAt(0);
        String[] splitted = location.split("");
        int row = Integer.parseInt(splitted[1]);
        return squares[row-1][columnKey.get(column)];
    }

    public Square[] getRowSquaresBetween(Square loc1,Square loc2) {
        char col= loc1.getColumn();
        int upperLimit = loc2.getRow();
        int lowerLimit = loc1.getRow();
        if(lowerLimit > upperLimit){
            lowerLimit = loc2.getRow();
            upperLimit = loc1.getRow();
        }
        Square[] toReturn = new Square[upperLimit-lowerLimit-1];
        int j = 0;
        for (int i = lowerLimit; i < upperLimit-1; i++) {

            toReturn[j] = squares[i][columnKey.get(col)];
            j++;
        }
        return toReturn;
    }

    public Square[] getColSquaresBetween(Square loc1,Square loc2){
        int row= loc1.getRow();
        char upperCol=loc2.getColumn();
        char lowerCol=loc1.getColumn();
        if(columnKey.get(lowerCol) > columnKey.get(upperCol)){
            lowerCol=upperCol;
            upperCol=loc1.getColumn();
        }
        int upperLim=columnKey.get(upperCol);
        int lowerLim=columnKey.get(lowerCol);
        int size=upperLim-lowerLim;
        int j =0;
        Square[] toReturn =new Square[size];
        for (int i =lowerLim ; i <upperLim ; i++) {
            toReturn[j]=squares[row][i];
            j++;
        }

        return toReturn;
    }

    public Square[] getDiagSquaresBetween(Square loc1, Square loc2) {
        if(loc1.isAtSameDiagonal(loc2)){
            boolean topLeftToBottomRight=false;

            char upperCol=loc2.getColumn();
            char lowerCol=loc1.getColumn();
            int upperRow= (loc2.getRow());

            int lowerRow= loc1.getRow();
            if(upperRow<lowerRow){
                lowerRow=upperRow;
                upperRow=loc1.getRow();
                topLeftToBottomRight = true;
            }
            upperRow=upperRow-1;
            int upperLimCol=columnKey.get(upperCol);
            int lowerLimCol=columnKey.get(lowerCol);
            if (lowerLimCol>upperLimCol){
                int temp=lowerLimCol;
                lowerLimCol=upperLimCol;
                upperLimCol=temp;
                topLeftToBottomRight=!topLeftToBottomRight;
            }

            Square []toReturn= new Square[Math.abs(upperRow-lowerRow)];
            int index=0;

            if(topLeftToBottomRight) {
                while (lowerLimCol < upperLimCol && lowerRow < upperRow) {
                    lowerLimCol++;
                    upperRow--;
                    toReturn[index] = squares[upperRow][lowerLimCol];
                    index++;

                }
            }
            else {
                while (lowerLimCol < upperLimCol && lowerRow < upperRow) {
                    lowerLimCol++;
                    toReturn[index] = squares[lowerRow][lowerLimCol];
                    index++;
                    lowerRow++;
                }

            }

            return toReturn;
        }
        else return null;
    }

    public void nextPlayer() {
        whitePlaying = !whitePlaying;
    }
}