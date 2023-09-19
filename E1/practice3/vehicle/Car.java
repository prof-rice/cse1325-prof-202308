public class Car extends Vehicle {
    public Car(String name, int range) {
        super(name, range);
    }
    @Override
    public void travel(Destination d) {
        System.out.println("Driving to " + d);
    }
}
