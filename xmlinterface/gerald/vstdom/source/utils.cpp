#include <sstream>
#include <iostream.h>

#include "vstdom.hpp"
#include "utils.hpp"


/** 
 * Converting boolean to string
 */
std::string boolToString(bool val)
{
  std::string retVal;
  if( val) {
    retVal = "true";
  }
  else {
    retVal = "false";
  }
  return retVal;
}

/**
 * Returns the corresponding string of the given Types Enumeration
 */
std::string enumToString( enum Types eType)
{
  std::string retValue;

  switch( eType) {
  case kbutton:
    retValue = "button";
    break;
  case kwindow:
    retValue = "window";
    break;
  case kMenu:
    retValue = "menu";
    break;
  case kmenubar:
    retValue = "menubar";
    break;
  case kMenuItem:
    retValue = "menuitem";
    break;
  case ktextfield:
    retValue = "textfield";
    break;
  case kcheckbox:
    retValue = "checkbox";
    break;
  case kcombobox:
    retValue = "combobox";
    break;
  case kcomboboxElement:
    retValue = "comboboxElement";
    break;
  case klabel:
    retValue = "label";
    break;
  default:
    retValue = "type not defined";
  }
  return retValue;
}

/**
 * Returns the int Value of the Types Enumaration
 */
enum Types sTypeToEnum( const std::string& s)
{
  enum Types type = kbutton; // default 0 

  if( !s.compare( "window" ))
  {
    type = kwindow;
  }
  if( !s.compare( "menu" ))
  {
    type = kMenu;
  }
  if( !s.compare( "menubar" ))
  {
    type = kmenubar;
  }
  if( !s.compare( "menuItem" ))
  {
    type = kMenuItem;
  }
  if( !s.compare( "textfield" ))
  {
    type = ktextfield;
  }
  if( !s.compare( "checkbox" ))
  {
    type = kcheckbox;
  }
  if( !s.compare( "combobox" ))
  {
    type = kcombobox;
  }
  if( !s.compare( "comboboxelement" ))
  {
    type = kcomboboxElement;
  }
  if( !s.compare( "button" ))
  {
    type = kbutton;
  }
  if( !s.compare( "label" ))
  {
    type = klabel;
  }

  return type;
}
