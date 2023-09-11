public abstract class Critter {
    public Critter(int frequency) {
        this.frequency = frequency; 
        this.timer = 0;
    }
    public void count() {
        if (++timer > frequency) timer = 0;
    }
    public abstract void speak();
    
    protected int frequency;
    protected int timer;
}
