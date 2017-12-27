# EXTEND-5: Limit the extensibility of classes and methods
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Design classes and methods for inheritance or declare them final [6]. Left non-final, a class or method can be maliciously overridden by an attacker. A class that does not permit subclassing is easier to implement and verify that it is secure. Prefer composition to inheritance.

```java
	// Unsubclassable class with composed behavior.
        public final class SensitiveClass {

            private final Behavior behavior;

            // Hide constructor.
            private SensitiveClass(Behavior behavior) {
                this.behavior = behavior;
            }

            // Guarded construction.
            public static SensitiveClass newSensitiveClass(
                Behavior behavior
            ) {
                // ... validate any arguments ...

                // ... perform security checks ...

                return new SensitiveClass(behavior);
            }
        }
```
Malicious subclasses that override the Object.finalize method can resurrect objects even if an exception was thrown from the constructor. Low-level classes with constructors explicitly throwing a java.security.SecurityException are likely to have security issues. From JDK6 on, an exception thrown before the java.lang.Object constructor exits which prevents the finalizer from being called. Therefore, if subclassing is allowed and security manager permission is required to construct an object, perform the check before calling the super constructor. This can be done by inserting a method call as an argument to an alternative ("this") constructor invocation.

```java
        public class NonFinal {

            // sole accessible constructor
            public NonFinal() {
                this(securityManagerCheck());
            }

            private NonFinal(Void ignored) {
                // ...
            }


            private static Void securityManagerCheck() {
                SecurityManager sm = System.getSecurityManager();
                if (sm != null) {
                    sm.checkPermission(...);
                }
                return null;
            }

        }
```
For compatibility with versions of Java prior to JDK 6, check that the class has been initialized before every sensitive operation and before trusting any other instance of the class. It may be possible to see a partially initialized instance, so any variable should have a safe interpretation for the default value. For mutable classes, it is advisable to make an "initialized" flag volatile to create a suitable happens-before relationship.

```java
        public class NonFinal {

            private volatile boolean initialized;

            // sole constructor
            public NonFinal() {
                securityManagerCheck();

                // ... initialize class ...

                // Last action of constructor.
                this.initialized = true;
            }

            public void doSomething() {
                checkInitialized();
            }

            private void checkInitialized() {
                if (!initialized) {
                    throw new SecurityException(
                        "NonFinal not initialized"
                    );
                }
            }
        }
```
When confirming an object's class type by examining the java.lang.Class instance belonging to that object, do not compare Class instances solely using class names (acquired via Class.getName), because instances are scoped both by their class name as well as the class loader that defined the class.


## Example of the finalizer attack

![Author](https://img.shields.io/badge/Author-Manu.DeWitte-blue.svg)
![Date](https://img.shields.io/badge/Date-20171101-lightgrey.svg)
![CHECKED BY LECTOR](https://img.shields.io/badge/CHECKED_BY_LECTOR-YES-green.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

The package ``be.howest.ti.secure.development.g4.g05.finalize`` contains two examples of the behaviour of the finalize method mentioned above. It shows the difference between throwing an exception in a constructor _before_ as opposed to _after_ the super constructor has run. In the latter case the ``finalize()`` method will be called, which opens a window for malicious subclasses to get access to the object that was being constructed.

The _insecure_ subpackage has the InsecureTrustedClass, which is extended by the ``UntrustedClass``. Run the ``InsecureTestApp`` and look at the output. Also take a look at the comments in the code. You should see that the ``finalize()`` method _is_ called in this example.

The _secure_ subpackage has the SecureTrustedClass, which is extended by the ``FutileUntrustedClass``. Run the ``SecureTestApp`` and look at the output. You should see that the ``finalize()`` method is _not_ called in this case.


## Impact on mocking frameworks for testing
![Author](https://img.shields.io/badge/Author-Jasper.Maes-blue.svg)
![Date](https://img.shields.io/badge/Date-20171227-lightgrey.svg)

Not all mocking frameworks can handle final classes. The implementation of this guideline thus also impacts the testing frameworks that can be used. A way around this limitation is to create a wrapper object that delegates all method calls to an instance of the final class but this requires a lot of boilerplate code. 

###Practical
The [FAQ](https://github.com/mockito/mockito/wiki/FAQ) on the website of Mockito lists what features each of their versions offers. Mockito v1.x.x cannot mock a final class. This feature was added in version 2.x.x.

The most recent version (3.5.1) of the Easymock framework cannot handle final classes (see [userguide](http://easymock.org/user-guide.html#mocking-limitations)). It produces the following error when mocking a final class:
```
java.lang.IllegalArgumentException: Cannot subclass final class class be.jaspmaes.model.InMemoryModel
   
   	at net.sf.cglib.proxy.Enhancer.generateClass(Enhancer.java:446)
   	at net.sf.cglib.core.DefaultGeneratorStrategy.generate(DefaultGeneratorStrategy.java:25)
   	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:216)
   	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:377)
   	at net.sf.cglib.proxy.Enhancer.createClass(Enhancer.java:317)
   	at org.easymock.internal.ClassProxyFactory.createProxy(ClassProxyFactory.java:175)
   	at org.easymock.internal.MocksControl.createMock(MocksControl.java:129)
   	at org.easymock.internal.MocksControl.createMock(MocksControl.java:103)
   	at org.easymock.internal.MocksControl.createMock(MocksControl.java:94)
   	at org.easymock.EasyMock.createMock(EasyMock.java:136)
   	at TestAssetCreationHandler.anAssetIsCorrectlyCreated(TestAssetCreationHandler.java:41)
   	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
   	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
   	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
   	at java.lang.reflect.Method.invoke(Method.java:483)
   	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
   	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
   	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
   	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    ....
   	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
   	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
   	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
   	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
   	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
  ```

*Note:* A Mock object is something used for unit testing. If you have an object whose methods you want to test, and those methods depend on some other object, you create a mock of the dependency rather than an actual instance of that dependency. This allows you to test your object in isolation. (from [StackOverflow](https://stackoverflow.com/a/2128153))