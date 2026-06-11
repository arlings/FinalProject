/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This interface defines the required methods for all chess pieces
*/

package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface Piece {

    /**
     * Gets valid moves for this piece
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece pieces[][]);

    /**
     * Sets valid moves
     * @param moves list of moves
     */
    public void setValidMoves(ArrayList<Move> moves);

    /**
     * Gets sprite image
     * @return sprite
     */
    public BufferedImage getSprite();

    /**
     * Sets sprite image
     * @param image new sprite
     */
    public void setSprite(BufferedImage image);

    /**
     * Gets row number
     * @return row index
     */
    public int getRowNum();

    /**
     * Gets column number
     * @return column index
     */
    public int getColumnNum();

    /**
     * Sets row number
     * @param rowNum new row
     */
    public void setRowNum(int rowNum);

    /**
     * Sets column number
     * @param columnNum new column
     */
    public void setColumnNum(int columnNum);

    /**
     * Checks if piece is white
     * @return true if white
     */
    public boolean isWhite();

    /**
     * Sets piece color
     * @param isWhite new color
     */
    public void setWhite(boolean isWhite);

    /**
     * Gets piece value
     * @return value
     */
    public int getValue();

    /**
     * Sets piece value
     * @param value new value
     */
    public void setValue(int value);

    /**
     * Searches in a direction for moves
     * @param moves list to add to
     * @param pieces board state
     * @param currentRow start row
     * @param currentCol start col
     * @param dRow row step
     * @param dCol col step
     */
    public void searchDirection(ArrayList<Move> moves, Piece pieces[][],
                                int currentRow, int currentCol, int dRow, int dCol);

    /**
     * Checks if position is inside board
     * @param row row index
     * @param col col index
     * @return true if inside
     */
    public boolean isInsideBoard(int row, int col);

    /**
     * Gets piece at position
     * @param row row index
     * @param col col index
     * @param pieces board state
     * @return piece at position
     */
    public Piece getPieceAt(int row, int col, Piece pieces[][]);

    /**
     * Checks if two pieces are equal
     * @param p piece to compare
     * @return true if equal
     */
    public boolean equals(Piece p);

    /**
     * Returns piece info as text
     * @return string
     */
    public String toString();
}
