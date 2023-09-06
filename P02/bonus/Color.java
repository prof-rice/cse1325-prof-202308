public enum Color{RED(0xFF0000), GREEN(0x00FF00), BLUE(0X0000FF), YELLOW(0xFFFF00);
    private int rgb;
    private Color(int rgb) {
        this.rgb = rgb;
    }
    @Override
    public String toString() {
        return String.format("%6s (0x%06X)", name(), rgb);
    }
};