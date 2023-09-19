public class Jet extends Vehicle implements Flyable {
    public Jet(String name, int range) {
        super(name, range);
    }
    @Override
    public void fly(Destination d) {
        System.out.println("Flying to " + d);
    }
    @Override
    public void travel(Destination d) {
        fly(d);
    }
}
