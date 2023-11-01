CXXFLAGS = --std=c++17

all: test_date birthday pick_a_date

test_date: test_date.cpp date.o *.h
	-$(CXX) $(CXXFLAGS) test_date.cpp date.o -o test_date 

birthday: birthday.cpp date.o *.h
	-$(CXX) $(CXXFLAGS) birthday.cpp date.o -o birthday

pick_a_date: pick_a_date.cpp date.o *.h
	-$(CXX) $(CXXFLAGS) pick_a_date.cpp date.o -o pick_a_date

date.o: date.cpp *.h
	-$(CXX) $(CXXFLAGS) -c date.cpp -o date.o

clean:
	rm -f *.o *.gch *.~ a.out test_date birthday pick_a_date

