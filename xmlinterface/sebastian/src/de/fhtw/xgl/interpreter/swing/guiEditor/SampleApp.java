package de.fhtw.xgl.interpreter.swing.guiEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * sampleApp.java
 *
 * Created on 23. Mai 2004, 13:06
 */

/**
 *
 * @author  Stefan
 */
public class SampleApp extends javax.swing.JFrame {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new SampleApp().show();
    }
    
    // Variables declaration
     javax.swing.JButton jButton1;
     javax.swing.JToggleButton jButton2;
     javax.swing.JLabel jLabel1;
     javax.swing.JTextPane jTextPane1;
     javax.swing.JMenu jMenu1;
     javax.swing.JMenuBar jMenuBar1;
     javax.swing.JMenuItem jMenuItem1, jMenuItem2, jMenuItem3;
     
     	
	private boolean method_visible = false;
	private GuiEditor GE;

    /** Creates new form sampleApp */
    public SampleApp() {
        initComponents();
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }
    
    private void initComponents() {//GEN-BEGIN:initComponents
    	
    		//ML = new MethodLister(this);
    		
        jLabel1 = new javax.swing.JLabel("irgendein Label");
        jButton1 = new javax.swing.JButton("submit");
        jButton2 = new javax.swing.JToggleButton("edit gui");

        jButton1.setName("submit");
        jButton2.setName("edit gui");
        jLabel1.setName("jLabel1");
        jLabel1.setBorder(new javax.swing.border.EtchedBorder());

        jTextPane1 = new javax.swing.JTextPane();
        jTextPane1.setBorder(new javax.swing.border.EtchedBorder());
        jTextPane1.setAutoscrolls(true);
        jTextPane1.setName("jTextPane1");
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jTextPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 450, 220));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));
        
        jButton2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				 methodlister(evt);}});
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);}});
        
        
        
        jMenuBar1 = new javax.swing.JMenuBar();
        
        jMenu1 = new javax.swing.JMenu("File");
        jMenuItem1 = new javax.swing.JMenuItem("open");
        jMenuItem2 = new javax.swing.JMenuItem("save");
        jMenuItem3 = new javax.swing.JMenuItem("close");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenuBar1.add(jMenu1);
        
        javax.swing.JMenu Menu2 = new javax.swing.JMenu("edit");
        javax.swing.JMenuItem jMenuItem21 = new javax.swing.JMenuItem("copy");
        javax.swing.JMenuItem jMenuItem22 = new javax.swing.JMenuItem("cut");
        javax.swing.JMenuItem jMenuItem23 = new javax.swing.JMenuItem("paste");
        Menu2.add(jMenuItem21);
        Menu2.add(jMenuItem22);
        Menu2.add(jMenuItem23);
        jMenuBar1.add(Menu2);
        
        javax.swing.JMenu Menu12 = new javax.swing.JMenu("recent files");
        javax.swing.JMenuItem jMenuItem121 = new javax.swing.JMenuItem("alpha");
        javax.swing.JMenuItem jMenuItem122 = new javax.swing.JMenuItem("beta");
        javax.swing.JMenuItem jMenuItem123 = new javax.swing.JMenuItem("gamma");
        Menu12.add(jMenuItem121);
        Menu12.add(jMenuItem122);
        Menu12.add(jMenuItem123);
        jMenu1.add(Menu12);
        

        setJMenuBar(jMenuBar1);
        
        
        
        pack();
    }
    // End of variables declaration

	/**
	 * @param evt
	 */
	protected void methodlister(ChangeEvent evt) {
		if(method_visible) {
			method_visible=false;
			GE.dispose();
		}
		else{
			method_visible=true;
//			GE = new GuiEditor(this);
		}

	}
}