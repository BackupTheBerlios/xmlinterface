#include <iostream.h>

#include "vstdom.hpp"

void parseWidget(vstdom *dom, widget *wid);

int main( int argC, char *argV[])
{
  // vstdom mit xml file initiieren
  vstdom *testvstdom = new vstdom("xml/formular_test.xml");
  // vstdom *testvstdom = new vstdom("xml/beispiel.xml");

  // xmlfile parsen lassen
  testvstdom->parse();

  // Anzahl der root Elemente ermitteln
  cout << endl << "Anzahl der root Widget " << testvstdom->getNumberOfChildWidget() << endl;  

  // Schleife ueber alle root Elemente
  for( int i = 0; i < testvstdom->getNumberOfChildWidget(); i++)
  {
    // root Element einem widget zuordnen
    widget *rootwidget = testvstdom->getWidget(testvstdom->getIdOfRootWidget(i));
  
    // Ausgabe der Attribute und Proberties aller Elemente (rekursiv)
    parseWidget(testvstdom, rootwidget);
  }

  // definiere neues Widget
  widget updatedWidget;
  updatedWidget._iId = 8;
  updatedWidget._eWidgetType = button;
  updatedWidget._iWidth = 100;
  updatedWidget._iHeight = 50;
  updatedWidget._iXCoord = 10;
  updatedWidget._iYCoord = 10;
  updatedWidget._sText   = "geänderter Text";
  updatedWidget._bEditable = true;

  // uebertrage geaendertes Widget
  testvstdom->updateWidget( updatedWidget, updatedWidget._iId);

  widget newWidget;
  newWidget._eWidgetType = window;
  newWidget._iId = 88;
  newWidget._iCallbackId = 120;
  newWidget._iHeight = 43;
  newWidget._iWidth = 66;
  newWidget._iXCoord = 5;
  newWidget._iYCoord = 7;
  newWidget._bchecked = true;
  newWidget._bIsScrollable = false;
  newWidget._bEditable = true;
  newWidget._sMnemonic = "Alt-Q";
  newWidget._sTitle = "Keine Ahnung";
  newWidget._sText = "Text-O-Text";

  testvstdom->addWidget(newWidget);

  widget newWidgetZwei;
  newWidgetZwei._eWidgetType = label;
  newWidgetZwei._iId = 10;
  newWidgetZwei._iCallbackId = 12;
  newWidgetZwei._iHeight = 4;
  newWidgetZwei._iWidth = 6;
  newWidgetZwei._iXCoord = 52;
  newWidgetZwei._iYCoord = 72;
  newWidgetZwei._bchecked = true;
  newWidgetZwei._bIsScrollable = false;
  newWidgetZwei._bEditable = true;
  newWidgetZwei._sMnemonic = "Alt-Q";
  newWidgetZwei._sTitle = "Label Text";
  newWidgetZwei._sText = "Text-O-Text";

  testvstdom->addWidget(newWidgetZwei, 88);
  
  // schreibe neues xml File
  testvstdom->writexml( "neues_xml.xml");
  
  delete testvstdom;

  cout << "\n" << flush;
  return 0;
}

static int depth = 0;

void parseWidget(vstdom *dom, widget *wid)
{
  std::string einrueckung;
  int a = 0;
  while( a < depth)
  {
    einrueckung += "|";
    a++;
  }
  einrueckung += " ";
  
  // Ausgabe eineindeutige ID 
  cout << endl << einrueckung.c_str() << " Widget ID " << wid->_iId;
  cout << endl << einrueckung.c_str();

  // Ausgabe der Properties
  cout << endl << einrueckung.c_str() << " Properties";
  cout << endl << einrueckung.c_str() << " width    " << wid->_iWidth;
  cout << endl << einrueckung.c_str() << " height   " << wid->_iHeight;
  cout << endl << einrueckung.c_str() << " xCoord   " << wid->_iXCoord;
  cout << endl << einrueckung.c_str() << " yCoord   " << wid->_iYCoord;
  cout << endl << einrueckung.c_str() << " title    " << (wid->_sTitle).c_str();
  cout << endl << einrueckung.c_str() << " text     " << (wid->_sText).c_str();
  cout << endl << einrueckung.c_str() << " editable " << wid->_bEditable;
  cout << endl << einrueckung.c_str() << " checked  " << wid->_bchecked;
  cout << endl << einrueckung.c_str() << " Callback " << wid->_iCallbackId;
  cout << endl << einrueckung.c_str();

  

  switch( wid->_eWidgetType)
  {
  case window:
    {
      cout << "\n## window gefunden";
      break;
    }
  case button:
    {
      cout << "\n## button gefunden";
    }
  }
  
  // Rekursiv über alle Childs
  cout << endl << einrueckung.c_str() << " " << wid->_iId << " hat " << wid->_vChildWidgets.size() << " Childwidgets.";
  for( int i = 0; i < wid->_vChildWidgets.size(); i++)
  {
    cout << endl << einrueckung.c_str();
    cout << endl << einrueckung.c_str() << i << ". Childwidget von " << wid->_iId << " ist " << wid->_vChildWidgets.at(i);
    widget *childwidget = dom->getWidget( wid->_vChildWidgets.at(i));
    if( childwidget != NULL)
    {
      depth++;
      parseWidget(dom, childwidget);
      depth--;
    }
  }
}