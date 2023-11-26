import java.util.HashMap;
public class Square {
    private final String location ;
    private Piece piece;
    private final Chessboard board;
    char[] column = {'a','b','c','d','e','f','g','h'};
    HashMap<Character,Integer> columnKey = new HashMap<>();

    public Square(String location,Chessboard board){
        this.location=location;
        this.board=board;

        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4);
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);
    }

    public Square(int row,char column,Chessboard board){
        this.location=column+""+row;
        this.board=board;
        columnKey.put('a',0);
        columnKey.put('b',1);
        columnKey.put('c',2);
        columnKey.put('d',3);
        columnKey.put('e',4);
        columnKey.put('f',5);
        columnKey.put('g',6);
        columnKey.put('h',7);
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public int getRow(){
        String[] splitting = this.location.split("");
        return Integer.parseInt(splitting[1]);
    }
    public char getColumn(){
        return location.charAt(0);
    }

    public boolean isAtLastRow(int color){
        if (color ==Chessboard.WHITE  && getRow() == 8){
            return true;
        }
        else return Chessboard.BLACK == color && getRow() == 1;
    }

    public boolean isAtSameDiagonal(Square targetLocation) {
        return Math.abs(this.getRow() - targetLocation.getRow()) == Math.abs(columnKey.get(this.getColumn()) - columnKey.get(targetLocation.getColumn()));
    }

    public int getRowDistance(Square location){
        return this.getRow() - location.getRow();
    }

    public int getColDistance(Square location) {
        return columnKey.get(this.getColumn())- columnKey.get(location.getColumn());
    }

    public boolean isAtSameColumn(Square s){
        return this.getColumn() == s.getColumn();
    }

    public boolean isAtSameRow(Square s) {
        return this.getRow() == s.getRow();
    }

    public boolean isNeighborColumn(Square target){
        int col = columnKey.get(this.getColumn());
        int targetCol = columnKey.get(target.getColumn());
        return col - 1 == targetCol || col + 1 == targetCol;
    }

    public boolean isEmpty(){
        return piece == null || piece.toString().equals(" ");

    }

    public String toString(){
        if (isEmpty()){
            return " ";
        }
        else return piece.toString();
    }

    public Chessboard getBoard() {
        return board;
    }

    public void putNewQueen(int color) {
        piece = new Queen(color,this);

    }

    public void clear() {
        piece=null;
    }


}