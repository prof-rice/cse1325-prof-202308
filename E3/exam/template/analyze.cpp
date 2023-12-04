#include "analyze.h"

template<class E>
std::ostream& operator<<(std::ostream& ost, const Analyze<E>& a) {
    ost << a._count << " elements of " << a._sum << " total size";
    return ost;
}
