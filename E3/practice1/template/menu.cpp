
#include <vector>
#include <iostream>

template<class T>
void menu(std::ostream& ost, std::vector<T> container) {
    for(int line=0; line<container.size(); ++line) 
        ost << line << ") " << container[line] << '\n';
    ost << std::endl;
}

int main() {
  std::vector<int> vi = {5, 4, 3, 2, 1};
  menu<int>(std::cout, vi);
  
  std::vector<double> vd = {3.14159265, 2.71828, 2.302585};
  menu<double>(std::cout, vd);
  
  std::vector<std::string> vs = {"Hamburger", "Fries", "Milkshake"};
  menu<std::string>(std::cout, vs);  
}
