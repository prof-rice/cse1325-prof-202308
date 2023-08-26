public class TrafficLight {
    private boolean operating;
    private Color light;
    
    TrafficLight() {operating = true; light = Color.red;}
    public boolean isOperating() {return operating;}
    public void power(boolean operating) {this.operating = operating;}
    public void turnOff() {operating = false;}
    public Color thisColor() {return light;}
    public Color nextColor() {
        light = ((light == Color.green)  ? Color.yellow : 
                ((light == Color.yellow) ? Color.red    :
                                          Color.green));
        return light;
    }
    @Override
    public String toString() {return operating ? light.name() : "off";}
}
