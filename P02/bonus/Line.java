public class Line {
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    @Override
    public String toString() {
        return String.format("%6s (%.3f,%.3f)-(%.3f,%.3f)", color, x1, y1, x2, y2);
        // OR return color.toString() + " (" + x1 + "," + y1 + ")-(" + x2 + "," + y2 + ")";
    }
    public double length() {
        double xLength = x2 - x1;
        double yLength = y2 - y1;
        return Math.sqrt(xLength * xLength + yLength * yLength);
    }
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Color color;
}