#include <algorithm>
#include <iterator>
#include <iostream>
#include <iomanip>

int main() {
    std::vector<int> v(50);   // Source vector
    std::generate(v.begin(), v.end(), [] {return rand()%10;});
    for(auto i : v) std::cout << i << ' ';
    std::cout << std::endl;

    int addend;
    std::cout << "What addend shall we add to each element? ";

    while(std::cin >> addend) {
        std::transform(v.begin(), v.end(), v.begin(), [addend] (int& i){return i + addend;});
        for(auto i : v) std::cout << i << ' ';
        std::cout << "\nNext addend? ";
    }
    std::cout << std::endl;
}
