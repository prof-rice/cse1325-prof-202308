#include "Time.h"

#include <iomanip>

Time::Time(int hour, int minute, int second)
    : _hour{hour}, _minute{minute}, _second{second} {
      rationalize(); 
}
Time::Time() : Time(0,0,0) { }
Time Time::operator+(Time time) {
    return Time{_hour + time._hour, _minute + time._minute, _second + time._second};
}
Time Time::operator+(int seconds) const {
    return Time{_hour, _minute, _second + seconds};
}
Time operator+(int seconds, const Time& time) {
    return time + seconds; // chain to operator+(int seconds)
}
Time& Time::operator++() { // ++(*this)
    ++_second;
    rationalize();
    return *this;
}
Time Time::operator++(int) { // (*this)++
    Time old = *this;
    operator++(); // chain to ++(*this)
    return old;
}
void Time::rationalize() {
    _minute -= force_between(_second, 0, 60);
    _hour -= force_between(_minute, 0, 60);
    force_between(_hour, 0, 24);
}
int Time::force_between(int& value, int min, int max) {
    int result = 0;  // TODO: Use modulo math instead
    while(value < min) {
        value += (max - min);
        ++result;
    }
    while(value > max-1) {
        value -= (max - min);
        --result;
    }
    return result;
}
int Time::compare(Time time) {
    if(_hour < time._hour) return -1;
    if(_hour > time._hour) return  1;
    if(_minute < time._minute) return -1;
    if(_minute > time._minute) return  1;
    if(_second < time._second) return -1;
    if(_second > time._second) return  1;
    return 0;
}
std::ostream& operator<<(std::ostream& ost, const Time& time) {
    ost << std::setfill('0') 
        << std::setw(2) << time._hour << ':'
        << std::setw(2) << time._minute << ':'
        << std::setw(2) << time._second;
    return ost;
}
std::istream& operator>>(std::istream& ist, Time& time) {
    char discard; // for the : in 12:30:15
    ist >> time._hour >> discard >> time._minute >> discard >> time._second;
    time.rationalize();
    return ist;
}
