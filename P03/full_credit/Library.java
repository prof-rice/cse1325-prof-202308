// Copyright 2023 George F. Rice <https://github.com/prof-rice>
//
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or 
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

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
