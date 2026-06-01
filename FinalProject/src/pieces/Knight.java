package pieces;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Knight extends AbstractPiece {

    private static int numKnights = 0;

    public Knight() {
        super();
    }

    public Knight(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }

    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        searchDirection(moves, pieces, xPos, yPos, 1, -2);
        searchDirection(moves, pieces, xPos, yPos, -1, -2);
        searchDirection(moves, pieces, xPos, yPos, 1, 2);
        searchDirection(moves, pieces, xPos, yPos, -1, 2);
        searchDirection(moves, pieces, xPos, yPos, 2, -1);
        searchDirection(moves, pieces, xPos, yPos, 2, 1);
        searchDirection(moves, pieces, xPos, yPos, -2, -1);
        searchDirection(moves, pieces, xPos, yPos, -2, 1);

        return moves;
    }

    @Override
    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy) {
        int targetX = currentX + dx;
        int targetY = currentY + dy;

        if (targetX >= 0 && targetX < 8 && targetY >= 0 && targetY < 8) {

            Piece piece = getPieceAt(targetX, targetY, pieces);

            if (piece == null) {
                moves.add(new Move(targetX, targetY));
            } else if (piece.isWhite() != this.isWhite) {
                moves.add(new Move(targetX, targetY));
            }
        }
    }

    public static int getNumKnights() {
        return numKnights;
    }

    @Override
    public String toString() {
        return ("Piece Type: Knight"
                + super.toString());
    }
}
