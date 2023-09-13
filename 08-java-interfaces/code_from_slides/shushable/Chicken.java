class Chicken extends Critter {
    public Chicken(int frequency) {super(frequency);}
    @Override
    public void speak() {if (timer == 0 && !isShushed()) System.out.println("Cluck! Cluck!"); }
}
