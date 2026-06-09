package finalproject;

public class PromotionWindow extends javax.swing.JFrame {

    private GameWindow gameWindow;
    
    // https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public PromotionWindow(GameWindow m, User user1, User user2) {
        MoveJFrame();
        initComponents();
        gameWindow = m;
        hiddenInfo.setText(user1.getUserName() + "," + user2.getUserName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        queenPromotionBtn = new javax.swing.JButton();
        bishopPromotionBtn = new javax.swing.JButton();
        knightPromotionBtn = new javax.swing.JButton();
        rookPromotionBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        hiddenInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Pick a piece to promote");

        queenPromotionBtn.setText("Queen");
        queenPromotionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queenPromotionBtnActionPerformed(evt);
            }
        });

        bishopPromotionBtn.setText("Bishop");
        bishopPromotionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bishopPromotionBtnActionPerformed(evt);
            }
        });

        knightPromotionBtn.setText("Knight");
        knightPromotionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knightPromotionBtnActionPerformed(evt);
            }
        });

        rookPromotionBtn.setText("Rook");
        rookPromotionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rookPromotionBtnActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Queen.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Bishop.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Knight.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White_Rook.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Queen.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Bishop.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Knight.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Black_Rook.png"))); // NOI18N

        hiddenInfo.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        hiddenInfo.setForeground(new java.awt.Color(204, 204, 255));
        hiddenInfo.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hiddenInfo)
                            .addComponent(queenPromotionBtn)))
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(bishopPromotionBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(knightPromotionBtn)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rookPromotionBtn)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rookPromotionBtn)
                    .addComponent(knightPromotionBtn)
                    .addComponent(bishopPromotionBtn)
                    .addComponent(queenPromotionBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(hiddenInfo)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queenPromotionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queenPromotionBtnActionPerformed

        int col = gameWindow.getPromotionCol();
        int row = gameWindow.getPromotionRow();
        boolean promotionIsWhite = gameWindow.getPromotionIsWhite();
        gameWindow.fixBoardAfterPromotion(col, row, "Queen", promotionIsWhite, hiddenInfo.getText().split(",")[0], hiddenInfo.getText().split(",")[1]);
        this.setVisible(false);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_queenPromotionBtnActionPerformed

    private void bishopPromotionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bishopPromotionBtnActionPerformed
        int col = gameWindow.getPromotionCol();
        int row = gameWindow.getPromotionRow();
        boolean promotionIsWhite = gameWindow.getPromotionIsWhite();
        if (promotionIsWhite) {
            gameWindow.fixBoardAfterPromotion(col, row, "Bishop", promotionIsWhite, hiddenInfo.getText().split(",")[0], hiddenInfo.getText().split(",")[1]);
        }
        this.setVisible(false);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_bishopPromotionBtnActionPerformed

    private void knightPromotionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knightPromotionBtnActionPerformed
        int col = gameWindow.getPromotionCol();
        int row = gameWindow.getPromotionRow();
        boolean promotionIsWhite = gameWindow.getPromotionIsWhite();
        gameWindow.fixBoardAfterPromotion(col, row, "Knight", promotionIsWhite, hiddenInfo.getText().split(",")[0], hiddenInfo.getText().split(",")[1]);
        PromotionWindow.this.setVisible(false);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_knightPromotionBtnActionPerformed

    private void rookPromotionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rookPromotionBtnActionPerformed
        int col = gameWindow.getPromotionCol();
        int row = gameWindow.getPromotionRow();
        boolean promotionIsWhite = gameWindow.getPromotionIsWhite();
        gameWindow.fixBoardAfterPromotion(col, row, "Rook", promotionIsWhite, hiddenInfo.getText().split(",")[0], hiddenInfo.getText().split(",")[1]);
        PromotionWindow.this.setVisible(false);
        gameWindow.setVisible(true);
    }//GEN-LAST:event_rookPromotionBtnActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bishopPromotionBtn;
    private javax.swing.JLabel hiddenInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton knightPromotionBtn;
    private javax.swing.JButton queenPromotionBtn;
    private javax.swing.JButton rookPromotionBtn;
    // End of variables declaration//GEN-END:variables
}
