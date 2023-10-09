import java.util.LinkedList;
import java.util.Arrays;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>(Arrays.asList(
            "Hello", "UTA", "World"
        ));
        
        /* Another approach to initializing a Linked List
        LinkedList<String> strings = new LinkedList<>() {{
            add("Hello");
            add("UTA");
            add("World");
        }};
        */
        
        strings.add(1, "there"); // Insert
        System.out.println("UTA is now index " + strings.indexOf("UTA"));
        for(var s : strings) System.out.println(s);
    }
}
