import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

abstract class Critter {
    public Critter(int frequency) {this.frequency = frequency; timer = 0;}
    public final void count() {if (++timer > frequency) timer = 0;}
    public abstract void speak();
    
    protected int frequency;
    protected int timer;

    public static void main(String[] args) {
        ArrayList<Critter> critters = new ArrayList<>();        critters.add(new Cow(13));
        critters.add(new Dog(11));    critters.add(new Dog(9)); critters.add(new Cow(7));
        critters.add(new Chicken(5)); critters.add(new Dog(3)); critters.add(new Chicken(2)); 
        
        TimeUnit ms = TimeUnit.MILLISECONDS;

        System.out.println("W E L C O M E   T O   T H E   B A R N Y A R D !");
        for (int i=0; i<120; ++i) {
            for (Critter c: critters) { c.count(); c.speak(); }
            try {ms.sleep(50L);} catch (InterruptedException e) { }
        }
    }
}
