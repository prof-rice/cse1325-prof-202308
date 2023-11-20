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




// 4b. Declare an iterator for “work” named “next_work”
//     (Remember, class "iterator" is nested within the container)
//     It will be initialized in main, so don’t initialize here



// 4c. Declare a mutual exclusion object named “m”, which will
//     protect “next_work” during updates by the threads


//
// The code each thread will execute
//

void worker() {
  bool done = false;
  while (true) {    // Loop until all work is assigned a thread

// 4d. Lock mutual exclusion object “m” to block other threads
//     from this region while this thread executes it


// 4e. Use next_work’s copy constructor to create an iterator
//       named “i” for the work vector 



    // Update next_work to point to the next item, if any
    if (next_work != work.end()) next_work++; else done = true;

// 4f. Unlock mutual exclusion object “m”


// If no more work left, exit the thread
    if (done) break; 
    i->set_result(sin(i->theta()*M_PI/180));                  // The calculation
    this_thread::sleep_for(chrono::milliseconds(rand()%3+1)); // Pause
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




  // 4h. Create and run 10 threads, each running “worker”,
  //     and storing them in an array or vector named “threads”




  // 4i. Join the 10 threads as they complete
  


  // Print out the results from the thread pool
  for (Work& w : work)
    cout << "sin(" << w.theta() << ") = " 
                   << w.result() << endl;
}

