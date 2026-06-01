package finalproject;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends javax.swing.JFrame {

    private SandboxWindow sandboxWindow;
    private GameWindow gameWindow;
    private CreateUser createUser;
    private EnterUsername enterUsername;
    
    // https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    public void MoveJFrame() {
        this.setUndecorated(true);
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public MainWindow() {
        MoveJFrame();
        initComponents();
    }
    
    public static class FrameDragListener extends MouseAdapter {
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        newGameBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        chessIconDecoration = new javax.swing.JLabel();
        chessIconDecoration2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        importGameMenuItem = new javax.swing.JMenuItem();
        exportGameMenuItem = new javax.swing.JMenuItem();
        newMenu = new javax.swing.JMenu();
        newUserMenuItem = new javax.swing.JMenuItem();
        newGameMenuItem = new javax.swing.JMenu();
        competitiveMenuItem = new javax.swing.JMenuItem();
        sandboxMenuItem = new javax.swing.JMenuItem();
        customizeMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        newGameBtn.setText("New Game");

        titleLabel.setFont(new java.awt.Font("Jokerman", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 153, 255));
        titleLabel.setText("Creative Chess");

        chessIconDecoration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChessKingArtIcon.png"))); // NOI18N

        chessIconDecoration2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChessPawnArtIcon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(newGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chessIconDecoration2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chessIconDecoration)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(chessIconDecoration))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chessIconDecoration2)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        importGameMenuItem.setText("Import Game...");
        importGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(importGameMenuItem);

        exportGameMenuItem.setText("Export Game...");
        fileMenu.add(exportGameMenuItem);

        menuBar.add(fileMenu);

        newMenu.setText("New");

        newUserMenuItem.setText("New User");
        newUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(newUserMenuItem);

        newGameMenuItem.setText("New Game...");

        competitiveMenuItem.setText("Competitive");
        competitiveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                competitiveMenuItemActionPerformed(evt);
            }
        });
        newGameMenuItem.add(competitiveMenuItem);

        sandboxMenuItem.setText("Sandbox");
        sandboxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sandboxMenuItemActionPerformed(evt);
            }
        });
        newGameMenuItem.add(sandboxMenuItem);

        newMenu.add(newGameMenuItem);

        menuBar.add(newMenu);

        customizeMenu.setText("Customize");
        menuBar.add(customizeMenu);

        helpMenu.setText("Help");
        menuBar.add(helpMenu);

        exitMenu.setText("Exit");
        menuBar.add(exitMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGameMenuItemActionPerformed
        // Used some ai to help with this
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text File (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(null);
        Scanner s = new Scanner("");
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try (InputStream is = new FileInputStream(selectedFile)) {
                
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_importGameMenuItemActionPerformed

    private void newUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserMenuItemActionPerformed
        if (createUser == null) {
            createUser = new CreateUser(this);
        }
        createUser.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_newUserMenuItemActionPerformed

    private void competitiveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competitiveMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(this);
        }
        enterUsername.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_competitiveMenuItemActionPerformed

    private void sandboxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandboxMenuItemActionPerformed
        if (sandboxWindow == null) {
            sandboxWindow = new SandboxWindow(this);
        }
        sandboxWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_sandboxMenuItemActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chessIconDecoration;
    private javax.swing.JLabel chessIconDecoration2;
    private javax.swing.JMenuItem competitiveMenuItem;
    private javax.swing.JMenu customizeMenu;
    private javax.swing.JMenu exitMenu;
    private javax.swing.JMenuItem exportGameMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importGameMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newGameBtn;
    private javax.swing.JMenu newGameMenuItem;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem newUserMenuItem;
    private javax.swing.JMenuItem sandboxMenuItem;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
