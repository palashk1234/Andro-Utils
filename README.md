# Andro-Utils
Useful utility classes and methods to use in android app development.
(Under Construction)

### Dependancy
  [Gson](https://github.com/google/gson)

### Usage
  Copy the class in your project and thats it.<br>
  Just Initialize the AndroUtils in Application class like this.<br>
  ```java
  AndroUtils.init(getApplicationContext());
  ```
  
### Andro-Utils Includes 
 * _**Preference Utilities**_<br> 
      All the methods to Insert and Retrieve values from Shared Preferances including String, Int, Float, Long, Boolean,    
      Set &#60;Strings&#62;  and List &#60;E&#62;.<br>     
      Use Methods to add data in Shared Preferences<br>      
      
      ```java
      AndroUtils.Prefs.addString(key,value);
      //OR
      String value = AndroUtils.Prefs.getString(key);
      ``` 
 

### Contiribute
Plese give your precious contribution for this project.

