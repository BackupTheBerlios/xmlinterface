#include <sstream>
#include <iostream.h>

#include "vstdom.hpp"
#include "utils.hpp"

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

  impl = DOMImplementationRegistry::getDOMImplementation(XMLString::transcode("Core"));
  int errorCode = 0;
  if (impl != NULL) {
    try {
     doc = impl->createDocument(
                 0,                    // root element namespace URI.
                 XMLString::transcode("xmlgui"),         // root element name
                 0);                   // document type object (DTD).

     DOMElement* rootElem = doc->getDocumentElement();

     DOMElement*  prodElem = doc->createElement(XMLString::transcode("widgets"));
     rootElem->appendChild(prodElem);

     
     rootElem->setAttribute(XMLString::transcode("magic"), XMLString::transcode("enable"));
     rootElem->setAttribute(XMLString::transcode("xmlns:xsi"), XMLString::transcode("http://www.w3.org/2001/XMLSchema-instance"));

     //
     // Now count the number of elements in the above DOM tree.
     //

     unsigned int elementCount = doc->getElementsByTagName(XMLString::transcode("*"))->getLength();
     XERCES_STD_QUALIFIER cout << "The tree just created contains: " << elementCount
          << " elements." << XERCES_STD_QUALIFIER endl;

   }
   catch (const DOMException& e)
   {
     XERCES_STD_QUALIFIER cerr << "DOMException code is:  " << e.code << XERCES_STD_QUALIFIER endl;
     errorCode = 2;
   }
   catch (...)
   {
     XERCES_STD_QUALIFIER cerr << "An error occurred creating the document" << XERCES_STD_QUALIFIER endl;
     errorCode = 3;
   }
 }  // (inpl != NULL)
 else
 {
     XERCES_STD_QUALIFIER cerr << "Requested implementation is not supported" << XERCES_STD_QUALIFIER endl;
     errorCode = 4;
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

  parse();
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
long vstdom::getNumberOfChildWidget( int id)
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
      
    int idWidget = stringTo<int>(XMLString::transcode( widget->getAttribute( XMLString::transcode( "id"))));
    // if( !id.compare( XMLString::transcode( widget->getAttribute(XMLString::transcode( "id")))))
    if( id == idWidget) {                       // Wenn ID's uebereinstimmen ...
      do 
      {
        if( widget->getNodeType() == DOMNode::ELEMENT_NODE)
        {
          count++;      // ... zaehle Kinder (wenn es denn ELEMENT_NODE sind)...
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
 * returns the id of a root Widget
 * check first with getNumberOfChildWidget, how many root Widget
 * are there
 *
 * return -1 if no childs available
 */
int vstdom::getIdOfRootWidget( long NumberOfRootWidget)
{
  int  id     = 0;
  long count  = 0;

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
        return stringTo<int>( XMLString::transcode( widget->getAttribute( XMLString::transcode( "id"))));
      }
      count++;      
    }
  } while( widget = (DOMElement *) widget->getNextSibling());

  return id;
}

/**
 * returns a widget specified by the id
 */
widget* vstdom::getWidget( int id)
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
    int idWidget = stringTo<int>( XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "id")))); 
    if( id == idWidget) {
      // Attribute zuordnen
      widgetElement->_sId     = XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "id")));
      widgetElement->_iId     = idWidget;
      if( domWidget->hasAttribute( XMLString::transcode( "callbackID"))) {
        widgetElement->_iCallbackId = stringTo<int>(XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "callbackID"))));
      }

      widgetElement->_eWidgetType = sTypeToEnum( XMLString::transcode( domWidget->getAttribute( XMLString::transcode( "type"))));

      DOMNodeList *propertiesList    = domWidget->getElementsByTagName( XMLString::transcode( "properties"));
      DOMElement  *propertiesElement = ( DOMElement *) propertiesList->item(0);
      DOMNodeList *propertyList      = propertiesElement->getElementsByTagName( XMLString::transcode( "property"));
      
      int                 propertyNr = 0;       // aktuelles property
      DOMElement          *propertyElement;     // 
      DOMCharacterData    *propertyChild;       // der Inhalt des property Element
      std::string         propertyType;

      // Schleife �ber alle properties
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
          if( strcmp(XMLString::transcode( propertyChild->getData()), "true")) {
            widgetElement->_bEditable = true;
          }
          else {
            widgetElement->_bEditable = false;
          }
        }
        if( !propertyType.compare( "isScrollable"))
        {
          widgetElement->_bIsScrollable = stringTo<bool>(XMLString::transcode( propertyChild->getData()));
        }
        propertyNr++;
      }

      // Get the Id's of all ChildWidgets
      DOMNodeList *childwidgetlist = domWidget->getElementsByTagName( XMLString::transcode( "widget"));

      if( !(childwidgetlist->getLength() <= 0))         // No widget found
      {
        DOMElement *childWidget = (DOMElement *) childwidgetlist->item(0);
  
        do 
        {
          if( childWidget->getNodeType() == DOMNode::ELEMENT_NODE)
          {
            widgetElement->_vChildWidgets.push_back(stringTo<int>( XMLString::transcode( childWidget->getAttribute( XMLString::transcode("id")))));
          }
        } while( childWidget = (DOMElement *) childWidget->getNextSibling());
      }

      return widgetElement;
    }
    temp++;
  } while (temp < widgetlist->getLength());

  return widgetElement;
}

/**
 * add a Widget to a widget in the VST-DOM Tree
 * if no Id specified, it will be add as root widget
 */
bool vstdom::addWidget( widget toBeAdd, int idOfParentWidget)
{
  // virtuelle DOMElemente
  DOMElement* newWidget           = doc->createElement( XMLString::transcode( "widget"));
  DOMElement* newWidgetProperties = doc->createElement( XMLString::transcode( "properties"));
  DOMElement* newWidgetChilds     = doc->createElement( XMLString::transcode( "widgets"));
  
  // Attribute setzen
  newWidget->setAttribute( XMLString::transcode( "id"), XMLString::transcode( (toString(toBeAdd._iId)).c_str()));
  newWidget->setAttribute( XMLString::transcode( "type"), XMLString::transcode( (enumToString( toBeAdd._eWidgetType)).c_str()));
  if( toBeAdd._iCallbackId != NULL) {
    newWidget->setAttribute( XMLString::transcode( "callbackID"), XMLString::transcode( (toString(toBeAdd._iCallbackId)).c_str()));
  }
  
  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._iWidth != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "width"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( (toString( toBeAdd._iWidth)).c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  
  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._iHeight != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "height"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( (toString( toBeAdd._iHeight)).c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }

  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._iXCoord != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "xCoord"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( (toString( toBeAdd._iXCoord)).c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  
  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._iYCoord != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "yCoord"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( (toString( toBeAdd._iYCoord)).c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }

  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._sTitle.compare("") ) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "title"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( toBeAdd._sTitle.c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }

  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._sText.compare("")) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "text"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( toBeAdd._sText.c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  
  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._sMnemonic.compare("")) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "mnemonic"));
    DOMText* newDOMText = doc->createTextNode( XMLString::transcode( toBeAdd._sMnemonic.c_str()));
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }

  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._bchecked != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "checked"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "true"));

    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  else {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "checked"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "false"));

    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }

  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._bEditable != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "editable"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "true"));

    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  else {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "editable"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "false"));
    
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  
  // Property Element zusammensetzen und an die Gruppe haengen
  if( toBeAdd._bIsScrollable != NULL) {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "scrollable"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "true"));
    
    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
  else {
    DOMElement* newProperty = doc->createElement( XMLString::transcode( "property"));
  
    newProperty->setAttribute( XMLString::transcode( "name"), XMLString::transcode( "scrollable"));
    DOMText* newDOMText = NULL;
    newDOMText = doc->createTextNode( XMLString::transcode( "false"));

    newProperty->appendChild( newDOMText);
    newWidgetProperties->appendChild( newProperty);
  }
    
  // die Gruppen Properties und Childs an das Widget Element haengen
  newWidget->appendChild( newWidgetProperties);
  newWidget->appendChild( newWidgetChilds);

  // obersten DOM Root ermitteln
  DOMNodeList *rootwidgetslist = doc->getElementsByTagName( XMLString::transcode( "widgets"));
  if( rootwidgetslist->getLength() <= 0)         // No widget found
  {
    return false;
  }
  if( idOfParentWidget == -1) {
    DOMElement *rootWidget = NULL;
    rootWidget = (DOMElement *) rootwidgetslist->item(0);
    rootWidget->appendChild( newWidget);
  }
  else {
    DOMNodeList *rootwidgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));
    if( rootwidgetlist->getLength() <= 0)         
    {
      return false;     // No widget founded
    }

    int temp = 0;

    do
    {
      DOMElement *widget = (DOMElement *) rootwidgetlist->item(temp);
      
      int id = stringTo<int>( XMLString::transcode( widget->getAttribute( XMLString::transcode( "id"))));
      if( id == idOfParentWidget) {                       // Wenn ID's uebereinstimmen ...
        DOMNodeList *widgetsList = widget->getElementsByTagName( XMLString::transcode( "widgets"));
        DOMElement *Widgets = (DOMElement *) widgetsList->item(0);
        Widgets->appendChild( newWidget);
        break;
      }
      temp++;
    }while( temp < rootwidgetlist->getLength());
  }

  return true;
}


/**
 * change the values of an existing Widget
 */
bool vstdom::updateWidget( widget changedWidget, int idOfWidget)
{
  DOMNodeList *rootwidgetlist = doc->getElementsByTagName( XMLString::transcode( "widget"));
  if( rootwidgetlist->getLength() <= 0)         
  {
    return false;     // No widget founded
  }

  int temp = 0;

  do
  {
    DOMElement *widget = (DOMElement *) rootwidgetlist->item(temp);
      
    int id = stringTo<int>( XMLString::transcode( widget->getAttribute( XMLString::transcode( "id"))));
    if( id == idOfWidget) {                       // Wenn ID's uebereinstimmen ...
      // ... aender die Werte im DOM
      // zuerst die Attribute
      std::string tempstr = enumToString( changedWidget._eWidgetType);
      widget->setAttribute( XMLString::transcode( "type"),
                            XMLString::transcode( tempstr.c_str()));
    
      // dann die Properties
      DOMNodeList *propertiesList    = widget->getElementsByTagName( XMLString::transcode( "properties"));
      DOMElement  *propertiesElement = ( DOMElement *) propertiesList->item(0);
      DOMNodeList *propertyList      = propertiesElement->getElementsByTagName( XMLString::transcode( "property"));
      
      int                 propertyNr = 0;       // aktuelles property
      DOMElement          *propertyElement;     // 
      DOMCharacterData    *propertyChild;       // der Inhalt des property Element
      std::string         propertyType;

      // Schleife �ber alle properties
      while( propertyNr < propertyList->getLength())
      {
        propertyElement = ( DOMElement *) propertyList->item( propertyNr);
        propertyChild   = ( DOMCharacterData *) propertyElement->getFirstChild();
        
        propertyType = XMLString::transcode( propertyElement->getAttribute( XMLString::transcode( "name")));
      
        if( !propertyType.compare( "width")) {
          if( changedWidget._iWidth != NULL) {
            propertyChild->setData( XMLString::transcode( (toString( changedWidget._iWidth)).c_str()));
          }
        }
        if( !propertyType.compare( "height")) {
          if( changedWidget._iHeight != NULL) {
            propertyChild->setData( XMLString::transcode( (toString( changedWidget._iHeight)).c_str()));
          }
        }
        if( !propertyType.compare( "xCoord")) {
          if( changedWidget._iXCoord != NULL) {
            propertyChild->setData( XMLString::transcode( (toString( changedWidget._iXCoord)).c_str()));
          }
        }
        if( !propertyType.compare( "yCoord")) {
          if( changedWidget._iYCoord != NULL) {
            propertyChild->setData( XMLString::transcode( (toString( changedWidget._iYCoord)).c_str()));
          }
        }
        if( !propertyType.compare( "title")) {
          propertyChild->setData( XMLString::transcode( changedWidget._sTitle.c_str()));
        }
        if( !propertyType.compare( "text")) {
          propertyChild->setData( XMLString::transcode( changedWidget._sText.c_str()));
        }
        if( !propertyType.compare( "mnemonic")) {
          propertyChild->setData( XMLString::transcode( changedWidget._sMnemonic.c_str()));
        }
        if( !propertyType.compare( "checked")) {
          if( changedWidget._bchecked != NULL) {
            propertyChild->setData( XMLString::transcode( (boolToString( changedWidget._bchecked)).c_str()));
          }
        }
        if( !propertyType.compare( "editable")) {
          if( changedWidget._bEditable != NULL) {
            propertyChild->setData( XMLString::transcode( (boolToString( changedWidget._bEditable)).c_str()));
          }
        }
        if( !propertyType.compare( "isScrollable")) {
          if( changedWidget._bIsScrollable != NULL) {
            propertyChild->setData( XMLString::transcode( (boolToString( changedWidget._bIsScrollable)).c_str()));
          }
        }
        
        propertyNr++;
      }
    }
    temp++;
  } while (temp < rootwidgetlist->getLength());

  
  return false;
}
