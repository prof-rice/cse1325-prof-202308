#include <algorithm>
#include <iostream>

int main() {
    std::vector<int> v(50);   // Source vector
    std::generate(v.begin(), v.end(), [] {return rand()%10;});
    for(auto i : v) std::cout << i << ' ';
    std::cout << std::endl;
}

