#include "Circle.h"
#include <math.h>

Circle::Circle(double radius)
                  : _radius{radius} { }
std::string Circle::name() {
    return " Circle of radius " + std::to_string(_radius);
}
double Circle::area() {
    return M_PI * _radius * _radius;
}
std::string Circle::draw() { }
