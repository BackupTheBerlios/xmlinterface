#ifndef WIDGET_HPP
#define WIDGET_HPP

#include <string>
#include <vector>

// Possible Widget Types
enum Types
{
    button,
    window,
    Menu,
    menubar,
    MenuItem,
    textfield,
    checkbox,
    combobox,
    comboboxElement
};

class widget
{
public:
  // Attributes
  std::string   _sId;
  std::string   _sType;
  std::string   _sUiType;
  enum Types    _eWidgetType;
	
  // Properties
  int         _iWidth; 
  int         _iHeight; 
  int         _iXCoord; 
  int         _iYCoord; 
  std::string _sTitle;
  std::string _sText;
  std::string _sMnemonic;
  std::string _sSearch;
  bool        _bchecked;
  bool        _bIsScrollable;
  bool        _bEditable;

  // Child widgets specified by name
  // std::vector<std::string>  _vChildWidgets;
  std::vector<char *>  _vChildWidgets;
  
  /**
   * Constructor initialize all Properties empty
   */
  widget() 
  {
    // Empty Attributes
    _sId   = "";
    _sType   = "";
    _sUiType = "";
    
    // Empty Properties
    _iWidth         = 0;
    _iHeight        = 0;
    _iXCoord        = 0;
    _iYCoord        = 0;
    _sTitle         = "";
    _sText          = "";
    _sMnemonic      = "";
    _bchecked       = false;
    _bIsScrollable  = false;
  }

};

#endif // WIDGET_HPP