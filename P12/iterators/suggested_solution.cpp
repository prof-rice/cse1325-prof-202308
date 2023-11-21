#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

int main(int argc, char* argv[]) {
    if(argc != 3) {
        std::cerr << "usage: " << argv[0] << " filename.txt oldword" << std::endl;
        return -1;
    }

    // Open the filename that is the first command line argument for reading as text
    // If the open failed, print an error message and exit with error code -2
    std::ifstream ifs{std::string{argv[1]}};
    if(!ifs) {
        std::cerr << argv[1] << " could not be opened" << std::endl;
        return -2;
    }
    
    std::string word;
    std::vector<std::string> v;
    
    // Fill vector v with each word in the text file
    while(ifs >> word) v.push_back(word);
    ifs.close();

    // set word to the second command line argument
    word = std::string{argv[2]};
    
    // Obtain an iterator to the first element of vector v
    auto it = v.begin();
    
    // Iterate over the vector, deleting any elements that match word
    while(it != v.end()) {
        if(*it == word) {
            it = v.erase(it);
        }
        else ++it;
    }
    
    // Reset the iterator to point to the first element of the modified vector v,
    //   then use the iterator to print all words in the vector
    it = v.begin();
    while(it != v.end()) std::cout << *(it++) << ' ';
    std::cout << std::endl;
}
