# CONFIDENTIAL-2: Do not log highly sensitive information
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Some information, such as Social Security numbers (SSNs) and passwords, is highly sensitive. This information should not be kept for longer than necessary nor where it may be seen, even by administrators. For instance, it should not be sent to log files and its presence should not be detectable through searches. Some transient data may be kept in mutable data structures, such as char arrays, and cleared immediately after use. Clearing data structures has reduced effectiveness on typical Java runtime systems as objects are moved in memory transparently to the programmer.

This guideline also has implications for implementation and use of lower-level libraries that do not have semantic knowledge of the data they are dealing with. As an example, a low-level string parsing library may log the text it works on. An application may parse an SSN with the library. This creates a situation where the SSNs are available to administrators with access to the log files.

## Simple example

![Author](https://img.shields.io/badge/Author-Jasper.Maes-blue.svg)
![Date](https://img.shields.io/badge/Date-20171226-lightgrey.svg)

The ```Example``` class has 2 methods that each transform a ```PaymentInformation``` object to a String for logging.

The ```unsafe``` method simply lists all fields with their values.

The ```safe``` method replaces the VISA card number by a generic string that does not disclose any information. By using this version in the overridden version, it is ensured that everywhere the object is converted to a String, the VISA information is not disclosed (for example when using ```System.out.println```).

### Example output
```
Dec 26, 2017 3:26:54 PM Example run
SEVERE: PaymentInformation{date=2017-12-26, amount=100, visaCardNumber='__FILTERED__}
Dec 26, 2017 3:26:54 PM Example run
SEVERE: PaymentInformation{date=2017-12-26, amount=100, visaCardNumber='123-456789-01'}
```