# CONFIDENTIAL-1: Purge sensitive information from exceptions
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Exception objects may convey sensitive information. For example, if a method calls the java.io.FileInputStream constructor to read an underlying configuration file and that file is not present, a java.io.FileNotFoundException containing the file path is thrown. Propagating this exception back to the method caller exposes the layout of the file system. Many forms of attack require knowing or guessing locations of files.

Exposing a file path containing the current user's name or home directory exacerbates the problem. SecurityManager checks guard this information when it is included in standard system properties (such as user.home) and revealing it in exception messages effectively allows these checks to be bypassed.

Internal exceptions should be caught and sanitized before propagating them to upstream callers. The type of an exception may reveal sensitive information, even if the message has been removed. For instance, FileNotFoundException reveals whether or not a given file exists.

It is sometimes also necessary to sanitize exceptions containing information derived from caller inputs. For example, exceptions related to file access could disclose whether a file exists. An attacker may be able gather useful information by providing various file names as input and analyzing the resulting exceptions.

Be careful when depending on an exception for security because its contents may change in the future. Suppose a previous version of a library did not include a potentially sensitive piece of information in the exception, and an existing client relied upon that for security. For example, a library may throw an exception without a message. An application programmer may look at this behavior and decide that it is okay to propagate the exception. However, a later version of the library may add extra debugging information to the exception message. The application exposes this additional information, even though the application code itself may not have changed. Only include known, acceptable information from an exception rather than filtering out some elements of the exception.

Exceptions may also include sensitive information about the configuration and internals of the system. Do not pass exception information to end users unless one knows exactly what it contains. For example, do not include exception stack traces inside HTML comments.

## Simple example

![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20171111-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-YES-green.svg)


The ```Example``` class has 2 methods that try to open a non-existing file.

The ```safe``` method catches the ```FileNotFoundException``` and returns a generic error to the caller. This prevents an attacker from finding out the filesystem layout.

The ```unsafe``` version catches the error and sends it to the caller unmodified. This could allow an attacker to check for file existence and provide the filename that should have been opened.

While the ```safe``` method is safe in the sense that it will not divulge any potentially useful information to an attacker, it will make debugging the code significantly harder.
