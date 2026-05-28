
package finalproject;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public interface Piece {    
    public ArrayList<Move> getValidMoves();
    abstract public void setValidMoves(ArrayList<Move> moves);
    public ImageIcon getImage();
    public void setImage(ImageIcon image);
    public int getXPos();
    public int getYPos();
    public void setXPos(int xPos);
    public void setYPos(int yPos);
    public boolean isWhite();
    public int getValue();//piece value(i.e. Knights=3 pts )
    public void setValue(int value);//piece value
    abstract public void searchDirection(ArrayList<Move> moves, ArrayList<Piece> pieces, int currentX, int currentY, int dx, int dy);
    public boolean isInsideBoard(int x, int y);
    public Piece getPieceAt(int x, int y, ArrayList<Piece> pieces);
    public boolean equals();
    public String toString();

}
