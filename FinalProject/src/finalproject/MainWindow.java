package finalproject;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import repo.FileImporter;

public class MainWindow extends javax.swing.JFrame {

    private SandboxWindow sandboxWindow;
    private GameWindow gameWindow;
    private CreateUser createUser;
    private EnterUsername enterUsername;
    private HelpWindow helpWindow;
    private MainWindow mainWindow;
    private LeaderboardWindow leaderboardWindow;
    private WarningWindow warningWindow;
    

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
        getTop3();
    }
    
    public void getTop3() {
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Leaderboard.txt");
            Scanner s = new Scanner(in);
            String nextLine = s.nextLine();
            try {
                firstPlaceLabel.setText("#" + nextLine.split("#")[1].split(",")[0]);
                secondPlaceLabel.setText("#" + nextLine.split("#")[2].split(",")[0]);
                thirdPlaceLabel.setText("#" + nextLine.split("#")[3].split(",")[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                
            }
        } catch (FileNotFoundException e) {
            warningWindow = new WarningWindow(this, "There was an error with the Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true);
        }
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
        newGameButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        chessIconDecoration = new javax.swing.JLabel();
        chessIconDecoration2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        secondPlaceLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstPlaceLabel = new javax.swing.JLabel();
        thirdPlaceLabel = new javax.swing.JLabel();
        viewFullLeaderboardButton = new javax.swing.JButton();
        topBar = new javax.swing.JMenuBar();
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

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Jokerman", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 153, 255));
        titleLabel.setText("Creative Chess");

        chessIconDecoration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChessKingArtIcon.png"))); // NOI18N

        chessIconDecoration2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChessPawnArtIcon.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setOpaque(false);

        secondPlaceLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        secondPlaceLabel.setForeground(new java.awt.Color(153, 153, 153));
        secondPlaceLabel.setText("N/A");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MainLeaderboardImage.png"))); // NOI18N

        firstPlaceLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        firstPlaceLabel.setForeground(new java.awt.Color(255, 204, 102));
        firstPlaceLabel.setText("N/A");

        thirdPlaceLabel.setForeground(new java.awt.Color(192, 142, 78));
        thirdPlaceLabel.setText("N/A");

        viewFullLeaderboardButton.setText("View Full Leaderboard");
        viewFullLeaderboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFullLeaderboardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28))
                            .addComponent(viewFullLeaderboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(secondPlaceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(thirdPlaceLabel)
                                .addGap(42, 42, 42))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(firstPlaceLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondPlaceLabel)
                    .addComponent(thirdPlaceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(firstPlaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewFullLeaderboardButton)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chessIconDecoration2)
                            .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chessIconDecoration))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chessIconDecoration2))
                            .addComponent(chessIconDecoration))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        topBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        fileMenu.setText("File");
        fileMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                fileMenuMenuSelected(evt);
            }
        });
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        importGameMenuItem.setText("Import Game...");
        importGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(importGameMenuItem);

        exportGameMenuItem.setText("Export Game...");
        fileMenu.add(exportGameMenuItem);

        topBar.add(fileMenu);

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

        topBar.add(newMenu);

        customizeMenu.setText("Customize");
        customizeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customizeMenuMouseClicked(evt);
            }
        });
        topBar.add(customizeMenu);

        helpMenu.setText("Help");
        helpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpMenuMouseClicked(evt);
            }
        });
        topBar.add(helpMenu);

        exitMenu.setText("Exit");
        exitMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMenuMouseClicked(evt);
            }
        });
        topBar.add(exitMenu);

        setJMenuBar(topBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGameMenuItemActionPerformed
        // Used some Gemini AI to help with how to set a file type
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text File (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(null);
        Scanner s = new Scanner("");
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
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
    }//GEN-LAST:event_newUserMenuItemActionPerformed

    private void competitiveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competitiveMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(this);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_competitiveMenuItemActionPerformed

    private void sandboxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandboxMenuItemActionPerformed
        if (sandboxWindow == null) {
            sandboxWindow = new SandboxWindow(this);
        }
        sandboxWindow.setVisible(true);
    }//GEN-LAST:event_sandboxMenuItemActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed

    }//GEN-LAST:event_exitMenuActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed

    }//GEN-LAST:event_fileMenuActionPerformed

    private void fileMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_fileMenuMenuSelected

    }//GEN-LAST:event_fileMenuMenuSelected

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        if (sandboxWindow == null) {
            sandboxWindow = new SandboxWindow(this);
        }
        sandboxWindow.setVisible(true);
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void viewFullLeaderboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFullLeaderboardButtonActionPerformed
        if (leaderboardWindow == null) {
            leaderboardWindow = new LeaderboardWindow(mainWindow);
        }
        leaderboardWindow.setVisible(true);
        getTop3();
    }//GEN-LAST:event_viewFullLeaderboardButtonActionPerformed

    private void helpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuMouseClicked
        if (helpWindow == null) {
            helpWindow = new HelpWindow(mainWindow);
        }
        helpWindow.setVisible(true);
    }//GEN-LAST:event_helpMenuMouseClicked

    private void exitMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitMenuMouseClicked

    private void customizeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizeMenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_customizeMenuMouseClicked

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
    private javax.swing.JLabel firstPlaceLabel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importGameMenuItem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton newGameButton;
    private javax.swing.JMenu newGameMenuItem;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem newUserMenuItem;
    private javax.swing.JMenuItem sandboxMenuItem;
    private javax.swing.JLabel secondPlaceLabel;
    private javax.swing.JLabel thirdPlaceLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JMenuBar topBar;
    private javax.swing.JButton viewFullLeaderboardButton;
    // End of variables declaration//GEN-END:variables
}
