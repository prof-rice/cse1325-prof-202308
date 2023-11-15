CXXFLAGS += --std=c++17

all: pqueue pqueue2 queue set stack

pqueue: pqueue.cpp
	$(CXX) $(CXXFLAGS) pqueue.cpp -o pqueue
pqueue2: pqueue2.cpp
	$(CXX) $(CXXFLAGS) pqueue2.cpp -o pqueue2
queue: queue.cpp
	$(CXX) $(CXXFLAGS) queue.cpp -o queue
set: set.cpp
	$(CXX) $(CXXFLAGS) set.cpp -o set
stack: stack.cpp
	$(CXX) $(CXXFLAGS) stack.cpp -o stack

clean:
	rm -f a.out pqueue pqueue2 queue set stack

