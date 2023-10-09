import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class MinMaxFreqDisj {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args); // convert array to List
        List<String> articles = Arrays.asList("a", "and", "the"); 
        for(String s : articles) 
            System.out.println("Found " + Collections.frequency(list, s) + " " + s);
        if(Collections.disjoint(list, articles)) System.out.println("No articles");
        else System.out.println("Some articles");
        System.out.println("Min word is " + Collections.min(list)
                    + " and max word is " + Collections.max(list));       
    }
}
