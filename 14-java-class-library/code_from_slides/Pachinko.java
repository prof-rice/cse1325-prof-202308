import java.util.Scanner;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class Pachinko {
    public static final int boardSize = 60;
    public static final int startingBalls = 20;
    
    public static final int minAim = 4 * boardSize / 5; 
    public static final int maxAim = 9 * boardSize / 10;
    
    public static final int margin = 4; // minimum score to win any balls
    
    private static int generatePin(int min, int max) {
        int pin = ThreadLocalRandom.current().nextInt(min, max + 1);
        return pin;
    }
    private static int playBall(ListIterator<Integer> it) {
        int infiniteDeLooper = -60;
        int ballsWon = -1; // -1 signals out of bounds
        
        // While the ball is within the playing area of the board
        while(it.nextIndex() > 0) {
        
            // print the ball position
            for(int i=0; i<it.nextIndex(); ++i) System.out.print('#');
            System.out.print(" " + it.nextIndex());
            
            // Determine where the ball bounces based on board value here
            //   offset by randomized bias (gravity) that grows increasingly negative
            int bounce = (it.hasNext() ? it.next() : 0) 
                       + generatePin(--infiniteDeLooper/20, 1);
            System.out.println(" " +  bounce);
            
            // If we reach the bottom, calculate balls won if any
            if(it.nextIndex() + bounce <= 1) {
                int finalPos = it.nextIndex() + bounce;
                int score = finalPos*finalPos;
                ballsWon = (score > margin) ? (score - margin) / 2 + generatePin(0 , 2) : 0;
                break;
            }
            // If we exceed the top, we're out of bounds
            if(it.nextIndex() + bounce > boardSize) {
                break;
            } 
            
            // Update the ListIterator to the new position
            if(bounce > 0) {
                while(bounce-- > 1) {
                    it.next(); 
                }
            } else if(bounce < 0) {
                while(bounce++ < 1) {
                    it.previous(); 
                }
            } else {
                it.previous();
            }
        } 
        return ballsWon;   
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        int aim=0;
        
        ArrayList<Integer> board = new ArrayList<>();
        ListIterator<Integer> it;

        for(int i=0; i<boardSize; ++i)
            board.add(generatePin(-6, 4));
        
        System.out.println("Board:");
        for(int i=0; i<boardSize; ++i)
            System.out.printf("%3d%s", board.get(i), (i%5 == 4) ? "\n" : "");
            
        int balls = startingBalls;
        while(balls > 0) {
            System.out.printf("%5d balls, aim (%3d to %3d, 'done' to cash out): ",
                              balls, minAim, maxAim);
            line = in.nextLine();
            if(!line.isEmpty()) {
                if(line.equalsIgnoreCase("done")) break;
                try {
                    aim = Integer.parseInt(line);
                    if(aim < minAim || maxAim < aim) 
                        throw new ArrayIndexOutOfBoundsException();
                } catch(Exception e) {
                    System.err.println("Invalid aim: " + line + ", try again");
                    continue;
                }
            }
            --balls;
            int ballsWon = playBall(board.listIterator(aim));
            if(ballsWon < 0) System.out.println("out of bounds");
            else if (ballsWon == 0) System.out.println("out");
            else {System.out.println("won " + ballsWon + " balls"); balls += ballsWon;}
        }
            
        if(balls > startingBalls) 
            System.out.println("You cash out with " + balls + " balls");
        else
            System.out.println("You are out of balls :-(");
    }
}


