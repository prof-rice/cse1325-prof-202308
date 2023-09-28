enum Pattern {Rattail, Willow, Kings, Harley, 
              Grecian, Dubarry, Britannia, Bead, 
              Warwick, Henley, Camelot, Cascade, 
              Echo, Artic, Athenian, Versailles}

interface Washable {
    void wash();
}

class Plate {
    private int diameter;
    public Plate(int diameter) {
        this.diameter = diameter;
    }
    @Override
    public String toString() {
        return "" + diameter + "\" plate";
    }
}
                 
abstract class Cutlery {
    protected Pattern pattern;
    public Cutlery(Pattern pattern) {
        this.pattern = pattern;
    }
    abstract void use();
}

class UnhygienicException extends IllegalStateException {
    public UnhygienicException(Cutlery utensil) {
        super(utensil.toString() + " isn't clean!");
    }
}

class Spoon extends Cutlery implements Washable {
    private boolean clean;
    public Spoon(Pattern pattern) {
        super(pattern);
        clean = true;
    }
    @Override
    public void use() {
        if(!clean) throw new UnhygienicException(this);
        System.out.println("Stirring with " + toString());
        clean = false;
    }
    @Override
    public void wash() {
        System.out.println("Washing " + toString());
        clean = true;
    }
    @Override
    public String toString() {
        return (clean ? "Clean " : "Dirty ") + pattern + " spoon";
    }
}

public class Setting {
    public static void main(String[] args) {
        Plate bowl = new Plate(8);
        Spoon spoon = new Spoon(Pattern.Rattail);
        spoon.use();
        spoon.wash();
        spoon.use();
        try {
            spoon.use();
        } catch(UnhygienicException e) {
            System.err.println("Yikes! " + spoon + " is dirty!");
            spoon.wash();
            spoon.use();
        }
    }
}

