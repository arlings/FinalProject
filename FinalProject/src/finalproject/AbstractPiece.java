package finalproject;

import java.util.ArrayList;
import javax.swing.ImageIcon;

abstract public class AbstractPiece implements Piece {

    protected ArrayList<Move> validMoves;
    protected int xPos;
    protected int yPos;
    protected ImageIcon sprite;
    protected boolean isWhite;
    protected static int numPieces;
    protected int value;

    public AbstractPiece() {
    }

    public AbstractPiece(int xPos, int yPos, ImageIcon sprite, boolean isWhite) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite = sprite;
        this.isWhite = isWhite;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public int getValue() {
        return value;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setSprite(ImageIcon sprite) {
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

    abstract public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy);

    public boolean isInsideBoard(int x, int y) {
        return (xPos>= 0 && xPos<= 7 && yPos>= 0 && yPos <= 7);
    }

    public Piece getPieceAt(int x, int y, ArrayList<Piece> pieces) {
        for (Piece p : pieces) {
            if (p.getXPos() == x && p.getYPos() == y) {
                return p;
            }
        }
        return null;

    }

    public static int getNumPieces() {
        return numPieces;
    }

}
