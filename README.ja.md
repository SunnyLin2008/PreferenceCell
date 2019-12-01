# PreferenceCell 
[English](./README.md) | [简体中文](./README.zh-cn.md) | 日本語

[![Download](https://img.shields.io/badge/Download-1.1-brightgreen)](https://bintray.com/sunnylin/Maven/PreferenceCell/1.1/link)
[![License](https://img.shields.io/badge/License-Apache%202-brightgreen)](https://www.apache.org/licenses/LICENSE-2.0.html)

PreferenceCellはSharePreferencesをベースにした軽量なAndroid Preferenceライブラリーです。PreferenceCellを使えば、今まで面倒なPreference読み書きメソッドの定義もいらなくなり、より安全、快速、綺麗な書き方でPreferenceデータの構築をコード１行だけで実現できる。
更に、データーキャッシュ保存機能を用いのPreferenceCellは、頻繁的なPreferenceデーター読み込み処理にはパフォーマンス上昇させる効果が抜群です。

## インストール
下記のビルド依存関係コードをルートフォルダーのbuild.gradleファイルに追加する:
```
  implementation 'com.sunnylin2008:preferencecell:1.1'
```

  (もしライブラリーソースの変更が必要であれば、[PreferenceCell](https://github.com/SunnyLin2008/PreferenceCell/blob/master/preferencecell/src/main/java/com/sunnylin/preferencecell/)フォルダーをあなたのプロジェクトにそのまま追加しても良い。)

## 初期化
1. [example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/PreferenceManage.java)の様にPreferenceManage管理クラスを作成する上で、下記の様にPreferenceデータを定義する。
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
2. 下記の様なデータ初期化コードをPreferenceManageに追加する:
```java
static {
  PreferenceCellBase.init(ExampleApplication.getInstance(), PreferenceManage.class);
}
```
   (ExampleApplicationは[example](https://github.com/SunnyLin2008/PreferenceCell/blob/master/sample/src/main/java/com/example/sunnylin/preferencecell/ExampleApplication.java)様なカスタマイズアプリケーションクラスです)

## 基本的な使い方

### 書き込み
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
### 読み込み
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
 ### リセット
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
  ### キャッシュのオン・オフ (デフォルト値はオン)
  ```java
  void enableCache(Boolean enableCache)
  ```
 ### キーのプレフィックス追加
 ```java
 PreferenceCellBase.setPrefix("Your prefix");
 ```

## ライセンス

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
