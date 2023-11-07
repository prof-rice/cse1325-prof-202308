#ifndef __CANVAS_H
#define __CANVAS_H

#include "Rectangle.h"

#include <gtkmm/drawingarea.h>

class Canvas : public Gtk::DrawingArea
{
public:
  Canvas(Rectangle r);
  virtual ~Canvas();
  
protected:
  bool on_draw(const Cairo::RefPtr<Cairo::Context>& cr) override;
  
private:
  Rectangle _r;
};

#endif
