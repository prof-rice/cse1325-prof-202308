CXXFLAGS += --std=c++17

all: alls

debug: CXXFLAGS += -g
debug: alls

findall: findall.cpp
	-$(CXX) $(CXXFLAGS) findall.cpp -o findall
find: find.cpp
	-$(CXX) $(CXXFLAGS) find.cpp -o find
findif: findif.cpp
	-$(CXX) $(CXXFLAGS) findif.cpp -o findif
generate_and_transform: generate_and_transform.cpp
	-$(CXX) $(CXXFLAGS) generate_and_transform.cpp -o generate_and_transform
generate: generate.cpp
	-$(CXX) $(CXXFLAGS) generate.cpp -o generate
minmax: minmax.cpp
	-$(CXX) $(CXXFLAGS) minmax.cpp -o minmax
shuffle_and_sort: shuffle_and_sort.cpp
	-$(CXX) $(CXXFLAGS) shuffle_and_sort.cpp -o shuffle_and_sort
transform1: transform1.cpp
	-$(CXX) $(CXXFLAGS) transform1.cpp -o transform1
transform: transform.cpp
	-$(CXX) $(CXXFLAGS) transform.cpp -o transform

alls:  findall find findif generate_and_transform generate minmax shuffle_and_sort transform1 transform

clean:
	rm -f *.o *.gch a.out  findall find findif generate_and_transform generate minmax shuffle_and_sort transform1 transform

