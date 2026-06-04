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

    public Pawn() {
    }

    public Pawn(int xPos, int yPos, BufferedImage sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }

    @Override
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        // Determine direction: White moves up the board rows (+1 or -1 depending on your board setup)
        // Assuming row + 1 is forward for White, row - 1 is forward for Black
        int direction = this.isWhite() ? 1 : -1;

        // Check one square directly ahead
        searchDirection(moves, pieces, xPos + direction, yPos, direction);

        return moves;
    }
    
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int targetX, int targetY, int direction) {
        if (isInsideBoard(targetX, targetY)) {
            Piece pieceForward = getPieceAt(targetX, targetY, pieces);
            if (pieceForward == null) {
                moves.add(new Move(targetX, targetY));
            }
        }

        int leftCol = yPos - 1;
        int rightCol = yPos + 1;
        int diagonalRow = xPos + direction; 

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

    @Override
    public String toString() {
        return ("Piece Type: Pawn"
                + super.toString());
    }
}
