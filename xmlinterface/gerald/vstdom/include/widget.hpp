#ifndef WIDGET_HPP
#define WIDGET_HPP

#include <string>

class widget
{
public:
  std::string _name;
	std::string _type;
	std::string _uiType;
	
  int         _width; 
  int         _height; 
  int         _xCoord; 
  int         _yCoord; 
  std::string _title;
  bool        _isScrollable;
};

#endif // WIDGET_HPP