package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

abstract public class AbstractPiece implements Piece {

    protected ArrayList<Move> validMoves;
    protected int rowNum;
    protected int columnNum;
    protected BufferedImage sprite;
    protected boolean isWhite;
    protected static int numPieces;
    protected int value;

    /**
     * Basic constructor with no parameters
     */
    public AbstractPiece() {

    }

    /**
     * Abstract Piece constructor
     *
     * @param rowNum - row number
     * @param columnNum - colomn number
     * @param sprite - the buffered image
     * @param isWhite - boolean of if the piece is white or not
     * @param value - value of the piece
     */
    public AbstractPiece(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, int value) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.sprite = sprite;
        this.isWhite = isWhite;
        this.value = value;
    }

    /**
     * get the row number
     *
     * @return - the row number
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * get the column number
     *
     * @return - the column number
     */
    public int getColumnNum() {
        return columnNum;
    }

    /**
     * get the Sprite
     *
     * @return Sprite
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * get the boolean of if the piece is white
     *
     * @return - the boolean isWhite
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * returns the team
     *
     * @return - the team
     */
    public String getTeam() {
        if (isWhite) {//if the piece is white it belongs to the white team
            return "White";
        }
        return "Black";//otherwise it belongs to the black team.
    }

    /**
     * get the value
     *
     * @return - the value
     */
    public int getValue() {
        return value;
    }

    /**
     * set the rown numer
     *
     * @param rowNum - the row number
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * set the column number
     *
     * @param columnNum - the column number
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * set the Sprite
     *
     * @param sprite - the Sprite
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * set if the piece is white
     *
     * @param isWhite - boolean isWhite
     */
    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * set the number of pieces
     *
     * @param numPieces - the number of pieces
     */
    public static void setNumPieces(int numPieces) {
        AbstractPiece.numPieces = numPieces;
    }

    /**
     * set the value of the piece
     *
     * @param value - the value of the piece
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * set the valid moves
     *
     * @param validMoves - array list of valid moves
     */
    public void setValidMoves(ArrayList<Move> validMoves) {
        this.validMoves = validMoves;
    }

    abstract public ArrayList<Move> getValidMoves(Piece pieces[][]);//abstract

    /**
     * search the direction
     *
     * @param moves - the array list of moves
     * @param pieces - 2d array of pieces
     * @param currentRow - current row
     * @param currentCol - current coloum
     * @param dRow - delta row
     * @param dCol - delta colums
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        if (!isInsideBoard(currentRow, currentCol)) {//if it is outside the board
            return;
        }
        
        Piece piece = getPieceAt(currentRow, currentCol, pieces);

        if (piece == null) {//if the piece is null
            moves.add(new Move(currentRow, currentCol));//add that to the moves
            searchDirection(moves, pieces, currentRow + dRow, currentCol + dCol, dRow, dCol);
        } else {
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentRow, currentCol));
            }
        }
    }

    /**
     * if inside the board
     *
     * @param row - the row
     * @param col- the column
     * @return - true if inside the board and false otherwise
     */
    public boolean isInsideBoard(int row, int col) {
        return (row >= 0 && row <= 7 && col >= 0 && col <= 7);
    }

    /**
     * get piece at a given index
     *
     * @param row - the row
     * @param col - the col
     * @param pieces- 2d array of pieces
     * @return - piece at the given row+column
     */
    public Piece getPieceAt(int row, int col, Piece[][] pieces) {
        return pieces[row][col];
    }

    /**
     * get the number of pieces
     *
     * @return - the number of pieces
     */
    public static int getNumPieces() {
        return numPieces;
    }

    /**
     * checks if 2 pieces are equal
     *
     * @param - a piece
     * @return - true if equal and false otherwise
     */
    public boolean equals(Piece p) {
        if (this == p) {
            return true;
        }
        if (p == null) {// if p is null
            return false;
        }
        if (getClass() != p.getClass()) {// if the classes aren't equal
            return false;
        }
        final AbstractPiece other = (AbstractPiece) p;
        if (this.isWhite != other.isWhite) {//if the team of the pieces are oposite
            return false;
        }
        if (this.value != other.value) {//if the values are not the same
            return false;
        }
        return Objects.equals(this.sprite, other.sprite);
    }

    /**
     * toString - returns the toString
     *
     * @return - the status of the piece
     */
    public String toString() {
        return ("Piece Information"
                + "\nRow Position: " + rowNum
                + "\nColumn Position: " + columnNum
                + "\nTeam: " + this.getTeam()
                + "\nNumber of Valid Moves: " + validMoves
                + "\nValue: " + value);
    }
}
