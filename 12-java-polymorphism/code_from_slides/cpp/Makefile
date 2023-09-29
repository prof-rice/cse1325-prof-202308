CXXFLAGS = --std=c++17

info:
	@echo
	@printf 'Type e.g., "make auto"  to create program "auto" from auto.cpp\n'
	@printf 'Type       "make all"   to create every program\n'
	@printf 'Type       "make clean" to delete every program\n\n'

box: box.cpp
	-$(CXX) $(CXXFLAGS) -o box box.cpp
	@printf "Now type ./box to execute the result\n\n"

box_no_override: box_no_override.cpp
	@printf "\n\nWARNING: This compile results in an error - no override!\n\n"
	-$(CXX) $(CXXFLAGS) -o box_no_override box_no_override.cpp
	@printf "\n\n"

box_nopoly: box_nopoly.cpp
	-$(CXX) $(CXXFLAGS) -o box_nopoly box_nopoly.cpp
	@printf "Now type ./box_nopoly to execute the result\n\n"

box_override: box_override.cpp
	-$(CXX) $(CXXFLAGS) -o box_override box_override.cpp
	@printf "Now type ./box_override to execute the result\n\n"

box_parameters: box_parameters.cpp
	-$(CXX) $(CXXFLAGS) -o box_parameters box_parameters.cpp
	@printf "Now type ./box_parameters to execute the result\n\n"

mem_layout: mem_layout.cpp
	-$(CXX) $(CXXFLAGS) -o mem_layout mem_layout.cpp
	@printf "Now type ./mem_layout to execute the result\n\n"

all:  box box_no_override box_nopoly box_override box_parameters mem_layout

clean:
	rm -f *.o *.gch a.out  box box_no_override box_nopoly box_override box_parameters mem_layout

