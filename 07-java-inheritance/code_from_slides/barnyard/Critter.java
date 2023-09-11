public class Critter {
    public Critter(int frequency) {
        this.frequency = frequency; 
        this.timer = 0;
    }
    public void count() {
        if (++timer > frequency) timer = 0;
    }
    public void speak() {
        if (timer == 0) System.out.println("Generic critter sound!"); 
    }
    
    protected int frequency;
    protected int timer;
}
