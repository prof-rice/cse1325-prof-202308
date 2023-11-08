#include "readings.h"
#include <iostream>
#include <fstream>

int main(int argc, char* argv[]) {
    if(argc != 2) {
        std::cerr << "usage: " << argv[0] << " tempfile.txt" << std::endl;
        return -1;
    }
    std::string file{argv[1]};
    std::ifstream ifs{file};
    if (!ifs) {
        std::cerr << "Invalid filename: " << file << std::endl;
        return -2;
    }

    Readings readings{ifs};
    std::cout << readings.graph() << std::endl;
    
    Reading min = readings[0];
    Reading max = readings[0];
    for(int i=1; i<readings.size(); ++i) {
        if(min.temp() < readings[i].temp()) min = readings[i];
        if(max.temp() > readings[i].temp()) max = readings[i];
   }
   std::cout << "Max temperature: " << max << std::endl;
   std::cout << "Min temperature: " << min << std::endl;    
}

