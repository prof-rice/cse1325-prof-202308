#include "Time.h"

#include <iomanip>

Time::Time(int hour, int minute, int second)
    : _seconds{60 * 60 * hour + 60 * minute + second} {
      rationalize(); 
}
Time Time::operator+(Time time) {
    Time sum;
    sum._seconds = _seconds + time._seconds;
    sum.rationalize();
    return sum;
}
Time& Time::operator++() {
    ++_seconds;
    rationalize();
    return *this;
}
Time Time::operator++(int) {
    Time old = *this;
    operator++();
    return old;
}
void Time::rationalize() {
    _seconds %= (24 * 60 * 60);
}
int Time::compare(Time time) {
    return (_seconds > time._seconds) - (_seconds < time._seconds);
}
std::ostream& operator<<(std::ostream& ost, const Time& time) {
    int hour = time._seconds / (60 * 60);
    int minute = (time._seconds - (60 * 60 * hour)) / 60;
    int second  = time._seconds - (60 * 60 * hour) - (60 * minute);
    ost << std::setfill('0') 
        << std::setw(2) << hour << ':'
        << std::setw(2) << minute << ':'
        << std::setw(2) << second;
    return ost;
}
std::istream& operator>>(std::istream& ist, Time& time) {
    char discard;
    int hour;
    int minute;
    int second;
    ist >> hour >> discard >> minute >> discard >> second;
    time._seconds = 60 * 60 * hour + 60 * minute + second;
    time.rationalize();
    return ist;
}
