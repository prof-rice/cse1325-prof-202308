import java.util.ArrayList;

public class Garden {
    public Garden(Light light) {
        this.light = light;
        this.plants = new ArrayList<>();
    }
    public void addPlant(Plant plant) {
        plants.add(plant);
    }
    @Override
    public String toString() {
        String result = "";
        String separator = "";
        for(Plant p : plants) {
            result += separator + p;
            separator = ", ";
        }
        return result;
    }
    private ArrayList<Plant> plants;
    private Light light;
}
