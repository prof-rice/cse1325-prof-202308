import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

class Critter {
    public Critter(int frequency) {this.frequency = frequency; timer = 0;}
    public void count() {if (++timer > frequency) timer = 0;}
    public void speak() {if (timer == 0) System.out.println("Generic critter sound!"); }
    
    protected int frequency;
    protected int timer;

    public static void main(String[] args) {
        ArrayList<Critter> critters = new ArrayList<> () {{
            add(new Critter(13));
            add(new Critter(11));
            add(new Critter(7));
            add(new Critter(3));
        }};
        
        TimeUnit ms = TimeUnit.MILLISECONDS;

        System.out.println("W E L C O M E   T O   T H E   B A R N Y A R D !");
        for (int i=0; i<120; ++i) {
            for (Critter c: critters) { c.count(); c.speak(); }
            try {ms.sleep(50L);} catch (InterruptedException e) { }
        }
    }
}
