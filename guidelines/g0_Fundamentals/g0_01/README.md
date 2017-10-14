# FUNDAMENTALS-1: Design APIs to avoid security concerns

![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)
![Author](https://img.shields.io/badge/Author-Bart.Devriendt-blue.svg)
![Date](https://img.shields.io/badge/Date-20171001-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-6-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

It is better to design APIs with security in mind. Trying to retrofit security into an existing API is more difficult and error prone. For example, making a class final prevents a malicious subclass from adding finalizers, cloning, and overriding random methods (Guideline 4-5). Any use of the SecurityManager highlights an area that should be scrutinized.


