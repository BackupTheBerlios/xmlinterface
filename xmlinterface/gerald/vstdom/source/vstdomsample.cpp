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

  for( int i = 0; i < testvstdom->getNumberOfChildWidget(); i++)
  {
    widget *rootwidget = testvstdom->getWidget(testvstdom->getNameOfRootWidget(i));
  
    // Ausgabe aller Elemente (rekursiv)
    parseWidget(testvstdom, rootwidget);
  }
  
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
  
  // eineindeutige ID 
  cout << endl << einrueckung.c_str() << " Widget ID " << wid->_sId.c_str();
  cout << endl << einrueckung.c_str();

  // Properties
  cout << endl << einrueckung.c_str() << " Properties";
  cout << endl << einrueckung.c_str() << " width    " << wid->_iWidth;
  cout << endl << einrueckung.c_str() << " height   " << wid->_iHeight;
  cout << endl << einrueckung.c_str() << " xCoord   " << wid->_iXCoord;
  cout << endl << einrueckung.c_str() << " yCoord   " << wid->_iYCoord;
  cout << endl << einrueckung.c_str() << " title    " << wid->_sTitle.c_str();
  cout << endl << einrueckung.c_str() << " text     " << wid->_sText.c_str();
  cout << endl << einrueckung.c_str() << " editable " << wid->_bEditable;
  cout << endl << einrueckung.c_str() << " checked  " << wid->_bchecked;
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
  cout << endl << einrueckung.c_str() << " " << wid->_sId.c_str() << " hat " << wid->_vChildWidgets.size() << " Childwidgets.";
  for( int i = 0; i < wid->_vChildWidgets.size(); i++)
  {
    cout << endl << einrueckung.c_str();
    cout << endl << einrueckung.c_str() << " " << i << ". Childwidget von " << wid->_sId.c_str() << " ist " << wid->_vChildWidgets.at(i);
    widget *childwidget = dom->getWidget( wid->_vChildWidgets.at(i));
    if( childwidget != NULL)
    {
      depth++;
      parseWidget(dom, childwidget);
      depth--;
    }
  }
}