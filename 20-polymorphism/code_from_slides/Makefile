CXXFLAGS = --std=c++17

info:
	@echo
	@printf 'Type e.g., "make auto"  to create program "auto" from auto.cpp\n'
	@printf 'Type       "make all"   to create every program\n'
	@printf 'Type       "make clean" to delete every program\n\n'

downcast_bad: downcast_bad.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_bad downcast_bad.cpp
	@printf "Now type ./downcast_bad to execute the result\n\n"

downcast_bad_pointer: downcast_bad_pointer.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_bad_pointer downcast_bad_pointer.cpp
	@printf "Now type ./downcast_bad_pointer to execute the result\n\n"

downcast_bad_static_cast: downcast_bad_static_cast.cpp
	@printf "\nWARNING: This compile should FAIL\n\n"
	-$(CXX) $(CXXFLAGS) -o downcast_bad_static_cast downcast_bad_static_cast.cpp

downcast_bad_void_star: downcast_bad_void_star.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_bad_void_star downcast_bad_void_star.cpp
	@printf "Now type ./downcast_bad_void_star to execute the result\n\n"

downcast_c++_style: downcast_c++_style.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_c++_style downcast_c++_style.cpp
	@printf "Now type ./downcast_c++_style to execute the result\n\n"

downcast_c_style: downcast_c_style.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_c_style downcast_c_style.cpp
	@printf "Now type ./downcast_c_style to execute the result\n\n"

downcast_direct: downcast_direct.cpp
	@printf "\nWARNING: This compile should FAIL\n\n"
	-$(CXX) $(CXXFLAGS) -o downcast_direct downcast_direct.cpp

downcast_dynamic: downcast_dynamic.cpp
	-$(CXX) $(CXXFLAGS) -o downcast_dynamic downcast_dynamic.cpp
	@printf "Now type ./downcast_dynamic to execute the result\n\n"

upcast_bad: upcast_bad.cpp
	-$(CXX) $(CXXFLAGS) -o upcast_bad upcast_bad.cpp
	@printf "Now type ./upcast_bad to execute the result\n\n"

upcast: upcast.cpp
	-$(CXX) $(CXXFLAGS) -o upcast upcast.cpp
	@printf "Now type ./upcast to execute the result\n\n"

all:  downcast_bad downcast_bad_pointer downcast_bad_static_cast downcast_bad_void_star downcast_c++_style downcast_c_style downcast_direct downcast_dynamic upcast_bad upcast

clean:
	rm -f *.o *.gch a.out  downcast_bad downcast_bad_pointer downcast_bad_static_cast downcast_bad_void_star downcast_c++_style downcast_c_style downcast_direct downcast_dynamic upcast_bad upcast

