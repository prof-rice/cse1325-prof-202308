#ifndef __RECTANGLE__
#define __RECTANGLE__

#include "Shape.h"

class Rectangle : public Shape {
  public:
    Rectangle(double height, double width);
    std::string name() override;
    double area() override;
  private:
    double _height;
    double _width;
};

#endif
