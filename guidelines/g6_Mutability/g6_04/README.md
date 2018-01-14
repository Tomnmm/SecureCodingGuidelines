# MUTABLE-4: Support copy functionality for a mutable class
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


When designing a mutable value class, provide a means to create safe copies of its instances. This allows instances of that class to be safely passed to or returned from methods in other classes (see Guideline 6-2 and Guideline 6-3). This functionality may be provided by a static creation method, a copy constructor, or by implementing a public copy method (for final classes).

If a class is final and does not provide an accessible method for acquiring a copy of it, callers could resort to performing a manual copy. This involves retrieving state from an instance of that class and then creating a new instance with the retrieved state. Mutable state retrieved during this process must likewise be copied if necessary. Performing such a manual copy can be fragile. If the class evolves to include additional state, then manual copies may not include that state.

The java.lang.Cloneable mechanism is problematic and should not be used. Implementing classes must explicitly copy all mutable fields which is highly error-prone. Copied fields may not be final. The clone object may become available before field copying has completed, possibly at some intermediate stage. In non-final classes Object.clone will make a new instance of the potentially malicious subclass. Implementing Cloneable is an implementation detail, but appears in the public interface of the class.


## Example

![Author](https://img.shields.io/badge/Author-Robin.Peiremans-blue.svg)
![Date](https://img.shields.io/badge/Date-20171226-lightgrey.svg)

The ```Person``` class provides 3 ways to copy ```Person``` objects:

* A classic copy constructor
* A static copy method
* A normal copy method

Both copy methods use the copy constructor internally, only how you call them is different.

The example creates a ```Person``` object, then creates a copy using each way mentioned above. Then the name field of each copy is changed and printed to show the changed in each object, proving the objects are in fact copies.

### Problem with the Example 
![Author](https://img.shields.io/badge/Author-Ben-blue.svg)
![Date](https://img.shields.io/badge/Date-20180114-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg) 
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg) 

Depending on what you want, the coping methods of the class `Person` can cause problems.  

In the example, when you change the name field of a copied object, only that specific name fields changes. The name fields of the other objects won't change.
This happens because the name field is of the class `String` and they are *immutable*.

The birthday field of the class `calendar` is *mutable*. This can cause a problem if you are not careful.   

**Extra examples with changing birthday field:**  
* In example `Example_Only_One_Date_Changes` the birthday field behaves as expected. We replace the birthday field with a new date. When 1 birthday field changes, the other birthday fields do not change.
* In example `Example_Dates_of_All_Objects_Changes` the birthday field does not behaves as expected. We change the birthday field using his method. When 1 birthday field changes, the other birthday fields change as well.
