
package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pawn extends AbstractPiece {

    private static int numPawns = 0;
    private boolean firstMove = true;
    private boolean enPassantEligible = false;
    
    /**
     * default Pawn constructor
     */
    public Pawn() {
    }
    

    /**
     * Pawn constructor
     * @param rowNum - row number
     * @param columnNum - column number
     * @param sprite - sprite
     * @param isWhite - if the piece is on the white team or not
     */
    public Pawn(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 1);//calls super class with the following constructors
    }

    /**
     * returns all the valid moves
     * @param pieces - 2d array of pieces
     * @return - array list of all possible moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        int direction;
        if (this.isWhite()) {//if on the white team
            direction = 1;
        } else {//on the black team
            direction = -1;
        }

        searchDirection(moves, pieces, rowNum + direction, columnNum, direction);
        if (firstMove) {
            int twoStepRow = rowNum + (2 * direction);
            if (isInsideBoard(twoStepRow, columnNum)
                    && getPieceAt(rowNum + direction, columnNum, pieces) == null
                    && getPieceAt(twoStepRow, columnNum, pieces) == null) {
                moves.add(new Move(twoStepRow, columnNum));
            }
        }
        
        if (columnNum - 1 >= 0) {
            Piece leftPiece = pieces[rowNum][columnNum - 1];
            if (leftPiece instanceof Pawn && leftPiece.isWhite() != this.isWhite()) {
                if (((Pawn) leftPiece).isEnPassantEligible()) {
                    moves.add(new Move(rowNum + direction, columnNum - 1));
                }
            }
        }

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
     * gets the direction
     * @param moves - array list of moves
     * @param pieces - 2d array of pieces
     * @param targetRow - target row
     * @param targetCol - target column
     * @param direction - direction
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int targetRow, int targetCol, int direction) {
        if (isInsideBoard(targetRow, targetCol)) {
            Piece pieceForward = getPieceAt(targetRow, targetCol, pieces);
            if (pieceForward == null) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
        int leftCol = columnNum - 1;
        int rightCol = columnNum + 1;
        int diagonalRow = rowNum + direction;
        if (isInsideBoard(diagonalRow, leftCol)) {
            Piece targetPiece = getPieceAt(diagonalRow, leftCol, pieces);
            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalRow, leftCol));
            }
        }
        if (isInsideBoard(diagonalRow, rightCol)) {
            Piece targetPiece = getPieceAt(diagonalRow, rightCol, pieces);
            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalRow, rightCol));
            }
        }
    }

    /**
     * get the number of pawns
     * @return - the number of pawns
     */
    public static int getNumPawns() {
        return numPawns;
    }
    
    /**
     * if enPassang is eligible
     * @return - true if eligible false otherwise
     */
    public boolean isEnPassantEligible() {
        return enPassantEligible;
    }
    
    /**
     * set first move
     * @param firstMove- the first move 
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    
    /**
     * set if enPassang is eligible
     * @param enPassantEligible - boolean of if enPassant is eligible
     */
    public void setEnPassantEligible(boolean enPassantEligible){
        this.enPassantEligible = enPassantEligible;
    }
    
    

    /**
     * returns the status of the current pawn
     * @return - the status
     */
    public String toString() {
        return ("Piece Type: Pawn"
                + super.toString());//calls the toString in the super class
    }


}
