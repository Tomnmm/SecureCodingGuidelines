# FUNDAMENTALS-6: Encapsulate
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)
![Agree](https://img.shields.io/badge/AGREE-1-green.svg) 
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Allocate behaviors and provide succinct interfaces. Fields of objects should be private and accessors avoided. The interface of a method, class, package, and module should form a coherent set of behaviors, and no more.

# Attack using Simple Java Code
![Author](https://img.shields.io/badge/Author-Bart.Devriendt-blue.svg)
![Date](https://img.shields.io/badge/Date-20171001-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-6-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

The class ```be.howest.ti.secure.development.g0.g06.domain.Carpass``` keeps a public (non-encapsulated) field for both the VIN number and the mileage.  
 
        public class Carpass {
            public String vinNumber;
            public BigDecimal mileage;
        }

This allows an attacker to modify the fields from outside.  Better is to make these ```private``` and, if possible, 
```final```:

        public class Carpass {
            private final String vinNumber;
            private final BigDecimal mileage;
        }
![Author](https://img.shields.io/badge/Author-Sven.Meuleman-blue.svg)
![Date](https://img.shields.io/badge/Date-20171223-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Move the source code to the apporiate folder structure, package could not compile without correct folder tree.

# Difference between private and final explained
![Author](https://img.shields.io/badge/Author-Sven.Meuleman-blue.svg)
![Date](https://img.shields.io/badge/Date-20171224-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

![Source](https://img.shields.io/badge/Source-Ernest.Friedman--Hill-orange.svg)
The purpose of "private" is to promote encapsulation. Member variables should almost always be private; class A should not be trying to touch the member data of class B.

"final" has a few different meanings. Applied to a variable, it prevents changes to the variable after initialization, making it a "constant." Applied to a method, it does, indeed, prevent overriding. Applied to a class, it prevents the class from being extended. All of these meanings have something in common: they allow the programmer to have fairly strong beliefs about the value or purpose of something. If you know a class can never be extended, then you know for sure how an instance of the class will behave -- you know you're not dealing with a subclass that changes something, since you know no subclasses can exist. This helps with both engineering and security.
[coderanch](https://coderanch.com/t/404696/java/Difference-Private-Final)
