#include "Index.h"
#include <fstream>
#include <sstream>

int main(int argc, char* argv[]) {
    Index index;
    std::string word;
    std::string text;
    for(int i=1; i<argc; ++i) {
        std::string filename{argv[i]};
        std::ifstream ifs{filename};
        int line=0;
        while(ifs) {
            std::getline(ifs, text); ++line; 
            if(text.empty()) continue;
            std::istringstream iss{text};
            while(iss) {
                iss >> word;
                try {
                    while(!word.empty()) { // remove punctuation from front of word
                        if(!isalpha(word.front())) word.erase(0,1); 
                        else break;
                    }
                    while(!word.empty()) { // remove punctuation from back of word
                        if(!isalpha(word.back())) word.pop_back(); 
                        else break;
                    }
                    if(!word.empty()) {
                        for(char& c : word) c = tolower(c);   // set lowercase
                        index.add_word(word, filename, line); // add to index!
                    }
                } catch(...) {  // ignore "bad" words
                }
            }
        }
    }
    std::cout << "Index\n=====\n\n" << index << std::endl;
}
