#ifndef __CIRCLE__
#define __CIRCLE__

#include "Shape.h"

class Circle : public Shape {
  public:
    Circle(double radius);
    std::string name() override;
    double area() override;
    std::string draw() override;
  private:
    double _radius;
};

#endif
