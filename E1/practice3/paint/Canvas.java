import java.util.ArrayList;

public class Canvas {
    public void stroke(Color color) {
        colors.add(color);
        System.out.println("Stroked in " + color);
    }
    @Override
    public String toString() {
        String result = "";
        for(Color c : colors) result += c;
        return result;
    }
    private ArrayList<Color> colors = new ArrayList<>();
    
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.stroke(Color.BLUE);
        System.out.println(canvas);
    }
}
