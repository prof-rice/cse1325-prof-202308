public class Patron {
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    private String name;
    private String email;
}