#ifndef WIDGET_HPP
#define WIDGET_HPP

#include <string>
#include <vector>

// Possible Widget Types
enum Types
{
  klabel,
  kbutton,
  kwindow,
  kMenu,
  kmenubar,
  kMenuItem,
  ktextfield,
  kcheckbox,
  kcombobox,
  kcomboboxElement
};

class widget
{
public:
  // Attributes
  int           _iId;
  int           _iCallbackId;
  std::string   _sId;         // deprecated
  enum Types    _eWidgetType;
	
  // Properties
  int         _iWidth; 
  int         _iHeight; 
  int         _iXCoord; 
  int         _iYCoord; 
  std::string _sTitle;
  std::string _sText;
  std::string _sMnemonic;
  bool        _bchecked;
  bool        _bIsScrollable;
  bool        _bEditable;

  // Child widgets specified by name
  // std::vector<std::string>  _vChildWidgets;
  std::vector<int>     _vChildWidgets; 
  
  /**
   * Constructor initialize all Properties empty
   */
  widget() 
  {
    // Empty Attributes
    _sId          = "";
    _iId          = NULL;
    _iCallbackId  = NULL;
    // _eWidgetType  = NULL;

    // Empty Properties
    _iWidth         = NULL;
    _iHeight        = NULL;
    _iXCoord        = NULL;
    _iYCoord        = NULL;
    _bchecked       = NULL;
    _bIsScrollable  = NULL;
    _bEditable      = NULL;
    _sTitle         = "";
    _sText          = "";
    _sMnemonic      = "";
  }

};

#endif // WIDGET_HPP