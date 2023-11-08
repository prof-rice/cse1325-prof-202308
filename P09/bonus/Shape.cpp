#include "Shape.h"

std::string Shape::to_string() {
    return name() + " with area " + std::to_string(area());
}
