package finalproject;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pieces.Piece;

public class GameWindow extends javax.swing.JFrame {
    SandboxWindow sandboxWindow;
    MainWindow mainWindow;
    
    private JPanel[][] tiles = new JPanel[8][8]; 
    private Piece[][] pieces = new Piece[8][8];
    public GameWindow(SandboxWindow m) {
        initComponents();
        sandboxWindow = m;
        this.setTitle("Game Window");
    }
    
    public GameWindow(MainWindow m) {
        initComponents();
        mainWindow = m;
        this.setTitle("Game Window");
    }
    
    /*
    private void storePanels(){
        Component[] children = boardContainerPanel.getComponents();
        int index = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col] = (JPanel) children[index];
                tiles[row][col].setLayout(new java.awt.GridBagLayout());
                index++;
            }
        }
    }
    */
    
    private void updateBoardUI() { 
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tiles[row][col].removeAll();
                Piece piece = pieces[row][col];
                if (piece != null) {
                    java.awt.image.BufferedImage img = piece.getSprite();
                    ImageIcon icon = new ImageIcon(img);
                    JLabel pieceLabel = new JLabel(icon);
                    tiles[row][col].add(pieceLabel);
                }
                tiles[row][col].revalidate();
                tiles[row][col].repaint();
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
