class Chicken extends Critter {
    public Chicken(int frequency) {super(frequency);}
    @Override
    public void speak() {if (timer == 0) System.out.println("Cluck! Cluck!"); }
    @Override
    public final void count() {if (timer++ > frequency) timer = 0;}
}
