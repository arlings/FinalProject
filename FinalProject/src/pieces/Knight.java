package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends AbstractPiece {

    private static int numKnights = 0;

    public Knight() {
        super();
    }

    public Knight(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite);
    }

    @Override
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        searchDirection(moves, pieces, rowNum, columnNum, 1, -2);
        searchDirection(moves, pieces, rowNum, columnNum, -1, -2);
        searchDirection(moves, pieces, rowNum, columnNum, 1, 2);
        searchDirection(moves, pieces, rowNum, columnNum, -1, 2);
        searchDirection(moves, pieces, rowNum, columnNum, 2, -1);
        searchDirection(moves, pieces, rowNum, columnNum, 2, 1);
        searchDirection(moves, pieces, rowNum, columnNum, -2, -1);
        searchDirection(moves, pieces, rowNum, columnNum, -2, 1);

        return moves;
    }

    @Override
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        int targetRow = currentRow + dRow;
        int targetCol = currentCol + dCol;

        if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {

            Piece piece = getPieceAt(targetRow, targetCol, pieces);

            if (piece == null) {
                moves.add(new Move(targetRow, targetCol));
            } else if (piece.isWhite() != this.isWhite()) { 
                moves.add(new Move(targetRow, targetCol));
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