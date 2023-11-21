#include "vehicle.h"

Vehicle::Vehicle(std::string name) : _name{name} { }
std::string Vehicle::name() {return _name;}
