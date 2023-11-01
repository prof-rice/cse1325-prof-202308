#include "date.h"
#include <iostream>
#include <sstream>

int main() {
    int result = 0;
    int vector = 1;
      
    // Default constructor and <<
    {
        std::string expected;
        Date date; 
        std::ostringstream oss;
        oss << date;
        
        expected = "1970 January 1";
        if(oss.str() != expected) {
            result |= vector;
            std::cerr << "FAIL: Invalid default constructor or << operator"
                      << "\n  Expected: " << expected
                      << "\n  Actual:   " << oss.str()
                      << std::endl;
        }
    }
    vector <<= 1;
    
    // >> and == 
    {
        Date date;
        std::istringstream iss{"2023 April 5"};
        iss >> date;
        
        Date expected{2023, Month::Apr, 5};
        if(date != expected) {
             result |= vector;
             std::cerr << "FAIL: Invalid >> or == operator"
                       << "\n  Expected: " << expected
                       << "\n  Actual:   " << date
                       << std::endl;
        }
    }
    vector <<= 1;
    
          
    // ==, !=, <, <=, >, >=
    {
        Date lesser{2023, Month::Apr, 5};
        Date greater{2023, Month::Apr, 6};

        if(lesser == greater) {
             result |= vector;
             std::cerr << "FAIL: Invalid == operator for"
                       << "\n  " << lesser << " == " << greater
                       << std::endl;
        }

        if(!(lesser != greater)) {
             result |= vector;
             std::cerr << "FAIL: Invalid != operator for"
                       << "\n  ! " << lesser << " != " << greater
                       << std::endl;
        }

        if(!(lesser < greater)) {
             result |= vector;
             std::cerr << "FAIL: Invalid < operator for"
                       << "\n  ! " << lesser << " < " << greater
                       << std::endl;
        }

        if(!(lesser <= greater)) {
             result |= vector;
             std::cerr << "FAIL: Invalid <= operator for"
                       << "\n  ! " << lesser << " <= " << greater
                       << std::endl;
        }

        if(lesser > greater) {
             result |= vector;
             std::cerr << "FAIL: Invalid > operator for"
                       << "\n  " << lesser << " > " << greater
                       << std::endl;
        }

        if(lesser >= greater) {
             result |= vector;
             std::cerr << "FAIL: Invalid >= operator for"
                       << "\n  " << lesser << " >= " << greater
                       << std::endl;
        }
    }
    vector <<= 1;

    if(result != 0) 
        std::cerr << "\nFAIL: Error Code " << result << std::endl;

    return result;
}
