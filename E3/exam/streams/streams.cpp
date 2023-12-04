#include <iostream>
#include <fstream>
#include <map>

int main(int argc, char* argv[]) {
    std::map<std::string, int> words;
    for(int i=1; i<argc; ++i) {
        std::string filename{argv[i]};
        std::ifstream ifs{filename};
        if(!ifs) std::cerr << "Bad filename: " << filename << std::endl;
        int count = 0;
        std::string word;
        while(ifs >> word) ++count;
        words[filename] = count;
    }
    for(auto& [filename, count] : words)
        std::cout << filename << " has " << count << " words." << std::endl;
}
