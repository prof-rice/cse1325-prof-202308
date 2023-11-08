#ifndef __SHAPE__
#define __SHAPE__

#include <iostream>

class Shape {
  public:
    virtual std::string name() = 0;
    virtual double area() = 0;
    virtual std::string draw() = 0;
    std::string to_string();
};
#endif
