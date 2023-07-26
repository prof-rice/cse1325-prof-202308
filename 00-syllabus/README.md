Lecture 00 - Intro to Java
==========================

This directory hierarchy contains code and other resources associate with the first lecture. Since we're computer people, that's Lecture 00. :)

Each lecture, the top directory may contain code similar to that which I (hope to) develop "live" in class as well as any supplemental material.

## code_from_slides

Each lecture, the ``code_from_slides`` subdirectory will contain compilable code that you may see in the slides. Even if we don't cover the slides in class, I'll provide them in Canvas and associated code via GitHub for your review. 

For Java class ``Hello``, you'll find its code in file ``Hello.java``. For other code, you should be able to find the filename of the code somewhere near it on the slide.

* Directories that I give you containing Java code will always include a build.xml file. Just type ``ant`` to build all of the code, and use ``java ClassName`` to run the main method in the specified class name.
* Directories that I give you containing C++ code will always include a Makefile. Just type ``make`` to build all of the code or at least obtain further instructions.

If a directory contains both Java and C++ code, the Makefile will typically build both (as long as the Java is fairly simple), while the build.xml file will build only the Java code.

See the README.md file in the subdirectory for details.

## test_your_environment

This directory (for this lecture only) contains code that will verify that you can build command line and (optionally) Swing graphical applications in Java as well as command line applications in C++. See the README.md file in the subdirectory for details.

## Searching for Keywords

To search for source files containing a specific word such as "override", try e.g., ``grep -i override *``. If you're searching for a phrase containing spaces such as "multiple inheritance", put them in double quotes, e.g., ``grep -i "multiple inheritance" *``.

You can also search through the slides and other PDF documents, too, if you put them in a common directory under Linux. To find "multiple inheritance" in a directory containing files with a .pdf extension, try ``pdfgrep -i "multiple inheritance" *.pdf``. 

(You may need to install the ``pdfgrep`` command in your Linux environment using something like ``sudo apt install pdfgrep``.)

Note that Linux cares about file name capitalization, so if you use the more Windows-like .PDF extension, use ``pdfgrep "multiple inheritance" *.PDF`` instead!

The ``-i`` flag in the above commands means "ignore case" - that is, find "multiple inheritance", "Multiple Inheritance", or even "MULTIPLE INHERITANCE". You can find other options using the ``--help`` flag.

## Need help?

If you need assistance, my TAs and I are always available via email!
