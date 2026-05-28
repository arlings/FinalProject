/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author NeWan5443
 */

public class Queen extends AbstractPiece {
    
    
    public Queen() {
        super();
    }

    public Queen(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }

    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy) {
        if (!isInsideBoard(currentX, currentY)) {
            return;
        }

        Piece piece = getPieceAt(currentX, currentY, pieces);

        if (piece == null) {
            moves.add(new Move(currentX, currentY));
            searchDirection(moves, pieces, currentX + dx, currentY + dy, dx, dy);
        } else {
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentX, currentY));
            }
        }
    }

    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> moves = new ArrayList();

        searchDirection(moves, pieces, xPos + 1, yPos, 1, 0);
        searchDirection(moves, pieces, xPos - 1, yPos, -1, 0);
        searchDirection(moves, pieces, xPos, yPos + 1, 0, 1);
        searchDirection(moves, pieces, xPos, yPos - 1, 0, -1);

        searchDirection(moves, pieces, xPos + 1, yPos + 1, 1, 1);
        searchDirection(moves, pieces, xPos - 1, yPos + 1, -1, 1);
        searchDirection(moves, pieces, xPos + 1, yPos - 1, 1, -1);
        searchDirection(moves, pieces, xPos - 1, yPos - 1, -1, -1);

        return moves;

    }

    public boolean equals() {
        return true; //finish later
    }



}
