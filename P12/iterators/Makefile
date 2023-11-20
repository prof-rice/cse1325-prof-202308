CXXFLAGS = --std=c++17

info:
	@echo
	@printf 'Type e.g., "make solution"  to create program "solution" from solution.cpp\n'
	@printf 'Type       "make all"   to create every program\n'
	@printf 'Type       "make clean" to delete every program\n\n'

solution: solution.cpp
	-$(CXX) $(CXXFLAGS) -o solution solution.cpp
	@printf "Now type ./solution captain.txt Captain! to execute the result\n\n"

all:  solution

clean:
	rm -f *.o *.gch a.out solution

