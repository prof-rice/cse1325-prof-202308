public class Cow extends Critter {
    public Cow(int frequency) {
        super(frequency);
    }
    @Override
    public void speak() {
        if (timer == 0) System.out.println("Moo! Mooooo!");
    }
}
