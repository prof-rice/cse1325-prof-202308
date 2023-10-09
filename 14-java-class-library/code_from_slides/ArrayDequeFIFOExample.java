import java.util.ArrayDeque;
import java.util.Iterator;

public class ArrayDequeFIFOExample {
    public static void main(String[] args) {
        ArrayDeque<Integer> fifo = new ArrayDeque<>(); // First-In First-Out Stack
        int popped;
        
        for (int i=1; i<10; ++i) {
            System.out.print("\n\nPushing " + i);
                        
            // First-In First-Out
            fifo.push(i);
            if(i%3 == 0) popped = fifo.removeLast(); // use removeFirst() or pop() for LIFO
            else popped = 0;

            System.out.print("\nFIFO: ");
            Iterator it = fifo.iterator();
            while(it.hasNext()) System.out.print(it.next() + " ");
            if(popped > 0) System.out.print("popped " + popped);       
        }
        System.out.println("\n");
    }
}
