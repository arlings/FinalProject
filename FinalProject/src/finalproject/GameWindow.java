package finalproject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import repo.FileImporter;

public class GameWindow extends javax.swing.JFrame {

    SandboxWindow sandboxWindow;
    MainWindow mainWindow;
    EnterUsername enterUsername;
    FileImporter fileImporter = new FileImporter();

    private JPanel[][] board = new JPanel[8][8];

    private Piece[][] pieces = new Piece[8][8];

    public GameWindow(SandboxWindow m) {
        startGame();
        sandboxWindow = m;
    }

    public GameWindow(MainWindow m) {
        startGame();
        mainWindow = m;
    }

    public GameWindow(EnterUsername m) {
        startGame();
        enterUsername = m;
    }
    
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void startGame() {
        MoveJFrame();
        initComponents();
        board = loadBoard();
        pieces = loadPieces();
        updateBoardUI();
    }

    private Piece[][] loadPieces() {
        //pawns        
        for (int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(1, i, loadImage("src/images/White_Pawn.png"), true);
            pieces[6][i] = new Pawn(6, i, loadImage("src/images/Black_Pawn.png"), false);
        }
        //rooks
        pieces[0][0] = new Rook(0, 0, loadImage("src/images/White_Rook.png"), true);
        pieces[0][7] = new Rook(0, 7, loadImage("src/images/White_Rook.png"), true);
        pieces[7][0] = new Rook(7, 0, loadImage("src/images/Black_Rook.png"), false);
        pieces[7][7] = new Rook(7, 7, loadImage("src/images/Black_Rook.png"), false);
        //knights
        pieces[0][1] = new Knight(0, 1, loadImage("src/images/White_Knight.png"), true);
        pieces[0][6] = new Knight(0, 6, loadImage("src/images/White_Knight.png"), true);
        pieces[7][1] = new Knight(7, 1, loadImage("src/images/Black_Knight.png"), false);
        pieces[7][6] = new Knight(7, 6, loadImage("src/images/Black_Knight.png"), false);
        //bishops
        pieces[0][2] = new Bishop(0, 2, loadImage("src/images/White_Bishop.png"), true);
        pieces[0][5] = new Bishop(0, 5, loadImage("src/images/White_Bishop.png"), true);
        pieces[7][2] = new Bishop(7, 2, loadImage("src/images/Black_Bishop.png"), false);
        pieces[7][5] = new Bishop(7, 5, loadImage("BlackBishop.png"), false);
        //queens
        pieces[0][3] = new Queen(0, 3, loadImage("src/images/White_Queen.png"), true);
        pieces[7][3] = new Queen(7, 3, loadImage("src/images/Black_Queen.png"), false);
        //kings
        pieces[0][4] = new King(0, 4, loadImage("src/images/White_King.png"), true, false, false);
        pieces[7][4] = new King(7, 4, loadImage("src/images/Black_King.png"), false, false, false);

        return pieces;
    }

    private JPanel[][] loadBoard() {
        return new JPanel[][]{
            {A1, B1, C1, D1, E1, F1, G1, H1},
            {A2, B2, C2, D2, E2, F2, G2, H2},
            {A3, B3, C3, D3, E3, F3, G3, H3},
            {A4, B4, C4, D4, E4, F4, G4, H4},
            {A5, B5, C5, D5, E5, F5, G5, H5},
            {A6, B6, C6, D6, E6, F6, G6, H6},
            {A7, B7, C7, D7, E7, F7, G7, H7},
            {A8, B8, C8, D8, E8, F8, G8, H8}
        };
    }

    //needs to be finished later
    private BufferedImage loadImage(String filePath) {
        try {
            return fileImporter.loadImage(filePath);
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, "Failed to load image: " + filePath, ex);
            return null;
        }
    }

    private void updateBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].removeAll();
                Piece piece = pieces[row][col];
                if (piece != null) {
                    java.awt.image.BufferedImage img = piece.getSprite();
                    ImageIcon icon = new ImageIcon(img);
                    JLabel pieceLabel = new JLabel(icon);
                    board[row][col].add(pieceLabel);
                }
                board[row][col].revalidate();
                board[row][col].repaint();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        C7.setBackground(new java.awt.Color(153, 102, 0));

        C7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        H3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        G4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        C2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        A4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        D2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        B5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        D7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        E2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        A5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        A7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        F2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        E7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        G2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        F7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        G7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        C5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        D5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        H2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        H6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        B2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        C6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        H7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        B3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        E5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        A8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Rook.png"))); // NOI18N

        javax.swing.GroupLayout A8Layout = new javax.swing.GroupLayout(A8);
        A8.setLayout(A8Layout);
        A8Layout.setHorizontalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(A8Label)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        A8Layout.setVerticalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(A8Label)
                .addGap(27, 27, 27))
        );

        A3.setBackground(new java.awt.Color(153, 102, 0));

        A3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        C3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        B1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Knight.png"))); // NOI18N

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

        B6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        C8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Bishop.png"))); // NOI18N

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(C8Label)
                .addGap(27, 27, 27))
        );

        D6.setBackground(new java.awt.Color(153, 102, 0));

        D6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        C1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Bishop.png"))); // NOI18N

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

        D8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Queen.png"))); // NOI18N

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
                .addGap(27, 27, 27))
        );

        D3.setBackground(new java.awt.Color(255, 255, 255));

        D3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        F5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        D1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Queen.png"))); // NOI18N

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

        E8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_King.png"))); // NOI18N

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

        H4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        E6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        F8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Bishop.png"))); // NOI18N

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

        C4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        G5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        E3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        B4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        F6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        H5Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        A1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        D4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        G6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        F3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        A6Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        E1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_King.png"))); // NOI18N

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

        E4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        F1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Bishop.png"))); // NOI18N

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

        G3Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        G8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Knight.png"))); // NOI18N

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

        G1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Knight.png"))); // NOI18N

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

        F4Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        H8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Rook.png"))); // NOI18N

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

        H1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

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

        B8Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Knight.png"))); // NOI18N

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B8Label)
                .addGap(27, 27, 27))
        );

        A2.setBackground(new java.awt.Color(255, 255, 255));

        A2Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Pawn.png"))); // NOI18N

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

        B7Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Pawn.png"))); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("User 1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("User 2");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Game Window");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 990, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(H6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(H4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(H2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(H1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(G1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
