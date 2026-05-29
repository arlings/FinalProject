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
    
    private static int numQueens = 0;
    public Queen() {
        super();
        numQueens++;
    }

    public Queen(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
        numQueens++;
    }

   
    @Override
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

    public static int getNumQueens() {
        return numQueens;
    }

    @Override
    public String toString() {
        return ("Piece Type: Queen"
                + super.toString());
    }
    
}
