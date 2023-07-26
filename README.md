# CSE1325-001 and 002
## Fall 2023

This repository includes code that complements each lecture, suggested solutions for each assignment and (often) exams, and additional example code for my Object-Oriented Programming in Java and C++ course.

## Cloning to your local machine

The commands below are based on the bash shell and its cousins. Installing git on Windows adds a bash shell, and Linux and Mac include bash and zsh (a bash-like shell) respectively by default.

To create a folder in your virtual machine that is linked to this repository, go to your home directory (``cd``), copy the following command (select and Ctrl-c) and paste it into your terminal (Ctrl-Alt-t to launch a terminal, then right-click -> Paste or Shift-Control-V):

``git clone https://github.com/prof-rice/cse1325-prof.git``

To download changes within your terminal at any later time, change to your cse1325-prof directory (``cd ~/cse1325-prof``) and type:

``git pull``

If that doesn't work, just delete it and clone it again!

## Viewing files

On Linux you can open ANY filename from the terminal (using the associated application, which may be graphical) with 

``xdg-open filename``

If you're using the supplied virtual machine or the bash enhancements discussed in lecture, this is shorter and perhaps easier to remember:

``e filename``

In bash and its cousins on any operating system, you can usually print text files to the console using this.

``cat filename``

Longer files can be viewed (with PgUp and PgDn, or ``q`` to quit) using

``less filename``

(If ``less`` doesn't work, use ``more`` instead. More is less with less features but is more commonly installed. Linux is fun.)

## Editing files

To edit a text file within the terminal on Linux or a Mac, try 

``nano filename`` 

Windows includes no equivalent, although if you've installed git as required for this class, something like this should work:

'C:\Program Files\Git\usr\bin\nano.exe' filename

If you're using VS Code, the executable's name is "code":

``code filename``

Or, in any OS, just use your favorite programming editor to edit code and other text files, and your favorite applications to edit other file types. Beware of complex Integrated Development Environments (IDEs), though - you want to focus on learning Java and C++ this semester, not Eclipse!

## Students from previous semesters

If you have used this repository during a previous semester, be advised that it is restarted each semester. Previous editions have been renamed as shown below (named for start of term in YYYYMM format except the first, for which the "date" is simply cpp). 

And before upcoming students get *too* excited, assignments change *every semester*, so ixnay on any eatingchay plans! ;-) 

Spring 2021 and earlier (C++ and gtkmm only): https://github.com/prof-rice/cse1325-prof-cpp

Fall 2021 (Java and Swing only): https://github.com/prof-rice/cse1325-prof-202108

Spring 2022 - 2023 (Java and Swing with limited C++): https://github.com/prof-rice/cse1325-prof-202301
