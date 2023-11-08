#ifndef __RECTANGLE__
#define __RECTANGLE__

#include "Shape.h"

class Rectangle : public Shape {
  public:
    Rectangle(double width, double height);
    std::string name() override;
    double area() override;
    std::string draw() override;
  private:
    double _height;
    double _width;
};

#endif
