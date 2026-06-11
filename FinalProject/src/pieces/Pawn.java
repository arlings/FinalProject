/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles pawn movement and pawn data
*/

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pawn extends AbstractPiece {

    private static int numPawns = 0;
    private boolean firstMove = true;
    private boolean enPassantEligible = false;

    /**
     * Default pawn constructor
     */
    public Pawn() {
    }

    /**
     * Pawn constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     */
    public Pawn(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 1); // pawn value is 1
    }

    /**
     * Gets valid pawn moves
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {

        ArrayList<Move> moves = new ArrayList<>();

        // white moves up, black moves down
        int direction = this.isWhite() ? 1 : -1;

        // forward one square
        searchDirection(moves, pieces, rowNum + direction, columnNum, direction);

        // forward two squares on first move
        if (firstMove) {
            int twoStepRow = rowNum + (2 * direction);

            if (isInsideBoard(twoStepRow, columnNum)
                    && getPieceAt(rowNum + direction, columnNum, pieces) == null
                    && getPieceAt(twoStepRow, columnNum, pieces) == null) {

                moves.add(new Move(twoStepRow, columnNum));
            }
        }

        // en passant left
        if (columnNum - 1 >= 0) {
            Piece leftPiece = pieces[rowNum][columnNum - 1];

            if (leftPiece instanceof Pawn && leftPiece.isWhite() != this.isWhite()) {
                if (((Pawn) leftPiece).isEnPassantEligible()) {
                    moves.add(new Move(rowNum + direction, columnNum - 1));
                }
            }
        }

        // en passant right
        if (columnNum + 1 < 8) {
            Piece rightPiece = pieces[rowNum][columnNum + 1];

            if (rightPiece instanceof Pawn && rightPiece.isWhite() != this.isWhite()) {
                if (((Pawn) rightPiece).isEnPassantEligible()) {
                    moves.add(new Move(rowNum + direction, columnNum + 1));
                }
            }
        }

        return moves;
    }

    /**
     * Handles forward and diagonal pawn movement
     * @param moves list to add to
     * @param pieces board state
     * @param targetRow row to check
     * @param targetCol col to check
     * @param direction move direction
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces,
                                int targetRow, int targetCol, int direction) {

        // forward move
        if (isInsideBoard(targetRow, targetCol)) {
            Piece pieceForward = getPieceAt(targetRow, targetCol, pieces);

            if (pieceForward == null) {
                moves.add(new Move(targetRow, targetCol));
            }
        }

        // diagonal captures
        int leftCol = columnNum - 1;
        int rightCol = columnNum + 1;
        int diagonalRow = rowNum + direction;

        // capture left
        if (isInsideBoard(diagonalRow, leftCol)) {
            Piece targetPiece = getPieceAt(diagonalRow, leftCol, pieces);

            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalRow, leftCol));
            }
        }

        // capture right
        if (isInsideBoard(diagonalRow, rightCol)) {
            Piece targetPiece = getPieceAt(diagonalRow, rightCol, pieces);

            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalRow, rightCol));
            }
        }
    }

    /**
     * Gets number of pawns created
     * @return count
     */
    public static int getNumPawns() {
        return numPawns;
    }

    /**
     * Checks if pawn is en passant eligible
     * @return true if eligible
     */
    public boolean isEnPassantEligible() {
        return enPassantEligible;
    }

    /**
     * Sets first move state
     * @param firstMove new state
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /**
     * Sets en passant eligibility
     * @param enPassantEligible new state
     */
    public void setEnPassantEligible(boolean enPassantEligible) {
        this.enPassantEligible = enPassantEligible;
    }

    /**
     * Returns pawn info as text
     * @return string
     */
    public String toString() {
        return "Piece Type: Pawn" + super.toString();
    }
}
