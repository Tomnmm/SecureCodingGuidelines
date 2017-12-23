# FUNDAMENTALS-7: Document security-related information
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)
![Agree](https://img.shields.io/badge/AGREE-4-green.svg) 
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

API documentation should cover security-related information such as required permissions, security-related exceptions, caller sensitivity (see Guidelines [9-8](../../g9_AccessControl/g9_08) through [9-11](../../g9_AccessControl/g9_11) for additional on this topic), and any preconditions or postconditions that are relevant to security. Documenting this information in comments for a tool such as JavaDoc can also help to ensure that it is kept up to date.

![Author](https://img.shields.io/badge/Author-Sven.Meuleman-blue.svg)
![Date](https://img.shields.io/badge/Date-20171223-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)
Documentation should containing this information should be handled as sensitive data, only distributed to developers and customers. Have this information out in the wild could give attackers and advantage.
