#include <xercesc/parsers/XercesDOMParser.hpp>
#include <xercesc/dom/DOM.hpp>
#include <xercesc/sax/HandlerBase.hpp>
#include <xercesc/util/XMLString.hpp>
#include <xercesc/util/PlatformUtils.hpp>
#include <iostream.h>

#include <xercesc/framework/StdOutFormatTarget.hpp>
#include <xercesc/framework/LocalFileFormatTarget.hpp>

#include "DOMPrintErrorHandler.hpp"

// indicate using Xerces-C++ namespace in general
using namespace xercesc;

int main (int argC, char *argV[])
{
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
    return 1;
  }

  XercesDOMParser *parser = new XercesDOMParser();
  parser->setValidationScheme( XercesDOMParser::Val_Always);  // optional.
  parser->setDoNamespaces( true);                             // optional.

  ErrorHandler *errHandler = (ErrorHandler*) new HandlerBase();
  parser->setErrorHandler( errHandler);

  char* xmlFile = "xml/beispiel.xml";

  try
  {
    parser->parse( xmlFile);
  }
  catch( const XMLException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.getMessage());
    cout << "Exception message is: \n"
         << message << "\n";
    XMLString::release( &message);
    return -1;
  }
  catch( const DOMException& toCatch)
  {
    char *message = XMLString::transcode( toCatch.msg);
    cout << "Exception message is: \n"
         << message << "\n";
    XMLString::release( &message);
    return -1;
  }
  catch (...)
  {
    cout << "Unexpected Exception \n";
    return -1;
  }

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

    // set feature if the serializer supports the feature/mode
    /*
    if (theSerializer->canSetFeature(XMLUni::fgDOMWRTSplitCdataSections, gSplitCdataSections))
        theSerializer->setFeature(XMLUni::fgDOMWRTSplitCdataSections, gSplitCdataSections);

    if (theSerializer->canSetFeature(XMLUni::fgDOMWRTDiscardDefaultContent, gDiscardDefaultContent))
        theSerializer->setFeature(XMLUni::fgDOMWRTDiscardDefaultContent, gDiscardDefaultContent);

    if (theSerializer->canSetFeature(XMLUni::fgDOMWRTFormatPrettyPrint, gFormatPrettyPrint))
        theSerializer->setFeature(XMLUni::fgDOMWRTFormatPrettyPrint, gFormatPrettyPrint);

    if (theSerializer->canSetFeature(XMLUni::fgDOMWRTBOM, gWriteBOM))
        theSerializer->setFeature(XMLUni::fgDOMWRTBOM, gWriteBOM);
    */
    //
    // Plug in a format target to receive the resultant
    // XML stream from the serializer.
    //
    // StdOutFormatTarget prints the resultant XML stream
    // to stdout once it receives any thing from the serializer.
    //

    XMLFormatTarget *myFormTarget;
    myFormTarget = new LocalFileFormatTarget("geaender.xml");
    // myFormTarget = new StdOutFormatTarget();

    // get the DOM representation
    DOMNode                     *doc = parser->getDocument();

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
    return 1;
  }

  delete parser;
  delete errHandler;
  return 0;
}


