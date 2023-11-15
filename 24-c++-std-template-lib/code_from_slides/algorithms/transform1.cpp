#include <algorithm>
#include <iterator>
#include <iostream>
#include <iomanip>

int main() {
    std::vector<int> v;   // Source vector
    std::vector<int> vx;  // Destination vector

    // Pointer to actual transform target - source (in place) or destination
    std::vector<int>* vp = &v; 

    // Initialize Source
    for(int i=0; i<50; ++i) {
        v.push_back(std::rand()%10);
        std::cout << std::setw(3) << v.back();
    }
    std::cout << std::endl;

    // Select transform target
    char c;
    std::cout << "Transform (i)n-place or (d)estination? ";
    std::cin >> c; std::cin.ignore(32767, '\n');
    if(c == 'd') {
        vp = &vx;
        vx.resize(v.size()); // Make room in destination
    }

    int addend;
    std::cout << "What addend shall we add to each element? ";

    while(std::cin >> addend) {
        std::transform(v.begin(), v.end(), vp->begin(), [addend] (int& i){return i + addend;});
        for(auto& e : *vp) std::cout << std::setw(3) << e;
        std::cout << "\nNext addend? ";
    }
    std::cout << std::endl;
}
