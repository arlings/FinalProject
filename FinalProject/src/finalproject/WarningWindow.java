/*
L Necakov, A Zalli, N Wang
May 21- June 10, 2026
Warning window that displays any exceptions in a user friendly manner.
 */
package finalproject;

public class WarningWindow extends javax.swing.JFrame {

    private CreateUser createUser;
    private EnterUsername enterUsername;
    private LeaderboardWindow leaderboardWindow;
    private GameWindow gameWindow;
    private MainWindow mainWindow;
    private SkinWindow skinWindow;

    /**
     * A method designed to move the frame white preventing the user from
     * accessing a hard-coded way to exit the frame.
     * https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
     * was used as a resource to find a clean way to hide the top menu of the
     * window.
     */
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Warning window constructor connected to any call from the frame
     * CreateUser
     *
     * @param m - The CreateUser frame
     * @param txt - Warning message text
     */
    public WarningWindow(CreateUser m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        createUser = m;
    }

    /**
     * Warning window constructor connected to any call from the frame
     * EnterUsername
     *
     * @param m- The EnterUsername frame
     * @param txt - Warning message text
     */
    public WarningWindow(EnterUsername m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        enterUsername = m;
    }

    /**
     * Warning window constructor connected to any call from the frame
     * LeaderboardWindow
     *
     * @param m- The LeaderboardWindow frame
     * @param txt - Warning message text
     */
    public WarningWindow(LeaderboardWindow m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        leaderboardWindow = m;
    }

    /**
     * Warning window constructor connected to any call from the frame
     * GameWindow
     *
     * @param m- The GameWindow frame
     * @param txt - Warning message text
     */
    public WarningWindow(GameWindow m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        gameWindow = m;
    }

    /**
     * Warning window constructor connected to any call from the frame
     * MainWindow
     *
     * @param m- The MainWindow frame
     * @param txt - Warning message text
     */
    public WarningWindow(MainWindow m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        mainWindow = m;
    }

    /**
     * Warning window constructor connected to any call from the frame
     * SkinWindow
     *
     * @param m- The SkinWindow frame
     * @param txt - Warning message text
     */
    public WarningWindow(SkinWindow m, String txt) {
        MoveJFrame();
        initComponents();
        warningMessage.setText(txt);
        skinWindow = m;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        warningMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Warning");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        warningMessage.setEditable(false);
        warningMessage.setBackground(new java.awt.Color(255, 204, 204));
        warningMessage.setColumns(20);
        warningMessage.setLineWrap(true);
        warningMessage.setRows(5);
        warningMessage.setWrapStyleWord(true);
        warningMessage.setOpaque(false);
        jScrollPane1.setViewportView(warningMessage);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea warningMessage;
    // End of variables declaration//GEN-END:variables
}
