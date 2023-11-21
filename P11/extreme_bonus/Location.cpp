#include "Location.h"

std::string Location::last_filename;

void Location::next_word() {
    last_filename = "";
}

Location::Location() : Location("", 0) { }
Location::Location(std::string filename, int line) 
    : _filename{filename}, _line{line} { }
    
int Location::compare(const Location& rhs) const {
    if(_filename < rhs._filename) return -1;
    if(_filename > rhs._filename) return  1;
    return _line-rhs._line;
}

std::ostream& operator<<(std::ostream& ost, const Location& location) {
    if(location._filename != Location::last_filename)
        ost << location._filename << " line ";
    ost << location._line;
    Location::last_filename = location._filename;
    return ost;
}
