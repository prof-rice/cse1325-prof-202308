 public class Sound {
     public Sound(double frequency, Timbre timbre) {
         if(frequency < 20 || frequency > 20000)
             throw new IllegalArgumentException("invalid frequency");
         this.frequency = frequency;
         this.timbre = timbre;
     }
     public Sound nextNote() {
         return new Sound(frequency*1.0595, timbre);
     } 
     public Sound nextOctave() {
         return new Sound(frequency*2, timbre);
     } 
     public Sound previousNote() {
         return new Sound(frequency/1.0595, timbre);
     } 
     public Sound previousOctave() {
         return new Sound(frequency/2, timbre);
     }
     public void play(double duration) {
     }
     @Override
     public String toString() {
         // return timbre + " @ " + frequency + " Hz";
         return String.format("%s @ %f Hz", timbre, frequency);
     }
    
     private double frequency;
     protected Timbre timbre;
 }
