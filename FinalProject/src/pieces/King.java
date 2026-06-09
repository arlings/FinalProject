
package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class King extends AbstractPiece {

    private static int numKings;
    private boolean inCheck; //add logic later
    private boolean inCheckMate;
    private boolean hasMoved;
    

    /**
     * Default default constructor
     */
    public King() {
        this(0, 4, null, true, false, false, false);//calls the King constructor with the following paramaters
    }

    /**
     * King constructor
     * @param rowNum - row number
     * @param columnNum - column number
     * @param sprite - sprite
     * @param isWhite- is the piece is white
     * @param inCheck- if king is in check
     * @param inCheckMate- if king is in check mate
     * @param hasMoved - if king has moved
     */
    public King(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, boolean inCheck, boolean inCheckMate, boolean hasMoved) {
        super(rowNum, columnNum, sprite, isWhite, 0);//calls super class with the following paramaters
        this.inCheck = inCheck;
        this.inCheckMate = inCheckMate;
        this.hasMoved = hasMoved;
    }

    /**
     * get the valid moves
     * @param pieces- 2d array of pieces
     * @return - array list of valid moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();
        // Straight directions
        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);
        // Diagonal directions
        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);
        
        if (!this.hasMoved) {//for castling
            checkCastling(moves, pieces);
        }
        
        return moves;
    }
    
    /**
     * check for castling
     * @param moves - array list of moves
     * @param pieces - 2d array of pieces
     */
    private void checkCastling(ArrayList<Move> moves, Piece[][] pieces){
        Piece kingSidePiece = pieces[this.rowNum][7];
        if(kingSidePiece instanceof Rook && !((Rook) kingSidePiece).hasMoved()){
            if(pieces[this.rowNum][5] == null && pieces[this.rowNum][6] == null){
                moves.add(new Move(this.rowNum, 6));
            }
        }
        
        Piece queenSidePiece = pieces[this.rowNum][0];
        if (queenSidePiece instanceof Rook && !((Rook) queenSidePiece).hasMoved()) {
            if (pieces[this.rowNum][1] == null && pieces[this.rowNum][2] == null && pieces[this.rowNum][3] == null) {
                moves.add(new Move(this.rowNum, 2)); 
            }
        }
    }
    
    /**
     * search direction
     * @param moves - array list of moves
     * @param pieces - 2d array of pieces
     * @param currentRow - current row
     * @param currentCol - current col
     * @param dRow - delta row
     * @param dCol - delta col
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        if (!isInsideBoard(currentRow, currentCol)) {//if it isn't inside of the board
            return;
        }

        Piece piece = getPieceAt(currentRow, currentCol, pieces);

        if (piece == null) {
            moves.add(new Move(currentRow, currentCol));
        } else {
            // Capture enemy piece, but block further movement
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentRow, currentCol));
            }
        }
    }

    /**
     * get the number of kings
     * @return - number of kings
     */
    public static int getNumKings() {
        return numKings;
    }

    /**
     * get if king is in check
     * @return - true if in check false otherwise
     */
    public boolean isInCheck() {
        return inCheck;
    }

    /**
     * get if king is in check mate
     * @return - true if in check mate false otherwise
     */
    public boolean isInCheckMate() {
        inCheckMate = validMoves.isEmpty();
        return inCheckMate;
    }
    
    /**
     * check if king has moved
     * @return - true if king has moved false otherwise
     */
    public boolean hasMoved(){
        return this.hasMoved;
    }
    
    /**
     * set if king has moved
     * @param hasMoved - boolean if king has moved
     */
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    /**
     * return toString of king class
     * @return - toString
     */
    public String toString() {
        return ("Piece Type: King"
                + super.toString());//calls super toString class
    }

}