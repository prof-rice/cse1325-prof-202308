#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <chrono>
#include <cmath>
using namespace std;

class Work { // Work to do – calc using theta and store result
  public:
    Work(double theta) : _theta{theta} { }
    double theta() {return _theta;}
    void set_result(double result) {_result = result;}
    double result() {return _result;}
  private:
    double _theta;
    double _result;
};

// 4a. Declare a global vector of Work objects named “work”

std::vector<Work> work;


// 4b. Declare an iterator for “work” named “next_work”
//     It will be initialized in main, so don’t initialize here

std::vector<Work>::iterator next_work;

// 4c. Declare a mutual exclusion object named “m”, which will
//     protect “next_work” during updates by the threads


std::mutex m;

//
// The code each thread will execute
//

void worker() {
  bool done = false;
  while (true) {    // Loop until all work is assigned a thread

// 4d. Lock mutual exclusion object “m” to block other threads
//     from this region while this thread executes it

    m.lock();

// 4e. Use next_work’s copy constructor to create an iterator
//       named “i” for the work vector 

    auto i = std::vector<Work>::iterator{next_work};

    // Update next_work to point to the next item, if any
    if (next_work != work.end()) next_work++; else done = true;

// 4f. Unlock mutual exclusion object “m”

    m.unlock();

    if (done) break; // If no more work left, exit the thread
    i->set_result(sin(i->theta()*M_PI/180)); // The calculation
    this_thread::sleep_for(chrono::milliseconds(rand()%3+1));
  }
}


//
// Main
//

int main() {
  // Create the work to be done by the threads
  for (double theta = 0.0; theta < 360.0; theta += 0.1)
    work.push_back(Work{theta});

  // 4g. Initialize the “next_work” iterator to point to
  //     the first element of vector “work”

  next_work = work.begin();


  // 4h. Create and run 10 threads, each running “worker”,
  //     and storing them in an array or vector named “threads”
  std::thread threads[10];
  for(int i=0; i<9; ++i) {
      threads[i] = std::thread{worker};
  }

  // 4i. Join the 10 threads as they complete
  
  for(int i=0; i<9; ++i) {
      threads[i].join();
  }


  // Print out the results from the thread pool
  for (Work& w : work)
    cout << "sin(" << w.theta() << ") = " 
                   << w.result() << endl;
}

