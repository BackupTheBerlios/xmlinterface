#include <sstream>
#include <iostream.h>

#include "vstdom.hpp"

/**
 * Converting from string to anything
 */
template<typename T> T stringTo(const std::string& s) 
{
  std::istringstream iss(s);
  T x;
  iss >> x;
  return x;
}

/**
 * Converting anything to string
 */
template<typename T> std::string toString(const T& x) 
{
  std::ostringstream oss;
  oss << x;
  return oss.str();
}

/**
 * Returns the int Value of the Types Enumaration
 */
enum Types sTypeToEnum( const std::string& s)
{
  enum Types type = button; // default 0 

  if( !s.compare( "window" ))
  {
    type = window;
  }
  if( !s.compare( "Menu" ))
  {
    type = Menu;
  }
  if( !s.compare( "menubar" ))
  {
    type = menubar;
  }
  if( !s.compare( "MenuItem" ))
  {
    type = MenuItem;
  }
  if( !s.compare( "textfield" ))
  {
    type = textfield;
  }
  if( !s.compare( "checkbox" ))
  {
    type = checkbox;
  }
  if( !s.compare( "combobox" ))
  {
    type = combobox;
  }
  if( !s.compare( "comboboxElement" ))
  {
    type = comboboxElement;
  }
  if( !s.compare( "button" ))
  {
    type = button;
  }

  return type;
}

/**
 * Standard Constructor 
 */
vstdom::vstdom()
{
  // Initialisierung des Xerces Parser Systems
  try
  {
    XMLPlatformUtils::Initialize();
  }
  catch( const XMLException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.getMessage());
    cout << "Error during initialization! :\n"
         << message << "\n";
    XMLString::release( &message);
  }

  xmlfile = "";
}

/**
 * Constructor with xml-file
 */
vstdom::vstdom( char *guixmlfile)
{
  xmlfile = guixmlfile;

  try
  {
    XMLPlatformUtils::Initialize();
  }
  catch( const XMLException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.getMessage());
    cout << "Error during initialization! :\n"
         << message << "\n";
    XMLString::release( &message);
  }
}

/**
 * Destructor
 */
vstdom::~vstdom()
{
  delete errHandler;
  delete parser;
  // Was initialisiert wurde muss auch wieder beendet werden
  XMLPlatformUtils::Terminate();
}

/**
 * parse the xml
 */
bool vstdom::parse()
{
  if (!strcmp(xmlfile, ""))
  {
    cout << "Fehler - kein XML File angegeben";
    return false;
  }
  parser = new XercesDOMParser();
  parser->setValidationScheme( XercesDOMParser::Val_Always);  // optional.
  parser->setDoNamespaces( true);                             // optional.

  errHandler = (ErrorHandler*) new HandlerBase();
  parser->setErrorHandler( errHandler);

  try
  {
    parser->parse( xmlfile);
  }
  catch( const XMLException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.getMessage());
    cout << "Exception message is: \n"
         << message << "\n";
    XMLString::release( &message);
    return false;
  }
  catch( const DOMException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.msg);
    cout << "Exception message is: \n"
         << message << "\n";
    XMLString::release( &message);
    return false;
  }
  catch (...)
  {
    cout << "Unexpected Exception \n";
    return false;
  }

  doc = parser->getDocument();

  return true;
}

/**
 * writes the xml DOM-Tree to a file
 */
bool vstdom::writexml( char *xmlfile)
{
  try
  {
    // get a serializer, an instance of DOMWriter
    XMLCh tempStr[100];
    XMLString::transcode("LS", tempStr, 99);
    DOMImplementation *impl          = DOMImplementationRegistry::getDOMImplementation(tempStr);
    DOMWriter         *theSerializer = ((DOMImplementationLS*)impl)->createDOMWriter();

    // set user specified output encoding
    theSerializer->setEncoding(0);

    // plug in user's own error handler
    DOMErrorHandler *myErrorHandler = new DOMPrintErrorHandler();
    theSerializer->setErrorHandler(myErrorHandler);

    XMLFormatTarget *myFormTarget;
    myFormTarget = new LocalFileFormatTarget(xmlfile);

    //
    // do the serialization through DOMWriter::writeNode();
    //
    theSerializer->writeNode(myFormTarget, *doc);

    delete theSerializer;

    //
    // Filter, formatTarget and error handler
    // are NOT owned by the serializer.
    //
    delete myFormTarget;
    delete myErrorHandler;

  }
  catch (XMLException& e)
  {
    char *message = XMLString::transcode( e.getMessage());
    cout << "Error during initialization! :\n"
         << XERCES_STD_QUALIFIER endl
         << message << XERCES_STD_QUALIFIER endl;
    XMLString::release( &message);
    return false;
  }

  return true;
}

/**
 * returns the number of Childwidget from a parent widget
 * if no name specified, it returns the number of root widgets
 */
long vstdom::getNumberOfChildWidget( std::string id)
{
  long count = 0;
  long temp = 0;

  DOMNodeList *rootwidgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));
  if( rootwidgetlist->getLength() <= 0)         // No widget found
  {
    return count;
  }

  do
  {
    DOMElement *widget = (DOMElement *) rootwidgetlist->item(temp);
      
    if( !id.compare( XMLString::transcode( widget->getAttribute(XMLString::transcode( "id")))))
    {
      do 
      {
        if( widget->getNodeType() == DOMNode::ELEMENT_NODE)
        {
          count++;
        }
      } while( widget = (DOMElement *) widget->getNextSibling());
      return count;
    }
    temp++;
  } while (temp < rootwidgetlist->getLength());

  return 0;
}

long vstdom::getNumberOfChildWidget()
{
  long count = 0;                               // Number of root widgets

  DOMNodeList *rootwidgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));

  if( rootwidgetlist->getLength() <= 0)         // No widget found
  {
    return count;
  }
  
  DOMElement *widget = (DOMElement *) rootwidgetlist->item(0);
  
  do 
  {
    if( widget->getNodeType() == DOMNode::ELEMENT_NODE)
    {
      count++;
    }
  } while( widget = (DOMElement *) widget->getNextSibling());

  return count;
}

/**
 * returns the name of a root Widget
 * check first the with getNumberOfChildWidget, how many root Widget
 * are there
 */
std::string vstdom::getNameOfRootWidget( long NumberOfRootWidget)
{
  std::string id = "";
  long count = 0;

  DOMNodeList *rootwidgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));
  if( rootwidgetlist->getLength() <= 0)         // No widget found
  {
    return id;
  }
  
  DOMElement *widget = (DOMElement *) rootwidgetlist->item(0);
  
  do 
  {
    if( widget->getNodeType() == DOMNode::ELEMENT_NODE)
    {
      if( count == NumberOfRootWidget)
      {
        return XMLString::transcode( widget->getAttribute(XMLString::transcode( "id")));
      }
      count++;      
    }
  } while( widget = (DOMElement *) widget->getNextSibling());

  return id;
}

/**
 * returns a widget specified by the name
 */
widget* vstdom::getWidget( std::string id)
{
  widget    *widgetElement = new widget();
  int       temp = 0;

  DOMNodeList *widgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));
  if( widgetlist->getLength() == 0)         // No widget found
  {
    return NULL;
  }  

  do
  {
    DOMElement *domWidget = ( DOMElement *) widgetlist->item( (XMLSize_t) temp);
      
    // passende Widget gefunden
    if( !id.compare( XMLString::transcode( domWidget->getAttribute(XMLString::transcode( "id")))))
    {
      // Attribute zuordnen
      widgetElement->_sId     = XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "id")));
      widgetElement->_sType   = XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "type")));
      widgetElement->_sUiType = XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "uiType")));

      widgetElement->_eWidgetType = sTypeToEnum( widgetElement->_sType);

      DOMNodeList *propertiesList    = domWidget->getElementsByTagName( XMLString::transcode( "properties"));
      DOMElement  *propertiesElement = ( DOMElement *) propertiesList->item(0);
      DOMNodeList *propertyList      = propertiesElement->getElementsByTagName( XMLString::transcode( "property"));
      
      int                 propertyNr = 0;       // aktuelles property
      DOMElement          *propertyElement;     // 
      DOMCharacterData    *propertyChild;       // der Inhalt des property Element
      std::string         propertyType;

      // Schleife über alle properties
      while( propertyNr < propertyList->getLength())
      {
        propertyElement = ( DOMElement *) propertyList->item( propertyNr);
        propertyChild   = ( DOMCharacterData *) propertyElement->getFirstChild();
        
        propertyType = XMLString::transcode( propertyElement->getAttribute( XMLString::transcode( "name")));
      
        if( !propertyType.compare( "width"))
        {
          widgetElement->_iWidth = stringTo<int>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "height"))
        {
          widgetElement->_iHeight = stringTo<int>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "xCoord"))
        {
          widgetElement->_iXCoord = stringTo<int>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "yCoord"))
        {
          widgetElement->_iYCoord = stringTo<int>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "title"))
        {
          widgetElement->_sTitle = XMLString::transcode( propertyChild->getData());
        }
        if( !propertyType.compare( "text"))
        {
          widgetElement->_sText = XMLString::transcode( propertyChild->getData());
        }
        if( !propertyType.compare( "Search"))
        {
          widgetElement->_sSearch = XMLString::transcode( propertyChild->getData());
        }
        if( !propertyType.compare( "mnemonic"))
        {
          widgetElement->_sMnemonic = XMLString::transcode( propertyChild->getData());
        }
        if( !propertyType.compare( "checked"))
        {
          widgetElement->_bchecked = stringTo<bool>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "editable"))
        {
          widgetElement->_bEditable = stringTo<bool>(XMLString::transcode( propertyChild->getData()));
        }
        if( !propertyType.compare( "isScrollable"))
        {
          widgetElement->_bIsScrollable = stringTo<bool>(XMLString::transcode( propertyChild->getData()));
        }
        propertyNr++;
      }

      // Get the names of all ChildWidgets
      DOMNodeList *childwidgetlist = domWidget->getElementsByTagName( XMLString::transcode( "widget"));

      if( !(childwidgetlist->getLength() <= 0))         // No widget found
      {
        DOMElement *childWidget = (DOMElement *) childwidgetlist->item(0);
  
        do 
        {
          if( childWidget->getNodeType() == DOMNode::ELEMENT_NODE)
          {
            widgetElement->_vChildWidgets.push_back(XMLString::transcode( childWidget->getAttribute( XMLString::transcode("id"))));
          }
        } while( childWidget = (DOMElement *) childWidget->getNextSibling());
      }

      return widgetElement;
    }
    temp++;
  } while (temp < widgetlist->getLength());

  return widgetElement;
}

