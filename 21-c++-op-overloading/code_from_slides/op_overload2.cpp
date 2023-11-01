#include "date.h"
#include <iostream>

int main() {
    Date birthday{1950, Month::Dec, 30};
    std::cout << birthday << std::endl;
}
