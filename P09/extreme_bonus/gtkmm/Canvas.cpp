#include "Canvas.h"
#include <cairomm/context.h>

Canvas::Canvas(Rectangle r) : Gtk::DrawingArea(), _r{r} {
    override_background_color(Gdk::RGBA("white"));
}

Canvas::~Canvas() { }

bool Canvas::on_draw(const Cairo::RefPtr<Cairo::Context>& cr) {
  // red 10-pixel wide lines
  cr->set_line_width(2.0);
  cr->set_source_rgb(0.8, 0.0, 0.0); // red

  // get window size
  Gtk::Allocation allocation = get_allocation();
  const double canvas_width = allocation.get_width();
  const double canvas_height = allocation.get_height();

  double xscale = canvas_width  / _r.width();
  double yscale = canvas_height / _r.height();
  double scale = (xscale < yscale) ? xscale : yscale;
  
  double width = _r.width() * scale;
  double height = _r.height() * scale;
  
  // coordinates for the corners of the window
  int x_left = static_cast<int> (width / 5);
  int x_right = x_left * 4;
  int y_top = static_cast<int> (height / 5);
  int y_bottom = y_top * 4;
  
  cr->move_to(x_left,  y_top);
  cr->line_to(x_right, y_top);
  cr->line_to(x_right, y_bottom);
  cr->line_to(x_left,  y_bottom);
  cr->line_to(x_left,  y_top);

  // draw the line on the canvas
  cr->stroke();
  
  // notify system the event has been handled
  return true;
}
