#include "Rectangle.h"

Rectangle::Rectangle(double width, double height)
                  : _width{width}, _height{height} { }
std::string Rectangle::name() {
    return std::to_string(_height) + "x"
         + std::to_string(_width) + " Rectangle";
}
double Rectangle::area() {
    return _height * _width;
}

double Rectangle::height() {
    return _height;
}
double Rectangle::width() {
    return _width;
}
