import java.util.Scanner;

public class TestTaggedArrayList {
    public static String menu() {
        return "Main Menu\n"
             + "=========\n\n"
             + "1) Add a String\n"
             + "2) Add an Integer\n"
             + "3) Add a Coordinate\n"
             + "0) Exit\n";
    }
             
    public static void main(String[] args) {
        TaggedArrayList<String> strings = new TaggedArrayList<>();
        TaggedArrayList<Integer> ints = new TaggedArrayList<>();
        TaggedArrayList<Coordinate> coords = new TaggedArrayList<>();
        
        Scanner in = new Scanner(System.in);
        int option = 99;
        String discard = "";
        
        while (option != 0) {
            System.out.print(menu() + "\nSelection? ");
            option = in.nextInt(); discard = in.nextLine();
            if (option == 0) {
            } else if (option == 1) {
                System.out.print("String? ");
                strings.add(in.nextLine());
            } else if (option == 2) {
                System.out.print("Integer? ");
                ints.add(in.nextInt()); discard = in.nextLine();
            } else if (option == 3) {
                System.out.print("Coordinate (x y z)? ");
                coords.add(new Coordinate(
                    in.nextDouble(), 
                    in.nextDouble(), 
                    in.nextDouble())); discard = in.nextLine();
            } else {
                System.err.println("Invalid option: " + option);
            }   
        }
        
        System.out.println("\nStrings\n=======\n");
        for(int i=0; i<strings.size(); ++i) 
            System.out.println("" + strings.when(i) + " '" + strings.get(i) + "'");
        
        System.out.println("\nIntegers\n========\n");
        for(int i=0; i<ints.size(); ++i) 
            System.out.println("" + ints.when(i) + " '" + ints.get(i) + "'");
        
        System.out.println("\nCoordinates\n===========\n");
        for(int i=0; i<coords.size(); ++i) 
            System.out.println("" + coords.when(i) + " '" + coords.get(i) + "'");
        
    }
}
