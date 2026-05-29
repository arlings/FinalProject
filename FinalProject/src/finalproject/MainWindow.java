package finalproject;

public class MainWindow extends javax.swing.JFrame {

    private SandboxWindow sandboxWindow;
    
    public MainWindow() {
        initComponents();
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
                .addContainerGap(258, Short.MAX_VALUE))
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
        fileMenu.add(importGameMenuItem);

        exportGameMenuItem.setText("Export Game...");
        fileMenu.add(exportGameMenuItem);

        menuBar.add(fileMenu);

        newMenu.setText("New");

        newUserMenuItem.setText("New User");
        newMenu.add(newUserMenuItem);

        newGameMenuItem.setText("New Game...");

        competitiveMenuItem.setText("Competitive");
        newGameMenuItem.add(competitiveMenuItem);

        sandboxMenuItem.setText("Sandbox");
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
        jPanel1.getAccessibleContext().setAccessibleParent(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
