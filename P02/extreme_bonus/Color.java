public enum Color{RED(0xFF0000), GREEN(0x00FF00), BLUE(0X0000FF), YELLOW(0xFFFF00);
    private int rgb;
    private Color(int rgb) {
        this.rgb = rgb;
    }
    @Override
    public String toString() {
        int red = rgb >> 16;
        int green = (rgb & 0xFF00) >> 8;
        int blue = rgb & 0xFF;
        String startColor = "\033[38;2;" + red + ";" + green + ";" + blue + "m";
        String endColor = "\033[0m";
        return String.format("%6s (%s0x%06X%s)", name(), startColor, rgb, endColor);
    }
};