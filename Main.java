import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Chessboard board = new Chessboard();

        board.printBoard();
        while (!board.isGameEnded()){
            System.out.println("It's " + (board.isWhitePlaying() ? "White": "Black")+ "'s turn");
            Piece piece;
            String to;
            String from;

            do {
                System.out.println("Enter locations of the piece: ");
                from = reader.next().strip().toLowerCase();
                piece = board.getPieceAt(from);

            }while (piece == null || piece.getColor() != (board.isWhitePlaying() ? Chessboard.WHITE : Chessboard.BLACK));

            do{
                System.out.println("Enter the new location for the piece: ");
                to = reader.next().strip().toLowerCase();

            }while (!piece.canMove(to));

            piece.move(to);
            board.nextPlayer();
            board.printBoard();


        }
        System.out.println((!board.isWhitePlaying()? "Player 1": "Player 2")+" wins");
        reader.close();
    }
}