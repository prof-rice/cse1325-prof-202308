import java.util.ArrayList;

public class TicTacToe {
    public static final int SIZE = 3;
    public enum Marker {s, O, X;
        @Override
        public String toString() {
            return (this == s) ? "." : name();
        }
    }
    private ArrayList<ArrayList<Marker>> board = new ArrayList<ArrayList<Marker>>();
    
    public TicTacToe() {
        for(int x=0; x<SIZE; ++x) {
            ArrayList<Marker> a = new ArrayList<Marker>();
            for(int y=0; y<SIZE; ++y) a.add(Marker.s);
            board.add(a);
        }
    }
    public void move(int x, int y, Marker m) {
        if(!board.get(y).get(x).equals(Marker.s))
            throw new IllegalArgumentException("Position " + x + "," + y 
                                + " already has " + board.get(x).get(y));
        board.get(y).set(x, m);
    }
    public Marker winner() {
        String win1 = "";
        String win2 = "";
        StringBuilder dia1 = new StringBuilder();
        StringBuilder dia2 = new StringBuilder();
        StringBuilder row  = new StringBuilder();
        StringBuilder col  = new StringBuilder();
        for(int y=0; y<SIZE; ++y) {
           win1 += Marker.values()[1];     // Build winning string of O
           win2 += Marker.values()[2];     // Build winning string of X
           dia1.append(board.get(y).get(y));        // Build \ diagonal
           dia2.append(board.get(y).get(SIZE-y-1)); // Build / diagonal
           for(int x=0; x<SIZE; ++x) {
               row.append(board.get(y).get(x));     // Build all rows
               col.append(board.get(x).get(y));     // Build all columns
           }
        }
        if(dia1.toString().equals(win1) || dia2.toString().equals(win1)) return Marker.values()[1];
        if(dia1.toString().equals(win2) || dia2.toString().equals(win2)) return Marker.values()[2];
        for(int i=0; i<row.length(); i += SIZE) {
            if(row.substring(i, i+SIZE).equals(win1) ||
               col.substring(i, i+SIZE).equals(win1)) return Marker.values()[1];
            if(row.substring(i, i+SIZE).equals(win2) ||
               col.substring(i, i+SIZE).equals(win2)) return Marker.values()[2];
        }
        return Marker.s;  // No winner yet
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y=0; y<SIZE; ++y) {
            for(int x=0; x<SIZE; ++x) sb.append(" " + board.get(y).get(x).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        int movesLeft = SIZE * SIZE;
        while(movesLeft-- > 0 && ttt.winner() == Marker.s) {
            System.out.println("\n" + ttt + "\n");
            while(true) {
                try {
                    Marker toMove = Marker.values()[1 + movesLeft%2];
                    ttt.move(Integer.parseInt(System.console().readLine(toMove + " move (x)? ")),
                             Integer.parseInt(System.console().readLine(toMove + " move (y)? ")),
                             toMove);
                     break;
                } catch(Exception e) {
                    System.err.println("Invalid move: " + e.getMessage());
                }
            }
        }
        System.out.println(((ttt.winner() == Marker.s) ? "Cat" : ttt.winner().toString()) + " wins!");
    }
}
