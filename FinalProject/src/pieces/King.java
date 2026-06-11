/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles king movement and king data
*/

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class King extends AbstractPiece {

    private static int numKings;
    private boolean inCheck;
    private boolean inCheckMate;
    private boolean hasMoved;

    /**
     * Default constructor
     * Creates a king with preset values
     */
    public King() {
        this(0, 4, null, true, false, false, false); // call main constructor
    }

    /**
     * King constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     * @param inCheck check state
     * @param inCheckMate checkmate state
     * @param hasMoved move state
     */
    public King(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite,
                boolean inCheck, boolean inCheckMate, boolean hasMoved) {

        super(rowNum, columnNum, sprite, isWhite, 0); // king has no point value
        this.inCheck = inCheck;
        this.inCheckMate = inCheckMate;
        this.hasMoved = hasMoved;
    }

    /**
     * Gets valid king moves
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {

        ArrayList<Move> moves = new ArrayList<>();

        // straight moves
        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        // diagonal moves
        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

        // castling if king has not moved
        if (!this.hasMoved) {
            checkCastling(moves, pieces);
        }

        return moves;
    }

    /**
     * Checks castling options
     * @param moves list to add moves to
     * @param pieces board state
     */
    private void checkCastling(ArrayList<Move> moves, Piece[][] pieces) {

        // king side rook at column 7
        Piece kingSidePiece = pieces[rowNum][7];
        if (kingSidePiece instanceof Rook && !((Rook) kingSidePiece).hasMoved()) {

            // squares between must be empty
            if (pieces[rowNum][5] == null && pieces[rowNum][6] == null) {
                moves.add(new Move(rowNum, 6));
            }
        }

        // queen side rook at column 0
        Piece queenSidePiece = pieces[rowNum][0];
        if (queenSidePiece instanceof Rook && !((Rook) queenSidePiece).hasMoved()) {

            // squares between must be empty
            if (pieces[rowNum][1] == null &&
                pieces[rowNum][2] == null &&
                pieces[rowNum][3] == null) {

                moves.add(new Move(rowNum, 2));
            }
        }
    }

    /**
     * Searches one square in a direction
     * @param moves list to add to
     * @param pieces board state
     * @param currentRow target row
     * @param currentCol target col
     * @param dRow row step
     * @param dCol col step
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces,
                                int currentRow, int currentCol, int dRow, int dCol) {

        // stop if outside board
        if (!isInsideBoard(currentRow, currentCol)) {
            return;
        }

        Piece piece = getPieceAt(currentRow, currentCol, pieces);

        if (piece == null) {
            moves.add(new Move(currentRow, currentCol)); // empty square
        } else if (piece.isWhite() != this.isWhite()) {
            moves.add(new Move(currentRow, currentCol)); // capture enemy
        }
    }

    /**
     * Gets number of kings created
     * @return count
     */
    public static int getNumKings() {
        return numKings;
    }

    /**
     * Checks if king is in check
     * @return true if in check
     */
    public boolean isInCheck() {
        return inCheck;
    }

    /**
     * Checks if king is in checkmate
     * @return true if in checkmate
     */
    public boolean isInCheckMate() {
        inCheckMate = validMoves.isEmpty(); // simple check
        return inCheckMate;
    }

    /**
     * Checks if king has moved
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
     * Returns king info as text
     * @return string
     */
    public String toString() {
        return "Piece Type: King" + super.toString();
    }
}
