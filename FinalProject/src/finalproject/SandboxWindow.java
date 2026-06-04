package finalproject;

public class SandboxWindow extends javax.swing.JFrame {

    private GameWindow gameWindow;
    MainWindow mainWindow;
    
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
    
    public SandboxWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        mainWindow = m;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startSandboxGameButton = new javax.swing.JButton();
        editPawnButton = new javax.swing.JButton();
        editKnightButton = new javax.swing.JButton();
        editBishopButton = new javax.swing.JButton();
        editRookButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startSandboxGameButton.setText("Start Game");
        startSandboxGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSandboxGameButtonActionPerformed(evt);
            }
        });

        editPawnButton.setText("Edit");

        editKnightButton.setText("Edit");

        editBishopButton.setText("Edit");

        editRookButton.setText("Edit");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startSandboxGameButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(editPawnButton)
                                .addGap(18, 18, 18)
                                .addComponent(editKnightButton)
                                .addGap(18, 18, 18)
                                .addComponent(editBishopButton))
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(editRookButton)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPawnButton)
                    .addComponent(editKnightButton)
                    .addComponent(editBishopButton)
                    .addComponent(editRookButton))
                .addGap(46, 46, 46)
                .addComponent(startSandboxGameButton)
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startSandboxGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSandboxGameButtonActionPerformed
        if (gameWindow == null) {
            gameWindow = new GameWindow(this, "Player 1", "Player 2");
        }
        gameWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_startSandboxGameButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editBishopButton;
    private javax.swing.JButton editKnightButton;
    private javax.swing.JButton editPawnButton;
    private javax.swing.JButton editRookButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton startSandboxGameButton;
    // End of variables declaration//GEN-END:variables
}
