# FUNDAMENTALS-6: Encapsulate
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)

Allocate behaviors and provide succinct interfaces. Fields of objects should be private and accessors avoided. The interface of a method, class, package, and module should form a coherent set of behaviors, and no more.

# Attack using Simple Java Code
![Author](https://img.shields.io/badge/Author-Bart.Devriendt-blue.svg)
![Date](https://img.shields.io/badge/Date-20171001-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-4-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

The class ```be.howest.ti.secure.development.g0.g06.domain.Carpass``` keeps a public (non-encapsulated) field for both the VIN number and the mileage.  
 
        public class Carpass {
            public String vinNumber;
            public BigDecimal mileage;
        }

This allows an attacker to modify the fields from outside.  Better is to make these private and final:

        public class Carpass {
            private final String vinNumber;
            private final BigDecimal mileage;
        }
