public class Destination {
    public Destination(String city, int distance) {
        this.city = city;
        this.distance = distance;
    }
    public int distance() {
        return distance;
    }
    @Override
    public String toString() {
        return city + " (" + distance + " miles)";
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if((o == null) || !(o instanceof Destination)) return false;
        // OR if (o == null || getClass() != o.getClass())  return false;
        Destination d = (Destination) o;
        return city.equals(d.city);
    }
    private String city;
    private int distance;
}
