#ifndef VST_DOM_HPP
#define VST_DOM_HPP

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
   * parse the xml
   */
  bool parse();

  /** 
   * writes the xml DOM-Tree to a file
   */
  bool writexml( char *xmlfile);

  /**
   * returns the number of Childwidget from a parent widget
   * if no name specified, it returns the number of root widgets
   */
  long getNumberOfChildWidget( std::string name);
  long getNumberOfChildWidget();

  /**
   * returns the name of a root Widget
   * check first the with getNumberOfChildWidget, how many root Widget
   * are there
   */
  std::string getNameOfRootWidget( long NumberOfRootWidget);

  /**
   * returns a widget specified by the name
   */
  widget getWidget( std::string name);

  /**
   * add a Widget to a widget in the VST-DOM Tree
   * if no name specified, it will be add as root widget
   */
  bool addWidget( widget toBeAdd, std::string nameOfParentWidget);
  bool addWidget( widget toBeAdd);
  
private :
  char                      *xmlfile; // die xml-Datei mit der GUI
  xercesc::DOMDocument      *doc;
  xercesc::XercesDOMParser  *parser;
  xercesc::ErrorHandler     *errHandler;    
};


#endif // VST_DOM_HPP