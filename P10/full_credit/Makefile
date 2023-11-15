CXXFLAGS += --std=c++17

testtime: TestTime.o Time.o *.h
	$(CXX) $(CXXFLAGS) TestTime.o Time.o  -o testtime

TestTime.o: TestTime.cpp *.h
	$(CXX) $(CXXFLAGS) -c TestTime.cpp -o TestTime.o

Time.o: Time.cpp *.h
	$(CXX) $(CXXFLAGS) -c Time.cpp  -o Time.o

clean:
	rm -f *.o *.gch *~ a.out testtime
