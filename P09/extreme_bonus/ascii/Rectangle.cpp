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
std::string Rectangle::draw() {
    int width  = static_cast<int>(_width);
    int height = static_cast<int>(_height);

    std::string result = std::string(width, '*') + "\n";

    // Draw sides: asterisk, width-2 spaces, asterisk
    for(int i=0; i<height-2; ++i)
        result += '*' + std::string(width-2, ' ') + "*\n";

    // Draw row of 'w' asterisks for bottom side
    result += std::string(width, '*') + "\n";
    return result;
}

