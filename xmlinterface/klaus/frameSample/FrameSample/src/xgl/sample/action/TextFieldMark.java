package xgl.sample.action;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
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

public class TextFieldMark implements FocusListener {
  JComponent component;
  Border b;
  public TextFieldMark(JComponent c) {
    this.component = c;
  }
  public void focusGained(FocusEvent e) {
    b = ((javax.swing.JTextField)this.component).getBorder();
    ((javax.swing.JTextField)this.component).setBorder(BorderFactory.createMatteBorder( 1, 5, 1, 1, Color.black));
    /**@todo Diese java.awt.event.FocusListener-Methode implementieren*/
    //throw new java.lang.UnsupportedOperationException("Methode focusGained() noch nicht implementiert.");
  }
  public void focusLost(FocusEvent e) {
    ((javax.swing.JTextField)this.component).setBorder(b);
    /**@todo Diese java.awt.event.FocusListener-Methode implementieren*/
    //throw new java.lang.UnsupportedOperationException("Methode focusLost() noch nicht implementiert.");
  }

}
