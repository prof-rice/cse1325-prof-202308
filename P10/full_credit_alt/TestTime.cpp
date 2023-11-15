#include "Time.h"

#include <iostream>
#include <sstream>
#include <vector>

// These 2 functions handle testing of time operations and comparison

// Test and report constructors and math operators
//   (operator<< must work for this to function properly).
// When result is converted to a string (with operator<<),
//   the string should exactly parameter "expected"
bool fails(const Time& result, std::string expected) {
    std::ostringstream oss;
    oss << result; // convert time to a string
    if(oss.str() == expected) return false;
    std::cerr << "FAIL: expected '" << expected
              << "' but generated '" << oss.str() << std::endl;
    return true;
}
// Report failure of comparison operator
void fails(const Time& t1, const Time& t2, std::string op) {
    std::cerr << "FAIL: operator '" << op
              << "' for '" << t1 << "' and '" << t2 << "'" << std::endl;
}

// =============================================================================
// Regression test to be performed on the Time class
//
// Any arguments of the form HH:MM:SS will also be converted to a Time object
// using operator>> and converted back with operator<< to ensure the result
// is the same as the argument. (Note: This should fail with times that require
// rationalization, e.g., 12:30:60.)
//
// Remaining tests use pre-defined values.

int main(int argc, char* argv[]) {
    int result = 0; // code to be returned to the OS (0 is pass, else fail)
    
    int vector = 1; // Constructors, operator<<
    if(fails(Time{        }, "00:00:00")) result |= vector;
    if(fails(Time{23,59,59}, "23:59:59")) result |= vector;
    
    // -------------------------------------------------------------------------
    vector <<= 1;   // operator>>, operator<<
    std::vector<std::string> times {"00:00:00", "23:59:59", 
        "01:00:00", "23:00:00", "00:01:00", "00:59:00", "00:00:01", "00:00:59"};
    for(int i=1; i<argc; ++i) // add command line arguments for testing
        times.push_back(std::string{argv[i]});
    
    Time time; // convert each vector element to Time and back to test operator>>
    for(std::string expected : times) {
        std::istringstream iss{expected};
        iss >> time;
        if(fails(time, expected)) result |= vector;
    }

    // -------------------------------------------------------------------------
    vector <<= 1;   // Rationalize
    if(fails(Time{3,4,60}, "03:05:00")) result |= vector;
    if(fails(Time{3,4,-1}, "03:03:59")) result |= vector;

    // -------------------------------------------------------------------------
    vector <<= 1;   // Addition
    if(fails(Time{1, 2, 3} + Time{6, 5, 4}, "07:07:07")) result |= vector;
    if(fails(Time{12,29,59} + Time{0, 0, 1}, "12:30:00")) result |= vector;

    // -------------------------------------------------------------------------
    vector <<= 1;   // Increment
    if(fails(++Time{12, 30, 59}, "12:31:00")) result |= vector;
    if(fails(Time{12, 30, 59}++, "12:30:59")) result |= vector;
    if(fails(++Time{23, 59, 59}, "00:00:00")) result |= vector;
    if(fails(++Time{ 0,  0,  0}, "00:00:01")) result |= vector;

    // -------------------------------------------------------------------------
    vector <<= 1;   // Comparison
    Time t0{12,30,0};
    Time t1{12,30,0}; // t0 == t1
    Time t2{12,30,1}; // t1 != t2 and t1 < t2
    
    if(  t1 == t2 ) { fails(t1, t2, "=="); result |= vector; }
    if(!(t0 == t1)) { fails(t0, t1, "=="); result |= vector; }
    if(!(t0 <= t1)) { fails(t0, t1, "<="); result |= vector; }
    if(!(t0 >= t1)) { fails(t0, t1, "<="); result |= vector; }

    if(t0 != t1) { fails(t0, t1, "!="); result |= vector; }
    if(t1 >  t2) { fails(t1, t2, "> "); result |= vector; }
    if(t1 >= t2) { fails(t1, t2, ">="); result |= vector; }
    if(t2 <  t1) { fails(t1, t2, "< "); result |= vector; }
    if(t2 <= t1) { fails(t1, t2, "<="); result |= vector; }

    // -------------------------------------------------------------------------
    // Report overall result (if any failures were detected)
    if(result != 0) std::cerr << "FAIL with error code " << result << std::endl;
    return result;
}
