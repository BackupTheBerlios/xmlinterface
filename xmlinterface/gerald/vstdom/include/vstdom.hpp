#ifndef VST_DOM_HPP
#define VST_DOM_HPP

#pragma warning( disable : 4786 )  // Disable warning messages

#include <string>
#include <iostream>

#include <xercesc/parsers/XercesDOMParser.hpp>
#include <xercesc/dom/DOM.hpp>
#include <xercesc/sax/HandlerBase.hpp>
#include <xercesc/util/XMLString.hpp>
#include <xercesc/util/PlatformUtils.hpp>

#include <xercesc/framework/StdOutFormatTarget.hpp>
#include <xercesc/framework/LocalFileFormatTarget.hpp>

#include "DOMPrintErrorHandler.hpp"
#include "widget.hpp"

// Helperfunctions
std::string enumToString( enum Types eType);

class vstdom
{
public:
  /**
   * Standard Constructor 
   */
  vstdom();

  /**
   * Constructor with xml-file
   */
  vstdom( char *xmlfile);

  /**
   * Destructor
   */
  ~vstdom();

  /** 
   * writes the xml DOM-Tree to a file
   */
  bool writexml( char *xmlfile);

  /**
   * returns the number of Childwidget from a parent widget
   * if no id specified, it returns the number of root widgets
   */
  long getNumberOfChildWidget( int id);
  long getNumberOfChildWidget();

  /**
   * returns the name of a root Widget
   * check first the with getNumberOfChildWidget, how many root Widget
   * are there
   */
  int getNameOfRootWidget( long NumberOfRootWidget); // deprecated
  int getIdOfRootWidget( long NumberOfRootWidget);

  /**
   * returns a widget specified by the id
   */
  widget* getWidget( int id);

  /**
   * add a Widget to a widget in the VST-DOM Tree
   * if no name specified, it will be add as root widget
   */
  bool addWidget( widget toBeAdd, int idOfParentWidget=-1);

  /**
   * change the values of an existing Widget
   */
  bool updateWidget( widget changedWidget, int idOfWidget);

private:
  /** 
   * parse the xml
   */
  bool parse();
  
private :
  char                      *xmlfile; // die xml-Datei mit der GUI
  xercesc::DOMDocument      *doc;
  xercesc::XercesDOMParser  *parser;
  xercesc::ErrorHandler     *errHandler;  
  xercesc::DOMImplementation *impl;
};


#endif // VST_DOM_HPP