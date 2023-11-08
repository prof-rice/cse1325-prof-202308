#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"

#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, char* argv[]) {
    std::vector<Shape*> shapes;
    Rectangle* rect1 = new Rectangle{150.0, 60.0};
    shapes.push_back(rect1);
    Rectangle* rect2 = new Rectangle{30.0, 90.0};
    shapes.push_back(rect2);
    Circle* circle = new Circle(2.0);
    shapes.push_back(circle);
    for(Shape* s : shapes) 
        std::cout << s->to_string() << std::endl;
    
    // const std::string viewer = "/usr/bin/eog"; // Eye of Gnome image viewer
    // const std::string viewer = "/usr/bin/xviewer"; // X Windows image viewer
    const std::string viewer = "/usr/bin/xdg-open"; // double-click equivalent
    const std::string filename = "rectangle.ppm";
    std::ofstream ofs {filename};
    if(!ofs) {
        std::cout << "\n\n" << rect1->draw() << std::endl;
    } else {
        ofs << rect1->draw() << std::endl;
        ofs.close();
        std::string cmd = viewer + ' ' + filename;
        system(cmd.c_str());
    }

    std::string line;
    std::getline(std::cin, line);

    const std::string filename2 = "rectangle.ppm";
    std::ofstream ofs2 {filename};
    if(!ofs2) {
        std::cout << "\n\n" << rect2->draw() << std::endl;
    } else {
        ofs2 << rect2->draw() << std::endl;
        ofs2.close();
        // std::string cmd = viewer + ' ' + filename;
        // system(cmd.c_str());
    }

}
