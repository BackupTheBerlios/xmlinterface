#ifndef UTILS_HPP
#define UTILS_HPP


/** 
 * Converting boolean to string
 */
std::string boolToString(bool val);

/**
 * Returns the corresponding string of the given Types Enumeration
 */
std::string enumToString( enum Types eType);

/**
 * Returns the int Value of the Types Enumaration
 */
enum Types sTypeToEnum( const std::string& s);

#endif // UTILS_HPP