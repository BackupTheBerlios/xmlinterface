package xgl.sample.action;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <p>Überschrift: Sample for selfimplemented Border</p>
 * <p>Beschreibung: To be used with SX**</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: MasterMind</p>
 * @author RIG
 * @version 1.0
 */

public class MarkAction implements ActionListener {
  java.awt.Component component;

  public MarkAction(java.awt.Component c) {
    component = c;
    System.out.println(component.hasFocus());
  }
  public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand() + component.hasFocus());
    /**@todoDiese java.awt.event.ActionListener-Methode implementieren*/
    //throw new java.lang.UnsupportedOperationException("Methode actionPerformed() noch nicht implementiert.");
  }

}
