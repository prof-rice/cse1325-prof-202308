CXXFLAGS += --std=c++17

caps: caps.cpp *.h
	-$(CXX) $(CXXFLAGS)  caps.cpp -o caps
	@printf "Now type ./caps and some words to execute the result\n\n"

clean:
	rm -f *.o *.gch *~ a.out caps

