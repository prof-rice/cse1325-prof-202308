// Copyright 2023 George F. Rice <https://github.com/prof-rice>
//
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or 
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

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
