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
public class Rook extends AbstractPiece{
    private static int numRooks;
    
    public Rook() {
        super();
    }

    public Rook(int xPos, int yPos, BufferedImage sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }
    
    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        ArrayList<Move> moves = new ArrayList();

        searchDirection(moves, pieces, xPos + 1, yPos, 1, 0);
        searchDirection(moves, pieces, xPos - 1, yPos, -1, 0);
        searchDirection(moves, pieces, xPos, yPos + 1, 0, 1);
        searchDirection(moves, pieces, xPos, yPos - 1, 0, -1);

        return moves;
    }

    

    public static int getNumRooks() {
        return numRooks;
    }
    
    @Override
    public String toString() {
        return ("Piece Type: Rook"
                + super.toString());
    }
    
}
