/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.awt.image.BufferedImage;
import pieces.Move;
import java.util.ArrayList;

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

    public Bishop(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 3);
        numBishop++;
    }
    
    @Override
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList();

        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

        return moves;
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