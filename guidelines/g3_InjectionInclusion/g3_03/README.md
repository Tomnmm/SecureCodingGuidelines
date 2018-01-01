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
