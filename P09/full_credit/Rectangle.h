#ifndef __RECTANGLE_H
#define __RECTANGLE_H

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
