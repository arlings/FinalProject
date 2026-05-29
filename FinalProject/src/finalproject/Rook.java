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
public class Rook extends AbstractPiece{
    private static int numRooks;
    
    public Rook() {
        super();
    }

    public Rook(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        super(xPos, yPos, sprite, isWhite);
    }
    
    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
