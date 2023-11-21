#include "Location.h"

Location::Location(std::string filename, int line) 
    : _filename{filename}, _line{line} { }
    
int Location::compare(const Location& rhs) const {
    if(_filename < rhs._filename) return -1;
    if(_filename > rhs._filename) return  1;
    return _line-rhs._line;
}

std::ostream& operator<<(std::ostream& ost, const Location& location) {
    ost << location._filename << " line " << location._line;
    return ost;
}
