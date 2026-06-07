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

    public AbstractPiece() {
    }

    public AbstractPiece(int rowNum, int columnNum, BufferedImage sprite, boolean isWhite, int value) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.sprite = sprite;
        this.isWhite = isWhite;
        this.value = value;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getTeam() {
        if (isWhite) {
            return "White";
        }
        return "Black";
    }

    public int getValue() {
        return value;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public static void setNumPieces(int numPieces) {
        AbstractPiece.numPieces = numPieces;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValidMoves(ArrayList<Move> validMoves) {
        this.validMoves = validMoves;
    }

    abstract public ArrayList<Move> getValidMoves(Piece pieces[][]);

    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentRow, int currentCol, int dRow, int dCol) {
        if (!isInsideBoard(currentRow, currentCol)) {
            return;
        }

        Piece piece = getPieceAt(currentRow, currentCol, pieces);

        if (piece == null) {
            moves.add(new Move(currentRow, currentCol));
            searchDirection(moves, pieces, currentRow + dRow, currentCol + dCol, dRow, dCol);
        } else {
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentRow, currentCol));
            }
        }
    }

    public boolean isInsideBoard(int row, int col) {
        return (row >= 0 && row <= 7 && col >= 0 && col <= 7);
    }

    public Piece getPieceAt(int row, int col, Piece[][] pieces) {
        return pieces[row][col];
    }

    public static int getNumPieces() {
        return numPieces;
    }

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
        final AbstractPiece other = (AbstractPiece) p;
        if (this.isWhite != other.isWhite) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return Objects.equals(this.sprite, other.sprite);
    }

    public String toString() {
        return ("Piece Information"
                + "\nRow Position: " + rowNum
                + "\nRow Position: " + rowNum
                + "\nTeam: " + this.getTeam()
                + "\nNumber of Valid Moves: " + validMoves
                + "\nValue: " + value);
    }

}
