package pieces;

import java.awt.image.BufferedImage;
import pieces.Move;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;

abstract public class AbstractPiece implements Piece {

    protected ArrayList<Move> validMoves;
    protected int xPos;
    protected int yPos;
    protected BufferedImage sprite;
    protected boolean isWhite;
    protected static int numPieces;
    protected int value;

    public AbstractPiece() {
    }

    public AbstractPiece(int xPos, int yPos, BufferedImage sprite, boolean isWhite) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite = sprite;
        this.isWhite = isWhite;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
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

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
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

    public void searchDirection(ArrayList<Move> moves, Piece[][] pieces, int currentX, int currentY, int dx, int dy) {
        if (!isInsideBoard(currentX, currentY)) {
            return;
        }

        Piece piece = getPieceAt(currentX, currentY, pieces);

        if (piece == null) {
            moves.add(new Move(currentX, currentY));
            searchDirection(moves, pieces, currentX + dx, currentY + dy, dx, dy);
        } else {
            if (piece.isWhite() != this.isWhite()) {
                moves.add(new Move(currentX, currentY));
            }
        }
    }

    public boolean isInsideBoard(int x, int y) {
        return (x >= 0 && x <= 7 && y >= 0 && y <= 7);
    }

    public Piece getPieceAt(int x, int y, Piece[][] pieces) {
        return pieces[x][y];
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
                + "\nX Position: " + xPos
                + "\nX Position: " + xPos
                + "\nTeam: " + this.getTeam()
                + "\nNumber of Valid Moves: " + validMoves
                + "\nValue: " + value);
    }

}
