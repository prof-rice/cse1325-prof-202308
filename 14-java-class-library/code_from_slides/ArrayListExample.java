import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(
            "Hello", "UTA", "World"
        ));
        
        /* Another approach to initializing an ArrayList
        ArrayList<String> strings = new ArrayList<>() {{
            add("Hello");
            add("UTA");
            add("World");
        }};
        */
        
        strings.add("Forever!");
        strings.remove(2);
        for(var s : strings) System.out.println(s);
    }
}
