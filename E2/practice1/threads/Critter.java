import java.util.ArrayList;

public class Critter {


    private static ArrayList<String> sounds = new ArrayList<>();
    public static void chatter(String sound) {
        for(double f=0; f<Math.random()*6; ++f) 
            sounds.add(sound);
    }
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        String[] says = {"arf", "meow", "chirp", "quack", "moo", "cluck", "hiss", "oink", "roar", "whinny"};
        
        for(String s: says) 
            chatter(s);
        

        for(String s : sounds) System.out.println(s);
    }
}
