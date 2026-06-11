/*
A Zalli, L Necakov, N Wang
May 21- June 10
A window where the user can get help if they encounter any bugs.
 */
package finalproject;

public class HelpWindow extends javax.swing.JFrame {

    MainWindow mainWindow;

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
     * help window constructor
     *
     * @param m - main window
     */
    public HelpWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        mainWindow = m;
        this.setTitle("Help menu");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        manualText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(153, 255, 153));

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("User Manual");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(631, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        manualText.setColumns(20);
        manualText.setRows(5);
        manualText.setText("This is a brief user manual summary for Creative Chess.\n\nThere are four drop down windows in this application:\n\n1) New - The user has the option to create a user name or create a new game. In order to create a user name, the\nuser must type the name of the username that they want to create in the text area. If that username is valid, \nthe username will be created. The user also has the option to create a game. A competitive game features \na game between two contestents with usernames. The winner gains points which add to the leaderboard \nwhile the looser does not lose any points. Stalemates result in no points. Within the competitive mode the\nuser must select the time of the game that each side has. If there is no winner at the end of ones timer, the\nteam with a greater number of points wins.\n\n2) Customize - The user has an option to Customize the game of chess and try out new skins based on the amount \nof wins they have acheived. These chess piece skins will appear in competitive games.\n\n3) Help - Opens a window with the user manual. You're here right now!\n\n4) Exit - The user has the option to exit the window when they are done playing chess and all the updated\ndata about all the usernames will be automatically saved.\n\nThis application also includes a View Full Leaderboard button. This button opens a window that gives the \nstatus of all the users going with the ones with the most wins to the least wins. Above the View Full \nLeaderboard button are also the top 3 users in terms of the number of games they have won. Should there be any \nissues with the leaderboard, either click the load button, or close and reopen the JAR file for Creative Chess.\n\nIf there are ever any errors regarding the presence or the corruption of a Leaderboard or Users file, please make\nsure that there exists a Users and Leaderboard file beside the JAR for Creative Chess and confirm that the Users and \nLeaderboard file are not being edited or tampered with while the program runs. For more information, \nplease consult the full user manual that has been provided to you at the download of the JAR file.\n");
        jScrollPane1.setViewportView(manualText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exitButton))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispose();//dispose of the window when the user selects exit
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea manualText;
    // End of variables declaration//GEN-END:variables
}
