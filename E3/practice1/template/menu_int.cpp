
#include <vector>
#include <iostream>

void menu(std::ostream& ost, std::vector<int> container) {
    for(int line=0; line<container.size(); ++line) 
        ost << line << ") " << container[line] << '\n';
}

int main() {
  std::vector<int> v = {5, 4, 3, 2, 1};
  menu(std::cout, v);
}
