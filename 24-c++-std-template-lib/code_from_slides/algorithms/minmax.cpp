#include <algorithm>
#include <iostream>
#include <iomanip>

int main() {
    // Generate random numbers
    std::vector<int> v(30);
    std::generate(v.begin(), v.end(), [] {return rand()%1000;});
    for(auto i : v) std::cout << std::setw(4) << i;
    std::cout << std::endl;

    // Find minimum and maximum elements
    // NOTE: In C++ 20, just use std::min(v); // AT LAST!
    std::cout << "Min of v[0] and v[1] is " << std::min(v[0], v[1]) 
                          << " and max is " << std::max(v[0], v[1]) << std::endl;

    // Get iterators to both min and max within a vector in a single call!
    //   min_element and max_element are also available
    std::pair<std::vector<int>::iterator, std::vector<int>::iterator> it_pair = 
        std::minmax_element(v.begin(), v.end());

    std::cout << "Min of vector is v[" << std::distance(v.begin(), it_pair.first)  << "] = " << *(it_pair.first)
                   << " and Max is v[" << std::distance(v.begin(), it_pair.second) << "] = " << *(it_pair.second)
                   << std::endl;
}

