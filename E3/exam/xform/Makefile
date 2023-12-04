CXXFLAGS += --std=c++17

all: alls

debug: CXXFLAGS += -g
debug: alls

xform: xform.cpp
	-$(CXX) $(CXXFLAGS) xform.cpp -o xform

alls:  xform

clean:
	rm -f *.o *.gch a.out  xform

