# INJECT-3: XML and HTML generation requires care
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Untrusted data should be properly sanitized before being included in HTML or XML output. Failure to properly sanitize the data can lead to many different security problems, such as Cross-Site Scripting (XSS) and XML Injection vulnerabilities. It is important to be particularly careful when using Java Server Pages (JSP).

There are many different ways to sanitize data before including it in output. Characters that are problematic for the specific type of output can be filtered, escaped, or encoded. Alternatively, characters that are known to be safe can be allowed, and everything else can be filtered, escaped, or encoded. This latter approach is preferable, as it does not require identifying and enumerating all characters that could potentially cause problems.

Implementing correct data sanitization and encoding can be tricky and error-prone. Therefore, it is better to use a library to perform these tasks during HTML or XML construction.

## Use HTML templates
![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20180101-lightgrey.svg)

Using HTML templating engines eliminates this risk and has the added benefit of separating the markup and the functional code.

## Use a proper XML engine
![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20180101-lightgrey.svg)

Java has the DocumentBuilder class to create XML documents. Use this instead of writing the tags by hand.
An example can be found [here](http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/).

## Simple XML injection Attack
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180124-lightgrey.svg)

An application allows the user to specif the quantity of an item available, generates the following XML
````XML
<item>
    <description>Item1</description>
    <price>500.0</price>
    <quantity>1</quantity>
</item>
````
The Attacker could enter the following String as quantity.
````XML
1</quantity><price>1.0</price><quantity>1
````
This would result in the following
````XML
<item>
    <description>Item1</description>
    <price>500.0</price>
    <quantity>1</quantity>
    <price>1.0</price>
    <quantity>1</quantity>
</item>
````
There is a possibility an XML parser may interpret the XML in this example such that the seconnd field overrides the first, changing the price to 1.0.
This could possibly  be injected as in a comment field.

####Fix
Use input validation, 
````Java
int count = Integer.parseUnsignedInt(quantity);
````
and/or validate using Document Type Definition or Schema.

[source](https://wiki.sei.cmu.edu/confluence/display/java/IDS16-J.+Prevent+XML+Injection)