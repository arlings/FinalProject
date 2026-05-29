/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

/**
 *
 * @author NeWan5443
 */
public class Move {
    private int xPos;
    private int yPos;

    public Move() {
        xPos = 0;
        yPos = 0;
    }

    public Move(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (this.xPos != other.xPos) {
            return false;
        }
        return this.yPos == other.yPos;
    }

    public String toString() {
        return "Move{" + "xPos=" + xPos + ", yPos=" + yPos + '}';
    }
    
    
}
