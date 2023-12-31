// Copyright 2023 George F. Rice <https://github.com/prof-rice>
//
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or 
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

import java.util.ArrayList;

public class Library {
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }
    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }
    public String patronMenu() {
       StringBuilder sb = new StringBuilder("Patrons\n\n");
        for(int i=0; i<patrons.size(); ++i)
            sb.append("" + i + ") " + patrons.get(i).toString() + "\n");
        return sb.toString();
    }
    public void checkOut(int publicationIndex, int patronIndex) {
        publications.get(publicationIndex).checkOut(patrons.get(patronIndex));
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n\n");
        for(int i=0; i<publications.size(); ++i)
            sb.append("" + i + ") " + publications.get(i).toString() + "\n");
        return sb.toString();
    }
    private String name;
    private ArrayList<Publication> publications;
    private ArrayList<Patron> patrons;
}
