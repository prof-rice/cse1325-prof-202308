public class TestLine {
    public static void main(String[] args) {
        for(Color c : Color.values()) {
            Line line = new Line(Math.random() * 10, Math.random() * 10,
                                 Math.random() * 10, Math.random() * 10, c);
            System.out.println(line + " has length " + line.length());
        }
    }
}