#include "van.h"
#include "pickup.h"
#include <vector>

int main() {
    std::vector<Vehicle*> vehicles = {
//        new Van{"Minivan", 140.5},
        new Pickup{"Short Bed", 5.7},
        new Pickup{"Long Bed", 14.5},
        new Van{"Cargo", 164.5},
    };
    
    for(Vehicle* v : vehicles) 
        std::cout << v->name() << " (" 
                  << v->cargo_space() << " ft³)\n";
}
