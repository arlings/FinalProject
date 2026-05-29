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
public class King extends AbstractPiece{
    private static int numKings;
    private boolean inCheck; //add logic later
    private boolean inCheckMate;

    public King() {
        super();
        inCheck = false;
        inCheckMate = false;
    }

    public King(int xPos, int yPos, ImageIcon sprite, boolean isWhite, boolean inCheck, boolean inCheckMate) {
        super(xPos, yPos, sprite, isWhite);
        this.inCheck = inCheck;
        this.inCheckMate = inCheckMate;
    }
    
    @Override
    public ArrayList<Move> getValidMoves(ArrayList<Piece> pieces) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    @Override
    public String toString() {
        return ("Piece Type: King"
                + super.toString());
    }
    
}
