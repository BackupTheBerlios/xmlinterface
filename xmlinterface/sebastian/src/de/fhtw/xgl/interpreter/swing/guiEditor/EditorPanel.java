package de.fhtw.xgl.interpreter.swing.guiEditor;
/*
 * JFrame_1.java
 *
 * Created on 6. Juni 2004, 13:18
 */

/**
 *
 * @author  Stefan
 */
public class EditorPanel extends javax.swing.JFrame {

	GuiEditor GE;
	MenuEditor ME;
	CallbackList cL;

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    
	public EditorPanel(GuiEditor editor) {
		GE = editor;
		initComponents();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cL = new CallbackList(GE);

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GuiEdit");
        setResizable(false);

        jButton1.setText("MenuEdit");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditPerformed(evt);
            }
        });

        jButton2.setText("save");
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePerformed(evt);
            }
        });

        jButton3.setText("Quit");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitPerformed(evt);
            }
        });
        
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, -1));
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, -1));
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 100, -1));
        getContentPane().add(cL.getList(), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 270, 300));
        
        
        pack();
    }

    private void menuEditPerformed(java.awt.event.ActionEvent evt) {
    		ME = new MenuEditor(GE);
        ME.show();
    }

    private void quitPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void savePerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
