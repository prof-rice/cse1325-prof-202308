public class Plant {
    public Plant(String name, Light light) {
        this.name = name;
        this.light = light;
    }
    public String name() {return name;}
    public Light light() {return light;}
    @Override
    public String toString() {
        return name + " (" + light + ")";
    }
    private String name;
    private Light light;
}
