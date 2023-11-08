CXXFLAGS += --std=c++17

all: temps

# Build with the debug option
debug: CXXFLAGS += -g
debug: all

rebuild: clean temps

temps: main.o readings.o reading.o
	$(CXX) $(CXXFLAGS) -o temps main.o readings.o reading.o

main.o: main.cpp *.h
	$(CXX) $(CXXFLAGS) -c main.cpp
readings.o: readings.cpp *.h
	$(CXX) $(CXXFLAGS) -c readings.cpp
reading.o: reading.cpp *.h
	$(CXX) $(CXXFLAGS) -c reading.cpp

clean:
	-rm -f *.o *.gch *~ a.out temps

