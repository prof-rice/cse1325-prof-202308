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
    
    const std::string foreground = "  0 255   0 ";
    const std::string background = "  0   0   0 ";

    // ppm header
    std::string result = "P3\n" 
        + std::to_string(width) + ' ' 
        + std::to_string(height) 
        + " 255\n"; // max color levels
    
    // ppm data
    for(int i=0; i<width; ++i) result += foreground;
    result += '\n';

    // Draw sides: asterisk, width-2 spaces, asterisk
    for(int i=0; i<height-2; ++i) {
        result += foreground; 
        for(int i=0; i<width-2; ++i) result += background;
        result += foreground + '\n';
    }

    // Draw row of 'w' asterisks for bottom side
    for(int i=0; i<width; ++i) result += foreground;
    result += '\n';
    
    return result;
}

