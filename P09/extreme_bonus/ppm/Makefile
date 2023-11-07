CXXFLAGS += -std=c++17

shapes: Shapes.o Shape.o Circle.o Rectangle.o *.h
	$(CXX) $(CXXFLAGS) Shapes.o Shape.o Circle.o Rectangle.o -o shapes 

Circle.o: Circle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Circle.cpp -o Circle.o

Rectangle.o: Rectangle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Rectangle.cpp  -o Rectangle.o

Shape.o: Shape.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shape.cpp -o Shape.o

Shapes.o: Shapes.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shapes.cpp -o Shapes.o

Mainwin.o: Mainwin.cpp *.h
	$(CXX) $(CXXFLAGS) -c Mainwin.cpp -o Mainwin.o

Canvas.o: Canvas.cpp *.h
	$(CXX) $(CXXFLAGS) -c Canvas.cpp -o Canvas.o

clean:
	rm -f *.o *.gch *~ a.out shapes *.ppm

