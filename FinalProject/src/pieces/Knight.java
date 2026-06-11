/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles knight movement and knight data
 */
package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends AbstractPiece {

    private static int numKnights = 0;

    /**
     * Default knight constructor Creates an empty knight
     */
    public Knight() {
        super(); // call parent default constructor
    }

    /**
     * Knight constructor
     *
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     */
    public Knight(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 3); // knight value is 3
    }

    /**
     * Gets valid knight moves
     *
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {

        ArrayList<Move> moves = new ArrayList<>();

        // all eight knight jumps
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

    /**
     * Checks one knight jump
     *
     * @param moves list to add to
     * @param pieces board state
     * @param currentRow starting row
     * @param currentCol starting col
     * @param dRow row offset
     * @param dCol col offset
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces,
            int currentRow, int currentCol, int dRow, int dCol) {

        int targetRow = currentRow + dRow;
        int targetCol = currentCol + dCol;

        // check board bounds
        if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {

            Piece piece = getPieceAt(targetRow, targetCol, pieces);

            // empty or enemy square
            if (piece == null || piece.isWhite() != this.isWhite()) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    /**
     * Gets number of knights created
     *
     * @return count
     */
    public static int getNumKnights() {
        return numKnights;
    }

    /**
     * Returns knight info as text
     *
     * @return string
     */
    public String toString() {
        return "Piece Type: Knight" + super.toString();
    }
}
