/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles bishop movement and bishop data
 */

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bishop extends AbstractPiece {

    private static int numBishop = 0;

    /**
     * Default bishop constructor
     * Creates an empty bishop and updates count
     */
    public Bishop() {
        super(); // call parent default constructor
        numBishop++; // track number of bishops
    }

    /**
     * Bishop constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     */
    public Bishop(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 3); // bishop value is 3
        numBishop++; // update count
    }

    /**
     * Gets all valid bishop moves
     * @param pieces board state
     * @return list of valid moves
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList<>();

        // bishop moves in four diagonal directions
        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

        return moves;
    }

    /**
     * Gets number of bishops created
     * @return bishop count
     */
    public static int getNumBishop() {
        return numBishop;
    }

    /**
     * Returns bishop info as text
     * @return string with bishop details
     */
    public String toString() {
        return "Piece Type: Bishop" + super.toString(); // add parent info
    }
}
