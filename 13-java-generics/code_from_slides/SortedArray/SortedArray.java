import java.util.Arrays;

public class SortedArray<E> {
    public SortedArray(int size) {
        array = new Object[size];
        nextElement = 0;
    }
    public int length() {
        return array.length;
    }
    public int size() {
        return nextElement;
    }
    public void add(E e) {
        array[nextElement] = e;
        Arrays.sort(array, 0, ++nextElement);
    }
    public E get(int index) {
        return (E) array[index];  
    }
    
    private Object[] array;
    private int nextElement;
}
