
import java.awt.Point;
/*
 * dragger.java
 *
 * Created on 25. April 2004, 14:26
 */

/**
 *
 * @author  Stefan
 */
public class dragger extends javax.swing.JFrame {
 
    public static void main(String args[]) {
        new dragger().show();
    }
    
    private javax.swing.JButton jButton1;
	private int moveToX, moveToY;
	private Point p;

    public dragger() {
        initComponents();
    }
    
    private void initComponents() {
   	
        jButton1 = new javax.swing.JButton("drag me");
        Point p = new Point();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);}
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);}});
        
        jButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jButton1MouseDragged(evt);}});
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));
        pack();
    }
    
    private void jButton1MouseDragged(java.awt.event.MouseEvent Mevt) {
    		moveToX = Math.max(jButton1.getX() - (p.x - Mevt.getX()), 0);	
    		moveToX = Math.min(moveToX, getWidth() - jButton1.getWidth());		
    		moveToY = Math.max(jButton1.getY() - (p.y - Mevt.getY()), 0);	
    		moveToY = Math.min(moveToY, getHeight() - jButton1.getHeight()*2);		
    		jButton1.setLocation(moveToX, moveToY);
    	}
    
	private void jButton1MousePressed(java.awt.event.MouseEvent evt) {
        p = evt.getPoint();
        setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    }
	
    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {
    		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
}