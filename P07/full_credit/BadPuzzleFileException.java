import java.io.IOException;

public class BadPuzzleFileException extends IOException {
    public BadPuzzleFileException(String name, int lineNumber) {
        super("Malformed puzzle '" + name + "' at line " + lineNumber);
    }
}
