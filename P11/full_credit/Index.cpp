#include "Index.h"

void Index::add_word(Word word, std::string filename, int line) {
    if(_index.count(word) == 0) _index[word] = Locations{};
    _index[word].insert(Location{filename, line});
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
    for (auto& [ word, locations ] : index._index) {
        ost << word << ": ";
        std::string separator;
        auto it = locations.begin();
        while(it != locations.end()) {
            ost << separator << *it++;
            separator = ", ";
        }
        ost << '\n';
    }
    return ost;
}
