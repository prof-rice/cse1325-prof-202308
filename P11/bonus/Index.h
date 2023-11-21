#ifndef __INDEX_H
#define __INDEX_H

#include "Location.h"

#include <map>
#include <set>
#include <iostream>

typedef std::string Word;
typedef std::set<Location> Locations;

class Index {
  public:
    void add_word(Word word, std::string filename, int line);
    friend std::ostream& operator<<(std::ostream& ost, const Index& index);
  private:
    std::map<Word, Locations> _index;
};

#endif
