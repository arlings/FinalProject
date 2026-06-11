/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class is the base for all chess pieces
*/

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
     * Default constructor
     * Creates an empty piece
     */
    public AbstractPiece() {
    }

    /**
     * Piece constructor
     * @param rowNum board row
     * @param columnNum board column
     * @param sprite piece image
     * @param isWhite piece color
     * @param value piece value
     */
    public AbstractPiece(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, int value) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.sprite = sprite;
        this.isWhite = isWhite;
        this.value = value;
    }

    /**
     * Gets row number
     * @return row index
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * Gets column number
     * @return column index
     */
    public int getColumnNum() {
        return columnNum;
    }

    /**
     * Gets sprite image
     * @return sprite
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Checks if piece is white
     * @return true if white
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Gets team name
     * @return White or Black
     */
    public String getTeam() {
        if (isWhite) {
            return "White";
        }
        return "Black";
    }

    /**
     * Gets piece value
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets row number
     * @param rowNum new row
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * Sets column number
     * @param columnNum new column
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * Sets sprite image
     * @param sprite new sprite
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets piece color
     * @param isWhite new color
     */
    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * Sets total number of pieces
     * @param numPieces count
     */
    public static void setNumPieces(int numPieces) {
        AbstractPiece.numPieces = numPieces;
    }

    /**
     * Sets piece value
     * @param value new value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets valid moves list
     * @param validMoves list of moves
     */
    public void setValidMoves(ArrayList<Move> validMoves) {
        this.validMoves = validMoves;
    }

    /**
     * Gets valid moves for this piece
     * @param pieces board state
     * @return list of moves
     */
    abstract public ArrayList<Move> getValidMoves(Piece pieces[][]);

    /**
     * Searches in a direction for sliding moves
     * @param moves list to add to
     * @param pieces board state
     * @param currentRow start row
     * @param currentCol start col
     * @param dRow row step
     * @param dCol col step
     */
    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {

        // stop if outside board
        if (!isInsideBoard(currentRow, currentCol)) {
            return;
        }

        Piece piece = getPieceAt(currentRow, currentCol, pieces);

        if (piece == null) {
            // empty square, add move and keep going
            moves.add(new Move(currentRow, currentCol));
            searchDirection(moves, pieces, currentRow + dRow, currentCol + dCol, dRow, dCol);
        } else {
            // enemy piece can be captured
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentRow, currentCol));
            }
        }
    }

    /**
     * Checks if position is inside board
     * @param row row index
     * @param col col index
     * @return true if inside
     */
    public boolean isInsideBoard(int row, int col) {
        return (row >= 0 && row <= 7 && col >= 0 && col <= 7);
    }

    /**
     * Gets piece at board position
     * @param row row index
     * @param col col index
     * @param pieces board state
     * @return piece at position
     */
    public Piece getPieceAt(int row, int col, Piece[][] pieces) {
        return pieces[row][col];
    }

    /**
     * Gets number of pieces created
     * @return count
     */
    public static int getNumPieces() {
        return numPieces;
    }

    /**
     * Checks if two pieces are equal
     * @param p piece to compare
     * @return true if equal
     */
    public boolean equals(Piece p) {
        if (this == p) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (getClass() != p.getClass()) {
            return false;
        }

        AbstractPiece other = (AbstractPiece) p;

        // compare color and value
        if (this.isWhite != other.isWhite) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }

        // compare sprite
        return Objects.equals(this.sprite, other.sprite);
    }

    /**
     * Returns piece info as text
     * @return string with details
     */
    public String toString() {
        return "Piece Information"
                + "\nRow Position: " + rowNum
                + "\nColumn Position: " + columnNum
                + "\nTeam: " + this.getTeam()
                + "\nNumber of Valid Moves: " + validMoves
                + "\nValue: " + value;
    }
}
