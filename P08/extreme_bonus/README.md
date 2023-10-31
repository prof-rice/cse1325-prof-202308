The caps.h file is empty, and exists only to meet the Makefile dependency

``*.h``

Normally, this ensures that if any header file is modified, the entire project
is rebuilt. Thus, the Makefile need not reflect which header files are included
in which body files.
