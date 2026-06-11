/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
This class handles custom piece movement rules
 */
package pieces;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class CustomPiece extends AbstractPiece {

    private boolean pawnMoves = false;
    private boolean pawnCaptures = false;
    private boolean isFirstMove = true;
    private boolean isSandboxMode = false;

    private ArrayList<int[]> slideDirections = new ArrayList<>();
    private ArrayList<int[]> knightJumps = new ArrayList<>();
    private ArrayList<Move> customJumps = new ArrayList<>();

    /**
     * Custom piece constructor
     *
     * @param row board row
     * @param col board column
     * @param sprite piece image
     * @param isWhite piece color
     * @param isSandboxMode sandbox mode flag
     */
    public CustomPiece(int row, int col, BufferedImage sprite, boolean isWhite, boolean isSandboxMode) {
        super(row, col, sprite, isWhite, 0); // custom piece has no fixed value
        this.isSandboxMode = isSandboxMode;
    }

    /**
     * Sets sandbox mode
     *
     * @param isSandboxMode new mode
     */
    public void setSandboxMode(boolean isSandboxMode) {
        this.isSandboxMode = isSandboxMode;
    }

    /**
     * Adds movement rules based on preset name
     *
     * @param preset rule name
     */
    public void addMoveRules(String preset) {

        // Rook directions
        if (preset.equalsIgnoreCase("UP_ROOK")) {
            slideDirections.add(new int[]{1, 0});
        } else if (preset.equalsIgnoreCase("DOWN_ROOK")) {
            slideDirections.add(new int[]{-1, 0});
        } else if (preset.equalsIgnoreCase("LEFT_ROOK")) {
            slideDirections.add(new int[]{0, -1});
        } else if (preset.equalsIgnoreCase("RIGHT_ROOK")) {
            slideDirections.add(new int[]{0, 1});
        } // Bishop directions
        else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
            slideDirections.add(new int[]{1, -1});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{1, 1});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_BISHOP")) {
            slideDirections.add(new int[]{-1, -1});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{-1, 1});
        } // Knight jumps
        else if (preset.equalsIgnoreCase("TOP_LEFT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{2, -1});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{2, 1});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{-2, -1});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{-2, 1});
        } else if (preset.equalsIgnoreCase("TOP_LEFT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{1, -2});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{1, 2});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{-1, -2});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{-1, 2});
        } // Pawn rules
        else if (preset.equalsIgnoreCase("PAWN_MOVE")) {
            pawnMoves = true;
        } else if (preset.equalsIgnoreCase("PAWN_CAPTURE")) {
            pawnCaptures = true;
        } else if (preset.equalsIgnoreCase("PAWN")) {
            pawnMoves = true;
            pawnCaptures = true;
        } // Group presets
        else if (preset.equalsIgnoreCase("ROOK")) {
            addMoveRules("UP_ROOK");
            addMoveRules("DOWN_ROOK");
            addMoveRules("LEFT_ROOK");
            addMoveRules("RIGHT_ROOK");
        } else if (preset.equalsIgnoreCase("BISHOP")) {
            addMoveRules("TOP_LEFT_BISHOP");
            addMoveRules("TOP_RIGHT_BISHOP");
            addMoveRules("BOTTOM_LEFT_BISHOP");
            addMoveRules("BOTTOM_RIGHT_BISHOP");
        } else if (preset.equalsIgnoreCase("KNIGHT")) {
            addMoveRules("TOP_LEFT_VERT_KNIGHT");
            addMoveRules("TOP_RIGHT_VERT_KNIGHT");
            addMoveRules("BOTTOM_LEFT_VERT_KNIGHT");
            addMoveRules("BOTTOM_RIGHT_VERT_KNIGHT");
            addMoveRules("TOP_LEFT_HORZ_KNIGHT");
            addMoveRules("TOP_RIGHT_HORZ_KNIGHT");
            addMoveRules("BOTTOM_LEFT_HORZ_KNIGHT");
            addMoveRules("BOTTOM_RIGHT_HORZ_KNIGHT");
        } else if (preset.equalsIgnoreCase("QUEEN")) {
            addMoveRules("ROOK");
            addMoveRules("BISHOP");
        }
    }

    /**
     * Removes movement rules based on preset name
     *
     * @param preset rule name
     */
    public void removeMoveRules(String preset) {

        // Rook directions
        if (preset.equalsIgnoreCase("UP_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == 0);
            customJumps.removeIf(m -> m.getRowNum() > 0 && m.getColumnNum() == 0);
        } else if (preset.equalsIgnoreCase("DOWN_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == 0);
            customJumps.removeIf(m -> m.getRowNum() < 0 && m.getColumnNum() == 0);
        } else if (preset.equalsIgnoreCase("LEFT_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 0 && dir[1] == -1);
            customJumps.removeIf(m -> m.getRowNum() == 0 && m.getColumnNum() < 0);
        } else if (preset.equalsIgnoreCase("RIGHT_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 0 && dir[1] == 1);
            customJumps.removeIf(m -> m.getRowNum() == 0 && m.getColumnNum() > 0);
        } // Bishop directions
        else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == -1);
            customJumps.removeIf(m -> m.getRowNum() > 0 && m.getColumnNum() < 0);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == 1);
            customJumps.removeIf(m -> m.getRowNum() > 0 && m.getColumnNum() > 0);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == -1);
            customJumps.removeIf(m -> m.getRowNum() < 0 && m.getColumnNum() < 0);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == 1);
            customJumps.removeIf(m -> m.getRowNum() < 0 && m.getColumnNum() > 0);
        } // Knight jumps
        else if (preset.equalsIgnoreCase("TOP_LEFT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 2 && j[1] == -1);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 2 && j[1] == 1);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -2 && j[1] == -1);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -2 && j[1] == 1);
        } else if (preset.equalsIgnoreCase("TOP_LEFT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 1 && j[1] == -2);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 1 && j[1] == 2);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -1 && j[1] == -2);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -1 && j[1] == 2);
        } // Pawn rules
        else if (preset.equalsIgnoreCase("PAWN_MOVE")) {
            pawnMoves = false;
        } else if (preset.equalsIgnoreCase("PAWN_CAPTURE")) {
            pawnCaptures = false;
        } else if (preset.equalsIgnoreCase("PAWN")) {
            pawnMoves = false;
            pawnCaptures = false;
        } // Group presets
        else if (preset.equalsIgnoreCase("ROOK")) {
            removeMoveRules("UP_ROOK");
            removeMoveRules("DOWN_ROOK");
            removeMoveRules("LEFT_ROOK");
            removeMoveRules("RIGHT_ROOK");
        } else if (preset.equalsIgnoreCase("BISHOP")) {
            removeMoveRules("TOP_LEFT_BISHOP");
            removeMoveRules("TOP_RIGHT_BISHOP");
            removeMoveRules("BOTTOM_LEFT_BISHOP");
            removeMoveRules("BOTTOM_RIGHT_BISHOP");
        } else if (preset.equalsIgnoreCase("KNIGHT")) {
            removeMoveRules("TOP_LEFT_VERT_KNIGHT");
            removeMoveRules("TOP_RIGHT_VERT_KNIGHT");
            removeMoveRules("BOTTOM_LEFT_VERT_KNIGHT");
            removeMoveRules("BOTTOM_RIGHT_VERT_KNIGHT");
            removeMoveRules("TOP_LEFT_HORZ_KNIGHT");
            removeMoveRules("TOP_RIGHT_HORZ_KNIGHT");
            removeMoveRules("BOTTOM_LEFT_HORZ_KNIGHT");
            removeMoveRules("BOTTOM_RIGHT_HORZ_KNIGHT");
        } else if (preset.equalsIgnoreCase("QUEEN")) {
            removeMoveRules("ROOK");
            removeMoveRules("BISHOP");
        }
    }

    /**
     * Clears all movement rules
     */
    public void clearRules() {
        slideDirections.clear();
        knightJumps.clear();
        customJumps.clear();
        pawnMoves = false;
        pawnCaptures = false;
    }

    /**
     * Adds a custom jump move
     *
     * @param dx row offset
     * @param dy col offset
     */
    public void addMoveRules(int dx, int dy) {
        customJumps.add(new Move(dx, dy));
    }

    /**
     * Removes a custom jump move
     *
     * @param dx row offset
     * @param dy col offset
     * @return true if removed
     */
    public boolean removeMoveRule(int dx, int dy) {
        for (int i = 0; i < customJumps.size(); i++) {
            Move m = customJumps.get(i);
            if (m.getRowNum() == dx && m.getColumnNum() == dy) {
                customJumps.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds knight jump to move list
     */
    private void knightJump(ArrayList<Move> moves, Piece[][] pieces, int dRow, int dCol) {
        int targetRow = rowNum + dRow;
        int targetCol = columnNum + dCol;

        if (isInsideBoard(targetRow, targetCol)) {
            Piece piece = getPieceAt(targetRow, targetCol, pieces);

            // can move if empty or enemy
            if (piece == null || piece.isWhite() != this.isWhite()) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    /**
     * Pawn forward movement
     */
    private void searchPawnForward(ArrayList<Move> moves, Piece[][] pieces, int targetRow, int targetCol, int direction) {
        if (isInsideBoard(targetRow, targetCol)) {
            Piece pieceForward = getPieceAt(targetRow, targetCol, pieces);
            if (pieceForward == null) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    /**
     * Pawn capture movement
     */
    private void searchPawnCaptures(ArrayList<Move> moves, Piece[][] pieces, int targetRow) {
        int[] captureCols = {columnNum - 1, columnNum + 1};

        for (int col : captureCols) {
            if (isInsideBoard(targetRow, col)) {
                Piece targetPiece = getPieceAt(targetRow, col, pieces);

                // sandbox allows capturing empty squares
                if (isSandboxMode || (targetPiece != null && targetPiece.isWhite() != this.isWhite())) {
                    moves.add(new Move(targetRow, col));
                }
            }
        }
    }

    /**
     * Gets all valid moves for this custom piece
     *
     * @param pieces board state
     * @return list of moves
     */
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {

        ArrayList<Move> moves = new ArrayList<>();

        // sliding moves
        for (int[] dir : slideDirections) {
            searchDirection(moves, pieces, rowNum + dir[0], columnNum + dir[1], dir[0], dir[1]);
        }

        // knight jumps
        for (int[] jump : knightJumps) {
            knightJump(moves, pieces, jump[0], jump[1]);
        }

        // custom jumps
        for (Move m : customJumps) {
            int targetRow = rowNum + m.getRowNum();
            int targetCol = columnNum + m.getColumnNum();

            if (isInsideBoard(targetRow, targetCol)) {
                Piece target = getPieceAt(targetRow, targetCol, pieces);

                if (target == null || target.isWhite() != this.isWhite()) {
                    moves.add(new Move(targetRow, targetCol));
                }
            }
        }

        // pawn movement
        if (pawnMoves || pawnCaptures) {

            int direction = this.isWhite() ? 1 : -1;

            if (pawnMoves) {
                searchPawnForward(moves, pieces, rowNum + direction, columnNum, direction);

                // first move two step
                if (isFirstMove) {
                    int targetRow = rowNum + direction;
                    int twoStepRow = rowNum + (2 * direction);

                    if (isInsideBoard(twoStepRow, columnNum)
                            && getPieceAt(targetRow, columnNum, pieces) == null
                            && getPieceAt(twoStepRow, columnNum, pieces) == null) {

                        moves.add(new Move(twoStepRow, columnNum));
                    }
                }
            }

            if (pawnCaptures) {
                searchPawnCaptures(moves, pieces, rowNum + direction);
            }
        }

        return moves;
    }

    /**
     * Checks if knight jump exists
     */
    public boolean hasKnight(int dx, int dy) {
        for (int[] jump : knightJumps) {
            if (jump[0] == dx && jump[1] == dy) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if pawn forward is enabled
     */
    public boolean hasPawnMove() {
        return pawnMoves;
    }

    /**
     * Checks if pawn capture is enabled
     */
    public boolean hasPawnCapture() {
        return pawnCaptures;
    }

    /**
     * Sets pawn forward movement
     *
     * @param value true or false
     */
    public void setPawnMoves(boolean value) {
        pawnMoves = value; // update flag
    }

    /**
     * Sets pawn capture movement
     *
     * @param value true or false
     */
    public void setPawnCaptures(boolean value) {
        pawnCaptures = value; // update flag
    }

    /**
     * Adds a knight jump
     *
     * @param dx row offset
     * @param dy col offset
     */
    public void addKnightMove(int dx, int dy) {
        knightJumps.add(new int[]{dx, dy}); // store jump
    }

    /**
     * Removes a knight jump. Note this method was made with help from ai
     *
     * @param dx row offset
     * @param dy col offset 
     */
    public void removeKnightMove(int dx, int dy) {
        knightJumps.removeIf(j -> j[0] == dx && j[1] == dy); // remove matching jump
    }

    /**
     * Creates a copy of this custom piece
     *
     * @param newRow new row
     * @param newCol new col
     * @return new CustomPiece with same rules
     */
    public CustomPiece copy(int newRow, int newCol) {
        CustomPiece cp = new CustomPiece(newRow, newCol, this.getSprite(), this.isWhite(), this.isSandboxMode);

        cp.slideDirections = new ArrayList<>(this.slideDirections);
        cp.knightJumps = new ArrayList<>(this.knightJumps);
        cp.customJumps = new ArrayList<>(this.customJumps);

        cp.pawnMoves = this.pawnMoves;
        cp.pawnCaptures = this.pawnCaptures;

        return cp;
    }

    @Override
    /**
     * Method that prints all the information about a piece into a string. Made
     * with help from ai.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CustomPiece {\n");
        sb.append("  Position: (").append(rowNum).append(", ").append(columnNum).append(")\n");
        sb.append("  White: ").append(isWhite()).append("\n");

        sb.append("  Slide Directions: ");
        for (int[] dir : slideDirections) {
            sb.append("(").append(dir[0]).append(", ").append(dir[1]).append(") ");
        }
        sb.append("\n");

        sb.append("  Knight Jumps: ");
        for (int[] jump : knightJumps) {
            sb.append("(").append(jump[0]).append(", ").append(jump[1]).append(") ");
        }
        sb.append("\n");

        sb.append("  Custom Jumps: ");
        for (Move move : customJumps) {
            sb.append("(").append(move.getRowNum())
                    .append(", ")
                    .append(move.getColumnNum())
                    .append(") ");
        }
        sb.append("\n");

        sb.append("  Pawn Moves: ").append(pawnMoves).append("\n");
        sb.append("  Pawn Captures: ").append(pawnCaptures).append("\n");

        sb.append("}");

        return sb.toString();
    }
}
