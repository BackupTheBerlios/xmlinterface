package de.fhtw.xgl.interpreter.swing.guiEditor;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import de.fhtw.xgl.interpreter.swing.widgets.SwingWindow;

/*
 * dragger.java
 *
 * Created on 25. April 2004, 14:26
 */

/**
 *
 * @author  Stefan
 */
public class GuiEditor extends javax.swing.JFrame implements ActionListener {
 
	private int moveToX, moveToY;
	private Point p;
	private Component cmp[];
	private int count=0;
	SwingWindow parent;
	Context popup;
	Component currentCMP;
	EditorPanel ep;
	MenuEditor ME;
	CallbackList cL;
	private Point oldPoint;
	
    public GuiEditor (SwingWindow w) {
    		parent = w;
    		Point p = new Point();
    		popup = new Context(this);
    		cL = new CallbackList(this);
    		ep = new EditorPanel(this);

    		ep.setLocation((parent.getX() + parent.getWidth()), parent.getY());
    		ep.show();
    		
        cmp = w.getContentPane().getComponents();

        popup.addItem("properties (n/a)");
        popup.addSeparator();
        popup.addItem("delete");
        parent.add(popup);
        
        for(count=0; count < cmp.length; count++)
        {
        		MouseListener ml[] = cmp[count].getMouseListeners();
        		MouseMotionListener mml[] = cmp[count].getMouseMotionListeners();
        		
        		for(int m=0; m < ml.length; m++)
				cmp[count].removeMouseListener(ml[m]);

        		for(int mm=0; mm < mml.length; mm++)
				cmp[count].removeMouseMotionListener(mml[mm]);
        		
			cmp[count].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {CMousePressed(evt);}
                public void mouseReleased(java.awt.event.MouseEvent evt) { CMouseReleased(evt);} });
			cmp[count].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) { CMouseDragged(evt);} });
        }
    }
 
  
  
  public boolean removableItem(String item)
  {
  	// quick«n dirty & andere items fehlen noch
  	if(item.equals("javax.swing.JButton"))
  		return true;
  	else 
  		return false;
  }
  
    public void actionPerformed(ActionEvent event)
    {
      if(event.getActionCommand() == "delete")
      	if(removableItem(currentCMP.getClass().getName()))
      	{
      		parent.getContentPane().remove(currentCMP);
      		parent.repaint();
      	}
    }
    
    
    private void CMouseDragged(java.awt.event.MouseEvent evt) {
		setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    		moveToX = Math.max(currentCMP.getX() - (p.x - evt.getX()), 0);	
    		moveToX = Math.min(moveToX, parent.getWidth() - currentCMP.getWidth());
    		moveToY = Math.max(currentCMP.getY() - (p.y - evt.getY()), 0);	
    		moveToY = Math.min(moveToY, parent.getHeight() - currentCMP.getHeight());	
    		currentCMP.setLocation(moveToX, moveToY);
    	}
    
    private boolean checkCollision(Component currentCMP)
    {
    		Rectangle currentCmpRect = currentCMP.getBounds();
		for(count=0; count < cmp.length; count++)
        {
			 Rectangle rect = cmp[count].getBounds();
			 if(rect.intersects(currentCmpRect) && cmp[count] != currentCMP)
			 	return false;
        }
    		return true;
    }
    
	private void CMousePressed(java.awt.event.MouseEvent evt) {
		setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        p = evt.getPoint();

        currentCMP = evt.getComponent(); 
        oldPoint = currentCMP.getLocation();
        
        if (evt.isPopupTrigger()) 
        		popup.show(evt.getComponent(), evt.getX(), evt.getY());
    }
	
    private void CMouseReleased(java.awt.event.MouseEvent evt) {
    		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    		boolean valid = checkCollision(currentCMP);
    		if(!valid)
    			currentCMP.setLocation(oldPoint);
    	}
}