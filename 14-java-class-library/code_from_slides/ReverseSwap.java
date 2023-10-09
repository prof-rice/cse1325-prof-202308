import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ReverseSwap {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(String s : args) list.add(s);
        Collections.reverse(list);
        for(int i=1; i<list.size(); i+=2)
            Collections.swap(list, i-1, i);
        for(String s : list) System.out.print(s + " ");
        System.out.println("");
    }
}
