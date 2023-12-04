 #ifndef __ANALYZE_H
 #define __ANALYZE_H
 
 #include <iostream>

 template<class T>
 class Analyze {
   public:
     void element(T next) {
         ++_count;
         _sum += next.size();
     }
     int count() {return _count;}
     int sum() {return _sum;}
     template<class E>
     friend std::ostream& operator<<(std::ostream& ost, const Analyze<E>& a);
   private:
     int _count = 1, _sum = 0;
 };

 #endif
