
package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Rook extends AbstractPiece{
    private static int numRooks;
    private boolean hasMoved;
    
    /**
     * default constructor
     */
    public Rook() {
        this(0, 0, null, true, false);//calls Rook constructor with 5 paramaters
    }

    /**
     * Rook constructor
     * @param rowNum- row number
     * @param columnNum- column number
     * @param sprite- sprite
     * @param isWhite- if the piece is white
     * @param hasMoved - if the piece has moved
     */
    public Rook(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, boolean hasMoved) {
        super(rowNum, columnNum, sprite, isWhite, 5);//calls the super constructor with the following paramaters
        this.hasMoved = hasMoved;
    }
    
    /**
     * returns the valid moves
     * @param pieces- 2d array of pieces
     * @return - array list of valid moves
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]) {
        ArrayList<Move> moves = new ArrayList();

        //possible options for the rook
        searchDirection(moves, pieces, rowNum + 1, columnNum, 1, 0);
        searchDirection(moves, pieces, rowNum - 1, columnNum, -1, 0);
        searchDirection(moves, pieces, rowNum, columnNum + 1, 0, 1);
        searchDirection(moves, pieces, rowNum, columnNum - 1, 0, -1);

        return moves;
    }

    /**
     * get if the rook has moved yet
     * @return - true if rook has already moved and false otherwise
     */
    public boolean hasMoved(){
        return hasMoved;
    }
    
    /**
     * set if the rook has moved yet
     * @param hasMoved - boolean of if the rook has moved yet
     */
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    /**
     * get the number of rooks
     * @return - the number of rooks
     */
    public static int getNumRooks() {
        return numRooks;
    }
    
    /**
     * returns the status of the current instance of the rook
     * @return - the status
     */
    public String toString() {
        return ("Piece Type: Rook"
                + super.toString());//calls the super constructor for toString()
    }
    
}