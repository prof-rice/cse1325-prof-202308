import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

public class Puzzle {
    public Puzzle(String name, BufferedReader br) throws IOException {
        this.name = name;
        
        int lineNumber = 0;  // for reporting in exceptions
        String line = null;  // each line read from br
        
        // Skip the solution
        while(!getLine(br).equals("Solution:")) {++lineNumber;} // find "Solution:"
        while(!getLine(br).isEmpty())           {++lineNumber;} // skip solution
        while((line = getLine(br)).isEmpty())   {++lineNumber;} // skip spaces before puzzle
        
        // Load the puzzle
        while(!line.isEmpty())  {
            board.add(line);
            line = getLine(br); ++lineNumber;
            if(line == null) // can't end puzzle before word list
                throw new BadPuzzleFileException(name, lineNumber);
        }
        // Load the word list
        while(line != null) {
            for(String word : line.split("\\s+")) {
                if(!word.isEmpty()) words.add(word);
            }
            line = br.readLine(); ++lineNumber;
        }
    }
    public String name() {
        return name;
    }
    public String[] getWords() {
        return words.toArray(new String[0]);
    }
    public int width() {
        return board.get(0).length();
    }
    public int height() {
        return board.size();
    }
    public char getChar(int x, int y) {
        return board.get(y).charAt(x);
    }
    public char getChar(int x, int y, Direction dir, int numChars) {
        try {
            return board.get(y+dir.deltaY()*numChars).charAt(x+dir.deltaX()*numChars);
        } catch(IndexOutOfBoundsException e) { // in x or y direction
            return '\0';
        }
    }
    // remove all whitespace as lines are read
    private String getLine(BufferedReader br) throws IOException {
        return br.readLine().replaceAll("\\s", ""); 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("From file " + name + "\n\n");
        for(String s : board) sb.append(s + '\n');
        sb.append("\n\n");
        int wordsPerLine = 3;
        for(String word : words) {
            sb.append(String.format("%28s  ", word));
            if(--wordsPerLine == 0) {
                wordsPerLine = 3;
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String name;
    private ArrayList<String> board = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();
}
