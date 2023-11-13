CXXFLAGS += -std=c++17
GTKFLAGS = `/usr/bin/pkg-config gtkmm-3.0 --cflags --libs`

all: map_easy map_practical map_practical_plus

debug: CXXFLAGS += -g
debug: rebuild

rebuild: clean all

map_easy: map_easy.cpp
	$(CXX) $(CXXFLAGS) map_easy.cpp -o map_easy
map_practical: map_practical.cpp
	$(CXX) $(CXXFLAGS) map_practical.cpp -o map_practical
map_practical_plus: map_practical_plus.cpp
	$(CXX) $(CXXFLAGS) map_practical_plus.cpp -o map_practical_plus
map_overloads: map_overloads.cpp
	$(CXX) $(CXXFLAGS) map_overloads.cpp -o map_overloads
clean:
	-rm -f *.o *.gch *~ a.out map_easy map_practical map_practical_plus map_overloads 

