package de.fhtw.xgl.interpreter.swing.guiEditor;
/*
 * Created on 06.06.2004
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
public class MenuTreeItem{
    public String name, id, type;
    public boolean isSubMenu, isMenu;

    public MenuTreeItem(String item, String id, String type, boolean isMenu, boolean isSubMenu) {
    		this.name = item;
        this.id = id;
        this.type = type;
        this.isMenu = isMenu;
        this.isSubMenu = isSubMenu;
     }
    
    public String toString() {
        return this.name;
    }
}
