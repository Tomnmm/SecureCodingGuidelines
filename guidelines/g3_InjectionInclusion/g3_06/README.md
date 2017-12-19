# INJECT-6: Care with BMP files
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


BMP images files may contain references to local ICC (International Color Consortium) files. Whilst the contents of ICC files is unlikely to be interesting, the act of attempting to read files may be an issue. Either avoid BMP files, or reduce privileges as Guideline [9-2](../../g9_AccessControl/g9_02).

## Why avoid BMP files?

![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20171130-lightgrey.svg)

This guideline seems to be a response to [vulnerability CVE-2007-2789](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2007-2789), a (currently) 10 year old vulnerability in 
java 1
.5. This 
vulnerability allowed an attacker to craft a BMP file which, when opened on a unix/linux system, trick java into open arbitrary files.

I don't currently see any reason to avoid BMP files. The problem was fixed years ago, nobody should be developing code that will run on an ancient java version like that.

If anyone has any other (valid) arguments to avoid BMP files, feel free to add them.


![Author](https://img.shields.io/badge/Author-Mattias_De_Wael-green.svg)
![Date](https://img.shields.io/badge/Date-20171206-lightgrey.svg)

You might be on to something, I am currently taking this up with Oracle.