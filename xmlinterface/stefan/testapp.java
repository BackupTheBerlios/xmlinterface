/*
 * testapp.java
 * 
 * Created on 25. April 2004, 14:26
 */
/**
 * @author Stefan
 */
public class testapp extends javax.swing.JFrame {
	
	public static void main(String args[]) {
		new testapp().show();
	}
	
	private javax.swing.JButton jButton;
	private javax.swing.JTextField jTextField;
	
	public testapp() {
		initComponents();
	}
	
	private void initComponents() {
		jButton = new javax.swing.JButton();
		jTextField = new javax.swing.JTextField();
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jButton.setText("jButton");
		jButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				jButtonMouseDragged(evt);
			}
		});
		jTextField
				.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
					public void mouseDragged(java.awt.event.MouseEvent evt) {
						jTextFieldMouseDragged(evt);
					}
				});
		getContentPane().add(
				jButton,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1,
						-1));
		jTextField.setText("jTextField");
		getContentPane().add(
				jTextField,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 160,
						-1));
		pack();
	}
	
	private void jButtonMouseDragged(java.awt.event.MouseEvent evt) {
		jButton.move(evt.getX(), evt.getY());
	}
	
	private void jTextFieldMouseDragged(java.awt.event.MouseEvent evt) {
		jTextField.move(evt.getX(), evt.getY());
	}
}