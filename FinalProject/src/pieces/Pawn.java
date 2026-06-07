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
public class Pawn extends AbstractPiece {

    private static int numPawns = 0;
    private boolean firstMove = true;
    private boolean enPassantEligible = false;
    
    public Pawn() {
    }
    

    public Pawn(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 1);
    }

    @Override
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        int direction;
        if (this.isWhite()) {
            direction = 1;
        } else {
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

    public static int getNumPawns() {
        return numPawns;
    }
    
    public boolean isEnPassantEligible() {
        return enPassantEligible;
    }
    
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    
    public void setEnPassantEligible(boolean enPassantEligible){
        this.enPassantEligible = enPassantEligible;
    }
    
    

    public String toString() {
        return ("Piece Type: Pawn"
                + super.toString());
    }
}
