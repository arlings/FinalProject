package pieces;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class CustomPiece extends AbstractPiece {

    private boolean pawnMoves = false;
    private boolean pawnCaptures = false;
    private boolean isFirstMove = true;
    private ArrayList<int[]> slideDirections = new ArrayList<>();
    private ArrayList<int[]> knightJumps = new ArrayList<>();
    private ArrayList<Move> customJumps = new ArrayList<>();

    public CustomPiece(int row, int col, BufferedImage sprite, boolean isWhite) {
        super(row, col, sprite, isWhite, 0);
    }

    public void addMoveRules(String preset) {
        // Rook-like
        if (preset.equalsIgnoreCase("UP_ROOK")) {
            slideDirections.add(new int[]{-1, 0});
        } else if (preset.equalsIgnoreCase("DOWN_ROOK")) {
            slideDirections.add(new int[]{1, 0});
        } else if (preset.equalsIgnoreCase("LEFT_ROOK")) {
            slideDirections.add(new int[]{0, -1});
        } else if (preset.equalsIgnoreCase("RIGHT_ROOK")) {
            slideDirections.add(new int[]{0, 1});
        } // Bishop-like
        else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
            slideDirections.add(new int[]{-1, -1});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{-1, 1});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_BISHOP")) {
            slideDirections.add(new int[]{1, -1});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_BISHOP")) {
            slideDirections.add(new int[]{1, 1});
        } // Knight-like
        else if (preset.equalsIgnoreCase("TOP_LEFT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{-2, -1});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{-2, 1});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{2, -1});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_VERT_KNIGHT")) {
            knightJumps.add(new int[]{2, 1});
        } else if (preset.equalsIgnoreCase("TOP_LEFT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{-1, -2});
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{-1, 2});
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{1, -2});
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_HORZ_KNIGHT")) {
            knightJumps.add(new int[]{1, 2});
        } // Pawn-like
        else if (preset.equalsIgnoreCase("PAWN_MOVE")) {
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
    public void removeMoveRules(String preset) {
        // Rook-like
        if (preset.equalsIgnoreCase("UP_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == 0);
        } else if (preset.equalsIgnoreCase("DOWN_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == 0);
        } else if (preset.equalsIgnoreCase("LEFT_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 0 && dir[1] == -1);
        } else if (preset.equalsIgnoreCase("RIGHT_ROOK")) {
            slideDirections.removeIf(dir -> dir[0] == 0 && dir[1] == 1);
        } // Bishop-like
        else if (preset.equalsIgnoreCase("TOP_LEFT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == -1);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == -1 && dir[1] == 1);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == -1);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_BISHOP")) {
            slideDirections.removeIf(dir -> dir[0] == 1 && dir[1] == 1);
        } // Knight-like
        else if (preset.equalsIgnoreCase("TOP_LEFT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -2 && j[1] == -1);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -2 && j[1] == 1);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 2 && j[1] == -1);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_VERT_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 2 && j[1] == 1);
        } else if (preset.equalsIgnoreCase("TOP_LEFT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -1 && j[1] == -2);
        } else if (preset.equalsIgnoreCase("TOP_RIGHT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == -1 && j[1] == 2);
        } else if (preset.equalsIgnoreCase("BOTTOM_LEFT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 1 && j[1] == -2);
        } else if (preset.equalsIgnoreCase("BOTTOM_RIGHT_HORZ_KNIGHT")) {
            knightJumps.removeIf(j -> j[0] == 1 && j[1] == 2);
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

    public void clearRules() {
        this.slideDirections.clear();
        this.knightJumps.clear();
        this.customJumps.clear();
        this.pawnMoves = false;
        this.pawnCaptures = false;
    }

    public void addMoveRules(int dx, int dy) {
        customJumps.add(new Move(dx, dy));
    }

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

    private void searchPawnForward(ArrayList<Move> moves, Piece[][] pieces, int targetRow, int targetCol, int direction) {
        if (isInsideBoard(targetRow, targetCol)) {
            Piece pieceForward = getPieceAt(targetRow, targetCol, pieces);
            if (pieceForward == null) {
                moves.add(new Move(targetRow, targetCol));
            }
        }
    }

    private void searchPawnCaptures(ArrayList<Move> moves, Piece[][] pieces, int targetRow, int targetCol, int direction) {
        int[] captureCols = {columnNum - 1, columnNum + 1};
        for (int col : captureCols) {
            if (isInsideBoard(targetRow, col)) {
                Piece targetPiece = getPieceAt(targetRow, col, pieces);
                if (targetPiece != null && targetPiece.isWhite() != this.isWhite()) {
                    moves.add(new Move(targetRow, col));
                }
            }
        }
    }

    @Override
    public ArrayList<Move> getValidMoves(Piece[][] pieces) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] dir : slideDirections) {
            searchDirection(moves, pieces, rowNum, columnNum, dir[0], dir[1]);
        }
        for (int[] jump : knightJumps) {
            knightJump(moves, pieces, jump[0], jump[1]);
        }

        for (Move m : customJumps) {
            int targetRow = rowNum + m.getRowNum();
            int targetCol = columnNum + m.getColumnNum();

            if (isInsideBoard(targetRow, targetCol)) {
                Piece target = getPieceAt(targetRow, targetCol, pieces);
                // Standard check: empty or opponent
                if (target == null || target.isWhite() != this.isWhite()) {
                    moves.add(new Move(targetRow, targetCol));
                }
            }
        }
        if (pawnMoves || pawnCaptures) {
            int direction = this.isWhite() ? 1 : -1;

            if (pawnMoves) {
                // Forward move: Relative row + direction
                searchPawnForward(moves, pieces, rowNum + direction, columnNum, direction);

                // Two-step: Relative row + (2 * direction)
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
                // Captures: Relative row + direction, Left/Right columns
                searchPawnCaptures(moves, pieces, rowNum + direction, columnNum, direction);
            }
        }
        return moves;
    }
}
