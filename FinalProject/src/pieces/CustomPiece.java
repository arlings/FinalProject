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
     * @param row - row
     * @param col - col
     * @param sprite - image
     * @param isWhite- if piece is White
     * @param isSandboxMode - if it is sandbox mode
     */
    public CustomPiece(int row, int col, BufferedImage sprite, boolean isWhite, boolean isSandboxMode) {
        super(row, col, sprite, isWhite, 0);//calls super constructor with the paramaters
        this.isSandboxMode = isSandboxMode;
    }

    /**
     * set the sandbox mode
     *
     * @param isSandboxMode- boolean of if it is the sandbox mode
     */
    public void setSandboxMode(boolean isSandboxMode) {
        this.isSandboxMode = isSandboxMode;
    }

    /**
     * add move rules
     *
     * @param preset - String of move
     */
    public void addMoveRules(String preset) {
        // Rook-like
        if (preset.equalsIgnoreCase("UP_ROOK")) {
            slideDirections.add(new int[]{1, 0});
        } else if (preset.equalsIgnoreCase("DOWN_ROOK")) {
            slideDirections.add(new int[]{-1, 0});
        } else if (preset.equalsIgnoreCase("LEFT_ROOK")) {
            slideDirections.add(new int[]{0, -1});
        } else if (preset.equalsIgnoreCase("RIGHT_ROOK")) {
            slideDirections.add(new int[]{0, 1});
        } // Bishop-like
        else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
            //System.out.println("Adding top left bishop");
            slideDirections.add(new int[]{1, -1});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{1, 1});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_BISHOP")) {
            slideDirections.add(new int[]{-1, -1});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{-1, 1});
        } // Knight-like
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
        } else if (preset.equalsIgnoreCase("PAWN_MOVE")) {
            pawnMoves = true;
        } else if (preset.equalsIgnoreCase("PAWN_CAPTURE")) {
            pawnCaptures = true;
        } else if (preset.equalsIgnoreCase("PAWN")) {
            pawnMoves = true;
            pawnCaptures = true;
        } // Combinations (Recursive calls)
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

    //fully just copied pasted from AI
    /**
     * removes the move rules
     *
     * @param preset - String
     */
    public void removeMoveRules(String preset) {
        // Rook-like
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
            // Bishop-like
        } else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
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
        } else if (preset.equalsIgnoreCase("TOP_LEFT_VERT_KNIGHT")) {
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
        } // Pawn-like
        else if (preset.equalsIgnoreCase("PAWN_MOVE")) {
            this.pawnMoves = false;
        } else if (preset.equalsIgnoreCase("PAWN_CAPTURE")) {
            this.pawnCaptures = false;
        } else if (preset.equalsIgnoreCase("PAWN")) {
            this.pawnMoves = false;
            this.pawnCaptures = false;
        } // Group Preset Removal (Recursive)
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

    private boolean matchesDirection(Move m, int dx, int dy) {
        return m.getRowNum() == dx && m.getColumnNum() == dy;
    }

    /**
     * clear the rules
     */
    public void clearRules() {
        this.slideDirections.clear();
        this.knightJumps.clear();
        this.customJumps.clear();
        this.pawnMoves = false;
        this.pawnCaptures = false;
    }

    /**
     * add the move rules
     *
     * @param dx - delta x
     * @param dy - delta y
     */
    public void addMoveRules(int dx, int dy) {
        customJumps.add(new Move(dx, dy));
    }

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
     * jump the knight
     *
     * @param moves - array list of moves
     * @param pieces - 2d array of pieces
     * @param dRow - delta row
     * @param dCol - delta col
     */
    private void knightJump(ArrayList<Move> moves, Piece[][] pieces, int dRow, int dCol) {
        int targetRow = rowNum + dRow;
        int targetCol = columnNum + dCol;

        if (isInsideBoard(targetRow, targetCol)) {
            Piece piece = getPieceAt(targetRow, targetCol, pieces);
            // Can move if square is empty or has an opponent
            if (piece == null || piece.isWhite() != this.isWhite()) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    /**
     * searc
     *
     * @param moves
     * @param pieces
     * @param targetRow
     * @param targetCol
     * @param direction
     */
    private void searchPawnForward(ArrayList<Move> moves, Piece[][] pieces, int targetRow, int targetCol, int direction) {
        if (isInsideBoard(targetRow, targetCol)) {
            Piece pieceForward = getPieceAt(targetRow, targetCol, pieces);
            if (pieceForward == null) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    private void searchPawnCaptures(ArrayList<Move> moves, Piece[][] pieces, int targetRow) {
        int[] captureCols = {columnNum - 1, columnNum + 1};
        for (int col : captureCols) {
            if (isInsideBoard(targetRow, col)) {
                Piece targetPiece = getPieceAt(targetRow, col, pieces);
                if (isSandboxMode || (targetPiece != null && targetPiece.isWhite() != this.isWhite())) {
                    moves.add(new Move(targetRow, col));
                }
            }
        }
    }

    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        //System.out.println("slideDirections size = " + slideDirections.size());
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] dir : slideDirections) {
            searchDirection(moves, pieces, rowNum + dir[0], columnNum + dir[1], dir[0], dir[1]);
        }
        for (int[] jump : knightJumps) {
            knightJump(moves, pieces, jump[0], jump[1]);
        }

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
        if (pawnMoves || pawnCaptures) {
            int direction = this.isWhite() ? 1 : -1;

            if (pawnMoves) {
                searchPawnForward(moves, pieces, rowNum + direction, columnNum, direction);

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
        //System.out.println("Valid moves: " + moves.size());
        return moves;
    }

    public boolean hasKnight(int dx, int dy) {
        for (int[] jump : knightJumps) {
            if (jump[0] == dx && jump[1] == dy) {
                return true;
            }
        }
        return false;
    }

    private boolean containsMove(ArrayList<Move> list, int dx, int dy) {
        for (Move m : list) {
            if (m.getRowNum() == dx && m.getColumnNum() == dy) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPawnMove() {
        return pawnMoves;
    }

    public boolean hasPawnCapture() {
        return pawnCaptures;
    }

    public void setPawnMoves(boolean value) {
        this.pawnMoves = value;
    }

    public void setPawnCaptures(boolean value) {
        this.pawnCaptures = value;
    }

    public void addKnightMove(int dx, int dy) {
        knightJumps.add(new int[]{dx, dy});
    }

    public void removeKnightMove(int dx, int dy) {
        knightJumps.removeIf(j -> j[0] == dx && j[1] == dy);
    }

    public CustomPiece copy(int newRow, int newCol) {
        CustomPiece cp = new CustomPiece(newRow, newCol, this.getSprite(), this.isWhite(), this.isSandboxMode);
        cp.slideDirections = new ArrayList<>(this.slideDirections);
        cp.knightJumps = new ArrayList<>(this.knightJumps);
        cp.customJumps = new ArrayList<>(this.customJumps);
        cp.pawnMoves = this.pawnMoves;
        cp.pawnCaptures = this.pawnCaptures;
        return cp;
    }

    @Override //generated by AI for
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
