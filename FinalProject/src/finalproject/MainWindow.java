package finalproject;

import static finalproject.LeaderboardWindow.mergeSort;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends javax.swing.JFrame {

    private SandboxWindow sandboxWindow;
    private GameWindow gameWindow;
    private CreateUser createUser;
    private EnterUsername enterUsername;
    private HelpWindow helpWindow;
    private MainWindow mainWindow;
    private LeaderboardWindow leaderboardWindow;
    private WarningWindow warningWindow;
    private SkinWindow skinWindow;
   
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
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Leaderboard.txt", true);
            FileOutputStream out2 = new FileOutputStream(System.getProperty("user.dir") + "/Leaderboard.txt", false);
            Scanner s = new Scanner(in);
            String[] items = s.nextLine().split(":");
            User[] leaderboard = new User[items.length];
            int[] scores = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                int wins = Integer.parseInt(items[i].split(",")[1]);
                int losses = Integer.parseInt(items[i].split(",")[3]);
                scores[i] = (wins - losses);
                leaderboard[i] = new User(items[i].split(",")[0], scores[i]);
            }
            mergeSort(leaderboard, 0, leaderboard.length - 1);
            String[] sLeaderboard = new String[leaderboard.length];
            for (int i = 0; i < leaderboard.length; i++) {
                sLeaderboard[i] = "#" + (i + 1) + " " + leaderboard[i].toString();
            }
            try {
                out2.write("".getBytes());
                for (int i = 0; i < sLeaderboard.length; i++) {
                    out.write(sLeaderboard[i].getBytes());
                }
            } catch(IOException e) {
                warningWindow = new WarningWindow(this, "There was an error with the parsing the Users file. Please see user manual for more help.");    
            }
        } catch (NoSuchElementException e) {
            
        } catch (FileNotFoundException e) {
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users file. Please see user manual for more help.");    
        }
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
        } catch (NoSuchElementException e) {
            
        } catch (FileNotFoundException e) {
            warningWindow = new WarningWindow(this, "There was an error with the location of the Leaderboard file. Please see user manual for more help");
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

        leaderboardPanel = new javax.swing.JPanel();
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
        newMenu = new javax.swing.JMenu();
        newUserMenuItem = new javax.swing.JMenuItem();
        newGameMenuItem = new javax.swing.JMenu();
        competitiveMenu = new javax.swing.JMenu();
        oneMinMenuItem = new javax.swing.JMenuItem();
        twoMinMenuItem = new javax.swing.JMenuItem();
        fiveMinMenuItem = new javax.swing.JMenuItem();
        tenMinMenuItem = new javax.swing.JMenuItem();
        thirtyMinMenuItem = new javax.swing.JMenuItem();
        sandboxMenuItem = new javax.swing.JMenuItem();
        customizeMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leaderboardPanel.setBackground(new java.awt.Color(204, 255, 255));
        leaderboardPanel.setForeground(new java.awt.Color(255, 255, 255));

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

        viewFullLeaderboardButton.setText("Load Full Leaderboard");
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
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(thirdPlaceLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(viewFullLeaderboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(secondPlaceLabel)
                            .addComponent(firstPlaceLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(thirdPlaceLabel)))
                .addGap(1, 1, 1)
                .addComponent(secondPlaceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firstPlaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewFullLeaderboardButton)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout leaderboardPanelLayout = new javax.swing.GroupLayout(leaderboardPanel);
        leaderboardPanel.setLayout(leaderboardPanelLayout);
        leaderboardPanelLayout.setHorizontalGroup(
            leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaderboardPanelLayout.createSequentialGroup()
                .addGroup(leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaderboardPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chessIconDecoration2)
                            .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chessIconDecoration))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaderboardPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leaderboardPanelLayout.setVerticalGroup(
            leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaderboardPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(leaderboardPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leaderboardPanelLayout.createSequentialGroup()
                                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chessIconDecoration2))
                            .addComponent(chessIconDecoration))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        topBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        newMenu.setText("New");

        newUserMenuItem.setText("New User");
        newUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(newUserMenuItem);

        newGameMenuItem.setText("New Game...");

        competitiveMenu.setText("Competitive");

        oneMinMenuItem.setText("1 minute");
        oneMinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneMinMenuItemActionPerformed(evt);
            }
        });
        competitiveMenu.add(oneMinMenuItem);

        twoMinMenuItem.setText("2 minute");
        twoMinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoMinMenuItemActionPerformed(evt);
            }
        });
        competitiveMenu.add(twoMinMenuItem);

        fiveMinMenuItem.setText("5 minute");
        fiveMinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveMinMenuItemActionPerformed(evt);
            }
        });
        competitiveMenu.add(fiveMinMenuItem);

        tenMinMenuItem.setText("10 minute");
        tenMinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenMinMenuItemActionPerformed(evt);
            }
        });
        competitiveMenu.add(tenMinMenuItem);

        thirtyMinMenuItem.setText("30 minute");
        thirtyMinMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thirtyMinMenuItemActionPerformed(evt);
            }
        });
        competitiveMenu.add(thirtyMinMenuItem);

        newGameMenuItem.add(competitiveMenu);

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
            .addComponent(leaderboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leaderboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        leaderboardPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserMenuItemActionPerformed
        if (createUser == null) {
            createUser = new CreateUser(this);
        }
        createUser.setVisible(true);
    }//GEN-LAST:event_newUserMenuItemActionPerformed

    private void sandboxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandboxMenuItemActionPerformed
        if (sandboxWindow == null) {
            sandboxWindow = new SandboxWindow(this);
        }
        sandboxWindow.setVisible(true);
    }//GEN-LAST:event_sandboxMenuItemActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed

    }//GEN-LAST:event_exitMenuActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        if (sandboxWindow == null) {
            sandboxWindow = new SandboxWindow(this);
        }
        sandboxWindow.setVisible(true);
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void viewFullLeaderboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFullLeaderboardButtonActionPerformed
        getTop3();
        if (leaderboardWindow == null) {
            leaderboardWindow = new LeaderboardWindow(mainWindow);
        }
        leaderboardWindow.setVisible(true);
    }//GEN-LAST:event_viewFullLeaderboardButtonActionPerformed

    private void helpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuMouseClicked
        if (helpWindow == null) {
            helpWindow = new HelpWindow(mainWindow);
        }
        helpWindow.setVisible(true);
    }//GEN-LAST:event_helpMenuMouseClicked

    private void exitMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuMouseClicked
        System.exit(0);
        
    }//GEN-LAST:event_exitMenuMouseClicked

    private void customizeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizeMenuMouseClicked
        if (skinWindow == null) {
            skinWindow = new SkinWindow(mainWindow);
        }
        skinWindow.setVisible(true);
    }//GEN-LAST:event_customizeMenuMouseClicked

    private void tenMinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenMinMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(mainWindow, 600);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_tenMinMenuItemActionPerformed

    private void oneMinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneMinMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(mainWindow, 60);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_oneMinMenuItemActionPerformed

    private void twoMinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoMinMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(mainWindow, 120);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_twoMinMenuItemActionPerformed

    private void fiveMinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveMinMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(mainWindow, 300);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_fiveMinMenuItemActionPerformed

    private void thirtyMinMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thirtyMinMenuItemActionPerformed
        if (enterUsername == null) {
            enterUsername = new EnterUsername(mainWindow, 1800);
        }
        enterUsername.setVisible(true);
    }//GEN-LAST:event_thirtyMinMenuItemActionPerformed

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
    private javax.swing.JMenu competitiveMenu;
    private javax.swing.JMenu customizeMenu;
    private javax.swing.JMenu exitMenu;
    private javax.swing.JLabel firstPlaceLabel;
    private javax.swing.JMenuItem fiveMinMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel leaderboardPanel;
    private javax.swing.JButton newGameButton;
    private javax.swing.JMenu newGameMenuItem;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem newUserMenuItem;
    private javax.swing.JMenuItem oneMinMenuItem;
    private javax.swing.JMenuItem sandboxMenuItem;
    private javax.swing.JLabel secondPlaceLabel;
    private javax.swing.JMenuItem tenMinMenuItem;
    private javax.swing.JLabel thirdPlaceLabel;
    private javax.swing.JMenuItem thirtyMinMenuItem;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JMenuBar topBar;
    private javax.swing.JMenuItem twoMinMenuItem;
    private javax.swing.JButton viewFullLeaderboardButton;
    // End of variables declaration//GEN-END:variables
}
