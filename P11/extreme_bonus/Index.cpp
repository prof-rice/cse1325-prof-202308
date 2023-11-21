#include "Index.h"

#include <utility>
#include <sstream>

void Index::add_word(Word word, std::string filename, int line) {
    _index.insert(std::pair(word, Location{filename, line}));
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
    std::string last_word;
    Location last_location;
    std::string separator;
    for(auto it=index._index.begin(); it!=index._index.end(); ++it) {
        if(last_word == it->first && last_location == it->second) continue;
        if(it->first != last_word) {
            Location::next_word();
            ost << '\n' << it->first << ": ";
            separator = "";
        }
        ost << separator << it->second;
        separator = ", ";
        last_word = it->first;
        last_location = it->second;
    }
    ost << '\n';
    return ost;
}
