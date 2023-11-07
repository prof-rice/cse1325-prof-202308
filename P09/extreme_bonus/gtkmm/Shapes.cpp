#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"

#include <iostream>
#include <vector>

#include <gtkmm.h>
#include "Mainwin.h"

int main(int argc, char* argv[]) {
    std::vector<Shape*> shapes;
    Rectangle* rect = new Rectangle{5.0, 3.0};
    shapes.push_back(rect);
    Circle* circle = new Circle(2.0);
    shapes.push_back(circle);
    for(Shape* s : shapes) 
        std::cout << s->to_string() << std::endl;
    
    auto app = Gtk::Application::create(argc, argv, "Shapes.app");
    Mainwin win{*rect};
    app->run(win);
}
