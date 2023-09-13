import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Farm {
    public static void main(String[] args) {
        ArrayList<Critter> critters = new ArrayList<>(
            Arrays.asList(new Cow(13),    new Dog(11), new Dog(9),  new Cow(7),
                          new Chicken(5), new Dog(3),  new Chicken(2)));
                
        TimeUnit ms = TimeUnit.MILLISECONDS;

        System.out.println("W E L C O M E   T O   T H E   B A R N Y A R D !");
        for (int i=0; i<120; ++i) {
            for (Critter c: critters) { c.count(); c.speak(); }
            if(i%20 == 0) {
                System.out.println("==> Unshushing the barnyard animals! Earplugs in!");                
                for (Critter c: critters) { c.unshush(); }
            } else if ((i+10)%20 == 0) {
                System.out.println("==> Shushing the barnyard animals... zzz...");                
                for (Critter c: critters) { c.shush(); }
            }
            try {ms.sleep(50L);} catch (InterruptedException e) { }
        }
    }
}
