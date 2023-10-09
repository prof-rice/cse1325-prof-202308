import java.util.ArrayDeque;
import java.util.Iterator;

public class ArrayDequeExample {
    public static void main(String[] args) {
        ArrayDeque<Integer> lifo = new ArrayDeque<>(); // Last-In First-Out Stack
        ArrayDeque<Integer> fifo = new ArrayDeque<>(); // First-In First-Out Stack
        int popped;
        
        for (int i=1; i<10; ++i) {
            System.out.print("\n\nPushing " + i);
            
            // Last-In First-Out
            lifo.push(i);
            if(i%3 == 0) popped = lifo.pop();
            else popped = 0;

            System.out.print("\nLIFO: ");
            Iterator it = lifo.iterator();
            while(it.hasNext()) System.out.print(it.next() + " ");
            if(popped > 0) System.out.print("popped " + popped);       
            
            // First-In First-Out
            fifo.push(i);
            if(i%4 == 0) popped = fifo.removeLast();
            else popped = 0;

            System.out.print("\nFIFO: ");
            it = fifo.iterator();
            while(it.hasNext()) System.out.print(it.next() + " ");
            if(popped > 0) System.out.print("popped " + popped);       
        }
        System.out.println("\n");
    }
}
