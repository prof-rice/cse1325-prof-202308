#ifndef __LOCATION_H
#define __LOCATION_H

#include <iostream>

class Location {
  public:
    Location();
    Location(std::string filename, int line);
    inline bool operator==(const Location& rhs) const {return (compare(rhs) == 0);}
    inline bool operator!=(const Location& rhs) const {return (compare(rhs) != 0);}
    inline bool operator< (const Location& rhs) const {return (compare(rhs) <  0);}
    inline bool operator<=(const Location& rhs) const {return (compare(rhs) <= 0);}
    inline bool operator> (const Location& rhs) const {return (compare(rhs) >  0);}
    inline bool operator>=(const Location& rhs) const {return (compare(rhs) >= 0);}
    friend std::ostream& operator<<(std::ostream& ost, const Location& location);
    static void next_word();  // call before streaming output for a new word
  private:
    static std::string last_filename;  // Set to empty string when changing words
    int compare(const Location& rhs) const;
    std::string _filename;
    int _line;
};

#endif
