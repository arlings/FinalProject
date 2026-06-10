package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends AbstractPiece {

    private static int numKnights = 0;

    /**
     * default Knight constructor
     */
    public Knight() {
        super();//calls super class constructor with no paramaters
    }

    /**
     * Knight constructor
     *
     * @param rowNum - row number
     * @param columnNum - col number
     * @param sprite - sprite
     * @param isWhite - boolean of if team is white
     */
    public Knight(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite) {
        super(rowNum, columnNum, sprite, isWhite, 3);//calls super class constructor with the following paramaters
    }

    /**
     * ger the valid moves
     *
     * @param pieces - 2d array of pieces
     * @return - array list of valid moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();

        //possible moves for knight
        searchDirection(moves, pieces, rowNum, columnNum, 1, -2);
        searchDirection(moves, pieces, rowNum, columnNum, -1, -2);
        searchDirection(moves, pieces, rowNum, columnNum, 1, 2);
        searchDirection(moves, pieces, rowNum, columnNum, -1, 2);
        searchDirection(moves, pieces, rowNum, columnNum, 2, -1);
        searchDirection(moves, pieces, rowNum, columnNum, 2, 1);
        searchDirection(moves, pieces, rowNum, columnNum, -2, -1);
        searchDirection(moves, pieces, rowNum, columnNum, -2, 1);

        return moves;
    }

    /**
     * search the direction
     *
     * @param moves - array list of moves
     * @param pieces - 2d array of pieces
     * @param currentRow - current row
     * @param currentCol - current col
     * @param dRow - delta row
     * @param dCol - delta col
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        int targetRow = currentRow + dRow;
        int targetCol = currentCol + dCol;

        if (targetRow >= 0 && targetRow < 8 && targetCol >= 0 && targetCol < 8) {

            Piece piece = getPieceAt(targetRow, targetCol, pieces);

            if (piece == null) {
                moves.add(new Move(targetRow, targetCol));
            } else if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    /**
     * get the number of knights
     *
     * @return - the number of knights
     */
    public static int getNumKnights() {
        return numKnights;
    }

    /**
     * return the status of the knight
     *
     * @return - toString status
     */
    public String toString() {
        return ("Piece Type: Knight"
                + super.toString());//calls toString in the super class
    }
}
