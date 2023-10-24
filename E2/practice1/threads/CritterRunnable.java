import java.util.ArrayList;

public class CritterRunnable implements Runnable {
    private static Object mutex = new Object();
    private static ArrayList<String> sounds = new ArrayList<>();

    private String sound;
    public CritterRunnable(String sound) {this.sound = sound;}
    
    @Override
    public void run() {chatter(sound);}

    public static void chatter(String sound) {
        for(double f=0; f<Math.random()*6; ++f) 
        synchronized(mutex) {sounds.add(sound);}
    }
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        String[] says = {"arf", "meow", "chirp", "quack", "moo", "cluck", "hiss", "oink", "roar", "whinny"};
        
        for(String s: says) 
            threads.add(new Thread(new CritterRunnable(s)));
        for(Thread t: threads) 
            t.start();

        try {
            for(Thread t : threads) t.join();
        } catch (InterruptedException e) { 
        }
        for(String s : sounds) System.out.println(s);
    }
}
