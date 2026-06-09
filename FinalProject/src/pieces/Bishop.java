package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bishop extends AbstractPiece {

    private static int numBishop = 0;

    /**
     * basic constructor for Bishob
     */
    public Bishop() {
        super();//calles the constructor with no paramaters from the super class
        numBishop++;//number of bishop pieces increase
    }

    /**
     * Constructor of Bishop
     *
     * @param rowNum - row number
     * @param columnNum - column number
     * @param sprite - sprite
     * @param isWhite - if piece is on white team
     */
    public Bishop(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 3);//calls the constructor with the following paramaters from the super class
        numBishop++;//number of bishops increase
    }

    /**
     * get the valid moves
     *
     * @param pieces - 2d array of piece
     * @return - the array list
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList();

        //the 4 possible options that a bishop could possibly move
        searchDirection(moves, pieces, rowNum + 1, columnNum + 1, 1, 1);
        searchDirection(moves, pieces, rowNum - 1, columnNum + 1, -1, 1);
        searchDirection(moves, pieces, rowNum + 1, columnNum - 1, 1, -1);
        searchDirection(moves, pieces, rowNum - 1, columnNum - 1, -1, -1);

        return moves;
    }

    /**
     * get the number of bishops
     *
     * @return - the number of bishops
     */
    public static int getNumBishop() {
        return numBishop;
    }

    /**
     * returns the toString
     *
     * @return - the toStrin
     */
    public String toString() {
        return ("Piece Type: Bishop"
                + super.toString());//calls super toString class
    }
}
