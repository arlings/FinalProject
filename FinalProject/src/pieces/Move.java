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
    private int rowNum;
    private int columnNum;

    public Move() {
        rowNum = 0;
        columnNum = 0;
    }

    public Move(int rowNum, int columnNum) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
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
        if (this.rowNum != other.rowNum) {
            return false;
        }
        return this.columnNum == other.columnNum;
    }

    public String toString() {
        return "Move{" + "rowNum=" + rowNum + ", columnNum=" + columnNum + '}';
    }
    
    
}