# PreferenceCell (previously [SYPreferenceUtils](https://github.com/SunnyLin2008/SYPreferenceUtils))

[![License](https://img.shields.io/badge/License-Apache%202-brightgreen)](https://www.apache.org/licenses/LICENSE-2.0.html)

PreferenceCell is a simple Android Preference helper library base on SharePreferences, which can help you to declare, write or read prefernce data in only one line. With the PreferenceCell which can use the cache, went you will read data frequently, it can also substantially improved the speed of read data. You can even save the enum to SharePreferences with PreferenceCell.


## Setup


1. Including in your project  with :
    ```
    implementation 'com.sunnylin2008:preferencecell:1.0'
    ```
    (Also you can only copy the [PreferenceCell](https://github.com/SunnyLin2008/PreferenceCell/blob/master/preferencecell/src/main/java/com/sunnylin/preferencecell/) folder into your project.
2. Create a PreferenceManage class like [example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/PreferenceManage.java) and preference fields with the data type what will be write and read.
```java
// Simple Type
public final static PreferenceCell<Boolean> BOOL_TYPE = new PreferenceCell<>(true); //a bool value preference 
public final static PreferenceCell<String> STRING_TYPE = new PreferenceCell<>(""); //a String value preference 
public final static PreferenceCell<Integer> INT_TYPE = new PreferenceCell<>(2); //a int value preference 
public final static PreferenceCell<ExampleEnum> ENUM_TYPE = new PreferenceCell<>(ExampleEnum.A); //a enum value that you declare and want to read and write to the preference.
//Map Type
public final static PreferenceMapCell<Integer, ExampleEnum> MAP_INTEGER_TYPE = new PreferenceMapCell<>(Integer.class, ExampleEnum.C); //a map enum value with int key preference 
public final static PreferenceMapCell<ExampleEnum,Integer> MAP_ENUM_TYPE = new PreferenceMapCell<>(ExampleEnum.class,0);//a map int value with enum key preference 
```
3. Add initial code to the PreferenceManage class file under the fields you had declared before :
```java
static {
  PreferenceCellBase.init(ExampleApplication.getInstance(), PreferenceManage.class);
}
```
  ExampleApplication is your customer Application that like [example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/ExampleApplication.java)

## How to use

### Write
```java
// Simple Type
PreferenceManage.BOOL_TYPE.set(false);
PreferenceManage.STRING_TYPE.set("Change the string");
PreferenceManage.INT_TYPE.set(999);
PreferenceManage.ENUM_TYPE.set(ExampleEnum.B);
//Map Type
PreferenceManage.MAP_ENUM_TYPE.set(ExampleEnum.B,123);
PreferenceManage.MAP_INTEGER_TYPE.set(321, ExampleEnum.A]);
```
### Read
```java
// Simple Type
Boolean boolType = PreferenceManage.BOOL_TYPE.get();
String stringType = PreferenceManage.STRING_TYPE.get();
int intType = PreferenceManage.INT_TYPE.get();
ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
// Map Type
PreferenceManage.MAP_ENUM_TYPE.get(ExampleEnum.B);
PreferenceManage.MAP_INTEGER_TYPE.get(321);
 ```
 ### Reset
 ```java
 // Simple Type
 PreferenceManage.BOOL_TYPE.reset();
 PreferenceManage.STRING_TYPE.reset();
 PreferenceManage.INT_TYPE.reset();
 PreferenceManage.ENUM_TYPE.reset();
 // Map Type
 PreferenceManage.MAP_ENUM_TYPE.reset(ExampleEnum.values());
 PreferenceManage.MAP_INTEGER_TYPE.reset(new Integer[]{[key range]});
  ```
  ### Enble/Disable Cache (enable default)
  ```java
  void enableCache(Boolean enableCache)
  ```
 ### Use with prefix
 ```java
 PreferenceCellBase.setPrefix("Your prefix");
 ```

## License

```
Copyright (C) 2017 Sunny Lin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
