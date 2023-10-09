import java.util.Iterator;
import java.util.ListIterator;

public class ListIteratorExample {
    private static void printIterator(String typeName, Iterator it) {
        System.out.print(typeName + " include ");
        String sep = "";
        while(it.hasNext()) {
            System.out.print(sep + it.next()); 
            sep = ", ";
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        Food food = new Food();
        printIterator("Fruits", food.liFruit());
        printIterator("Veggies", food.liVeggie());
        System.out.println("\nWait - isn't tomato a fruit???\n");
        
        Veggie tomato = new Veggie("Tomato");    // Delete all of these objects
        ListIterator<Veggie> vi=food.liVeggie(); // Iterate through the veggies
        while(vi.hasNext()) if(vi.next().equals(tomato)) vi.remove();
        ListIterator<Fruit> fi = food.liFruit(); // Iterate through the fruits
        fi.add(new Fruit("Tomato"));             // Insert this at the beginning
    
        printIterator("Fruits", food.liFruit());
        printIterator("Veggies", food.liVeggie());
    }
}
