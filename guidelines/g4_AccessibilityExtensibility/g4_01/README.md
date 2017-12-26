# EXTEND-1: Limit the accessibility of classes, interfaces, methods, and fields
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


A Java package comprises a grouping of related Java classes and interfaces. Declare any class or interface public if it is specified as part of a published API, otherwise, declare it package-private. Similarly, declare class members and constructors (nested classes, methods, or fields) public or protected as appropriate, if they are also part of the API. Otherwise, declare them private or package-private to avoid exposing the implementation. Note that members of interfaces are implicitly public.

Classes loaded by different loaders do not have package-private access to one another even if they have the same package name. Classes in the same package loaded by the same class loader must either share the same code signing certificate or not have a certificate at all. In the Java virtual machine class loaders are responsible for defining packages. It is recommended that, as a matter of course, packages are marked as sealed in the jar file manifest.

![Author](https://img.shields.io/badge/Author-Sven.Meuleman-blue.svg)
![Date](https://img.shields.io/badge/Date-20171226-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

## Explanation about who can use what from whom
![inheritence](img/inheritence.PNG)  
Explanations
* A private member is only accessible within the same class as it is declared.
* A member with no access modifier is only accessible within classes in the same package.
* A protected member is accessible within all classes in the same package and within subclasses in other packages.
* A public member is accessible to all classes (unless it resides in a module that does not export the package it is declared in).