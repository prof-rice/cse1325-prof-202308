#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"

#include <iostream>
#include <vector>

int main(int argc, char* argv[]) {
    std::vector<Shape*> shapes;
    Rectangle* rect = new Rectangle{15.0, 6.0};
    shapes.push_back(rect);
    Circle* circle = new Circle(2.0);
    shapes.push_back(circle);
    for(Shape* s : shapes) 
        std::cout << s->to_string() << std::endl;
    
    std::cout << "\n\n" << rect->draw() << std::endl;
}
