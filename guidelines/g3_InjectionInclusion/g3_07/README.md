# INJECT-7: Disable HTML display in Swing components
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Many Swing pluggable look-and-feels interpret text in certain components starting with <html> as HTML. If the text is from an untrusted source, an adversary may craft the HTML such that other components appear to be present or to perform inclusion attacks.

To disable the HTML render feature, set the "html.disable" client property of each component to Boolean.TRUE (no other Boolean true instance will do).

        label.putClientProperty("html.disable", true);

## ExampleG3G07
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180115-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-PENDING-orange.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Very Quick and Simple Example of Swing with html rendering enabled and html rendering disabled.