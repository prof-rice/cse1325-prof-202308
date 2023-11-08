#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"

#include <iostream>
#include <vector>

int main() {
    std::vector<Shape*> shapes;
    Rectangle* rect = new Rectangle{3.0, 4.0};
    shapes.push_back(rect);
    Circle* circle = new Circle(2.0);
    shapes.push_back(circle);
    for(Shape* s : shapes) 
        std::cout << s->to_string() << std::endl;
}
