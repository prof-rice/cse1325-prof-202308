CXXFLAGS = --std=c++17

analyze.o: analyze.gch
	$(CXX) $(CXXFLAGS) -c analyze.cpp

analyze.gch: analyze.h
	$(CXX) $(CXXFLAGS) -c analyze.h

clean:
	rm -f *.o *.gch a.out

