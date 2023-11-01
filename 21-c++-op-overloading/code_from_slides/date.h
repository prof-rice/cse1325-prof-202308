#include <iostream>

enum class Month {Jan, Feb, Mar, Apr, May, Jun,
                  Jul, Aug, Sep, Oct, Nov, Dec};
                  
// Stream I/O operators
std::ostream& operator<<(std::ostream& ost, const Month& month);
std::istream& operator>>(std::istream& is, Month& month); 

// comparison operators are predefined for enum classes

class Date {
  public:
    Date(int year, Month month, int day);
    Date();
    
    // Stream I/O operators
    friend std::ostream& operator<<(std::ostream& os, const Date& date);
    friend std::istream& operator>>(std::istream& is, Date& date);
    
    // Comparison operators
    inline bool operator==(const Date& rhs) {return (compare(rhs) == 0);}
    inline bool operator!=(const Date& rhs) {return (compare(rhs) != 0);}
    inline bool operator< (const Date& rhs) {return (compare(rhs) <  0);}
    inline bool operator<=(const Date& rhs) {return (compare(rhs) <= 0);}
    inline bool operator> (const Date& rhs) {return (compare(rhs) >  0);}
    inline bool operator>=(const Date& rhs) {return (compare(rhs) >= 0);} 
    
  private:
    int compare(const Date& rhs);             // Perform all comparisons
    int days_in_month(Month month, int year); // For data validation on input
    
    int _year;
    Month _month;
    int _day;
};

