package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Queen extends AbstractPiece {

    private static int numQueens = 0;

    /**
     * default queen constructor
     */
    public Queen() {
        super();//calls the default super constructor
        numQueens++;//number of queens increase
    }

    /**
     * Queen constructor
     *
     * @param rowNum - rown number
     * @param columnNum - column number
     * @param sprite - sprite
     * @param isWhite - if the piece is on the white team
     */
    public Queen(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 9);//calls the super constructor with the following paramaters
        numQueens++;//number of queens increase
    }

    /**
     * get the valid moves
     *
     * @param pieces- d2 array of pieces
     * @return - an array list of all the valid moves
     */
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

    /**
     * get the number of queens
     *
     * @return - the number of queens
     */
    public static int getNumQueens() {
        return numQueens;
    }

    /**
     * return the status of an instance of the queen
     *
     * @return - the status
     */
    public String toString() {
        return ("Piece Type: Queen"
                + super.toString());//calls the super constructor for toString
    }

}
