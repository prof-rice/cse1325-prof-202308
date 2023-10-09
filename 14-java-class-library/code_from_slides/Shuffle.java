import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Shuffle {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(String s : args) list.add(s);
        Collections.shuffle(list);
        for(String s : list) System.out.print(s + " ");
        System.out.println("");
    }
}
