package finalproject;

public class SandboxWindow extends javax.swing.JFrame {

    private GameWindow gameWindow;
    MainWindow mainWindow;
    
    public SandboxWindow(MainWindow m) {
        initComponents();
        this.setTitle("Sandbox Rules");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startSandboxGameButton)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(editPawnButton)
                .addGap(18, 18, 18)
                .addComponent(editKnightButton)
                .addGap(18, 18, 18)
                .addComponent(editBishopButton)
                .addGap(18, 18, 18)
                .addComponent(editRookButton)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPawnButton)
                    .addComponent(editKnightButton)
                    .addComponent(editBishopButton)
                    .addComponent(editRookButton))
                .addGap(90, 90, 90)
                .addComponent(startSandboxGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startSandboxGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSandboxGameButtonActionPerformed
        if (gameWindow == null) {
            gameWindow = new GameWindow(this);
        }
        gameWindow.setVisible(true);
        gameWindow.setTitle("Sandbox / Free Play Game");
        this.setVisible(false);
    }//GEN-LAST:event_startSandboxGameButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editBishopButton;
    private javax.swing.JButton editKnightButton;
    private javax.swing.JButton editPawnButton;
    private javax.swing.JButton editRookButton;
    private javax.swing.JButton startSandboxGameButton;
    // End of variables declaration//GEN-END:variables
}
