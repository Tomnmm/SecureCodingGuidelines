# INJECT-4: Avoid any untrusted data on the command line
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


When creating new processes, do not place any untrusted data on the command line. Behavior is platform-specific, poorly documented, and frequently surprising. Malicious data may, for instance, cause a single argument to be interpreted as an option (typically a leading - on Unix or / on Windows) or as two separate arguments. Any data that needs to be passed to the new process should be passed either as encoded arguments (e.g., Base64), in a temporary file, or through a inherited channel.

## Bad practice example
![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20180101-lightgrey.svg)

This example illustrated a possible outcome of bad (or no) input validation when passing user input to the OS shell. It is in no way complete, there are numerous ways passing user input to a command can go horribly wrong.

An, unfortunately, fairly common practice is to read files based on user input (think the filename of an image or the likes). When that input is not sufficiently validated and sanitized, unpleasant things might happen.

In the example, a simple textfile is created that represents a valid file. The file is then concatenated and printed on the standard output (I know, this should be done using pure java instead of a system call, but it is an example of bad coding for a reason).

When giving the user control over the parameters to cat (the filename in this case), he/she can try to access other than intended files.

`safeExecCmd` validates input using a fairly strict regular expression. If the argument doesn't match the pattern, the command isn't executed.

### NOTE
> **This example is written for a linux environment. A windows user that knows what he/she is doing can update it for that OS if wanted.**

### Bad practice example
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180124-lightgrey.svg)

Added a Windows Executable command, and an alternative validation methode.

I've also commented Robin's Linux commands, just to be able to run on Windows. Be free to "uncomment" this :)