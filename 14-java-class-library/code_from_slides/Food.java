import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

class Fruit {
    public Fruit(String name) {this.name = name;}
    @Override
    public String toString() {return "Fruit " + name;}
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Fruit r = (Fruit) o;
        return name.equals(r.name);
    }
    @Override
    public int hashCode() {
        return 7 + 31*name.hashCode();
    }
    private String name;
}

class Veggie {
    public Veggie(String name) {this.name = name;}
    @Override
    public String toString() {return "Veggie " + name;}
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Veggie r = (Veggie) o;
        return name.equals(r.name);
    }
    @Override
    public int hashCode() {
        return 3 + 31*name.hashCode();
    }
     private String name;
}

public class Food {
    // Lists of foods
    private ArrayList<Fruit> fruits = new ArrayList<>(
        Arrays.asList(new Fruit("Apple"), new Fruit("Pear"), new Fruit("Grape")));
    private ArrayList<Veggie> veggies = new ArrayList<>(
        Arrays.asList(new Veggie("Carrot"), new Veggie("Pea"), new Veggie("Tomato")));
        
    // Iterators and ListIterators for the lists
    public Iterator<Fruit> iFruit() {return fruits.iterator();}
    public Iterator<Veggie> iVeggie() {return veggies.iterator();}
    public ListIterator<Fruit> liFruit() {return fruits.listIterator();}
    public ListIterator<Veggie> liVeggie() {return veggies.listIterator();}
}
