#include <iostream>
#include <vector>
#include <algorithm>

bool is_shorter(std::string lhs, std::string rhs) {
    if(lhs.size() == rhs.size()) return lhs < rhs;
    else return lhs.size() < rhs.size();
}
int main(int argc, char* argv[]) {
    std::vector<std::string> caps;
    std::vector<std::string>* no_caps = new std::vector<std::string>{};
    
    for(int i=1; i<argc; ++i) {
        std::string s = std::string{argv[i]};
        if(isupper(s[0])) caps.push_back(s);
        else no_caps->push_back(s);   
    }
    
    std::sort(caps.begin(), caps.end());
    std::cout << "Capitalized:\n";
    for(auto s : caps) std::cout << s << '\n';
    std::cout << "\n\n";
    
    std::sort(no_caps->begin(), no_caps->end(), is_shorter);
    std::cout << "Lower Case:\n";
    for(auto s : *no_caps) std::cout << s << '\n';
    std::cout << "\n\n";
}
    
