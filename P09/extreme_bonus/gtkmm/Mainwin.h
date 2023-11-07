#ifndef __MAINWIN_H
#define __MAINWIN_H

#include "Rectangle.h"

#include <gtkmm.h>

class Mainwin : public Gtk::Window {
  public:
    Mainwin(Rectangle r);
    virtual ~Mainwin();
  private:
    Gtk::DrawingArea* canvas;
};

#endif
