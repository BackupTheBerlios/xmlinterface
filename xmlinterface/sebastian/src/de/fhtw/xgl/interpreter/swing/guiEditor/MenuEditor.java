package de.fhtw.xgl.interpreter.swing.guiEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;


public class MenuEditor extends javax.swing.JFrame implements ActionListener {

    //private javax.swing.JButton addActionButton;
    private javax.swing.JButton addmenuButton;
    //private javax.swing.JButton addSubmenuButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel listPanel;
    //private javax.swing.JList methodList;
    private javax.swing.JInternalFrame previewFrame;
    private javax.swing.JMenuBar previewMenubar;
    private javax.swing.JButton saveButton;
    private javax.swing.JTree tree;
    private javax.swing.JPanel treepanel;
    private JScrollPane treeScroll;
    private GuiEditor gE;
    private javax.swing.JMenuBar originMenubar;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuItem jMenuItem;
    private DefaultMutableTreeNode top, submenu, item = null;
    public DefaultTreeModel treeModel;
    private static String lineStyle = "Horizontal";
    private MenuTreeItem menuTreeItem;
    public Vector menuStructure;
	private Context popup;
	private MouseListener ml;
	private int lastClickedItem=0;
	private CallbackList cL;
    
    public MenuEditor(GuiEditor editor) {
    		gE = editor;
    		cL = new CallbackList(editor);
    		initComponents();
    		originMenubar = gE.parent.getJMenuBar();
    		buildStructure();
    		paintTree();
    }
    
    private void deleteFromTree(MenuTreeItem item){
		menuStructure.removeElement(item);
		paintTree();
		preview();
    }

    void buildStructure(){
    		menuStructure = new Vector();
    		for(int mC=0; mC < originMenubar.getMenuCount(); mC++){
    			jMenu = originMenubar.getMenu(mC);
    			addMenu(originMenubar.getMenu(mC), true, false);
    		}
    }

    /* ToDo:
     * -items differenzieren (getType / getId, Klassen vom Interpreter Ÿbernehmen)
     * -> notewendig, um zw. menu, item  & anderen Controls zu unterscheiden
     */
	public void addMenu(JMenu item, boolean main, boolean sub){
		if(item.getSubElements().length >= 1){
			menuTreeItem = new MenuTreeItem(item.getText(), "", item.getClass().toString(), main, sub);
			menuStructure.addElement(menuTreeItem);
			
			for(int miC=0; miC < item.getItemCount(); miC++){
	        		if(item.getItem(miC).getSubElements().length >= 1)
	        			addMenu((JMenu) item.getItem(miC), false, true);
	        		else{
	        			menuTreeItem = new MenuTreeItem(item.getItem(miC).getText(), "", item.getClass().toString(), false, false);
	        			menuStructure.addElement(menuTreeItem);
	        		}
			}
		}
		else{
			menuTreeItem = new MenuTreeItem(item.getText(), "", item.getClass().toString(), false, false);
			menuStructure.addElement(menuTreeItem);
		}
	}

	void paintTree()
	{
		if(treeScroll != null) treepanel.remove(treeScroll);
	    DefaultMutableTreeNode master=null, node=null, lastNode=null;
	    master = new DefaultMutableTreeNode("MenuBar");
	   
	    for(int j=0; j<menuStructure.size(); j++){
	    		menuTreeItem = (MenuTreeItem) menuStructure.elementAt(j);
	    		node = new DefaultMutableTreeNode(menuTreeItem);
	    		if(menuTreeItem.isMenu){
	    			master.add(node);
	    			lastNode = node;
	    		}
	    		else if(menuTreeItem.isSubMenu){
	    			lastNode.add(node);
	    			lastNode = node;
	    		}
	    		else 
	    			lastNode.add(node);
	    }
	    
	    tree = new JTree(master);
	    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree.addTreeSelectionListener(new myTreeSelectionListener());
		tree.setRootVisible(true);
	    tree.putClientProperty("JTree.lineStyle", lineStyle);
	    tree.addMouseListener(ml);
	    treeScroll = new JScrollPane(tree);
	    treepanel.add(treeScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 250, 220));
	    treepanel.repaint();
	    setVisible(true);
	    preview();
	}
/*
    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
*/
    private void addmenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	 	menuTreeItem = new MenuTreeItem("tmp", "tmp", "tmp", true, false);
    	 	menuStructure.addElement(menuTreeItem);
    	 	menuTreeItem = new MenuTreeItem("<<dummy>>", "x", "x", false, false);
    	 	menuStructure.addElement(menuTreeItem);
    	 	paintTree();
    		preview();
    }

	protected void showContext(MouseEvent evt) {
		if (evt.isPopupTrigger()) 
    			popup.show(evt.getComponent(), evt.getX(), evt.getY());
	}

	public void preview()
    {
    		previewFrame.removeAll();
    		previewMenubar = new javax.swing.JMenuBar();
    		JMenu prevMenu=null, prevSubMenu=null;
    		
        for(int j=0; j< menuStructure.size(); j++){
        		menuTreeItem = (MenuTreeItem) menuStructure.elementAt(j);
        		if(menuTreeItem.isMenu) {
        			prevMenu = new javax.swing.JMenu(menuTreeItem.toString());
        			previewMenubar.add(prevMenu);
        		}
        		else if(menuTreeItem.isSubMenu) {
        			prevSubMenu = new javax.swing.JMenu(menuTreeItem.toString());
        			prevMenu.add(prevSubMenu);
        			prevMenu = prevSubMenu;
        		} 
        		else {
        			JMenuItem prevMenuItem = new javax.swing.JMenuItem(menuTreeItem.toString());
        			prevMenu.add(prevMenuItem);
        		}
        }
        previewFrame.setJMenuBar(previewMenubar);
        previewFrame.updateUI();
    }

/*
    private void addSubmenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
    		if(lastClickedItem != 0){
    			MenuTreeItem newSub = new MenuTreeItem("submenu", "123", "menu", false, true);
    			MenuTreeItem lastMenuItem=null;
    			Vector tmp = new Vector();
    			
    			for(int i=0; i<menuStructure.size();i++){
    				if(i<lastClickedItem){
    					tmp.add(menuStructure.elementAt(i));
    					lastMenuItem = (MenuTreeItem) menuStructure.elementAt(i);
    					if(!lastMenuItem.isMenu && !lastMenuItem.isSubMenu)
    						lastMenuItem = null;
    				}
    				else if(i == lastClickedItem)
    				{
    					tmp.insertElementAt(newSub, i);
    					MenuTreeItem tmpItem = (MenuTreeItem) menuStructure.elementAt(i);
    					
    				}
    				else
    					tmp.add(menuStructure.elementAt(i-1));
	    		}
    			
    			menuStructure = tmp;
    			paintTree();
    			//addSubmenuButton.setEnabled(false);
    			lastClickedItem=0;
    		}
    }*/

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        jButton1.setVisible(true);
        jLabel1.setVisible(true);
        jButton2.setVisible(true);
        exitButton.setVisible(false);
    }
    
	protected void saveAndExit(ActionEvent evt) {
		gE.parent.setJMenuBar(previewMenubar);
		dispose();
	}

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {
    		System.out.println("treeValueChanged");
    }

    private class myTreeSelectionListener implements TreeSelectionListener, ActionListener{
        public void valueChanged(TreeSelectionEvent e) {
        		MenuTreeItem mTI = getItemByEvent(e);
        		//System.out.println("MenuTreeItem: "+mTI+" : "+mTI.type);
        		//if(mTI.type.equalsIgnoreCase("menu")){
        			lastClickedItem = menuStructure.indexOf(mTI);
        			//addSubmenuButton.setEnabled(true);
        		//}
    		}

		public void actionPerformed(ActionEvent arg0) {
			System.out.println("actionPerformed: ");
		}
    }
       

	
	public MenuTreeItem getItemByEvent(TreeSelectionEvent e){
	      DefaultMutableTreeNode node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
	    	  Object nodeInfo = node.getUserObject();
	    	  if(nodeInfo instanceof MenuTreeItem){
	    	  	MenuTreeItem item = (MenuTreeItem)nodeInfo;
	    	  	return item;
	    	  }else 
	    	  	return null;
	}
	

	public void actionPerformed(ActionEvent arg) {
		if(arg.getActionCommand().equalsIgnoreCase("delete") && lastClickedItem != -1)
			deleteFromTree((MenuTreeItem) menuStructure.elementAt(lastClickedItem));
		else if(lastClickedItem != -1)
			moveItem((String) arg.getActionCommand());
		lastClickedItem = -1;
	}


	private void moveItem(String direction) {
		if(direction.equalsIgnoreCase("move up"))
		{
			MenuTreeItem item = (MenuTreeItem) menuStructure.elementAt(lastClickedItem);
			MenuTreeItem item1 = (MenuTreeItem) menuStructure.elementAt(lastClickedItem-1);
			menuStructure.setElementAt(item1, (lastClickedItem));
			menuStructure.setElementAt(item, (lastClickedItem-1));
			paintTree();
			preview();
		}
		else if(direction.equalsIgnoreCase("move down"))
		{
			MenuTreeItem item = (MenuTreeItem) menuStructure.elementAt(lastClickedItem);
			MenuTreeItem item1 = (MenuTreeItem) menuStructure.elementAt(lastClickedItem+1);
			menuStructure.setElementAt(item1, (lastClickedItem));
			menuStructure.setElementAt(item, (lastClickedItem+1));
			paintTree();
			preview();
		}
	}

	// only constructor below 
    private void initComponents() {
        treepanel = new javax.swing.JPanel();
        tree = new javax.swing.JTree();
        addmenuButton = new javax.swing.JButton();
       // addSubmenuButton = new javax.swing.JButton();
        listPanel = new javax.swing.JPanel();
        previewFrame = new javax.swing.JInternalFrame();
        exitButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        previewMenubar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        
		popup = new Context(this);
        popup.addItem("move up");
        popup.addSeparator();
        popup.addItem("move down");
        popup.addSeparator();
        popup.addItem("delete");
        getContentPane().add(popup);
        
        ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
               // TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
               // System.out.println("selPath: "+selPath);
                if(selRow != -1) 
                		showContext(e);
            }
        };
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MenuEditor");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        treepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        treepanel.setBorder(new javax.swing.border.TitledBorder("MenuTree"));

        addmenuButton.setText("add Menu");
        addmenuButton.setFocusPainted(false);
        addmenuButton.setFocusable(false);
        addmenuButton.setName("addMenu");
        addmenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenuButtonActionPerformed(evt);
            }
        });

        treepanel.add(addmenuButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 120, -1));

        getContentPane().add(treepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 290));
        getContentPane().add(cL.getList(), new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 280, 290));

        previewFrame.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        previewFrame.setBorder(null);
        previewFrame.setName("previewFrame");
        previewFrame.setVisible(true);
        
        exitButton.setText("Exit");
        exitButton.setFocusPainted(false);
        exitButton.setFocusable(false);
        exitButton.setName("exitButton");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }});

        previewFrame.getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 100, -1));

        jButton1.setText("Exit only");
        jButton1.setFocusPainted(false);
        jButton1.setVisible(false);
        previewFrame.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 150, -1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }});

        jButton2.setText("Save & Exit");
        jButton2.setVisible(false);
        jButton2.setFocusPainted(false);
        previewFrame.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 100, -1));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndExit(evt);
            }});

        previewMenubar.setBackground(new java.awt.Color(204, 204, 255));
        jMenu1.setBackground(new java.awt.Color(204, 204, 255));
        jMenu1.setText("MenuPreview");
        jMenu1.setName("previewMenuBar");
        previewMenubar.add(jMenu1);

        previewFrame.setJMenuBar(previewMenubar);
        getContentPane().add(previewFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 570, 100));
        pack();
    }
}



