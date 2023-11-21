#ifndef __PRODUCT_
#define __PRODUCT_

#include <iostream>

class Product {
  public:
    Product(std::string name, double price);
    std::string name();
    double price();
    bool operator<(const Product& rhs);
    friend std::ostream& operator<<(std::ostream& ost, const Product& product);
  private:
    std::string _name;
    double _price;
};

#endif
