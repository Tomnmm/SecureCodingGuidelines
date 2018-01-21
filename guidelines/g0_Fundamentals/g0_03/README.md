# FUNDAMENTALS-3: Restrict privileges ![Author](https://img.shields.io/badge/Author-Oracle-blue.svg) ![Agree](https://img.shields.io/badge/AGREE-4-green.svg) ![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Despite best efforts, not all coding flaws will be eliminated even in well reviewed code. However, if the code is operating with reduced privileges, then exploitation of any flaws is likely to be thwarted. The most extreme form of this is known as the principle of least privilege. Using the Java security mechanism this can be implemented statically by restricting permissions through policy files and dynamically with the use of the java.security.AccessController.doPrivileged mechanism (see section [9](../../g9_AccessControl)).

Rich Internet Applications (RIA) can specify their requested permissions via an applet parameter or in the JNLP. A signed jar can also include a manifest attribute that specifies whether it must run in a sandbox or with all permissions (see [11]). If a sandboxed applet or application attempts to execute security-sensitive code, the JRE will throw a security exception. RIAs should follow the principle of least privilege, and should be configured to run with the least amount of necessary permissions. Running a RIA with all permissions should be avoided whenever possible.

## ExampleG0G03
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180113-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-PENDING-orange.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Created an example code using SecurityManager (with custom java.policy) and a doPrivilege example.

### Split to privilegedClass
![Author](https://img.shields.io/badge/Author-Jürgen.Taverniers-blue.svg)
![Date](https://img.shields.io/badge/Date-20180121-lightgrey.svg)

Split privilegedAction to a different Class,
I do have some issues with fine tuning the policy file. Instead of only permit the privileged class I need to give permission to the complete source folder. 

Anyone has an idea? Probably just an error with my local setup of intelliJ.


I currently have to add following to the policy file:
````javaPolicyFile
grant codeBase "file:${user.home}${/}IdeaProjects/SecureCodingGuidelines/out/production/SecureCodingGuidelines/" { 
    permission java.util.PropertyPermission "java.home", "read";
};
````
this works because the whole stack has permission, and I would like to have something like:
````javaPolicyFile
grant codeBase "file:${user.home}${/}IdeaProjects/SecureCodingGuidelines/out/production/SecureCodingGuidelines/be/howest/ti/secure/development/g0/g03/priviledged/" { 
    permission java.util.PropertyPermission "java.home", "read";
};
````    