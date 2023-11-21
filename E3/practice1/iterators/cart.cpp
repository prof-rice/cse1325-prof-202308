#include "cart.h"

Cart::Cart(std::string customer) : _customer{customer} { }
void Cart::add_product(Product product) {_products.push_back(product);}
std::string Cart::customer() {return _customer;}

