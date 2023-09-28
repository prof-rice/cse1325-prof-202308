import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Simple {
    public Simple(String aString, int anInt, double aDouble, char aChar, boolean aBoolean) {
        this.aString = aString;
        this.anInt = anInt;
        this.aDouble = aDouble;
        this.aChar = aChar;
        this.aBoolean = aBoolean;
    }
    
    public Simple(BufferedReader br) throws IOException {
        this.aString  =                      br.readLine();
        this.anInt    = Integer.parseInt    (br.readLine());
        this.aDouble  = Double.parseDouble  (br.readLine());
        this.aChar    =                      br.readLine().charAt(0);
        this.aBoolean = Boolean.parseBoolean(br.readLine());
    }
    
    public void save(BufferedWriter bw) throws IOException {
        bw.write(aString       + '\n');
        bw.write("" + anInt    + '\n');
        bw.write("" + aDouble  + '\n');
        bw.write("" + aChar    + '\n');
        bw.write("" + aBoolean + '\n');
    }
    
    @Override
    public String toString() {
        return aString + " " + anInt + " " + aDouble + " " + aChar + " " + aBoolean;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;
        Simple s = (Simple) o;
        return aString.equals(s.aString)
            && anInt    == s.anInt
            && aDouble  == s.aDouble
            && aChar    == s.aChar
            && aBoolean == s.aBoolean;
    }

    private String aString;
    private int anInt;
    private double aDouble;
    private char aChar;
    private boolean aBoolean;
    
    public static void main(String[] args) {
        String filename = "simple.txt";
    
        // Create and print a simple object
        Simple simple = new Simple(
            "Hello, World!", 42, 3.14, 'x', true);
        System.out.println(simple.toString());
        
        // Save the object to a simple file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            simple.save(bw);
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
            System.exit(-1);
        }
        
        // Open the simple file and recreate the object
        Simple simpleRecreated = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            simpleRecreated = new Simple(br);
        } catch (Exception e) {
            System.err.println("Failed to read: " + e);
            System.exit(-2);
        }
        System.out.println(simpleRecreated.toString());
        
        if(simple.equals(simpleRecreated)) System.out.println("They match!");
    }
}
