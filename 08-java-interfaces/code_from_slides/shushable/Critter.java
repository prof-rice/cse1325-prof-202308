abstract class Critter implements Shushable {
    public Critter(int frequency) {this.frequency = frequency; timer = 0; shushed = false;}
    public void count() {if (++timer > frequency) timer = 0;}
    public abstract void speak();
    
    protected int frequency;
    protected int timer;
    
    // Implementing Shushable interface
    private boolean shushed;
    
    public void shush() {shushed = true;}
    public void unshush() {shushed = false;}
    public boolean isShushed() {return shushed;}
}
