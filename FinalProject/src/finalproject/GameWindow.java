/*
L Necakov, Arlind Zalli, Neo Wang
May 21- June 10
Game window of chess, for the competitive pane.
 */
package finalproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Move;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import repo.FileImporter;
import javax.swing.JOptionPane;
import pieces.CustomPiece;

public class GameWindow extends javax.swing.JFrame implements ActionListener {

    private PromotionWindow promotionWindow;
    private SandboxWindow sandboxWindow;
    private MainWindow mainWindow;
    private EnterUsername enterUsername;
    private WarningWindow warningWindow;
    private FileImporter fileImporter = new FileImporter();
    private boolean isSandbox;

    private Piece selectedPiece = null;
    private Move selectedPos = null;

    public JLabel[][] board = new JLabel[8][8];
    public Piece[][] pieces = new Piece[8][8];
    public Piece customPiece;
    private String selectedPieceType = "";
    private String customPieceFilePath = "";

    private boolean whiteTurn = true;

    private int totalTime;
    private int whiteTime;
    private int blackTime;
    private Timer matchTimer;
    private boolean ended = false;

    private User[] users = new User[2];

    private int promotionCol;
    private int promotionRow;
    private boolean promotionIsWhite;

    /**
     * A method designed to move the frame white preventing the user from
     * accessing a hard-coded way to exit the frame.
     * https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
     * was used as a resource to find a clean way to hide the top menu of the
     * window.
     */
    public void MoveJFrame() {
        //removes top frame borders and sets custom drag click mouse tracking listeners
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * get the promotion column
     *
     * @return - the promotion column
     */
    public int getPromotionCol() {
        return promotionCol;
    }

    /**
     * get the promotion row
     *
     * @return - the promotion row
     */
    public int getPromotionRow() {
        return promotionRow;
    }

    /**
     * get is the promotion is white
     *
     * @return - true if white and false if black
     */
    public boolean getPromotionIsWhite() {
        return promotionIsWhite;
    }

    /**
     * Game window constructor
     *
     * @param m - main window
     * @param user2- user 2
     * @param user1 - user 1
     * @param chosenTime - time of game
     * @param isSandbox - if it is a sandbox
     */
    public GameWindow(MainWindow m, User user2, User user1, int chosenTime, boolean isSandbox) {
        //starts game setups and sets user labels for main window path
        this.isSandbox = isSandbox;
        startGame(user1, user2, chosenTime);
        users[0] = user1;
        users[1] = user2;
        mainWindow = m;
    }

    /**
     * Game Window constructor
     *
     * @param m - main window
     * @param user2 - user 2
     * @param user1 - user 1
     * @param chosenTime - time of game
     * @param isSandbox - if it is a sandbox
     * @param pieces - 2d array of pieces
     */
    public GameWindow(MainWindow m, User user2, User user1, int chosenTime, boolean isSandbox, Piece[][] pieces) {
        //starts game setups and sets user labels for main window path
        this.isSandbox = isSandbox;
        startGame(user1, user2, chosenTime);
        users[0] = user1;
        users[1] = user2;
        mainWindow = m;
    }

    /**
     * GameWindow constructor
     *
     * @param m - sandbox window
     * @param user2 - user 2
     * @param user1 - user 1
     * @param chosenTime- time of game
     * @param isSandbox- if mode is sandbox
     * @param customPiece - custom piece
     */
    public GameWindow(SandboxWindow m, User user2, User user1, int chosenTime, boolean isSandbox, Piece customPiece, String customPieceFilePath) {
        //starts game setups and sets user labels for sandbox path
        this.isSandbox = isSandbox;
        this.customPieceFilePath = customPieceFilePath;
        this.customPiece = customPiece;
        startGame(user1, user2, chosenTime);
        users[0] = user1;
        users[1] = user2;
        sandboxWindow = m;
    }

    /**
     * Game window constructor
     *
     * @param m - enter username window
     * @param user2 - user 2
     * @param user1 - user 1
     * @param chosenTime - time of game
     * @param isSandbox - if sandbox
     */
    public GameWindow(EnterUsername m, User user2, User user1, int chosenTime, boolean isSandbox) {
        //starts game setups and sets user labels for username entry path
        this.isSandbox = isSandbox;
        startGame(user1, user2, chosenTime);
        users[0] = user1;
        users[1] = user2;
        enterUsername = m;
    }

    /**
     * updates chess game when clicked
     *
     * @param row - row
     * @param col - col
     */
    private void handleClick(int row, int col) {
        //if timer is null or if timer is not running clicks are ignored
        if (matchTimer == null || !matchTimer.isRunning()) {
            return;
        }

        //if no piece is selected yet click selects a piece if it matches turn color
        if (selectedPiece == null) {
            //checks if clicked spot has a piece and if piece color matches current turn
            if (pieces[row][col] != null && pieces[row][col].isWhite() == whiteTurn) {
                //saves clicked piece and position variables then shows valid move dots
                selectedPiece = pieces[row][col];
                selectedPos = new Move(row, col);
                getValidMoves(selectedPiece);
            }
            return;
        }

        //if clicking another piece belonging to the current player it switches selection
        if (isCurrentPlayerPiece(row, col)) {
            //updates selected piece and position to the new piece and redraws move indicators
            selectedPiece = pieces[row][col];
            selectedPos = new Move(row, col);
            getValidMoves(selectedPiece);
            return;
        }

        ArrayList<Move> rawMoves = selectedPiece.getValidMoves(pieces); //AI idea to use raw moves instead of valid moves
        boolean isValidMove = false; //AI idea to use boolean flag isValidMove
        Move attemptedMove = null;
        //loops through all raw moves to check if clicked square matches any valid destinations
        for (Move move : rawMoves) {
            //if row and column match the clicked spot then move is found in list
            if (move.getRowNum() == row && move.getColumnNum() == col) {
                //sets valid flag to true and stores the specific move object
                isValidMove = true;
                attemptedMove = move;
                break;
            }
        }
        //AI assisted, fixed my previous errors in this if chain with isValidMove boolean flag
        if (isValidMove && attemptedMove != null) {
            //checks if move is legal and does not put or leave own king in check
            if (isMoveLegal(selectedPos, attemptedMove, whiteTurn)) {
                //executes the move and clears selected tracking variables
                movePiece(selectedPos, attemptedMove);
                selectedPiece = null;
                selectedPos = null;
            } else {
                //resets board graphics and finds king to flash red because move was illegal
                updateBoardUI();
                Piece king = findKing(pieces, whiteTurn);
                //if king is found it highlights the check indicator with a flash
                if (king != null) {
                    highlightCheck(king, true);
                }
                //clears selection variables after failed illegal move attempt
                selectedPiece = null;
                selectedPos = null;
            }
        } else {
            //clears selection and updates board ui if click was on an entirely invalid square
            selectedPiece = null;
            selectedPos = null;
            updateBoardUI();
        }
    }

    /**
     * moves piece
     *
     * @param orgPos- original position
     * @param newPos - final position
     */
    public void movePiece(Move orgPos, Move newPos) {

        int orgRow = orgPos.getRowNum();
        int orgCol = orgPos.getColumnNum();
        int newRow = newPos.getRowNum();
        int newCol = newPos.getColumnNum();

        Piece movingPiece = pieces[orgRow][orgCol];

        //castling logic
        if (movingPiece instanceof King) {
            handleCastlingRookMove(movingPiece, orgCol, newCol, orgRow);
        }
        //if moving piece is a rook it sets moved flag to true to prevent future castling
        if (movingPiece instanceof Rook) {
            Rook rook = (Rook) movingPiece;
            rook.setHasMoved(true);
        }

        //enpassant logic
        handleEnPassant(movingPiece, orgRow, orgCol, newRow, newCol);

        //updates internal row and column variables of the moving piece
        movingPiece.setRowNum(newRow);
        movingPiece.setColumnNum(newCol);

        //moves piece reference to new array spot and clears the original slot
        pieces[newRow][newCol] = movingPiece;
        pieces[orgRow][orgCol] = null;

        if (movingPiece instanceof Pawn) {
            checkIfPromoted((Pawn) movingPiece);
        }
        whiteTurn = !whiteTurn;
        performPostMoveChecks(whiteTurn);
    }

    /**
     * gets the valid moves
     *
     * @param piece - a piece
     */
    public void getValidMoves(Piece piece) {

        //wipes board markers before drawing new valid movement indicators
        updateBoardUI();

        //if piece is null it stops execution immediately
        if (piece == null) {
            return;
        }

        ArrayList<Move> validMoves = piece.getValidMoves(pieces);

        BufferedImage img = loadImage("/images/LightGreenPicture.png");
        ImageIcon icon = new ImageIcon(img);

        //loops through every valid move coordinate inside the list
        for (Move move : validMoves) {

            int row = move.getRowNum();
            int col = move.getColumnNum();

            //if there is a piece at destination it combines image overlays for capture dots
            if (pieces[row][col] != null) {
                board[row][col].setIcon(overlayImages(pieces[row][col], img));
            } else {
                //if square is empty it sets icon to standard green move dot image
                board[row][col].setIcon(icon);
            }
        }
    }

    /**
     * handle enPassant
     *
     * @param movingPiece- the moving piece
     * @param orgRow- the original row
     * @param orgCol - the original column
     * @param newRow - the new row
     * @param newCol - the new column
     */
    private void handleEnPassant(Piece movingPiece, int orgRow, int orgCol, int newRow, int newCol) {
        //if moving piece is pawn and changes columns and lands on an empty square then it is enpassant capture
        if (movingPiece instanceof Pawn && orgCol != newCol && pieces[newRow][newCol] == null) {
            //sets enemy pawn to null to capture it from original row and new column location
            pieces[orgRow][newCol] = null;
        }

        //if moving piece is a pawn we handle first move and double step eligibility
        if (movingPiece instanceof Pawn) {
            Pawn pawn = (Pawn) movingPiece;
            //sets pawn first move to false since it just moved
            pawn.setFirstMove(false);
            //if row distance is exactly two squares then pawn is eligible to be captured via enpassant
            if (Math.abs(newRow - orgRow) == 2) {
                //sets enpassant eligibility to true for this pawn
                pawn.setEnPassantEligible(true);
            }
        }
        //loops through entire board rows
        for (int r = 0; r < 8; r++) {
            //loops through entire board columns
            for (int c = 0; c < 8; c++) {
                Piece piece = pieces[r][c];
                //if the piece is not the one currently moving and is a pawn we clear its flag
                if (piece != movingPiece && piece instanceof Pawn) {
                    Pawn pawn = (Pawn) piece;
                    //sets enpassant eligible to false since it expired after one turn
                    pawn.setEnPassantEligible(false);
                }
            }
        }
    }

    /**
     * handle castling
     *
     * @param king - king piece
     * @param orgCol - original column
     * @param newCol- new column
     * @param row - row of king
     */
    private void handleCastlingRookMove(Piece king, int orgCol, int newCol, int row) {
        //if king column increases by two then it is a kingside castle move
        if (newCol - orgCol == 2) {
            Piece rook = pieces[row][7];
            //if rook exists it moves rook from column 7 to column 5 and flags it as moved
            if (rook != null) {
                pieces[row][5] = rook;
                pieces[row][7] = null;
                rook.setColumnNum(5);
                ((Rook) rook).setHasMoved(true);
            }
            //if king column decreases by two then it is a queenside castle move
        } else if (orgCol - newCol == 2) {
            Piece rook = pieces[row][0];
            //if rook exists it moves rook from column 0 to column 3 and flags it as moved
            if (rook != null) {
                pieces[row][3] = rook;
                pieces[row][0] = null;
                rook.setColumnNum(3);
                ((Rook) rook).setHasMoved(true);
            }
        }
        //sets king moved variable to true so castling cannot be performed again
        ((King) king).setHasMoved(true);
    }

    /**
     * check if king is in check
     *
     * @param king - the king piece
     * @param pieces - 2d array of pieces
     * @return - true if in check and false otherwise
     */
    private boolean isKingInCheck(Piece king, Piece pieces[][]) {
        int kingRow = king.getRowNum();
        int kingCol = king.getColumnNum();
        boolean opponentColour = !king.isWhite();

        //loops through rows to scan for enemy pieces that threaten king position
        for (int r = 0; r < 8; r++) {
            //loops through columns to scan for enemy pieces that threaten king position
            for (int c = 0; c < 8; c++) {
                //if board square has a piece and its color matches the opponent color parameter
                if (pieces[r][c] != null && pieces[r][c].isWhite() == opponentColour) {
                    ArrayList<Move> validMoves = pieces[r][c].getValidMoves(pieces);
                    //loops through all valid moves of that enemy piece to see if it hits king square
                    for (Move move : validMoves) {
                        //if enemy move row and column match king coordinates then king is in check
                        if (move.getRowNum() == kingRow && move.getColumnNum() == kingCol) {
                            return true;
                        }
                    }
                }
            }
        }
        //returns false if no enemy piece has a valid move landing on king position
        return false;
    }

    /**
     * find the king
     *
     * @param pieces - 2d array of pieces
     * @param isWhite - true if on white team and false if on black team
     * @return - king piece
     */
    private Piece findKing(Piece pieces[][], boolean isWhite) {
        //loops rows of the passed board matrix array

        for (int r = 0; r < pieces.length; r++) {
            //loops columns of the passed board matrix array
            for (int c = 0; c < pieces[0].length; c++) {
                //if spot is not null and is a king instance matching the searched color flag
                if (pieces[r][c] != null && pieces[r][c] instanceof King
                        && pieces[r][c].isWhite() == isWhite) {
                    //returns the king piece object reference once found
                    return pieces[r][c];
                }
            }
        }
        return null;
    }

    /**
     * check if a move is legal
     *
     * @param orgPos - original position
     * @param futurePos - future position
     * @param isWhite - team of the piece
     * @return - true if it is a valid move and false otherwise
     */
    private boolean isMoveLegal(Move orgPos, Move futurePos, boolean isWhite) {
        //creates cloned virtual board to simulate move without changing real game state
        Piece temp[][] = copyBoard(pieces);
        Piece testPiece = temp[orgPos.getRowNum()][orgPos.getColumnNum()];

        int targetRow = futurePos.getRowNum();
        int targetCol = futurePos.getColumnNum();
        int savedRow = testPiece.getRowNum();
        int savedCol = testPiece.getColumnNum();

        //if piece is king it runs castling security checks to prevent moving through attacked squares
        if (testPiece instanceof King) {
            int orgCol = orgPos.getColumnNum();

            //if king jumps two columns right it checks columns 4 5 and 6 for enemy threats
            if (targetCol - orgCol == 2) {
                //if any castling path squares are attacked it returns false to block the move
                if (isSquareAttacked(savedRow, 4, isWhite)
                        || isSquareAttacked(savedRow, 5, isWhite)
                        || isSquareAttacked(savedRow, 6, isWhite)) {
                    return false;
                }
                //if king jumps two columns left it checks columns 4 3 and 2 for enemy threats
            } else if (orgCol - targetCol == 2) {
                //if any castling path squares are attacked it returns false to block the move
                if (isSquareAttacked(savedRow, 4, isWhite)
                        || isSquareAttacked(savedRow, 3, isWhite)
                        || isSquareAttacked(savedRow, 2, isWhite)) {
                    return false;
                }
            }
        }

        //executes move on the temporary simulation board matrix array
        temp[targetRow][targetCol] = testPiece;
        temp[orgPos.getRowNum()][orgPos.getColumnNum()] = null;
        //updates simulated piece position variables to match the target square
        testPiece.setRowNum(targetRow);
        testPiece.setColumnNum(targetCol);

        Piece king = findKing(temp, isWhite);
        //checks if own king is in check after the simulated move is completed
        boolean inCheck = isKingInCheck(king, temp);

        //reverts internal piece coordinates back to original state to protect real piece data
        testPiece.setRowNum(savedRow);
        testPiece.setColumnNum(savedCol);

        //returns true if move did not leave or put own king in check state
        return !inCheck;
    }

    /**
     * copy the board
     *
     * @param original- original 2d array board
     * @return - copied 2d array board
     */
    private Piece[][] copyBoard(Piece[][] original) {
        Piece[][] copy = new Piece[8][8];
        //loops rows to duplicate references from original piece array into copy array
        for (int r = 0; r < copy.length; r++) {
            //loops columns to duplicate references from original piece array into copy array
            for (int c = 0; c < copy[0].length; c++) {
                copy[r][c] = original[r][c];
            }
        }
        //returns the newly populated board reference copy matrix array
        return copy;
    }

    /**
     * check if a square is attacked
     *
     * @param row - row
     * @param col- col
     * @param isKingWhite- boolean of if king is white or not
     * @return
     */
    private boolean isSquareAttacked(int row, int col, boolean isKingWhite) {
        boolean opponentColor = !isKingWhite;

        //loops rows to check if any opponent pieces can strike the targeted square
        for (int r = 0; r < 8; r++) {
            //loops columns to check if any opponent pieces can strike the targeted square
            for (int c = 0; c < 8; c++) {
                Piece enemyPiece = pieces[r][c];

                //if enemy piece exists and color matches opponent color configuration
                if (enemyPiece != null && enemyPiece.isWhite() == opponentColor) {
                    // Get the raw valid moves for the enemy piece
                    ArrayList<Move> enemyMoves = enemyPiece.getValidMoves(pieces);
                    //loops through enemy moves to check if any match the targeted check square
                    for (Move move : enemyMoves) {
                        //if enemy move row and column equal the square arguments it returns true
                        if (move.getRowNum() == row && move.getColumnNum() == col) {
                            return true;
                        }
                    }
                }
            }
        }
        //returns false if no enemy piece can legally move to or attack the square
        return false;
    }

    /**
     * start the game
     *
     * @param user1 - user 1
     * @param user2- user 2
     * @param chosenTime - chosen time
     */
    private void startGame(User user1, User user2, int chosenTime) {
        MoveJFrame();
        initComponents();
        board = loadBoard();
        java.awt.Dimension currentSize = this.getSize();
        if (isSandbox) {
            JOptionPane.showMessageDialog(null, "Sandbox Rules"
                    + "\n1. Select any piece from the piece select side bar to add it"
                    + "\n2. Right click to add a piece, left click to remove a piece"
                    + "\n3. To start the game, there must be EXACTLY 1 white and 1 black king on the board"
                    + "\n4. Custom Pieces will be highlighted yellow for clarity while playing"
                    + "\nHave fun customising the board and using your custom piece!");

            customPieceBtn.setIcon(new ImageIcon(customPiece.getSprite()));
        } else {
            pieces = loadPieces(user1.getSkin(), user2.getSkin());
            this.remove(pieceSelectPanel);
            this.setSize(currentSize.width - 135, currentSize.height);
        }
        this.revalidate();
        this.repaint();
        updateBoardUI();
        setPlayerNames(user1.getUserName(), user2.getUserName());

        //assigns player labels and sets time variables from game settings parameters
        this.user1Lbl.setText(user1.getUserName());
        this.user2Lbl.setText(user2.getUserName());
        this.totalTime = chosenTime;
        this.whiteTime = chosenTime;
        this.blackTime = chosenTime;
        //updates timer view elements and adjusts button enablement flags
        updateTime();
        startGameBtn.setEnabled(true);
        //zeros out original side panel advantage text displays
        whiteAdvantageLbl.setText("+0");
        blackAdvantageLbl.setText("+0");
    }

    /**
     * reset the game
     *
     * @param user1 - user 1
     * @param user2 - user 2
     */
    private void resetGame(User user1, User user2) {
        //halts game timer loop and sets turn flags back to default white player
        matchTimer.stop();
        whiteTurn = true;
        whiteTime = totalTime;
        blackTime = totalTime;
        //clears selection variables and completely recreates fresh piece layouts
        selectedPiece = null;
        selectedPos = null;
        pieces = new Piece[8][8];
        pieces = loadPieces(user1.getSkin(), user2.getSkin());
        //refreshes panel labels and resets timer readouts and interaction buttons
        updateBoardUI();
        updateTime();
        startGameBtn.setEnabled(true);
        whiteAdvantageLbl.setText("+0");
        blackAdvantageLbl.setText("+0");
    }

    /**
     * load the pieces
     *
     * @param blackSkin- black skin
     * @param whiteSkin- white skin
     * @return - 2d array of pieces
     */
    private Piece[][] loadPieces(String blackSkin, String whiteSkin) {

        //loops row columns index up to eight to fill row one and six with pawns
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(1, i, loadImage("/images/" + whiteSkin + "White_Pawn.png"), true);
            pieces[6][i] = new Pawn(6, i, loadImage("/images/" + blackSkin + "Black_Pawn.png"), false);
        }

        //sets up rooks on back corners of the chess piece matrix board array
        pieces[0][0] = new Rook(0, 0, loadImage("/images/" + whiteSkin + "White_Rook.png"), true, false);
        pieces[0][7] = new Rook(0, 7, loadImage("/images/" + whiteSkin + "White_Rook.png"), true, false);
        pieces[7][0] = new Rook(7, 0, loadImage("/images/" + blackSkin + "Black_Rook.png"), false, false);
        pieces[7][7] = new Rook(7, 7, loadImage("/images/" + blackSkin + "Black_Rook.png"), false, false);

        //sets up knights on indices one and six of the back rows
        pieces[0][1] = new Knight(0, 1, loadImage("/images/" + whiteSkin + "White_Knight.png"), true);
        pieces[0][6] = new Knight(0, 6, loadImage("/images/" + whiteSkin + "White_Knight.png"), true);
        pieces[7][1] = new Knight(7, 1, loadImage("/images/" + blackSkin + "Black_Knight.png"), false);
        pieces[7][6] = new Knight(7, 6, loadImage("/images/" + blackSkin + "Black_Knight.png"), false);

        //sets up bishops on indices two and five of the back rows
        pieces[0][2] = new Bishop(0, 2, loadImage("/images/" + whiteSkin + "White_Bishop.png"), true);
        pieces[0][5] = new Bishop(0, 5, loadImage("/images/" + whiteSkin + "White_Bishop.png"), true);
        pieces[7][2] = new Bishop(7, 2, loadImage("/images/" + blackSkin + "Black_Bishop.png"), false);
        pieces[7][5] = new Bishop(7, 5, loadImage("/images/" + blackSkin + "Black_Bishop.png"), false);

        //sets up queens on the column position three coordinates
        pieces[0][3] = new Queen(0, 3, loadImage("/images/" + whiteSkin + "White_Queen.png"), true);
        pieces[7][3] = new Queen(7, 3, loadImage("/images/" + blackSkin + "Black_Queen.png"), false);

        //sets up kings on the column position four coordinates with tracking variables
        pieces[0][4] = new King(0, 4, loadImage("/images/" + whiteSkin + "White_King.png"), true, false, false, false);
        pieces[7][4] = new King(7, 4, loadImage("/images/" + blackSkin + "Black_King.png"), false, false, false, false);

        //returns the fully configured initial piece position matrix array
        return pieces;
    }

    /**
     * Load the board in based on the jLabels.
     *
     * @return - the board
     */
    private JLabel[][] loadBoard() {
        //returns a hardcoded label mapping matching rows and columns layout of grid swing panel
        return new JLabel[][]{
            {A1Label, B1Label, C1Label, D1Label, E1Label, F1Label, G1Label, H1Label},
            {A2Label, B2Label, C2Label, D2Label, E2Label, F2Label, G2Label, H2Label},
            {A3Label, B3Label, C3Label, D3Label, E3Label, F3Label, G3Label, H3Label},
            {A4Label, B4Label, C4Label, D4Label, E4Label, F4Label, G4Label, H4Label},
            {A5Label, B5Label, C5Label, D5Label, E5Label, F5Label, G5Label, H5Label},
            {A6Label, B6Label, C6Label, D6Label, E6Label, F6Label, G6Label, H6Label},
            {A7Label, B7Label, C7Label, D7Label, E7Label, F7Label, G7Label, H7Label},
            {A8Label, B8Label, C8Label, D8Label, E8Label, F8Label, G8Label, H8Label}
        };
    }

    //AI idea to create a new method for highlighting checks, implemented manually
    private void highlightCheck(Piece king, boolean flash) {
        int r = king.getRowNum();
        int c = king.getColumnNum();
        BufferedImage redOverlay = loadImage("/images/RedBackground.png");
        //sets combined red overlay image onto king square label to show threat status
        board[r][c].setIcon(overlayImages(king, redOverlay));

        //if flash parameter is true a non repeating short swing timer triggers a rapid refresh
        if (flash) {
            Timer timer = new Timer(200, (ActionListener) this);
            timer.setRepeats(false);
            timer.start();
        }
    }

    /**
     * Check if the king is in checkmate.
     *
     * @param king the king as an object of type King
     * @param isWhiteTurn if it is the white teams turn
     * @return true if in check mate and false otherwise
     */
    private boolean isCheckmate(Piece king, boolean isWhiteTurn) {
        //if king reference is missing or if king is not currently checked it is not checkmate
        if (king == null || !isKingInCheck(king, pieces)) {
            return false;
        }

        //loops rows of board to scan all friendly pieces for a saving escape move
        for (int r = 0; r < board.length; r++) {
            //loops columns of board to scan all friendly pieces for a saving escape move
            for (int c = 0; c < board[0].length; c++) {
                Piece piece = pieces[r][c];
                //if piece belongs to current player turn it tests all its possible target moves
                if (piece != null && piece.isWhite() == isWhiteTurn) {
                    ArrayList<Move> validMoves = piece.getValidMoves(pieces);
                    //loops through moves to see if any move option breaks check state
                    for (Move move : validMoves) {
                        Move currentPos = new Move(r, c);
                        //if a single move is legal it means king can escape and it is not checkmate
                        if (isMoveLegal(currentPos, move, isWhiteTurn)) {
                            return false;
                        }
                    }
                }
            }
        }
        //returns true if king is in check and absolutely no legal move stops the threat
        return true;
    }

    /**
     * check if it is a stalemate
     *
     * @param king - the king
     * @param isWhiteTurn - if it is white's turn
     * @return - true if it is a stalemate and false otherwise
     */
    private boolean isStalemate(Piece king, boolean isWhiteTurn) {
        //if king reference is missing or if king is checked it is not stalemate
        if (king == null || isKingInCheck(king, pieces)) {
            return false;
        }

        //loops rows of board to scan all friendly pieces for a saving escape move
        for (int r = 0; r < board.length; r++) {
            //loops columns of board to scan all friendly pieces for a saving escape move
            for (int c = 0; c < board[0].length; c++) {
                Piece piece = pieces[r][c];
                //if piece belongs to current player turn it tests all its possible target moves
                if (piece != null && piece.isWhite() == isWhiteTurn) {
                    ArrayList<Move> validMoves = piece.getValidMoves(pieces);
                    //loops through moves to see if any move option breaks check state
                    for (Move move : validMoves) {
                        Move currentPos = new Move(r, c);
                        //if a single move is legal it means king can escape and it is not checkmate
                        if (isMoveLegal(currentPos, move, isWhiteTurn)) {
                            return false;
                        }
                    }
                }
            }
        }
        //returns true if king is in check and absolutely no legal move stops the threat
        return true;
    }

    /**
     * maintains the red in when king is in check
     *
     * @param e - action event
     */
    public void actionPerformed(ActionEvent e) {
        //if chain is AI, fixes some King UI flashing issues
        Piece activeKing = findKing(pieces, whiteTurn);
        //if king is found and remains in check it continues showing the red alert box overlay
        if (activeKing != null && isKingInCheck(activeKing, pieces)) {
            highlightCheck(activeKing, false);
        } else {
            //refreshes ui icons normally if king is safe and no flashing timer is active
            updateBoardUI();
        }
    }

    /**
     * updates the board
     */
    private void updateBoardUI() {
        //loops through all board rows to synchronize graphic frame boxes
        for (int row = 0; row < 8; row++) {
            //loops through all board columns to synchronize graphic frame boxes
            for (int col = 0; col < 8; col++) {
                Piece piece = pieces[row][col];
                if (piece instanceof CustomPiece) {
                    board[row][col].setOpaque(true);
                    board[row][col].setBackground(new Color(255, 255, 180)); // light yellow
                } else {
                    board[row][col].setOpaque(false);
                }
                //if a piece sits on this array slot it pulls its sprite image file
                if (piece != null) {
                    BufferedImage img = piece.getSprite();
                    //if image loads correctly icon is mapped to label else sets transparent icon
                    if (img != null) {
                        board[row][col].setIcon(new ImageIcon(img));
                    } else {
                        setTransparentIcon(board[row][col]);
                    }
                } else {
                    //sets empty box to transparent image asset if no piece occupies array square
                    setTransparentIcon(board[row][col]);
                }

                //forces layout manager to redraw and recalculate box label visuals
                board[row][col].revalidate();
                board[row][col].repaint();
            }
        }
    }

    /**
     * overlay images
     *
     * @param piece -the piece
     * @param dot - the dot image
     * @return - image icon
     */
    private ImageIcon overlayImages(Piece piece, BufferedImage dot) {

        //if piece object is null it just prints the plain dot indicator image
        if (piece == null) {
            return new ImageIcon(dot);
        }

        BufferedImage base = piece.getSprite();

        //creates container canvas matching width and height dimensions of base piece image
        BufferedImage combined = new BufferedImage(
                base.getWidth(),
                base.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        //draws piece sprite onto canvas first then paints indicator dot directly on top
        java.awt.Graphics g = combined.getGraphics();
        g.drawImage(base, 0, 0, null);
        g.drawImage(dot, 0, 0, null);
        g.dispose();

        //returns the merged combined image wrapped inside a swing icon object container
        return new ImageIcon(combined);
    }

    /**
     * set the jlabel to transparent
     *
     * @param label - the JLabel
     */
    private void setTransparentIcon(JLabel label) {
        BufferedImage img = loadImage("/images/Transparent_Background.png");

        //assigns transparent asset icon to label or sets null if image fails file stream check
        if (img != null) {
            label.setIcon(new ImageIcon(img));
        } else {
            label.setIcon(null);
        }
    }

    /**
     * load the image
     *
     * @param filePath- the file path
     * @return - the image
     */
    private BufferedImage loadImage(String filePath) {
        try {
            //attempts file import load method using provided file path argument string
            return fileImporter.loadImage(filePath);
        } catch (IOException ex) {
            //logs severe file read exception inside console trace if asset is missing
            Logger.getLogger(GameWindow.class.getName())
                    .log(Level.SEVERE, "Failed to load image: " + filePath, ex);
            return null;
        }
    }

    /**
     * Checks if it is the current player piece
     *
     * @param row- the row
     * @param col- the column
     * @return - true if spot is filled and color field matches current active
     * turn flag
     */
    private boolean isCurrentPlayerPiece(int row, int col) {
        Piece p = pieces[row][col];
        //returns true if spot is filled and color field matches current active turn flag
        return p != null && p.isWhite() == whiteTurn;
    }

    /**
     * Set the player names on the jLabels
     *
     * @param user1 - user 1
     * @param user2 - user 2
     */
    private void setPlayerNames(String user1, String user2) {
        //updates player label elements with configurations fetched from menu windows
        user1Lbl.setText(user1);
        user2Lbl.setText(user2);
    }

    //time methods
    //https://stackoverflow.com/questions/28337718/java-swing-timer-countdown
    /**
     * start the timer
     */
    private void startTimer() {

        //creates countdown timer task executing once every one thousand milliseconds
        matchTimer = new Timer(1000, new ActionListener() {
            /**
             * action when the user clicks on a piece
             */
            public void actionPerformed(ActionEvent e) {

                //if white turn flag is active it decrements white player countdown variable
                if (whiteTurn) {
                    whiteTime--;
                    updateTime();
                    //if white time expires it zeros out variable and stops game with black victory
                    if (whiteTime <= 0) {
                        whiteTime = 0;
                        matchTimer.stop();
                        JOptionPane.showMessageDialog(null, "Black Wins!");
                        GameWindow.this.dispose();
                        if (!isSandbox) {
                            win(false);
                        }
                        ended = true;
                    }
                    //if black turn flag is active it decrements black player countdown variable
                } else {
                    
                    blackTime--;
                    updateTime();
                    //if black time expires it zeros out variable and stops game with white victory
                    if (blackTime <= 0) {
                        blackTime = 0;
                        matchTimer.stop();
                        JOptionPane.showMessageDialog(null, "White Wins!");
                        GameWindow.this.dispose();
                        if (!isSandbox) {
                            win(true);
                        }
                        ended = true;
                    }
                }
                //updates display clocks after updating time tracking counters
                
            }
        });
        //starts game loop thread execution block
        if (ended) {
            this.dispose();
        } else {
            matchTimer.start();
        }
    }

    /**
     * updates the time
     */
    private void updateTime() {
        //formats integer remaining seconds into clock strings for label panels
        whiteTimeLbl.setText(formatTime(whiteTime));
        blackTimeLbl.setText(formatTime(blackTime));
    }

    /**
     * formats the time
     *
     * @param time - the time in ints
     * @return - format
     */
    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        //returns clean string with padded digits for double column clock view
        return minutes + ":" + String.format("%02d", seconds); //https://stackoverflow.com/questions/71706078/is-there-a-way-to-get-always-2-digits-values-with-timestamp-in-java
    }

    /**
     * updates the values
     */
    private void updateValues() {
        int whiteTotal = 0;
        int blackTotal = 0;
        //loops rows to sum up value ratings of all active pieces still on board
        for (int r = 0; r < 8; r++) {
            //loops columns to sum up value ratings of all active pieces still on board
            for (int c = 0; c < 8; c++) {
                Piece piece = pieces[r][c];
                //if piece is found its value rating adds to matching color sum tracker
                if (piece != null) {
                    if (piece.isWhite()) {
                        whiteTotal += piece.getValue();
                    } else {
                        blackTotal += piece.getValue();
                    }
                }
            }
        }
        int difference = whiteTotal - blackTotal;

        //if difference variable is positive white is winning or scores are level
        if (difference >= 0) {//white is winning or tied
            //assigns positive marker text strings to display side advantages
            whiteAdvantageLbl.setText("+" + difference);
            blackAdvantageLbl.setText("-" + difference);
            //if difference is negative black player holds a point advantage
        } else {//black is winning
            //assigns absolute valuation offsets to prevent double negative symbol prints
            whiteAdvantageLbl.setText("-" + Math.abs(difference));
            blackAdvantageLbl.setText("+" + Math.abs(difference));
        }
    }

    /**
     * check if the pawn if promoted
     *
     * @param p - the pawn
     */
    private void checkIfPromoted(Pawn p) {

        if ((p.isWhite() && p.getRowNum() == 7) || (!p.isWhite() && p.getRowNum() == 0)) {//if the pawn is whte and in the seventh row or the pawn is black and in the first row(i.e. eligible to be promoted)
            if (matchTimer != null) {//stop the timer
                matchTimer.stop();
            }
            promotionCol = p.getColumnNum();
            promotionRow = p.getRowNum();
            promotionIsWhite = p.isWhite();

            if (promotionWindow == null) {
                promotionWindow = new PromotionWindow(this, users[0], users[1]);
            }
            promotionWindow.setVisible(true);
        }
    }

    /**
     * Actions took when user has won, based on if it is whites turn or not
     *
     * @param isWhiteTurn - if it is white's turn
     */
    public void win(boolean isWhiteTurn) {
        try {
            // 1. Convert via URI to cleanly parse spaces and special characters
            File usersFile = new File(System.getProperty("user.dir") + "/Users.txt");
            // Convert via URI to cleanly parse spaces and special characters
            java.net.URI jarURI = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            File jarLocation = new File(jarURI);
            String actualFolder = jarLocation.getParent();
            usersFile = new File(actualFolder, "Users.txt");

            // Read all the data into memory first
            String fileContent = "";
            try (Scanner s = new Scanner(usersFile)) {
                if (s.hasNextLine()) {
                    fileContent = s.nextLine();
                }
            }

            // Catch edge-case if the file exists but was left completely empty
            if (fileContent.trim().isEmpty()) {
                throw new FileNotFoundException();
            }

            // Processing player data from the file
            String[] players = fileContent.split(":");
            for (int i = 0; i < players.length; i++) {
                // For each player caught in the files,
                String[] data = players[i].split(",");

                if (!isWhiteTurn) {
                    if (data[0].equalsIgnoreCase(user2Lbl.getText())) {
                        players[i] = data[0] + "," + (Integer.parseInt(data[1]) + 1) + "," + data[2] + "," + data[3] + "," + data[4];
                    }
                } else {
                    if (data[0].equalsIgnoreCase(user1Lbl.getText())) {
                        players[i] = data[0] + "," + (Integer.parseInt(data[1]) + 1) + "," + data[2] + "," + data[3] + "," + data[4];
                    }
                }
                // Update the data files and include proper win and loss credits
            }

            for (int i = 0; i < players.length; i++) {
                String[] data = players[i].split(",");
                if (data.length < 5) {
                    continue;
                }

                if (!isWhiteTurn) {
                    if (data[0].equalsIgnoreCase(user1Lbl.getText())) {
                        players[i] = data[0] + "," + data[1] + "," + data[2] + "," + (Integer.parseInt(data[3]) + 1) + "," + data[4];
                    }
                } else {
                    if (data[0].equalsIgnoreCase(user2Lbl.getText())) {
                        players[i] = data[0] + "," + data[1] + "," + data[2] + "," + (Integer.parseInt(data[3]) + 1) + "," + data[4];
                    }
                }
            }

            // 4. Safely open the output stream and overwrite the file ONLY after processing
            String newFile = String.join(":", players) + ":";
            try (FileOutputStream out = new FileOutputStream(usersFile)) {
                out.write(newFile.getBytes());
            }

            this.dispose();

        } catch (Exception e) {
            warningWindow = new WarningWindow(this, "There was an error with the Users file. Please see user manual for more help. (You probably haven't made any users yet!)");
            warningWindow.setVisible(true);
        } 
    }

    /**
     * Perform post move checks, check if moves can be made after a player is in
     * check.
     *
     * @param isWhiteTurn if it is white's turn
     */
    private void performPostMoveChecks(boolean isWhiteTurn) {
        updateBoardUI();
        updateValues();

        Piece whiteKing = findKing(pieces, true);
        Piece blackKing = findKing(pieces, false);

        //check if in king is in check and if so higlight the king
        if (whiteKing != null && isKingInCheck(whiteKing, pieces)) {
            highlightCheck(whiteKing, false);
        }
        if (blackKing != null && isKingInCheck(blackKing, pieces)) {
            highlightCheck(blackKing, false);
        }
        Piece nextKing = findKing(pieces, isWhiteTurn);
        // check if king is in check mate
        if (isCheckmate(nextKing, isWhiteTurn)) {
            matchTimer.stop();
            JOptionPane.showMessageDialog(null, "Checkmate!");
            //this.dispose();
            ended = true;
            if (!isSandbox) {
                win(isWhiteTurn);
            }

        }

        if (isStalemate(nextKing, isWhiteTurn)) {//check for stalemate
            matchTimer.stop();
            JOptionPane.showMessageDialog(null, "Stalemate! The game is a draw.");
            this.dispose();
        }
    }

    /**
     * Fix the board after promotion happens.
     *
     * @param col The column number as an int
     * @param row The row number as an int
     * @param piece The piece name
     * @param isWhite If on the white team
     * @param whiteSkin The white skin prefix
     * @param blackSkin The black skin prefix
     */
    public void fixBoardAfterPromotion(int col, int row, String piece, boolean isWhite, String whiteSkin, String blackSkin) {
        if (isWhite) {//if the colour is white
            if (piece.equalsIgnoreCase("Queen")) {//if queen create new queen
                pieces[row][col] = new Queen(row, col, loadImage("/images/" + whiteSkin + "White_Queen.png"), isWhite);
            } else if (piece.equalsIgnoreCase("Rook")) {//if rook create new rook
                pieces[row][col] = new Rook(row, col, loadImage("/images/" + whiteSkin + "White_Rook.png"), isWhite, true);
            } else if (piece.equalsIgnoreCase("Bishop")) {//if bishop create new bishop
                pieces[row][col] = new Bishop(row, col, loadImage("/images/" + whiteSkin + "White_Bishop.png"), isWhite);
            } else if (piece.equalsIgnoreCase("Knight")) {//if knight create new knight
                pieces[row][col] = new Knight(row, col, loadImage("/images/" + whiteSkin + "White_Knight.png"), isWhite);
            }
        } else {//if the colour is black
            if (piece.equalsIgnoreCase("Queen")) {//if queen create new queen
                pieces[row][col] = new Queen(row, col, loadImage("/images/" + blackSkin + "Black_Queen.png"), isWhite);
            } else if (piece.equalsIgnoreCase("Rook")) {//if rook create new rook
                pieces[row][col] = new Rook(row, col, loadImage("/images/" + blackSkin + "Black_Rook.png"), isWhite, true);
            } else if (piece.equalsIgnoreCase("Bishop")) {//if bishop create new bishop
                pieces[row][col] = new Bishop(row, col, loadImage("/images/" + blackSkin + "Black_Bishop.png"), isWhite);
            } else if (piece.equalsIgnoreCase("Knight")) {//if knight create new knight
                pieces[row][col] = new Knight(row, col, loadImage("/images/" + blackSkin + "Black_Knight.png"), isWhite);
            }
        }
        performPostMoveChecks(!isWhite);
        boolean nextTurn = !isWhite;
        Piece nextKing = findKing(pieces, nextTurn);

        if (!isCheckmate(nextKing, nextTurn) && !isStalemate(nextKing, nextTurn)) {//if it isn't check mate or stalemate
            matchTimer.start();
            // Switch the turn only if the game is continuing
            whiteTurn = nextTurn;

        }
    }

    private void handleSandboxClick(int r, int c, java.awt.event.MouseEvent evt) {
        if (startGameBtn.isEnabled() && isSandbox) {
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                if (getPieceButton(r, c) != null) {
                    pieces[r][c] = getPieceButton(r, c);
                }
            } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                pieces[r][c] = null;
            }
            updateBoardUI();
        }
    }

    /**
     * Creates a new piece instance based on the currently selected piece type.
     *
     * @param r board row
     * @param c board column
     * @return a new Piece placed at (r, c), or null if no type is selected
     */
    private Piece getPieceButton(int r, int c) {
        boolean isWhite = false;
        String team = "Black";
        if (whiteTeamBtn.isSelected()) {
            isWhite = true;
            team = "White";
        }

        if (selectedPieceType.equals("Pawn")) {
            return (new Pawn(r, c, loadImage("/images/Default" + team + "_Pawn.png"), isWhite));
        } else if (selectedPieceType.equals("Knight")) {
            return (new Knight(r, c, loadImage("/images/Default" + team + "_Knight.png"), isWhite));
        } else if (selectedPieceType.equals("Bishop")) {
            return (new Bishop(r, c, loadImage("/images/Default" + team + "_Bishop.png"), isWhite));
        } else if (selectedPieceType.equals("Rook")) {
            return (new Rook(r, c, loadImage("/images/Default" + team + "_Rook.png"), isWhite, false));
        } else if (selectedPieceType.equals("Queen")) {
            return (new Queen(r, c, loadImage("/images/Default" + team + "_Queen.png"), isWhite));
        } else if (selectedPieceType.equals("King")) {
            return (new King(r, c, loadImage("/images/Default" + team + "_King.png"), isWhite, false, false, false));
        } else if (selectedPieceType.equals("Custom")) {
            CustomPiece cp = ((CustomPiece) customPiece).copy(r, c);
            customPieceFilePath = customPieceFilePath.replace("White", team);
            cp.setWhite(isWhite);
            cp.setSprite(loadImage(customPieceFilePath));
            return cp;

        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        C7 = new javax.swing.JPanel();
        C7Label = new javax.swing.JLabel();
        H3 = new javax.swing.JPanel();
        H3Label = new javax.swing.JLabel();
        G4 = new javax.swing.JPanel();
        G4Label = new javax.swing.JLabel();
        C2 = new javax.swing.JPanel();
        C2Label = new javax.swing.JLabel();
        A4 = new javax.swing.JPanel();
        A4Label = new javax.swing.JLabel();
        D2 = new javax.swing.JPanel();
        D2Label = new javax.swing.JLabel();
        B5 = new javax.swing.JPanel();
        B5Label = new javax.swing.JLabel();
        D7 = new javax.swing.JPanel();
        D7Label = new javax.swing.JLabel();
        E2 = new javax.swing.JPanel();
        E2Label = new javax.swing.JLabel();
        A5 = new javax.swing.JPanel();
        A5Label = new javax.swing.JLabel();
        A7 = new javax.swing.JPanel();
        A7Label = new javax.swing.JLabel();
        F2 = new javax.swing.JPanel();
        F2Label = new javax.swing.JLabel();
        E7 = new javax.swing.JPanel();
        E7Label = new javax.swing.JLabel();
        G2 = new javax.swing.JPanel();
        G2Label = new javax.swing.JLabel();
        F7 = new javax.swing.JPanel();
        F7Label = new javax.swing.JLabel();
        G7 = new javax.swing.JPanel();
        G7Label = new javax.swing.JLabel();
        C5 = new javax.swing.JPanel();
        C5Label = new javax.swing.JLabel();
        D5 = new javax.swing.JPanel();
        D5Label = new javax.swing.JLabel();
        H2 = new javax.swing.JPanel();
        H2Label = new javax.swing.JLabel();
        H6 = new javax.swing.JPanel();
        H6Label = new javax.swing.JLabel();
        B2 = new javax.swing.JPanel();
        B2Label = new javax.swing.JLabel();
        C6 = new javax.swing.JPanel();
        C6Label = new javax.swing.JLabel();
        H7 = new javax.swing.JPanel();
        H7Label = new javax.swing.JLabel();
        B3 = new javax.swing.JPanel();
        B3Label = new javax.swing.JLabel();
        E5 = new javax.swing.JPanel();
        E5Label = new javax.swing.JLabel();
        A8 = new javax.swing.JPanel();
        A8Label = new javax.swing.JLabel();
        A3 = new javax.swing.JPanel();
        A3Label = new javax.swing.JLabel();
        C3 = new javax.swing.JPanel();
        C3Label = new javax.swing.JLabel();
        B1 = new javax.swing.JPanel();
        B1Label = new javax.swing.JLabel();
        B6 = new javax.swing.JPanel();
        B6Label = new javax.swing.JLabel();
        C8 = new javax.swing.JPanel();
        C8Label = new javax.swing.JLabel();
        D6 = new javax.swing.JPanel();
        D6Label = new javax.swing.JLabel();
        C1 = new javax.swing.JPanel();
        C1Label = new javax.swing.JLabel();
        D8 = new javax.swing.JPanel();
        D8Label = new javax.swing.JLabel();
        D3 = new javax.swing.JPanel();
        D3Label = new javax.swing.JLabel();
        F5 = new javax.swing.JPanel();
        F5Label = new javax.swing.JLabel();
        D1 = new javax.swing.JPanel();
        D1Label = new javax.swing.JLabel();
        E8 = new javax.swing.JPanel();
        E8Label = new javax.swing.JLabel();
        H4 = new javax.swing.JPanel();
        H4Label = new javax.swing.JLabel();
        E6 = new javax.swing.JPanel();
        E6Label = new javax.swing.JLabel();
        F8 = new javax.swing.JPanel();
        F8Label = new javax.swing.JLabel();
        C4 = new javax.swing.JPanel();
        C4Label = new javax.swing.JLabel();
        G5 = new javax.swing.JPanel();
        G5Label = new javax.swing.JLabel();
        E3 = new javax.swing.JPanel();
        E3Label = new javax.swing.JLabel();
        B4 = new javax.swing.JPanel();
        B4Label = new javax.swing.JLabel();
        F6 = new javax.swing.JPanel();
        F6Label = new javax.swing.JLabel();
        H5 = new javax.swing.JPanel();
        H5Label = new javax.swing.JLabel();
        A1 = new javax.swing.JPanel();
        A1Label = new javax.swing.JLabel();
        D4 = new javax.swing.JPanel();
        D4Label = new javax.swing.JLabel();
        G6 = new javax.swing.JPanel();
        G6Label = new javax.swing.JLabel();
        F3 = new javax.swing.JPanel();
        F3Label = new javax.swing.JLabel();
        A6 = new javax.swing.JPanel();
        A6Label = new javax.swing.JLabel();
        E1 = new javax.swing.JPanel();
        E1Label = new javax.swing.JLabel();
        E4 = new javax.swing.JPanel();
        E4Label = new javax.swing.JLabel();
        F1 = new javax.swing.JPanel();
        F1Label = new javax.swing.JLabel();
        G3 = new javax.swing.JPanel();
        G3Label = new javax.swing.JLabel();
        G8 = new javax.swing.JPanel();
        G8Label = new javax.swing.JLabel();
        G1 = new javax.swing.JPanel();
        G1Label = new javax.swing.JLabel();
        F4 = new javax.swing.JPanel();
        F4Label = new javax.swing.JLabel();
        H8 = new javax.swing.JPanel();
        H8Label = new javax.swing.JLabel();
        H1 = new javax.swing.JPanel();
        H1Label = new javax.swing.JLabel();
        B8 = new javax.swing.JPanel();
        B8Label = new javax.swing.JLabel();
        A2 = new javax.swing.JPanel();
        A2Label = new javax.swing.JLabel();
        B7 = new javax.swing.JPanel();
        B7Label = new javax.swing.JLabel();
        user1Lbl = new javax.swing.JLabel();
        user2Lbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        whiteTimeLbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        blackTimeLbl = new javax.swing.JLabel();
        startGameBtn = new javax.swing.JButton();
        whiteAdvantageLbl = new javax.swing.JLabel();
        blackAdvantageLbl = new javax.swing.JLabel();
        pieceSelectPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pawnBtn = new javax.swing.JButton();
        knightBtn = new javax.swing.JButton();
        bishopBtn = new javax.swing.JButton();
        rookBtn = new javax.swing.JButton();
        queenBtn = new javax.swing.JButton();
        kingBtn = new javax.swing.JButton();
        whiteTeamBtn = new javax.swing.JRadioButton();
        blackTeamBtn = new javax.swing.JRadioButton();
        customPieceBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        C7.setBackground(new java.awt.Color(153, 102, 0));

        C7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C7Layout = new javax.swing.GroupLayout(C7);
        C7.setLayout(C7Layout);
        C7Layout.setHorizontalGroup(
            C7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C7Layout.setVerticalGroup(
            C7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C7Label)
                .addGap(27, 27, 27))
        );

        H3.setBackground(new java.awt.Color(255, 255, 255));

        H3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H3Layout = new javax.swing.GroupLayout(H3);
        H3.setLayout(H3Layout);
        H3Layout.setHorizontalGroup(
            H3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H3Layout.setVerticalGroup(
            H3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H3Label)
                .addGap(27, 27, 27))
        );

        G4.setBackground(new java.awt.Color(255, 255, 255));

        G4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G4Layout = new javax.swing.GroupLayout(G4);
        G4.setLayout(G4Layout);
        G4Layout.setHorizontalGroup(
            G4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G4Layout.setVerticalGroup(
            G4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G4Label)
                .addGap(27, 27, 27))
        );

        C2.setBackground(new java.awt.Color(255, 255, 255));

        C2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C2Layout = new javax.swing.GroupLayout(C2);
        C2.setLayout(C2Layout);
        C2Layout.setHorizontalGroup(
            C2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C2Layout.setVerticalGroup(
            C2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C2Label)
                .addGap(27, 27, 27))
        );

        A4.setBackground(new java.awt.Color(255, 255, 255));

        A4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A4Layout = new javax.swing.GroupLayout(A4);
        A4.setLayout(A4Layout);
        A4Layout.setHorizontalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A4Layout.setVerticalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A4Label)
                .addGap(27, 27, 27))
        );

        D2.setBackground(new java.awt.Color(153, 102, 0));

        D2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D2Layout = new javax.swing.GroupLayout(D2);
        D2.setLayout(D2Layout);
        D2Layout.setHorizontalGroup(
            D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D2Layout.setVerticalGroup(
            D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D2Label)
                .addGap(27, 27, 27))
        );

        B5.setBackground(new java.awt.Color(255, 255, 255));

        B5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B5Layout = new javax.swing.GroupLayout(B5);
        B5.setLayout(B5Layout);
        B5Layout.setHorizontalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B5Layout.setVerticalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B5Label)
                .addGap(27, 27, 27))
        );

        D7.setBackground(new java.awt.Color(255, 255, 255));

        D7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D7Layout = new javax.swing.GroupLayout(D7);
        D7.setLayout(D7Layout);
        D7Layout.setHorizontalGroup(
            D7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D7Layout.setVerticalGroup(
            D7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D7Label)
                .addGap(27, 27, 27))
        );

        E2.setBackground(new java.awt.Color(255, 255, 255));

        E2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E2Layout = new javax.swing.GroupLayout(E2);
        E2.setLayout(E2Layout);
        E2Layout.setHorizontalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E2Layout.setVerticalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E2Label)
                .addGap(27, 27, 27))
        );

        A5.setBackground(new java.awt.Color(153, 102, 0));

        A5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A5Layout = new javax.swing.GroupLayout(A5);
        A5.setLayout(A5Layout);
        A5Layout.setHorizontalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A5Layout.setVerticalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A5Label)
                .addGap(27, 27, 27))
        );

        A7.setBackground(new java.awt.Color(153, 102, 0));

        A7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A7Layout = new javax.swing.GroupLayout(A7);
        A7.setLayout(A7Layout);
        A7Layout.setHorizontalGroup(
            A7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A7Layout.setVerticalGroup(
            A7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A7Label)
                .addGap(27, 27, 27))
        );

        F2.setBackground(new java.awt.Color(153, 102, 0));

        F2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F2Layout = new javax.swing.GroupLayout(F2);
        F2.setLayout(F2Layout);
        F2Layout.setHorizontalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F2Layout.setVerticalGroup(
            F2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F2Label)
                .addGap(27, 27, 27))
        );

        E7.setBackground(new java.awt.Color(153, 102, 0));

        E7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E7Layout = new javax.swing.GroupLayout(E7);
        E7.setLayout(E7Layout);
        E7Layout.setHorizontalGroup(
            E7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E7Layout.setVerticalGroup(
            E7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E7Label)
                .addGap(27, 27, 27))
        );

        G2.setBackground(new java.awt.Color(255, 255, 255));

        G2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G2Layout = new javax.swing.GroupLayout(G2);
        G2.setLayout(G2Layout);
        G2Layout.setHorizontalGroup(
            G2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G2Layout.setVerticalGroup(
            G2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G2Label)
                .addGap(27, 27, 27))
        );

        F7.setBackground(new java.awt.Color(255, 255, 255));

        F7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F7Layout = new javax.swing.GroupLayout(F7);
        F7.setLayout(F7Layout);
        F7Layout.setHorizontalGroup(
            F7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F7Layout.setVerticalGroup(
            F7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F7Label)
                .addGap(27, 27, 27))
        );

        G7.setBackground(new java.awt.Color(153, 102, 0));

        G7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G7Layout = new javax.swing.GroupLayout(G7);
        G7.setLayout(G7Layout);
        G7Layout.setHorizontalGroup(
            G7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G7Layout.setVerticalGroup(
            G7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G7Label)
                .addGap(27, 27, 27))
        );

        C5.setBackground(new java.awt.Color(153, 102, 0));

        C5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C5Layout = new javax.swing.GroupLayout(C5);
        C5.setLayout(C5Layout);
        C5Layout.setHorizontalGroup(
            C5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C5Layout.setVerticalGroup(
            C5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C5Label)
                .addGap(27, 27, 27))
        );

        D5.setBackground(new java.awt.Color(255, 255, 255));

        D5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D5Layout = new javax.swing.GroupLayout(D5);
        D5.setLayout(D5Layout);
        D5Layout.setHorizontalGroup(
            D5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D5Layout.setVerticalGroup(
            D5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D5Label)
                .addGap(27, 27, 27))
        );

        H2.setBackground(new java.awt.Color(153, 102, 0));

        H2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H2Layout = new javax.swing.GroupLayout(H2);
        H2.setLayout(H2Layout);
        H2Layout.setHorizontalGroup(
            H2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H2Layout.setVerticalGroup(
            H2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H2Label)
                .addGap(27, 27, 27))
        );

        H6.setBackground(new java.awt.Color(153, 102, 0));

        H6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H6Layout = new javax.swing.GroupLayout(H6);
        H6.setLayout(H6Layout);
        H6Layout.setHorizontalGroup(
            H6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H6Layout.setVerticalGroup(
            H6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H6Label)
                .addGap(27, 27, 27))
        );

        B2.setBackground(new java.awt.Color(153, 102, 0));

        B2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B2Layout = new javax.swing.GroupLayout(B2);
        B2.setLayout(B2Layout);
        B2Layout.setHorizontalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B2Layout.setVerticalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B2Label)
                .addGap(27, 27, 27))
        );

        C6.setBackground(new java.awt.Color(255, 255, 255));

        C6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C6Layout = new javax.swing.GroupLayout(C6);
        C6.setLayout(C6Layout);
        C6Layout.setHorizontalGroup(
            C6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C6Layout.setVerticalGroup(
            C6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C6Label)
                .addGap(27, 27, 27))
        );

        H7.setBackground(new java.awt.Color(255, 255, 255));

        H7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H7Layout = new javax.swing.GroupLayout(H7);
        H7.setLayout(H7Layout);
        H7Layout.setHorizontalGroup(
            H7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H7Layout.setVerticalGroup(
            H7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H7Label)
                .addGap(27, 27, 27))
        );

        B3.setBackground(new java.awt.Color(255, 255, 255));

        B3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B3Layout = new javax.swing.GroupLayout(B3);
        B3.setLayout(B3Layout);
        B3Layout.setHorizontalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B3Layout.setVerticalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B3Label)
                .addGap(27, 27, 27))
        );

        E5.setBackground(new java.awt.Color(153, 102, 0));

        E5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E5Layout = new javax.swing.GroupLayout(E5);
        E5.setLayout(E5Layout);
        E5Layout.setHorizontalGroup(
            E5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E5Layout.setVerticalGroup(
            E5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E5Label)
                .addGap(27, 27, 27))
        );

        A8.setBackground(new java.awt.Color(255, 255, 255));

        A8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A8Layout = new javax.swing.GroupLayout(A8);
        A8.setLayout(A8Layout);
        A8Layout.setHorizontalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(A8Label)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        A8Layout.setVerticalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A8Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(A8Label)
                .addGap(11, 11, 11))
        );

        A3.setBackground(new java.awt.Color(153, 102, 0));

        A3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A3Layout = new javax.swing.GroupLayout(A3);
        A3.setLayout(A3Layout);
        A3Layout.setHorizontalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A3Layout.setVerticalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A3Label)
                .addGap(27, 27, 27))
        );

        C3.setBackground(new java.awt.Color(153, 102, 0));

        C3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C3Layout = new javax.swing.GroupLayout(C3);
        C3.setLayout(C3Layout);
        C3Layout.setHorizontalGroup(
            C3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C3Layout.setVerticalGroup(
            C3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C3Label)
                .addGap(27, 27, 27))
        );

        B1.setBackground(new java.awt.Color(255, 255, 255));

        B1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B1Layout = new javax.swing.GroupLayout(B1);
        B1.setLayout(B1Layout);
        B1Layout.setHorizontalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B1Layout.setVerticalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B1Label)
                .addGap(27, 27, 27))
        );

        B6.setBackground(new java.awt.Color(153, 102, 0));

        B6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B6Layout = new javax.swing.GroupLayout(B6);
        B6.setLayout(B6Layout);
        B6Layout.setHorizontalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B6Layout.setVerticalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B6Label)
                .addGap(27, 27, 27))
        );

        C8.setBackground(new java.awt.Color(255, 255, 255));

        C8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C8Layout = new javax.swing.GroupLayout(C8);
        C8.setLayout(C8Layout);
        C8Layout.setHorizontalGroup(
            C8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C8Layout.setVerticalGroup(
            C8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C8Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(C8Label)
                .addGap(11, 11, 11))
        );

        D6.setBackground(new java.awt.Color(153, 102, 0));

        D6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D6Layout = new javax.swing.GroupLayout(D6);
        D6.setLayout(D6Layout);
        D6Layout.setHorizontalGroup(
            D6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D6Layout.setVerticalGroup(
            D6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D6Label)
                .addGap(27, 27, 27))
        );

        C1.setBackground(new java.awt.Color(153, 102, 0));

        C1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C1Layout = new javax.swing.GroupLayout(C1);
        C1.setLayout(C1Layout);
        C1Layout.setHorizontalGroup(
            C1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C1Layout.setVerticalGroup(
            C1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C1Label)
                .addGap(27, 27, 27))
        );

        D8.setBackground(new java.awt.Color(153, 102, 0));

        D8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D8Layout = new javax.swing.GroupLayout(D8);
        D8.setLayout(D8Layout);
        D8Layout.setHorizontalGroup(
            D8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D8Layout.setVerticalGroup(
            D8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D8Label)
                .addGap(11, 11, 11))
        );

        D3.setBackground(new java.awt.Color(255, 255, 255));

        D3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D3Layout = new javax.swing.GroupLayout(D3);
        D3.setLayout(D3Layout);
        D3Layout.setHorizontalGroup(
            D3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D3Layout.setVerticalGroup(
            D3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D3Label)
                .addGap(27, 27, 27))
        );

        F5.setBackground(new java.awt.Color(255, 255, 255));

        F5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F5Layout = new javax.swing.GroupLayout(F5);
        F5.setLayout(F5Layout);
        F5Layout.setHorizontalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F5Layout.setVerticalGroup(
            F5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F5Label)
                .addGap(27, 27, 27))
        );

        D1.setBackground(new java.awt.Color(255, 255, 255));

        D1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D1Layout = new javax.swing.GroupLayout(D1);
        D1.setLayout(D1Layout);
        D1Layout.setHorizontalGroup(
            D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D1Layout.setVerticalGroup(
            D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D1Label)
                .addGap(27, 27, 27))
        );

        E8.setBackground(new java.awt.Color(255, 255, 255));

        E8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E8Layout = new javax.swing.GroupLayout(E8);
        E8.setLayout(E8Layout);
        E8Layout.setHorizontalGroup(
            E8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E8Layout.setVerticalGroup(
            E8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E8Label)
                .addGap(27, 27, 27))
        );

        H4.setBackground(new java.awt.Color(153, 102, 0));

        H4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H4Layout = new javax.swing.GroupLayout(H4);
        H4.setLayout(H4Layout);
        H4Layout.setHorizontalGroup(
            H4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H4Layout.setVerticalGroup(
            H4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H4Label)
                .addGap(27, 27, 27))
        );

        E6.setBackground(new java.awt.Color(255, 255, 255));

        E6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E6Layout = new javax.swing.GroupLayout(E6);
        E6.setLayout(E6Layout);
        E6Layout.setHorizontalGroup(
            E6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E6Layout.setVerticalGroup(
            E6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E6Label)
                .addGap(27, 27, 27))
        );

        F8.setBackground(new java.awt.Color(153, 102, 0));

        F8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F8Layout = new javax.swing.GroupLayout(F8);
        F8.setLayout(F8Layout);
        F8Layout.setHorizontalGroup(
            F8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F8Layout.setVerticalGroup(
            F8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F8Label)
                .addGap(27, 27, 27))
        );

        C4.setBackground(new java.awt.Color(255, 255, 255));

        C4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        C4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout C4Layout = new javax.swing.GroupLayout(C4);
        C4.setLayout(C4Layout);
        C4Layout.setHorizontalGroup(
            C4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(C4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        C4Layout.setVerticalGroup(
            C4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, C4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C4Label)
                .addGap(27, 27, 27))
        );

        G5.setBackground(new java.awt.Color(153, 102, 0));

        G5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G5Layout = new javax.swing.GroupLayout(G5);
        G5.setLayout(G5Layout);
        G5Layout.setHorizontalGroup(
            G5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G5Layout.setVerticalGroup(
            G5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G5Label)
                .addGap(27, 27, 27))
        );

        E3.setBackground(new java.awt.Color(153, 102, 0));

        E3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E3Layout = new javax.swing.GroupLayout(E3);
        E3.setLayout(E3Layout);
        E3Layout.setHorizontalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E3Layout.setVerticalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E3Label)
                .addGap(27, 27, 27))
        );

        B4.setBackground(new java.awt.Color(153, 102, 0));

        B4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B4Layout = new javax.swing.GroupLayout(B4);
        B4.setLayout(B4Layout);
        B4Layout.setHorizontalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B4Layout.setVerticalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B4Label)
                .addGap(27, 27, 27))
        );

        F6.setBackground(new java.awt.Color(153, 102, 0));

        F6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F6Layout = new javax.swing.GroupLayout(F6);
        F6.setLayout(F6Layout);
        F6Layout.setHorizontalGroup(
            F6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F6Layout.setVerticalGroup(
            F6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F6Label)
                .addGap(27, 27, 27))
        );

        H5.setBackground(new java.awt.Color(255, 255, 255));

        H5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H5Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H5LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H5Layout = new javax.swing.GroupLayout(H5);
        H5.setLayout(H5Layout);
        H5Layout.setHorizontalGroup(
            H5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H5Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H5Layout.setVerticalGroup(
            H5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H5Label)
                .addGap(27, 27, 27))
        );

        A1.setBackground(new java.awt.Color(153, 102, 0));

        A1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A1Layout = new javax.swing.GroupLayout(A1);
        A1.setLayout(A1Layout);
        A1Layout.setHorizontalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A1Layout.setVerticalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A1Label)
                .addGap(27, 27, 27))
        );

        D4.setBackground(new java.awt.Color(153, 102, 0));

        D4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        D4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout D4Layout = new javax.swing.GroupLayout(D4);
        D4.setLayout(D4Layout);
        D4Layout.setHorizontalGroup(
            D4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(D4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(D4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        D4Layout.setVerticalGroup(
            D4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, D4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(D4Label)
                .addGap(27, 27, 27))
        );

        G6.setBackground(new java.awt.Color(255, 255, 255));

        G6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G6Layout = new javax.swing.GroupLayout(G6);
        G6.setLayout(G6Layout);
        G6Layout.setHorizontalGroup(
            G6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G6Layout.setVerticalGroup(
            G6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G6Label)
                .addGap(27, 27, 27))
        );

        F3.setBackground(new java.awt.Color(255, 255, 255));

        F3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F3Layout = new javax.swing.GroupLayout(F3);
        F3.setLayout(F3Layout);
        F3Layout.setHorizontalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F3Layout.setVerticalGroup(
            F3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F3Label)
                .addGap(27, 27, 27))
        );

        A6.setBackground(new java.awt.Color(255, 255, 255));

        A6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A6Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A6LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A6Layout = new javax.swing.GroupLayout(A6);
        A6.setLayout(A6Layout);
        A6Layout.setHorizontalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A6Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A6Layout.setVerticalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A6Label)
                .addGap(27, 27, 27))
        );

        E1.setBackground(new java.awt.Color(153, 102, 0));

        E1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E1Layout = new javax.swing.GroupLayout(E1);
        E1.setLayout(E1Layout);
        E1Layout.setHorizontalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E1Layout.setVerticalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E1Label)
                .addGap(27, 27, 27))
        );

        E4.setBackground(new java.awt.Color(255, 255, 255));

        E4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        E4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                E4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout E4Layout = new javax.swing.GroupLayout(E4);
        E4.setLayout(E4Layout);
        E4Layout.setHorizontalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        E4Layout.setVerticalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, E4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(E4Label)
                .addGap(27, 27, 27))
        );

        F1.setBackground(new java.awt.Color(255, 255, 255));

        F1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F1Layout = new javax.swing.GroupLayout(F1);
        F1.setLayout(F1Layout);
        F1Layout.setHorizontalGroup(
            F1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F1Layout.setVerticalGroup(
            F1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F1Label)
                .addGap(27, 27, 27))
        );

        G3.setBackground(new java.awt.Color(153, 102, 0));

        G3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G3LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G3Layout = new javax.swing.GroupLayout(G3);
        G3.setLayout(G3Layout);
        G3Layout.setHorizontalGroup(
            G3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G3Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G3Layout.setVerticalGroup(
            G3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G3Label)
                .addGap(27, 27, 27))
        );

        G8.setBackground(new java.awt.Color(255, 255, 255));

        G8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G8Layout = new javax.swing.GroupLayout(G8);
        G8.setLayout(G8Layout);
        G8Layout.setHorizontalGroup(
            G8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G8Layout.setVerticalGroup(
            G8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G8Label)
                .addGap(27, 27, 27))
        );

        G1.setBackground(new java.awt.Color(153, 102, 0));

        G1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        G1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout G1Layout = new javax.swing.GroupLayout(G1);
        G1.setLayout(G1Layout);
        G1Layout.setHorizontalGroup(
            G1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(G1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(G1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        G1Layout.setVerticalGroup(
            G1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(G1Label)
                .addGap(27, 27, 27))
        );

        F4.setBackground(new java.awt.Color(153, 102, 0));

        F4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        F4Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                F4LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout F4Layout = new javax.swing.GroupLayout(F4);
        F4.setLayout(F4Layout);
        F4Layout.setHorizontalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(F4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(F4Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        F4Layout.setVerticalGroup(
            F4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, F4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F4Label)
                .addGap(27, 27, 27))
        );

        H8.setBackground(new java.awt.Color(153, 102, 0));

        H8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H8Layout = new javax.swing.GroupLayout(H8);
        H8.setLayout(H8Layout);
        H8Layout.setHorizontalGroup(
            H8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H8Layout.setVerticalGroup(
            H8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H8Label)
                .addGap(27, 27, 27))
        );

        H1.setBackground(new java.awt.Color(255, 255, 255));

        H1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        H1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                H1LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout H1Layout = new javax.swing.GroupLayout(H1);
        H1.setLayout(H1Layout);
        H1Layout.setHorizontalGroup(
            H1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(H1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(H1Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        H1Layout.setVerticalGroup(
            H1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, H1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(H1Label)
                .addGap(27, 27, 27))
        );

        B8.setBackground(new java.awt.Color(153, 102, 0));

        B8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B8Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B8LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B8Layout = new javax.swing.GroupLayout(B8);
        B8.setLayout(B8Layout);
        B8Layout.setHorizontalGroup(
            B8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B8Layout.setVerticalGroup(
            B8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B8Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(B8Label)
                .addGap(11, 11, 11))
        );

        A2.setBackground(new java.awt.Color(255, 255, 255));

        A2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        A2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A2LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout A2Layout = new javax.swing.GroupLayout(A2);
        A2.setLayout(A2Layout);
        A2Layout.setHorizontalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A2Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A2Layout.setVerticalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A2Label)
                .addGap(27, 27, 27))
        );

        B7.setBackground(new java.awt.Color(255, 255, 255));

        B7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transparent_Background.png"))); // NOI18N
        B7Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B7LabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout B7Layout = new javax.swing.GroupLayout(B7);
        B7.setLayout(B7Layout);
        B7Layout.setHorizontalGroup(
            B7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(B7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(B7Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        B7Layout.setVerticalGroup(
            B7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B7Label)
                .addGap(27, 27, 27))
        );

        user1Lbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        user1Lbl.setText("User 1");

        user2Lbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        user2Lbl.setText("User 2");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Game Window");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        whiteTimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        whiteTimeLbl.setForeground(new java.awt.Color(255, 255, 255));
        whiteTimeLbl.setText("0:00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(whiteTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(whiteTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        blackTimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        blackTimeLbl.setForeground(new java.awt.Color(255, 255, 255));
        blackTimeLbl.setText("0:00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(blackTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(blackTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        startGameBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        startGameBtn.setText("Start Game");
        startGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameBtnActionPerformed(evt);
            }
        });

        whiteAdvantageLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        whiteAdvantageLbl.setText("+0");

        blackAdvantageLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        blackAdvantageLbl.setText("+0");

        pieceSelectPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("Piece Select");

        pawnBtn.setBackground(java.awt.Color.gray);
        pawnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_Pawn.png"))); // NOI18N
        pawnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pawnBtnActionPerformed(evt);
            }
        });

        knightBtn.setBackground(java.awt.Color.gray);
        knightBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_Knight.png"))); // NOI18N
        knightBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knightBtnActionPerformed(evt);
            }
        });

        bishopBtn.setBackground(java.awt.Color.gray);
        bishopBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_Bishop.png"))); // NOI18N
        bishopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bishopBtnActionPerformed(evt);
            }
        });

        rookBtn.setBackground(java.awt.Color.gray);
        rookBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_Rook.png"))); // NOI18N
        rookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rookBtnActionPerformed(evt);
            }
        });

        queenBtn.setBackground(java.awt.Color.gray);
        queenBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_Queen.png"))); // NOI18N
        queenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queenBtnActionPerformed(evt);
            }
        });

        kingBtn.setBackground(java.awt.Color.gray);
        kingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DefaultWhite_King.png"))); // NOI18N
        kingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kingBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(whiteTeamBtn);
        whiteTeamBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        whiteTeamBtn.setSelected(true);
        whiteTeamBtn.setText("White");
        whiteTeamBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteTeamBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(blackTeamBtn);
        blackTeamBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        blackTeamBtn.setText("Black");
        blackTeamBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackTeamBtnActionPerformed(evt);
            }
        });

        customPieceBtn.setBackground(java.awt.Color.gray);
        customPieceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customPieceBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pieceSelectPanelLayout = new javax.swing.GroupLayout(pieceSelectPanel);
        pieceSelectPanel.setLayout(pieceSelectPanelLayout);
        pieceSelectPanelLayout.setHorizontalGroup(
            pieceSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pieceSelectPanelLayout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(25, 25, 25))
            .addGroup(pieceSelectPanelLayout.createSequentialGroup()
                .addGroup(pieceSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customPieceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(queenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bishopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(knightBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pieceSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pieceSelectPanelLayout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addGroup(pieceSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(whiteTeamBtn)
                                .addComponent(blackTeamBtn)))
                        .addGroup(pieceSelectPanelLayout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(pawnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pieceSelectPanelLayout.setVerticalGroup(
            pieceSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pieceSelectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(whiteTeamBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackTeamBtn)
                .addGap(18, 18, 18)
                .addComponent(pawnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(knightBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bishopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(queenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customPieceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(user2Lbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(whiteAdvantageLbl))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pieceSelectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(user1Lbl)
                                            .addGap(18, 18, 18)
                                            .addComponent(blackAdvantageLbl))
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(startGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(C8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(A8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(D8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(user1Lbl)
                                    .addComponent(blackAdvantageLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(startGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(user2Lbl)
                                    .addComponent(whiteAdvantageLbl)))))
                    .addComponent(pieceSelectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B8LabelMousePressed
        handleClick(7, 1);
        handleSandboxClick(7, 1, evt);
    }//GEN-LAST:event_B8LabelMousePressed

    private void D8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D8LabelMousePressed
        handleClick(7, 3);
        handleSandboxClick(7, 3, evt);
    }//GEN-LAST:event_D8LabelMousePressed

    private void E8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E8LabelMousePressed
        handleClick(7, 4);
        handleSandboxClick(7, 4, evt);
    }//GEN-LAST:event_E8LabelMousePressed

    private void F8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F8LabelMousePressed
        handleClick(7, 5);
        handleSandboxClick(7, 5, evt);
    }//GEN-LAST:event_F8LabelMousePressed

    private void G8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G8LabelMousePressed
        handleClick(7, 6);
        handleSandboxClick(7, 6, evt);
    }//GEN-LAST:event_G8LabelMousePressed

    private void H8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H8LabelMousePressed
        handleClick(7, 7);
        handleSandboxClick(7, 7, evt);
    }//GEN-LAST:event_H8LabelMousePressed

    private void A7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A7LabelMousePressed
        handleClick(6, 0);
        handleSandboxClick(6, 0, evt);
    }//GEN-LAST:event_A7LabelMousePressed

    private void C7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C7LabelMousePressed
        handleClick(6, 2);
        handleSandboxClick(6, 2, evt);
    }//GEN-LAST:event_C7LabelMousePressed

    private void H3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H3LabelMousePressed
        handleClick(2, 7);
        handleSandboxClick(2, 7, evt);
    }//GEN-LAST:event_H3LabelMousePressed

    private void G4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G4LabelMousePressed
        handleClick(3, 6);
        handleSandboxClick(3, 6, evt);
    }//GEN-LAST:event_G4LabelMousePressed

    private void C2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C2LabelMousePressed
        handleClick(1, 2);
        handleSandboxClick(1, 2, evt);
    }//GEN-LAST:event_C2LabelMousePressed

    private void A4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A4LabelMousePressed
        handleClick(3, 0);
        handleSandboxClick(3, 0, evt);
    }//GEN-LAST:event_A4LabelMousePressed

    private void D2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D2LabelMousePressed
        handleClick(1, 3);
        handleSandboxClick(1, 3, evt);
    }//GEN-LAST:event_D2LabelMousePressed

    private void B5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B5LabelMousePressed
        handleClick(4, 1);
        handleSandboxClick(4, 1, evt);
    }//GEN-LAST:event_B5LabelMousePressed

    private void D7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D7LabelMousePressed
        handleClick(6, 3);
        handleSandboxClick(6, 3, evt);
    }//GEN-LAST:event_D7LabelMousePressed

    private void E2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E2LabelMousePressed
        handleClick(1, 4);
        handleSandboxClick(1, 4, evt);
    }//GEN-LAST:event_E2LabelMousePressed

    private void A5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A5LabelMousePressed
        handleClick(4, 0);
        handleSandboxClick(4, 0, evt);
    }//GEN-LAST:event_A5LabelMousePressed

    private void F2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F2LabelMousePressed
        handleClick(1, 5);
        handleSandboxClick(1, 5, evt);
    }//GEN-LAST:event_F2LabelMousePressed

    private void E7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E7LabelMousePressed
        handleClick(6, 4);
        handleSandboxClick(6, 4, evt);
    }//GEN-LAST:event_E7LabelMousePressed

    private void G2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G2LabelMousePressed
        handleClick(1, 6);
        handleSandboxClick(1, 6, evt);
    }//GEN-LAST:event_G2LabelMousePressed

    private void F7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F7LabelMousePressed
        handleClick(6, 5);
        handleSandboxClick(6, 5, evt);
    }//GEN-LAST:event_F7LabelMousePressed

    private void G7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G7LabelMousePressed
        handleClick(6, 6);
        handleSandboxClick(6, 6, evt);
    }//GEN-LAST:event_G7LabelMousePressed

    private void C5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C5LabelMousePressed
        handleClick(4, 2);
        handleSandboxClick(4, 2, evt);
    }//GEN-LAST:event_C5LabelMousePressed

    private void D5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D5LabelMousePressed
        handleClick(4, 3);
        handleSandboxClick(4, 3, evt);
    }//GEN-LAST:event_D5LabelMousePressed

    private void H2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H2LabelMousePressed
        handleClick(1, 7);
        handleSandboxClick(1, 7, evt);
    }//GEN-LAST:event_H2LabelMousePressed

    private void H6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H6LabelMousePressed
        handleClick(5, 7);
        handleSandboxClick(5, 7, evt);
    }//GEN-LAST:event_H6LabelMousePressed

    private void B2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2LabelMousePressed
        handleClick(1, 1);
        handleSandboxClick(1, 1, evt);
    }//GEN-LAST:event_B2LabelMousePressed

    private void C6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C6LabelMousePressed
        handleClick(5, 2);
        handleSandboxClick(5, 2, evt);
    }//GEN-LAST:event_C6LabelMousePressed

    private void H7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H7LabelMousePressed
        handleClick(6, 7);
        handleSandboxClick(6, 7, evt);
    }//GEN-LAST:event_H7LabelMousePressed

    private void B3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3LabelMousePressed
        handleClick(2, 1);
        handleSandboxClick(2, 1, evt);
    }//GEN-LAST:event_B3LabelMousePressed

    private void E5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E5LabelMousePressed
        handleClick(4, 4);
        handleSandboxClick(4, 4, evt);
    }//GEN-LAST:event_E5LabelMousePressed

    private void A8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A8LabelMousePressed
        handleClick(7, 0);
        handleSandboxClick(7, 0, evt);
    }//GEN-LAST:event_A8LabelMousePressed

    private void A3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A3LabelMousePressed
        handleClick(2, 0);
        handleSandboxClick(2, 0, evt);
    }//GEN-LAST:event_A3LabelMousePressed

    private void C3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C3LabelMousePressed
        handleClick(2, 2);
        handleSandboxClick(2, 2, evt);
    }//GEN-LAST:event_C3LabelMousePressed

    private void B1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1LabelMousePressed
        handleClick(0, 1);
        handleSandboxClick(0, 1, evt);
    }//GEN-LAST:event_B1LabelMousePressed

    private void B6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B6LabelMousePressed
        handleClick(5, 1);
        handleSandboxClick(5, 1, evt);
    }//GEN-LAST:event_B6LabelMousePressed

    private void C8LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C8LabelMousePressed
        handleClick(7, 2);
        handleSandboxClick(7, 2, evt);
    }//GEN-LAST:event_C8LabelMousePressed

    private void D6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D6LabelMousePressed
        handleClick(5, 3);
        handleSandboxClick(5, 3, evt);
    }//GEN-LAST:event_D6LabelMousePressed

    private void C1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C1LabelMousePressed
        handleClick(0, 2);
        handleSandboxClick(0, 2, evt);
    }//GEN-LAST:event_C1LabelMousePressed

    private void D3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D3LabelMousePressed
        handleClick(2, 3);
        handleSandboxClick(2, 3, evt);
    }//GEN-LAST:event_D3LabelMousePressed

    private void F5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F5LabelMousePressed
        handleClick(4, 5);
        handleSandboxClick(4, 5, evt);
    }//GEN-LAST:event_F5LabelMousePressed

    private void D1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D1LabelMousePressed
        handleClick(0, 3);
        handleSandboxClick(0, 3, evt);
    }//GEN-LAST:event_D1LabelMousePressed

    private void H4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H4LabelMousePressed
        handleClick(3, 7);
        handleSandboxClick(3, 7, evt);
    }//GEN-LAST:event_H4LabelMousePressed

    private void E6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E6LabelMousePressed
        handleClick(5, 4);
        handleSandboxClick(5, 4, evt);
    }//GEN-LAST:event_E6LabelMousePressed

    private void C4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C4LabelMousePressed
        handleClick(3, 2);
        handleSandboxClick(3, 2, evt);
    }//GEN-LAST:event_C4LabelMousePressed

    private void G5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G5LabelMousePressed
        handleClick(4, 6);
        handleSandboxClick(4, 6, evt);
    }//GEN-LAST:event_G5LabelMousePressed

    private void E3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E3LabelMousePressed
        handleClick(2, 4);
        handleSandboxClick(2, 4, evt);
    }//GEN-LAST:event_E3LabelMousePressed

    private void B4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4LabelMousePressed
        handleClick(3, 1);
        handleSandboxClick(3, 1, evt);
    }//GEN-LAST:event_B4LabelMousePressed

    private void F6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F6LabelMousePressed
        handleClick(5, 5);
        handleSandboxClick(5, 5, evt);
    }//GEN-LAST:event_F6LabelMousePressed

    private void H5LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H5LabelMousePressed
        handleClick(4, 7);
        handleSandboxClick(4, 7, evt);
    }//GEN-LAST:event_H5LabelMousePressed

    private void A1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A1LabelMousePressed
        handleClick(0, 0);
        handleSandboxClick(0, 0, evt);
    }//GEN-LAST:event_A1LabelMousePressed

    private void D4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D4LabelMousePressed
        handleClick(3, 3);
        handleSandboxClick(3, 3, evt);
    }//GEN-LAST:event_D4LabelMousePressed

    private void G6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G6LabelMousePressed
        handleClick(5, 6);
        handleSandboxClick(5, 6, evt);
    }//GEN-LAST:event_G6LabelMousePressed

    private void F3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F3LabelMousePressed
        handleClick(2, 5);
        handleSandboxClick(2, 5, evt);
    }//GEN-LAST:event_F3LabelMousePressed

    private void A6LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A6LabelMousePressed
        handleClick(5, 0);
        handleSandboxClick(5, 0, evt);
    }//GEN-LAST:event_A6LabelMousePressed

    private void E1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E1LabelMousePressed
        handleClick(0, 4);
        handleSandboxClick(0, 4, evt);
    }//GEN-LAST:event_E1LabelMousePressed

    private void E4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E4LabelMousePressed
        handleClick(3, 4);
        handleSandboxClick(3, 4, evt);
    }//GEN-LAST:event_E4LabelMousePressed

    private void F1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F1LabelMousePressed
        handleClick(0, 5);
        handleSandboxClick(0, 5, evt);
    }//GEN-LAST:event_F1LabelMousePressed

    private void G3LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G3LabelMousePressed
        handleClick(2, 6);
        handleSandboxClick(2, 6, evt);
    }//GEN-LAST:event_G3LabelMousePressed

    private void G1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G1LabelMousePressed
        handleClick(0, 6);
        handleSandboxClick(0, 6, evt);
    }//GEN-LAST:event_G1LabelMousePressed

    private void F4LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_F4LabelMousePressed
        handleClick(3, 5);
        handleSandboxClick(3, 5, evt);
    }//GEN-LAST:event_F4LabelMousePressed

    private void H1LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_H1LabelMousePressed
        handleClick(0, 7);
        handleSandboxClick(0, 7, evt);
    }//GEN-LAST:event_H1LabelMousePressed

    private void A2LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A2LabelMousePressed
        handleClick(1, 0);
        handleSandboxClick(1, 0, evt);
    }//GEN-LAST:event_A2LabelMousePressed

    private void B7LabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B7LabelMousePressed
        handleClick(6, 1);
        handleSandboxClick(6, 1, evt);
    }//GEN-LAST:event_B7LabelMousePressed

    private void startGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameBtnActionPerformed
        int whiteKings = 0;
        int blackKings = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (pieces[r][c] instanceof King) {
                    if (pieces[r][c].isWhite()) {
                        whiteKings++;
                    } else {
                        blackKings++;
                    }
                }
            }
        }

        java.awt.Dimension currentSize = this.getSize();
        
        if (isSandbox && !(whiteKings == 1 && blackKings == 1)) {
            JOptionPane.showMessageDialog(null, "Please Ensure there is exactly 1 White King and 1 Black King", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Piece blackKing = findKing(pieces, false);
            if(isKingInCheck(blackKing, pieces)){
                JOptionPane.showMessageDialog(null, "Plase ensure the black king is not in check on the first move", "Error!", JOptionPane.ERROR_MESSAGE);
            }else{
                if(isSandbox){
                this.remove(pieceSelectPanel);
                this.setSize(currentSize.width - 135, currentSize.height);
                this.revalidate();
                this.repaint();
                }
                startTimer();
                startGameBtn.setEnabled(false);
            }
        }
    }//GEN-LAST:event_startGameBtnActionPerformed

    private void pawnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pawnBtnActionPerformed
        pawnBtn.setBackground(Color.green);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "Pawn";
    }//GEN-LAST:event_pawnBtnActionPerformed

    private void knightBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knightBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.green);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "Knight";
    }//GEN-LAST:event_knightBtnActionPerformed

    private void bishopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bishopBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.green);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "Bishop";
    }//GEN-LAST:event_bishopBtnActionPerformed

    private void rookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rookBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.green);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "Rook";
    }//GEN-LAST:event_rookBtnActionPerformed

    private void queenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queenBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.green);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "Queen";
    }//GEN-LAST:event_queenBtnActionPerformed

    private void kingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kingBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.green);
        customPieceBtn.setBackground(Color.gray);
        selectedPieceType = "King";
    }//GEN-LAST:event_kingBtnActionPerformed

    private void blackTeamBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackTeamBtnActionPerformed
        pawnBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_Pawn.png")));
        knightBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_Knight.png")));
        bishopBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_Bishop.png")));
        rookBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_Rook.png")));
        queenBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_Queen.png")));
        kingBtn.setIcon(new ImageIcon(loadImage("/images/DefaultBlack_King.png")));
        customPieceFilePath = customPieceFilePath.replace("White", "Black");
        customPieceBtn.setIcon(new ImageIcon(loadImage(customPieceFilePath)));
    }//GEN-LAST:event_blackTeamBtnActionPerformed

    private void customPieceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customPieceBtnActionPerformed
        pawnBtn.setBackground(Color.gray);
        knightBtn.setBackground(Color.gray);
        bishopBtn.setBackground(Color.gray);
        rookBtn.setBackground(Color.gray);
        queenBtn.setBackground(Color.gray);
        kingBtn.setBackground(Color.gray);
        customPieceBtn.setBackground(Color.green);
        selectedPieceType = "Custom";
    }//GEN-LAST:event_customPieceBtnActionPerformed

    private void whiteTeamBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteTeamBtnActionPerformed
        pawnBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_Pawn.png")));
        knightBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_Knight.png")));
        bishopBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_Bishop.png")));
        rookBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_Rook.png")));
        queenBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_Queen.png")));
        kingBtn.setIcon(new ImageIcon(loadImage("/images/DefaultWhite_King.png")));
        customPieceFilePath = customPieceFilePath.replace("Black", "White");
        customPieceBtn.setIcon(new ImageIcon(loadImage(customPieceFilePath)));
    }//GEN-LAST:event_whiteTeamBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A1;
    private javax.swing.JLabel A1Label;
    private javax.swing.JPanel A2;
    private javax.swing.JLabel A2Label;
    private javax.swing.JPanel A3;
    private javax.swing.JLabel A3Label;
    private javax.swing.JPanel A4;
    private javax.swing.JLabel A4Label;
    private javax.swing.JPanel A5;
    private javax.swing.JLabel A5Label;
    private javax.swing.JPanel A6;
    private javax.swing.JLabel A6Label;
    private javax.swing.JPanel A7;
    private javax.swing.JLabel A7Label;
    private javax.swing.JPanel A8;
    private javax.swing.JLabel A8Label;
    private javax.swing.JPanel B1;
    private javax.swing.JLabel B1Label;
    private javax.swing.JPanel B2;
    private javax.swing.JLabel B2Label;
    private javax.swing.JPanel B3;
    private javax.swing.JLabel B3Label;
    private javax.swing.JPanel B4;
    private javax.swing.JLabel B4Label;
    private javax.swing.JPanel B5;
    private javax.swing.JLabel B5Label;
    private javax.swing.JPanel B6;
    private javax.swing.JLabel B6Label;
    private javax.swing.JPanel B7;
    private javax.swing.JLabel B7Label;
    private javax.swing.JPanel B8;
    private javax.swing.JLabel B8Label;
    private javax.swing.JPanel C1;
    private javax.swing.JLabel C1Label;
    private javax.swing.JPanel C2;
    private javax.swing.JLabel C2Label;
    private javax.swing.JPanel C3;
    private javax.swing.JLabel C3Label;
    private javax.swing.JPanel C4;
    private javax.swing.JLabel C4Label;
    private javax.swing.JPanel C5;
    private javax.swing.JLabel C5Label;
    private javax.swing.JPanel C6;
    private javax.swing.JLabel C6Label;
    private javax.swing.JPanel C7;
    private javax.swing.JLabel C7Label;
    private javax.swing.JPanel C8;
    private javax.swing.JLabel C8Label;
    private javax.swing.JPanel D1;
    private javax.swing.JLabel D1Label;
    private javax.swing.JPanel D2;
    private javax.swing.JLabel D2Label;
    private javax.swing.JPanel D3;
    private javax.swing.JLabel D3Label;
    private javax.swing.JPanel D4;
    private javax.swing.JLabel D4Label;
    private javax.swing.JPanel D5;
    private javax.swing.JLabel D5Label;
    private javax.swing.JPanel D6;
    private javax.swing.JLabel D6Label;
    private javax.swing.JPanel D7;
    private javax.swing.JLabel D7Label;
    private javax.swing.JPanel D8;
    private javax.swing.JLabel D8Label;
    private javax.swing.JPanel E1;
    private javax.swing.JLabel E1Label;
    private javax.swing.JPanel E2;
    private javax.swing.JLabel E2Label;
    private javax.swing.JPanel E3;
    private javax.swing.JLabel E3Label;
    private javax.swing.JPanel E4;
    private javax.swing.JLabel E4Label;
    private javax.swing.JPanel E5;
    private javax.swing.JLabel E5Label;
    private javax.swing.JPanel E6;
    private javax.swing.JLabel E6Label;
    private javax.swing.JPanel E7;
    private javax.swing.JLabel E7Label;
    private javax.swing.JPanel E8;
    private javax.swing.JLabel E8Label;
    private javax.swing.JPanel F1;
    private javax.swing.JLabel F1Label;
    private javax.swing.JPanel F2;
    private javax.swing.JLabel F2Label;
    private javax.swing.JPanel F3;
    private javax.swing.JLabel F3Label;
    private javax.swing.JPanel F4;
    private javax.swing.JLabel F4Label;
    private javax.swing.JPanel F5;
    private javax.swing.JLabel F5Label;
    private javax.swing.JPanel F6;
    private javax.swing.JLabel F6Label;
    private javax.swing.JPanel F7;
    private javax.swing.JLabel F7Label;
    private javax.swing.JPanel F8;
    private javax.swing.JLabel F8Label;
    private javax.swing.JPanel G1;
    private javax.swing.JLabel G1Label;
    private javax.swing.JPanel G2;
    private javax.swing.JLabel G2Label;
    private javax.swing.JPanel G3;
    private javax.swing.JLabel G3Label;
    private javax.swing.JPanel G4;
    private javax.swing.JLabel G4Label;
    private javax.swing.JPanel G5;
    private javax.swing.JLabel G5Label;
    private javax.swing.JPanel G6;
    private javax.swing.JLabel G6Label;
    private javax.swing.JPanel G7;
    private javax.swing.JLabel G7Label;
    private javax.swing.JPanel G8;
    private javax.swing.JLabel G8Label;
    private javax.swing.JPanel H1;
    private javax.swing.JLabel H1Label;
    private javax.swing.JPanel H2;
    private javax.swing.JLabel H2Label;
    private javax.swing.JPanel H3;
    private javax.swing.JLabel H3Label;
    private javax.swing.JPanel H4;
    private javax.swing.JLabel H4Label;
    private javax.swing.JPanel H5;
    private javax.swing.JLabel H5Label;
    private javax.swing.JPanel H6;
    private javax.swing.JLabel H6Label;
    private javax.swing.JPanel H7;
    private javax.swing.JLabel H7Label;
    private javax.swing.JPanel H8;
    private javax.swing.JLabel H8Label;
    private javax.swing.JButton bishopBtn;
    private javax.swing.JLabel blackAdvantageLbl;
    private javax.swing.JRadioButton blackTeamBtn;
    private javax.swing.JLabel blackTimeLbl;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton customPieceBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton kingBtn;
    private javax.swing.JButton knightBtn;
    private javax.swing.JButton pawnBtn;
    private javax.swing.JPanel pieceSelectPanel;
    private javax.swing.JButton queenBtn;
    private javax.swing.JButton rookBtn;
    private javax.swing.JButton startGameBtn;
    private javax.swing.JLabel user1Lbl;
    private javax.swing.JLabel user2Lbl;
    private javax.swing.JLabel whiteAdvantageLbl;
    private javax.swing.JRadioButton whiteTeamBtn;
    private javax.swing.JLabel whiteTimeLbl;
    // End of variables declaration//GEN-END:variables
}
