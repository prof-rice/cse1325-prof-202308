CXXFLAGS = --std=c++17 -pthread

info:
	@echo
	@printf 'Type e.g., "make auto"  to create program "auto" from auto.cpp\n'
	@printf 'Type       "make all"   to create every program\n'
	@printf 'Type       "make clean" to delete every program\n\n'

hw_concur: hw_concur.cpp
	-$(CXX) $(CXXFLAGS) -o hw_concur hw_concur.cpp
	@printf "Now type ./hw_concur to execute the result\n\n"

mutex: mutex.cpp
	-$(CXX) $(CXXFLAGS) -o mutex mutex.cpp
	@printf "Now type ./mutex to execute the result\n\n"

no_mutex: no_mutex.cpp
	-$(CXX) $(CXXFLAGS) -o no_mutex no_mutex.cpp
	@printf "Now type ./no_mutex to execute the result\n\n"

phone: phone.cpp
	-$(CXX) $(CXXFLAGS) -o phone phone.cpp
	@printf "Now type ./phone to execute the result\n\n"

simple_thread: simple_thread.cpp
	-$(CXX) $(CXXFLAGS) -o simple_thread simple_thread.cpp
	@printf "Now type ./simple_thread to execute the result\n\n"

simple_thread_lambda: simple_thread_lambda.cpp
	-$(CXX) $(CXXFLAGS) -o simple_thread_lambda simple_thread_lambda.cpp
	@printf "Now type ./simple_thread_lambda to execute the result\n\n"

all:  hw_concur mutex no_mutex phone simple_thread simple_thread_lambda

clean:
	rm -f *.o *.gch a.out  hw_concur mutex no_mutex phone simple_thread simple_thread_lambda

