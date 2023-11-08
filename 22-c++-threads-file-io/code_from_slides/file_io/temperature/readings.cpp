#include "readings.h"
#include <sstream>
#include <fstream>

Readings::Readings(std::istream& ist) {
    Reading reading;
    ist.exceptions (ist.exceptions() | std::ifstream::badbit);
    while(ist >> reading) {  // Using our overloaded >> operator
        _readings.push_back(reading);
    }
    if(!ist.eof()) throw new std::ifstream::failure("Error reading temperatures");
}

int Readings::size() {return _readings.size();}
Reading& Readings::operator[](int index) {
    if(0 > index || index >= _readings.size()) 
        throw new std::out_of_range{"Invalid Readings index: " + index};
    return _readings[index];
}

std::string Readings::graph() {
    std::ostringstream oss; // Use a stream to format a string (more shortly)
    for(auto r : _readings) {
        oss << r << ' ';
        for (int j=0; j<r.temp()/2; j++) oss << '#';
        oss << '\n';
    }
    return oss.str();
}

