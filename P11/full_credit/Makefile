CXXFLAGS = --std=c++17

mkindex: mkindex.o Index.o Location.o *.h
	$(CXX) $(CXXFLAGS) mkindex.o Index.o Location.o -o mkindex
	
Index.o: Index.cpp *.h
	$(CXX) $(CXXFLAGS) -c Index.cpp

Location.o: Location.cpp
	$(CXX) $(CXXFLAGS) -c Location.cpp

mkindex.o: mkindex.cpp
	$(CXX) $(CXXFLAGS) -c mkindex.cpp

clean:
	rm -f *.o *.gch a.out mkindex

