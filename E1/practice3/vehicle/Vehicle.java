public abstract class Vehicle {
    public Vehicle(String name, int range) {
        this.name = name;
        this.range = range;
    }

    public abstract void travel(Destination d);

    @Override
    public String toString() {
        return name + " (range " + range + " miles)";
    }

    private String name;
    protected int range;
}
