#include <iostream.h>

#include "vstdom.hpp"

int main( int argC, char *argV[])
{
  // vstdom mit xml file initiieren
  vstdom *testvstdom = new vstdom("xml/beispiel.xml");

  // xmlfile parsen lassen
  testvstdom->parse();

  // Anzahl der root Elemente ermitteln
  cout << endl << "Anzahl der root Widget " << testvstdom->getNumberOfChildWidget() << endl;  

  cout << endl << "Name vom root Widget " << (testvstdom->getNameOfRootWidget(0)).c_str() << endl;
  
  // Anzahl der ChildElemente
  cout << endl << (testvstdom->getNameOfRootWidget(0)).c_str()
               << " hat so viele Kinder " 
               << testvstdom->getNumberOfChildWidget((testvstdom->getNameOfRootWidget(0)).c_str())
               << endl;

  // Get Widget Element
  widget testwidget = testvstdom->getWidget((testvstdom->getNameOfRootWidget(0)).c_str());
  // widget testwidget = testvstdom->getWidget("CheckBox1");
  // widget testwidget = testvstdom->getWidget("FileMenu");
  // widget testwidget = testvstdom->getWidget("Knopf");

  cout << endl << "Properties of    " << testwidget._sId.c_str()
       << endl << "id               " << testwidget._sId.c_str()
       << endl << "height           " << testwidget._iHeight
       << endl << "width            " << testwidget._iWidth
       << endl << "title            " << testwidget._sTitle.c_str()
       << endl << "text             " << testwidget._sText.c_str()
       << endl << "xCoord           " << testwidget._iXCoord
       << endl << "yCoord           " << testwidget._iYCoord
       << endl << "type             " << testwidget._sType.c_str()
       << endl << "number of Childs " << testwidget._vChildWidgets.size()
       << endl;

  cout << endl << "Behandlung untergeordneter Widgets"
       << endl << "Anzahl " << testwidget._vChildWidgets.size();

  widget testchildwidget;

  for( int i = 0; i < testwidget._vChildWidgets.size(); i++)
  {
    cout << endl << "Name of the " << i + 1 << ". ChildWidget of " 
         << testwidget._sId.c_str() << " " << (testwidget._vChildWidgets.at(i)).c_str();
    
    testchildwidget = testvstdom->getWidget((testwidget._vChildWidgets.at(i)).c_str());
    cout << endl << "Properties of    " << testchildwidget._sId.c_str()
         << endl << "id               " << testchildwidget._sId.c_str()
         << endl << "height           " << testchildwidget._iHeight
         << endl << "width            " << testchildwidget._iWidth
         << endl << "title            " << testchildwidget._sTitle.c_str()
         << endl << "text             " << testchildwidget._sText.c_str()
         << endl << "xCoord           " << testchildwidget._iXCoord
         << endl << "yCoord           " << testchildwidget._iYCoord
         << endl << "type             " << testchildwidget._sType.c_str()
         << endl << "number of Childs " << testchildwidget._vChildWidgets.size()
         << endl;
  }


  // testvstdom->writexml("xml/neu.xml");

  delete testvstdom;

  cout << "\n" << flush;
  return 0;
}