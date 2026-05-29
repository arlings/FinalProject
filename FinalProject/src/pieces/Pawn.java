/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author NeWan5443
 */
public class Pawn extends AbstractPiece {

    private static int numPawns = 0;

    public Pawn() {
    }

    public Pawn(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }

    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> moves = new ArrayList();
        
        if(this.isWhite){
            searchDirection(moves, pieces, xPos, yPos + 1, 1);
        }else{
            searchDirection(moves, pieces, xPos, yPos + 1, -1);
        }
        
        return moves;
    }

    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int forwardRow) {
        if (!isInsideBoard(currentX, currentY)) {
            return;
        }
        
        Piece pieceForward = getPieceAt(currentX, currentY, pieces);
        if (pieceForward == null) {
            moves.add(new Move(currentX, currentY));
        }
        
        if(this.isWhite){
            forwardRow = 1;
        }else{
            forwardRow = -1;
        }
        
        int leftCol = -1;
        int rightCol = 1;
        
        Move diagonalLeft = new Move(this.getXPos() + forwardRow, this.getYPos() + leftCol);
        Move diagonalRight = new Move(this.getXPos() + forwardRow, this.getYPos() + rightCol);
        
        if (isInsideBoard(diagonalLeft.getXPos(), diagonalLeft.getYPos())) {
            Piece targetPiece = getPieceAt(diagonalLeft.getXPos(), diagonalLeft.getYPos(), pieces);
            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalLeft.getXPos(), diagonalLeft.getYPos()));
            }
        }

        if (isInsideBoard(diagonalRight.getXPos(), diagonalRight.getYPos())) {
            Piece targetPiece = getPieceAt(diagonalRight.getXPos(), diagonalRight.getYPos(), pieces);
            if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                moves.add(new Move(diagonalRight.getXPos(), diagonalRight.getYPos()));
            }
        }
    }

    public static int getNumPawns() {
        return numPawns;
    }

    @Override
    public String toString() {
        return ("Piece Type: Pawn"
                + super.toString());
    }
}
