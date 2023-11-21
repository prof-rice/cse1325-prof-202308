#include "product.h"
#include <iomanip>

Product::Product(std::string name, double price)
  : _name{name}, _price{price} { }
std::string Product::name() {return _name;}
double Product::price() {return _price;}
bool Product::operator<(const Product& rhs) {
    return _name < rhs._name;
}
std::ostream& operator<<(std::ostream& ost, const Product& product) {
    ost << std::setprecision(2) << std::fixed;
    ost << product._name << " ($" << product._price << ")";
    return ost;
}

