package xgl.sample.action;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * <p>Überschrift: Sample for selfimplemented Border</p>
 * <p>Beschreibung: To be used with SX**</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: MasterMind</p>
 * @author RIG
 * @version 1.0
 */

public class ResizeTheShit
    implements KeyListener {
  JComponent component;
  public ResizeTheShit(JComponent jc) {
    this.component = jc;
  }

  public void keyTyped(KeyEvent e) {
    /**@todo Diese java.awt.event.KeyListener-Methode implementieren*/
    if (e.getKeyCode() == '+') {

      component.getVisibleRect().setSize( (int) component.getSize().getWidth() +
                                         1,
                                         (int) component.getSize().getHeight() + 1);
      component.setSize(1000, 2000);
      component.repaint();
      //component.setBounds(new Rectangle(35, 34, 133+10000, 43+10000));

    }

  }

  public void keyPressed(KeyEvent e) {
    /**@todo Diese java.awt.event.KeyListener-Methode implementieren*/
    if (e.getKeyChar() == '+') {
      //    component.getVisibleRect().setSize((int)component.getSize().getWidth()+1,(int)component.getSize().getHeight()+1);
//      component.setBounds(new Rectangle(35, 34, 133+10, 43+10));

    }
    System.out.println(e.getKeyCode());
    //38 = uppe
    //40 = downer
    if (38 == e.getKeyCode()) {

      component.setSize( (int) component.getSize().getWidth(),
                        (int) component.getSize().getHeight() + 1);

    }
    if (39 == e.getKeyCode()) {

      component.setSize( (int) component.getSize().getWidth() + 1,
                        (int) component.getSize().getHeight());

    }

    if (40 == e.getKeyCode()) {

      component.setSize( (int) component.getSize().getWidth(),
                        (int) component.getSize().getHeight() - 1);

    }
    if (37 == e.getKeyCode()) {

      component.setSize( (int) component.getSize().getWidth() - 1,
                        (int) component.getSize().getHeight());

    }

  }

  public void keyReleased(KeyEvent e) {
    /**@todo Diese java.awt.event.KeyListener-Methode implementieren*/

  }

}
