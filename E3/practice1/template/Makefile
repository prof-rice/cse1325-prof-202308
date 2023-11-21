CXXFLAGS = --std=c++17

all: menu menu_int

menu: menu.cpp
	$(CXX) $(CXXFLAGS) menu.cpp -o menu

menu_int: menu_int.cpp
	$(CXX) $(CXXFLAGS) menu_int.cpp -o menu_int

clean:
	rm -f *.o *.gch *~ a.out menu menu_int
