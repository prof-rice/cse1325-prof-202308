#ifndef __RECTANGLE__
#define __RECTANGLE__

#include "Shape.h"

class Rectangle : public Shape {
  public:
    Rectangle(double width, double height);
    std::string name() override;
    double area() override;
    double height();
    double width();
  private:
    double _height;
    double _width;
};

#endif
