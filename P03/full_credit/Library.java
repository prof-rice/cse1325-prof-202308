import java.util.ArrayList;

public class Library {
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>(); // = new ArrayList<>() may be in field
    }
    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    public void checkOut(int publicationIndex, String patron) {
        publications.get(publicationIndex).checkOut(patron);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n\n");
        for(int i=0; i<publications.size(); ++i)
            sb.append("" + i + ") " + publications.get(i).toString() + "\n");
        return sb.toString();
    }
    private String name;
    private ArrayList<Publication> publications; // = new ArrayList<>(); // also permissible
}
