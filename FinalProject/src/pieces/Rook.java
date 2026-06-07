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
    private boolean hasMoved;
    
    public Rook() {
        this(0, 0, null, true, false);
    }

    public Rook(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, boolean hasMoved) {
        super(rowNum, columnNum, sprite, isWhite, 5);
        this.hasMoved = hasMoved;
    }
    
    @Override
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList();

        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        return moves;
    }

    public boolean hasMoved(){
        return hasMoved;
    }
    
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
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