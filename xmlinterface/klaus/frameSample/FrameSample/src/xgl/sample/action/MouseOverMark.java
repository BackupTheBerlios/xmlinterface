package xgl.sample.action;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 * <p>Überschrift: Sample for selfimplemented Border</p>
 * <p>Beschreibung: To be used with SX**</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: MasterMind</p>
 * @author RIG
 * @version 1.0
 */

public class MouseOverMark implements MouseListener {
  JComponent component;
  Border b;

  public MouseOverMark(JComponent c) {
    component = c;
  }
  public void mouseClicked(MouseEvent e) {
    /**@todo Diese java.awt.event.MouseListener-Methode implementieren*/

  }
  public void mousePressed(MouseEvent e) {
    /**@todo Diese java.awt.event.MouseListener-Methode implementieren*/

  }
  public void mouseReleased(MouseEvent e) {
    /**@todo Diese java.awt.event.MouseListener-Methode implementieren*/

  }
  public void mouseEntered(MouseEvent e) {
    b = this.component.getBorder();
    this.component.setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.black));
    /**@todo Diese java.awt.event.MouseListener-Methode implementieren*/

  }
  public void mouseExited(MouseEvent e) {
    /**@todo Diese java.awt.event.MouseListener-Methode implementieren*/
    (this.component).setBorder(b);
  }

}
