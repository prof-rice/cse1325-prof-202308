#include <algorithm>
#include <iostream>
#include <iomanip>
#include <vector>
#include <random>       // std::default_random_engine
#include <chrono>       // std::chrono::system_clock

int main() {
    // Fill an array using the generate function
    std::vector<int> v(50);
    int i=0; std::generate(v.begin(), v.end(), [&i] {return i++;});
    for(auto i : v) std::cout << std::setw(3) << i;
    std::cout << std::endl;

    // Shuffle the ints randomly (this is a more robust random function)
    int seed = std::chrono::system_clock::now().time_since_epoch().count();
    std::shuffle(v.begin(), v.end(), std::default_random_engine(seed));
    for(auto i : v) std::cout << std::setw(3) << i;
    std::cout << std::endl;

    // Sort them back into order
    std::sort(v.begin(), v.end());
    for(auto i : v) std::cout << std::setw(3) << i;
    std::cout << std::endl;

    // Sort first 25 elements in reverse order using lambda compare function
    std::sort(v.begin(), v.begin()+25, [ ] (int lhs, int rhs) {return rhs < lhs;});
    for(auto i : v) std::cout << std::setw(3) << i;
    std::cout << std::endl;
}
