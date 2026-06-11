/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles rook movement and rook data
*/

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Rook extends AbstractPiece {

    private static int numRooks;
    private boolean hasMoved;

    /**
     * Default rook constructor
     * Creates a rook with preset values
     */
    public Rook() {
        this(0, 0, null, true, false); // call main constructor
    }

    /**
     * Rook constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     * @param hasMoved move state
     */
    public Rook(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, boolean hasMoved) {
        super(rowNum, columnNum, sprite, isWhite, 5); // rook value is 5
        this.hasMoved = hasMoved;
    }

    /**
     * Gets valid rook moves
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {

        ArrayList<Move> moves = new ArrayList<>();

        // rook moves in four straight directions
        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        return moves;
    }

    /**
     * Checks if rook has moved
     * @return true if moved
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Sets moved state
     * @param hasMoved new state
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Gets number of rooks created
     * @return count
     */
    public static int getNumRooks() {
        return numRooks;
    }

    /**
     * Returns rook info as text
     * @return string
     */
    public String toString() {
        return "Piece Type: Rook" + super.toString();
    }
}
