/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class stores a move position on the board
*/

package pieces;

public class Move {

    private int rowNum;
    private int columnNum;

    /**
     * Default constructor
     * Creates a move at row 0 col 0
     */
    public Move() {
        rowNum = 0;
        columnNum = 0;
    }

    /**
     * Move constructor
     * @param rowNum row index
     * @param columnNum column index
     */
    public Move(int rowNum, int columnNum) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
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
     * Checks if two moves are equal
     * @param obj object to compare
     * @return true if same row and column
     */
    public boolean equals(Object obj) {

        if (this == obj) {
            return true; // same reference
        }
        if (obj == null) {
            return false; // null check
        }
        if (getClass() != obj.getClass()) {
            return false; // must be same class
        }

        Move other = (Move) obj;

        // compare row and column
        if (this.rowNum != other.rowNum) {
            return false;
        }
        return this.columnNum == other.columnNum;
    }

    /**
     * Returns move info as text
     * @return string with row and column
     */
    public String toString() {
        return "Move{rowNum=" + rowNum + ", columnNum=" + columnNum + "}";
    }
}
