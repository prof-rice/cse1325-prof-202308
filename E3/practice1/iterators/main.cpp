#include "cart.h"
#include <iostream>
#include <algorithm>

int main() {
    Cart cart{"Prof Rice"};
    cart.add_product(Product{"Texas flag", 11.87});
    cart.add_product(Product{"Dr. Pepper", 1.49});
    cart.add_product(Product{"Rattlesnake fajitas", 14.95});
    
    // Part b
    std::sort(cart.begin(), cart.end());
    
    for(Product p : cart) std::cout << p << std::endl;

    // Part c
    std::cout << *std::find_if(cart.begin(), cart.end(), 
        [] (Product p) {return p.price() > 12;}) << "\n\n";
    }
