import java.util.ArrayList;

public class Paintbrush {
    public Paintbrush(Material material, Shape shape, int bristleWidth) {
        this.material = material;
        this.shape = shape;
        this.bristleWidth = bristleWidth;
        if(bristleWidth < 10) 
            throw new IllegalArgumentException("Width of " + bristleWidth + " too small");
    }
    // public Paintbrush(Paintbrush p) {
    //     this(p.material, p.shape, p.bristleWidth);
    // }
    public void stroke(Color color) {
        colors.add(color);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(material + " " + shape + " " + bristleWidth + " mm paintbrush");
        for(Color c : colors) result.append("\n  Painted " + c);
        return result.toString();
    }

    private Material material;
    private Shape shape;
    private int bristleWidth; // in mm
    private ArrayList<Color> colors = new ArrayList<>();

    public static void main(String[] args) {
        Paintbrush p = null;
        try {
            p = new Paintbrush(Material.SQUIRREL, Shape.MOP, 10);
        } catch(IllegalArgumentException e) {
            System.err.println(e);
            System.exit(-1);
        }
        p.stroke(Color.BLUE);
        System.out.println(p);
    }

}
