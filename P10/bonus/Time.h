#ifndef __TIME_H
#define __TIME_H

#include <iostream>

class Time {
  public:
    Time(int hour, int minute, int second);
    Time();
    Time operator+(Time time);
    Time operator+(int seconds) const;
    friend Time operator+(int seconds, const Time& time);
    Time& operator++();
    Time operator++(int);
    
    inline bool operator==(const Time& rhs) { return compare(rhs) == 0; }
    inline bool operator!=(const Time& rhs) { return compare(rhs) != 0; }
    inline bool operator< (const Time& rhs) { return compare(rhs) <  0; }
    inline bool operator> (const Time& rhs) { return compare(rhs) >  0; }
    inline bool operator<=(const Time& rhs) { return compare(rhs) <= 0; }
    inline bool operator>=(const Time& rhs) { return compare(rhs) >= 0; }
    
    friend std::ostream& operator<<(std::ostream& ost, const Time& time);
    friend std::istream& operator>>(std::istream& ist, Time& time);
  private:
    void rationalize();  // force hour 0..23, minutes and seconds 0..59
    int force_between(int& value, int min, int max); // support rationalize()
    int compare(Time time);  // support operator== through operator>=
    
    int _hour;
    int _minute;
    int _second;
};

#endif
