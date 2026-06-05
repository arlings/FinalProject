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

public class Queen extends AbstractPiece {
    
    private static int numQueens = 0;
    public Queen() {
        super();
        numQueens++;
    }

    public Queen(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite);
        numQueens++;
    }

   
    @Override
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList();

        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

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