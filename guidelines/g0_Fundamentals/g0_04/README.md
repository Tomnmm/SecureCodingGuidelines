# FUNDAMENTALS-4: Establish trust boundaries

![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)
![Agree](https://img.shields.io/badge/AGREE-2-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

In order to ensure that a system is protected, it is necessary to establish trust boundaries. Data that crosses these boundaries should be sanitized and validated before use. Trust boundaries are also necessary to allow security audits to be performed efficiently. Code that ensures integrity of trust boundaries must itself be loaded in such a way that its own integrity is assured.

For instance, a web browser is outside of the system for a web server. Equally, a web server is outside of the system for a web browser. Therefore, web browser and server software should not rely upon the behavior of the other for security.

When auditing trust boundaries, there are some questions that should be kept in mind. Are the code and data used sufficiently trusted? Could a library be replaced with a malicious implementation? Is untrusted configuration data being used? Is code calling with lower privileges adequately protected against?

# Don't let untrusted code change your private fields
![Author](https://img.shields.io/badge/Author-Manu.DeWitte-blue.svg)
![Date](https://img.shields.io/badge/Date-20171020-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-YES-green.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)


For the Java fans. If your security manager does not limit the use of reflection, untrusted third-party code may be able 
to change your private fields. Class be.howest.ti.secure.development.g0.g04.OnePlusOneEqualsFour shows how you change 
the internal value of an Integer object.