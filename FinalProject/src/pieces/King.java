/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author NeWan5443
 */
public class King extends AbstractPiece {

    private static int numKings;
    private boolean inCheck; //add logic later
    private boolean inCheckMate;
    private boolean hasMoved;
    

    public King() {
        this(0, 4, null, true, false, false, false);
    }

    public King(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, boolean inCheck, boolean inCheckMate, boolean hasMoved) {
        super(rowNum, columnNum, sprite, isWhite, 0);
        this.inCheck = inCheck;
        this.inCheckMate = inCheckMate;
        this.hasMoved = hasMoved;
    }

    @Override
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
        
        if (!this.hasMoved) {
            checkCastling(moves, pieces);
        }
        
        return moves;
    }
    
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
    
    @Override
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        if (!isInsideBoard(currentRow, currentCol)) {
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

    public static int getNumKings() {
        return numKings;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public boolean isInCheckMate() {
        inCheckMate = validMoves.isEmpty();
        return inCheckMate;
    }
    
    public boolean hasMoved(){
        return this.hasMoved;
    }
    
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    @Override
    public String toString() {
        return ("Piece Type: King"
                + super.toString());
    }

}