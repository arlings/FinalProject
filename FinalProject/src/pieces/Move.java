
package pieces;

public class Move {
    private int rowNum;
    private int columnNum;

    /**
     * default constructor
     */
    public Move() {
        rowNum = 0;
        columnNum = 0;
    }

    /**
     * Move constrctor
     * @param rowNum- row number
     * @param columnNum - column number
     */
    public Move(int rowNum, int columnNum) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
    }

    /**
     * get the row number
     * @return - the row number
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * get the column number
     * @return - the column number
     */
    public int getColumnNum() {
        return columnNum;
    }

    /**
     * set the row number
     * @param rowNum - new row number
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * set the column number
     * @param columnNum - new column number
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * check if 2 objects are the same
     * @param obj - the second object or the object that is being compared to the given index
     * @return 
     */
    public boolean equals(Object obj) {
        if (this == obj) {//if the same reference
            return true;
        }
        if (obj == null) {//if object is null
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (this.rowNum != other.rowNum) {//if row number are different
            return false;
        }
        return this.columnNum == other.columnNum;
    }

    /**
     * return the status of the current move object
     * @return - the status
     */
    public String toString() {
        return "Move{" + "rowNum=" + rowNum + ", columnNum=" + columnNum + '}';
    }
    
    
}