package pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public interface Piece {    
    public ArrayList<Move> getValidMoves(Piece pieces[][]);
    public void setValidMoves(ArrayList<Move> moves);
    public BufferedImage getSprite();
    public void setSprite(BufferedImage image);
    public int getRowNum();
    public int getColumnNum();
    public void setRowNum(int rowNum);
    public void setColumnNum(int columnNum);
    public boolean isWhite();
    public int getValue();//piece value(i.e. Knights=3 pts )
    public void setValue(int value);//piece value
    abstract public void searchDirection(ArrayList<Move> moves, Piece pieces[][], int currentRow, int currentCol, int dRow, int dCol);
    public boolean isInsideBoard(int row, int col);
    public Piece getPieceAt(int row, int col, Piece pieces[][]);
    public boolean equals(Piece p);
    public String toString();

}