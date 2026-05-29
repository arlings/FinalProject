/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author NeWan5443
 */
public class Bishop extends AbstractPiece{
    private static int numBishop = 0;
    public Bishop() {
        super();
        numBishop++;
    }

    public Bishop(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
        numBishop++;
    }
    
    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static int getNumBishop() {
        return numBishop;
    }
    
    @Override
    public String toString() {
        return ("Piece Type: Bishop"
                + super.toString());
    }
}
