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
 * _**General Utilities**_<br> 
    Verious methods for validation like checking object as null, checking string as null, checking a valid mobile or 
    email etc.
    To check any String is null or not
    ```java
    AndroidUtils.isNullString(txtString);
    ```
    To check any object is null or not    
    ```java
    AndroidUtils.isNull(object);
    ```
    To check any email is valid or not    
    ```java
    AndroUtils.isValidMail(emailString);
    ```
    And so on.
    
 
 * _**Preference Utilities**_<br> 
      All the methods to Insert and Retrieve values from Shared Preferances including String, Int, Float, Long, Boolean,    
      Set &#60;Strings&#62;  and List &#60;E&#62;.<br>     
      Use Methods to add data in Shared Preferences<br>      
      
      ```java
      AndroUtils.Prefs.addString(key,value);
      //OR
      String value = AndroUtils.Prefs.getString(key);
      ``` 
      
* _**Activity Utilities**_<br> 
     Methods to start activity with or without data<br>
     To send list to an activity<br>
     
     ```java
     AndroUtils.Activity.startActivityWithList(Activity.this, list);
     ```
     To start activity for result
     
     ```java
     AndroUtils.Activity.startActivityForResult(FirstActivity, SecondActivity, requestCode);
     ```

* _**Dialog Utilities**_<br> 
     Methods to dialogs on the screen<br>
     ```java
     AndroUtils.DialogUtils.showProgressDialog(context,"Please wait",false); 
     // false is for cancelable attribute of dialog
     ```
### Contiribute
Plese give your precious contribution for this project.

