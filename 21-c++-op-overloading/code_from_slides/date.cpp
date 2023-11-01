#include "date.h"
#include <vector>
#include <exception>
#include <regex>
#include <algorithm>

// ///////////////////////////////////////////////////////////
// DATE

Date::Date(int year, Month month, int day) : _year{year}, _month{month}, _day{day} {
    if (1 > day || day > days_in_month(month, year)) 
        throw std::runtime_error{"Invalid day: " + std::to_string(day)};
}
Date::Date() : Date{1970, Month::Jan, 1} { }

// Stream out a date object
std::ostream& operator<<(std::ostream& os, const Date& date) {
    os << date._year << " " << date._month << " " << date._day;
    return os;
}

// Stream into an existing Date object
// Note the dependence on streaming in a Month (overloaded below)
std::istream& operator>>(std::istream& is, Date& date) {
    is >> date._year;
    is >> date._month;
    is >> date._day; 
    if (1 > date._day || date._day > date.days_in_month(date._month, date._year)) 
        throw std::runtime_error{"Invalid day: " + std::to_string(date._day)};
    return is;
}

// Implement the comparison operators using a PRIVATE method
// compare returns -1 if this < rhs, 0 of this == rhs, and 1 if this > rhs
int Date::compare(const Date& rhs) {
    if(_year <rhs._year ) return -1;
    if(_year >rhs._year ) return  1;
    if(_month<rhs._month) return -1;
    if(_month>rhs._month) return  1;
    if(_day  <rhs._day  ) return -1;
    if(_day  >rhs._day  ) return  1;
    return 0;   
}


// Support function that calcuates number of days in a month (data validation)
int Date::days_in_month(Month month, int year) {
    switch(month) {
        case Month::Apr:
        case Month::Jun:
        case Month::Sep:
        case Month::Nov: return 30;
        case Month::Feb: return ((year%400==0) || (year%4==0 && year%100!=0)) ? 29 : 28;
        default:         return 31;
    }
}

// ///////////////////////////////////////////////////////////
// MONTH
std::ostream& operator<<(std::ostream& ost, const Month& month) {
    if(month == Month::Jan) ost << "January";
    if(month == Month::Feb) ost << "February";
    if(month == Month::Mar) ost << "March";
    if(month == Month::Apr) ost << "April";
    if(month == Month::May) ost << "May";
    if(month == Month::Jun) ost << "June";
    if(month == Month::Jul) ost << "July";
    if(month == Month::Aug) ost << "August";
    if(month == Month::Sep) ost << "September";
    if(month == Month::Oct) ost << "October";
    if(month == Month::Nov) ost << "November";
    if(month == Month::Dec) ost << "December";
    return ost;
}

std::istream& operator>>(std::istream& is, Month& month) {
  class month_record{
    public: 
      std::regex rx;
      Month month; 
  };

  std::vector<month_record> month_records =
  {
      {std::regex{R"(([Jj]an(uary)?(,)?)|(0)?1(,)?)"},   Month::Jan},
      {std::regex{R"(([Ff]eb(uary)?(,)?)|(0)?2(,)?)"},   Month::Feb},
      {std::regex{R"(([Mm]ar()ch?(,)?)|(0)?3(,)?)"},     Month::Mar},
      {std::regex{R"(([Aa]pr(il)?(,)?)|(0)?4(,)?)"},     Month::Apr},
      {std::regex{R"(([Mm]ay(,)?)|(0)?5(,)?)"},          Month::May},
      {std::regex{R"(([Jj]un(e)?(,)?)|(0)?6(,)?)"},      Month::Jun},
      {std::regex{R"(([Jj]ul(y)?(,)?)|(0)?7(,)?)"},      Month::Jul},
      {std::regex{R"(([Aa]ug(ust)?(,)?)|(0)?8(,)?)"},    Month::Aug},
      {std::regex{R"(([Ss]ep(tember)?(,)?)|(0)?9(,)?)"}, Month::Sep},
      {std::regex{R"(([Oo]ct(ober)?(,)?)|10(,)?)"},  Month::Oct},
      {std::regex{R"(([Nn]ov(ember)?(,)?)|11(,)?)"}, Month::Nov},
      {std::regex{R"(([Dd]ec(ember)?(,)?)|12(,)?)"}, Month::Dec},
  };
  std::string s; 
  is >> s;
  for(auto mr : month_records) {
      if (std::regex_match(s, mr.rx)) {month = mr.month; s.clear(); break;}
  }
  if (!s.empty()) 
      throw std::runtime_error{"Invalid month: " + s};
  return is;
}

// Enums are ordered, and so comparison operators are pre-defined


