#include <thread>
#include <mutex>
#include <vector>
#include <iostream>

const int NUM_THREADS = 30;
const int NUM_INTS = 50;
const int INT_END = 10;

int main() {
    std::vector<int> ints;
    std::vector<std::thread*> threads;
    std::mutex m;
    
    for(int t=0; t < NUM_THREADS; ++t) {
        threads.push_back(new std::thread{[&] {
            for(int i=0; i<NUM_INTS; ++i) {
                m.lock(); 
                ints.push_back(rand()%INT_END);
                m.unlock();
            }
        }});
    }
    for(auto t : threads) t->join();
    std::cout << ints.size() << std::endl;
}
        
