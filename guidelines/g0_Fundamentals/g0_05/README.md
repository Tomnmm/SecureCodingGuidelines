# FUNDAMENTALS-5: Minimise the number of permission checks
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)
![Agree](https://img.shields.io/badge/AGREE-4-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Java is primarily an object-capability language. SecurityManager checks should be considered a last resort. Perform security checks at a few defined points and return an object (a capability) that client code retains so that no further permission checks are required.

# Why
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180113-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-PENDING-orange.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Why should you consider SecurityManager checks a last resort? Does this slow down you code drastically? Of course rechecking and rechecking is not very performance friendly but define any check as a last resort?


####Is this the check they are referring?
````java
    FilePermission perm = new FilePermission("/temp/testFile", "read");
    AccessController.checkPermission(perm);
````
