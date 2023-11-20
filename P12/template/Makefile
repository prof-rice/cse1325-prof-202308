CXXFLAGS = --std=c++17

info:
	@echo
	@printf 'Type e.g., "make template"  to create program "template" from template.cpp\n'
	@printf 'Type       "make all"   to create every program (YOU must write solution.cpp!)\n'
	@printf 'Type       "make clean" to delete every program\n\n'

template: template.cpp
	-$(CXX) $(CXXFLAGS) -o template template.cpp
	@printf "Now type ./template to execute the result\n\n"

solution: solution.cpp
	-$(CXX) $(CXXFLAGS) -o solution solution.cpp
	@printf "Now type ./solution to execute the result\n\n"

all:  solution template 

clean:
	rm -f *.o *.gch a.out  solution template

