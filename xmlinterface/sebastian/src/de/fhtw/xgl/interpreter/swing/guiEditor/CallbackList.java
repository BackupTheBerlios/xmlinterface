package de.fhtw.xgl.interpreter.swing.guiEditor;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;
		
/*
 * Created on 15.06.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Stefan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CallbackList {

    private javax.swing.JButton addActionButton;
    private javax.swing.JPanel listPanel;
    private javax.swing.JList methodList;
	private GuiEditor gE;
	private Vector methods = new Vector();

	public CallbackList(GuiEditor editor) {
		gE = editor;
		init();
		collectMethods();
	}

    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("addAction: "+methodList.getSelectedIndex());
    }
	
    public Component getList()
    {
    		return listPanel;
    }
    
    private void fillList()
    {
        methodList.setModel(new javax.swing.AbstractListModel() {
        		Vector methods = collectMethods();
			public int getSize() { return methods.size(); }
			public Object getElementAt(int arg0) {
				return methods.elementAt(arg0); }
        });
    }
    
	protected Vector collectMethods() {
		Component[] cmp = gE.parent.getContentPane().getComponents();
		for(int i=0; i<cmp.length; i++)
		{
			Method m = null;
			try {
				m = cmp[i].getClass().getMethod("getCallbackID", null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			if(m != null)
			{
				System.out.println(cmp[i].getClass().getMethods().toString());
				Object cbId = null;
				try {
					cbId = m.invoke(cmp[i], null);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
				callBack cb = new callBack(cbId.toString(), cmp[i].getName());
				if(!methods.contains(cb))
					methods.add(cb);
			}
		}
		return methods;
	}

	private void init()
    {
        listPanel = new javax.swing.JPanel();
        methodList = new javax.swing.JList();
        addActionButton = new javax.swing.JButton();

        listPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        listPanel.setBorder(new javax.swing.border.TitledBorder("available Methods"));
        listPanel.setName("methodListPanel");
        methodList.setBorder(new javax.swing.border.EtchedBorder());
        /*
        methodList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public Object getElementAt(int i) { return strings[i]; }
            public int getSize() { return strings.length; }
        });
        */
        methodList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        methodList.setFocusable(false);
        methodList.setName("methodList");
        listPanel.add(methodList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 220));
        addActionButton.setText("<< add Action");
        addActionButton.setFocusPainted(false);
        addActionButton.setFocusable(false);
        addActionButton.setName("addAction");
        addActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionButtonActionPerformed(evt);
            }});

        listPanel.add(addActionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));
    }
}






