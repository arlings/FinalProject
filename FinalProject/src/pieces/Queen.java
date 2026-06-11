/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles queen movement and queen data
*/

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Queen extends AbstractPiece {

    private static int numQueens = 0;

    /**
     * Default queen constructor
     * Creates an empty queen and updates count
     */
    public Queen() {
        super(); // call parent default constructor
        numQueens++; // track number of queens
    }

    /**
     * Queen constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     */
    public Queen(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 9); // queen value is 9
        numQueens++; // update count
    }

    /**
     * Gets valid queen moves
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {

        ArrayList<Move> moves = new ArrayList<>();

        // rook style moves
        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        // bishop style moves
        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

        return moves;
    }

    /**
     * Gets number of queens created
     * @return count
     */
    public static int getNumQueens() {
        return numQueens;
    }

    /**
     * Returns queen info as text
     * @return string
     */
    public String toString() {
        return "Piece Type: Queen" + super.toString();
    }
}
