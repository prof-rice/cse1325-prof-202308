import java.util.ArrayList;

public class Fleet {
    public static void main(String[] args) {
        vehicles.add(new Car("Tesla Model S", 402));
        vehicles.add(new Jet("Falcon 8X", 7295));
        destinations.add(new Destination("Houston, TX", 255));
        destinations.add(new Destination("El Paso, TX", 608));
        destinations.add(new Destination("Ankorage, AK", 3967));
        
        for(Destination d : destinations) {
            for(Vehicle v : vehicles) {
                System.out.println("===\nTraveling to " + d + " in our " + v + "\n");
                v.travel(d);
            }
        }
        
    }

    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<Destination> destinations = new ArrayList<>();
}
