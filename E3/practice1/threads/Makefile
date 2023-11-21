CXXFLAGS = --std=c++17 -pthread

all: threads

threads: main.cpp
	$(CXX) $(CXXFLAGS) main.cpp -o threads

clean:
	rm -f *.o *.gch *~ a.out threads
