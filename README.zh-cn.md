# PreferenceCell 
[English](./README.md) | 简体中文

[![Download](https://img.shields.io/badge/Download-1.0-brightgreen)](https://bintray.com/beta/#/sunnylin/Maven/PreferenceCell?tab=overview)
[![License](https://img.shields.io/badge/License-Apache%202-brightgreen)](https://www.apache.org/licenses/LICENSE-2.0.html)

PreferenceCell是一个基于SharePreferences的轻量Android Preference工具库。利用PreferenceCell您只需1行代码便可实现快速安全地构建Preference数据的读写。当您需要频繁地读取某个Preference数据时，PreferenceCell的缓存功能还能使二次读取时直接绕开io，让速度得到飞跃性的提高。另外PreferenceCell还实现了枚举类型和Map关联等功能。

## 安装/初始化


1. 把下面的依赖设置添加到项目的dependencies里:
```
  implementation 'com.sunnylin2008:preferencecell:1.0'
```

  (如果需要修改库的代码或者不喜欢添加依赖，您可以直接把[PreferenceCell](https://github.com/SunnyLin2008/PreferenceCell/blob/master/preferencecell/src/main/java/com/sunnylin/preferencecell/) 文件夹直接添加到您的项目里.)

2. 如[example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/PreferenceManage.java)一样创建一个PreferenceManage管理类(如果不喜欢管理类或者只有一两个数据，可以自行选择不添加)，并参考下面代码定义好您需要用到的Preference数据。
```java
//Simple Type
public final static PreferenceCell<Boolean> BOOL_TYPE = new PreferenceCell<>(true); //a bool value preference 
public final static PreferenceCell<String> STRING_TYPE = new PreferenceCell<>(""); //a String value preference 
public final static PreferenceCell<Integer> INT_TYPE = new PreferenceCell<>(2); //a int value preference 
public final static PreferenceCell<ExampleEnum> ENUM_TYPE = new PreferenceCell<>(ExampleEnum.A); //a enum value that you declare and want to read and write to the preference.
//Map Type
public final static PreferenceMapCell<Integer, ExampleEnum> MAP_INTEGER_TYPE = new PreferenceMapCell<>(Integer.class, ExampleEnum.C); //a map enum value with int key preference 
public final static PreferenceMapCell<ExampleEnum,Integer> MAP_ENUM_TYPE = new PreferenceMapCell<>(ExampleEnum.class,0);//a map int value with enum key preference 
```
3. 参照如下代码在PreferenceManage中添加自动初始化指令（如需手动一个个初始化，可略过这一步）:
```java
static {
  PreferenceCellBase.init(ExampleApplication.getInstance(), PreferenceManage.class);
}
```
   (其中ExampleApplication是您需要自定义的一个类似[example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/ExampleApplication.java)的Application类。)

## 基本使用

### 写入
```java
//Simple Type
PreferenceManage.BOOL_TYPE.set(false);
PreferenceManage.STRING_TYPE.set("Change the string");
PreferenceManage.INT_TYPE.set(999);
PreferenceManage.ENUM_TYPE.set(ExampleEnum.B);
//Map Type
PreferenceManage.MAP_ENUM_TYPE.set(ExampleEnum.B,123);
PreferenceManage.MAP_INTEGER_TYPE.set(321, ExampleEnum.A]);
```
### 读取
```java
//Simple Type
Boolean boolType = PreferenceManage.BOOL_TYPE.get();
String stringType = PreferenceManage.STRING_TYPE.get();
Integer intType = PreferenceManage.INT_TYPE.get();
ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
//Map Type
PreferenceManage.MAP_ENUM_TYPE.get(ExampleEnum.B);
PreferenceManage.MAP_INTEGER_TYPE.get(321);
 ```
 ### 重置
 ```java
 //Simple Type
 PreferenceManage.BOOL_TYPE.reset();
 PreferenceManage.STRING_TYPE.reset();
 PreferenceManage.INT_TYPE.reset();
 PreferenceManage.ENUM_TYPE.reset();
 //Map Type
 PreferenceManage.MAP_ENUM_TYPE.reset(ExampleEnum.values());
 PreferenceManage.MAP_INTEGER_TYPE.reset(new Integer[]{[key range]});
  ```
  ### 开启/关闭缓存 (默认开启)
  ```java
  void enableCache(Boolean enableCache)
  ```
 ### 添加Key前缀 （可用于区分某些服务于特定功能的数据，一般不会用到）
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
