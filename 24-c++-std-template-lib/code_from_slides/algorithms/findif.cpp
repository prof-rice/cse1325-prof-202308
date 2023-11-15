#include <algorithm>
#include <iterator>
#include <iostream>

int main() {
    std::vector<int> v;
    for(int i=0; i<100; ++i) {
        v.push_back(std::rand()%10);
        std::cout << v.back() << ' ';
    }
    std::cout << std::endl;

    int target;
    std::cout << "find multiples of which int? ";
    while(std::cin >> target) {
        auto it_next = v.begin();
        while(it_next != v.end()) {
            auto it = std::find_if(it_next, v.end(), [target] (int i) {return ((i%target)==0);});
            if(it == v.end()) break;
            else std::cout << "Found multiple of " << target << " at v["
                       << std::distance(v.begin(), it) << "] = " << *it << "\n";
            it_next = it+1;
        }
        std::cout << "Next search? ";
    }
    std::cout << std::endl;
}
