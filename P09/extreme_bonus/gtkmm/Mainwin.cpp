#include "Mainwin.h"
#include "Canvas.h"

Mainwin::Mainwin(Rectangle r) : canvas{new Canvas{r}} {
    set_default_size(300, 300);
    add(*canvas);
    canvas->show_all();
}
Mainwin::~Mainwin() { }

