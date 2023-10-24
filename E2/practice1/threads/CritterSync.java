import java.util.ArrayList;

public class CritterSync {
    private static ArrayList<String> sounds = new ArrayList<>();
    private synchronized static void addSound(String sound) {sounds.add(sound);}
    public static void chatter(String sound) {
        for(double f=0; f<Math.random()*6; ++f) 
            addSound(sound);
    }
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        String[] says = {"arf", "meow", "chirp", "quack", "moo", "cluck", "hiss", "oink", "roar", "whinny"};
        
        for(String s: says) 
            threads.add(new Thread(() -> chatter(s)));
        for(Thread t: threads) 
            t.start();

        try {
            for(Thread t : threads) t.join();
        } catch (InterruptedException e) { 
        }
        for(String s : sounds) System.out.println(s);
    }
}
