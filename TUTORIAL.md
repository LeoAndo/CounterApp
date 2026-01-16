# Android アプリ開発入門

Java ベースの Android アプリ開発を学ぶための授業用テキストです。
このテンプレートプロジェクトを通じて、Android アプリの基本構成を理解しましょう。

---

## 目次

1. [はじめに](#1-はじめに)
2. [プロジェクト構成](#2-プロジェクト構成)
3. [MainActivity.java の解説](#3-mainactivityjava-の解説)
4. [レイアウトファイルの解説](#4-レイアウトファイルの解説)
5. [リソースファイル](#5-リソースファイル)
6. [ビルドと実行](#6-ビルドと実行)
7. [演習問題](#7-演習問題)

---

## 1. はじめに

### 1.1 このテキストの目的

このテキストでは、Android Studio を使用して Java ベースの Android アプリを開発する基礎を学びます。
「Hello World!」を表示するシンプルなアプリを題材に、Android アプリの構成要素を理解しましょう。

### 1.2 開発環境

| 項目 | バージョン |
|------|-----------|
| Android Studio | Panda 1（2025.3.1）以降 |
| JDK | 11 |
| 最小 SDK | 30（Android 11） |
| ターゲット SDK | 36（Android 16） |

### 1.3 学習目標

- Android アプリのプロジェクト構成を理解する
- Activity の役割とライフサイクルを理解する
- XML レイアウトの基本を理解する
- アプリのビルドと実行ができるようになる

---

## 2. プロジェクト構成

### 2.1 ディレクトリ構造

```
JecAndroidJavaTemplate/
├── app/                          # アプリモジュール
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/            # Java ソースコード
│   │   │   │   └── jp/ac/jec/cm0199/jecandroidjavatemplate/
│   │   │   │       └── MainActivity.java
│   │   │   ├── res/             # リソースファイル
│   │   │   │   ├── layout/      # レイアウト XML
│   │   │   │   ├── values/      # 文字列・色・テーマ
│   │   │   │   ├── drawable/    # 画像・図形
│   │   │   │   └── mipmap/      # アプリアイコン
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                # 単体テスト
│   │   └── androidTest/         # 計測テスト
│   └── build.gradle.kts         # アプリのビルド設定
├── gradle/
│   └── libs.versions.toml       # 依存ライブラリのバージョン管理
├── build.gradle.kts             # プロジェクト全体のビルド設定
└── settings.gradle.kts          # プロジェクト設定
```

### 2.2 主要ファイルの役割

| ファイル | 役割 |
|---------|------|
| `MainActivity.java` | アプリのメイン画面を制御する Activity |
| `activity_main.xml` | メイン画面のレイアウト定義 |
| `AndroidManifest.xml` | アプリの基本情報と構成を宣言 |
| `strings.xml` | 文字列リソース（多言語対応用） |
| `build.gradle.kts` | ビルド設定と依存ライブラリ |

---

## 3. MainActivity.java の解説

### 3.1 ソースコード全体

```java
package jp.ac.jec.cm0199.jecandroidjavatemplate;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
```

### 3.2 パッケージ宣言

```java
package jp.ac.jec.cm0199.jecandroidjavatemplate;
```

- Java クラスが属するパッケージを宣言します
- パッケージ名はアプリを一意に識別するための ID となります
- 慣例として逆ドメイン形式（例: `jp.ac.jec.xxx`）を使用します

### 3.3 import 文

```java
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
```

| クラス | 役割 |
|--------|------|
| `Bundle` | Activity 間のデータ受け渡しや状態保存に使用 |
| `EdgeToEdge` | 画面端までコンテンツを表示する機能 |
| `AppCompatActivity` | 後方互換性のある Activity 基底クラス |
| `Insets` | 画面の余白情報を保持 |
| `ViewCompat` | View 操作の互換性ヘルパー |
| `WindowInsetsCompat` | システムバーの余白情報 |

### 3.4 クラス宣言

```java
public class MainActivity extends AppCompatActivity {
```

- `MainActivity` は `AppCompatActivity` を継承しています
- `AppCompatActivity` を使うことで、古い Android バージョンでも新しい機能が使えます

### 3.5 onCreate メソッド

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // ...
}
```

- Activity が作成されたときに呼ばれるメソッドです
- `@Override` は親クラスのメソッドを上書きすることを示します
- `super.onCreate()` で親クラスの初期化処理を実行します

### 3.6 EdgeToEdge の有効化

```java
EdgeToEdge.enable(this);
```

- 画面の端（ステータスバーやナビゲーションバーの下）までコンテンツを表示します
- モダンな Android アプリのデザインに対応します

### 3.7 レイアウトの設定

```java
setContentView(R.layout.activity_main);
```

- `res/layout/activity_main.xml` をこの Activity の画面として設定します
- `R` クラスはリソースへの参照を自動生成したクラスです

### 3.8 システムバーの余白処理

```java
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
```

- EdgeToEdge を有効にした際、コンテンツがシステムバーと重ならないよう余白を設定します
- `findViewById()` で XML 内の要素を取得します
- ラムダ式 `(v, insets) -> { ... }` でリスナーを簡潔に記述しています

---

## 4. レイアウトファイルの解説

### 4.1 activity_main.xml 全体

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />

</LinearLayout>
```

### 4.2 XML 宣言

```xml
<?xml version="1.0" encoding="utf-8"?>
```

- XML ファイルであることと文字エンコーディングを宣言します

### 4.3 名前空間（xmlns）

```xml
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
```

| 名前空間 | 用途 |
|---------|------|
| `android:` | Android 標準の属性 |
| `app:` | ライブラリ固有の属性 |
| `tools:` | 開発時のみ使用する属性（実行時は無視） |

### 4.4 LinearLayout

```xml
<LinearLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
```

| 属性 | 値 | 説明 |
|------|-----|------|
| `android:id` | `@+id/main` | この要素の識別子（`@+id/` は新規作成） |
| `android:layout_width` | `match_parent` | 親要素の幅いっぱいに広がる |
| `android:layout_height` | `match_parent` | 親要素の高さいっぱいに広がる |
| `android:orientation` | `vertical` | 子要素を縦方向に並べる |

### 4.5 TextView

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!" />
```

| 属性 | 値 | 説明 |
|------|-----|------|
| `android:layout_width` | `wrap_content` | テキストの幅に合わせる |
| `android:layout_height` | `wrap_content` | テキストの高さに合わせる |
| `android:text` | `Hello World!` | 表示するテキスト |

### 4.6 layout_width / layout_height の値

| 値 | 説明 |
|----|------|
| `match_parent` | 親要素のサイズに合わせる |
| `wrap_content` | コンテンツのサイズに合わせる |
| `100dp` | 固定サイズ（dp: density-independent pixels） |

---

## 5. リソースファイル

### 5.1 strings.xml

`res/values/strings.xml` にアプリで使用する文字列を定義します。

```xml
<resources>
    <string name="app_name">JecAndroidJavaTemplate</string>
</resources>
```

**文字列リソースを使う理由:**
- 多言語対応が容易になる
- 文字列の一元管理ができる
- コード内にハードコードしない

**使用方法:**
- XML 内: `@string/app_name`
- Java 内: `getString(R.string.app_name)`

### 5.2 colors.xml

`res/values/colors.xml` に色を定義します。

```xml
<resources>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

**色の形式:** `#AARRGGBB`（AA: 透明度, RR: 赤, GG: 緑, BB: 青）

### 5.3 AndroidManifest.xml

アプリの基本情報を宣言するファイルです。

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.JecAndroidJavaTemplate">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

| 要素/属性 | 説明 |
|---------|------|
| `<application>` | アプリ全体の設定 |
| `android:icon` | アプリアイコン |
| `android:label` | アプリ名 |
| `android:theme` | アプリのテーマ |
| `<activity>` | 画面（Activity）の宣言 |
| `android:exported="true"` | 外部からの起動を許可 |
| `intent-filter` | Activity の起動条件 |
| `action.MAIN` | メインエントリーポイント |
| `category.LAUNCHER` | ランチャーに表示 |

---

## 6. ビルドと実行

### 6.1 Android Studio での実行

1. Android Studio でプロジェクトを開く
2. エミュレータまたは実機を接続
3. **Run** ボタン（緑の三角）をクリック

### 6.2 コマンドラインでのビルド

```bash
# デバッグ APK を生成
./gradlew assembleDebug

# 接続端末にインストール
./gradlew installDebug

# ユニットテストを実行
./gradlew test

# Android Lint を実行
./gradlew lint
```

### 6.3 APK の出力場所

ビルドした APK は以下に出力されます:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 7. 演習問題

### 演習 1: テキストを変更する

`activity_main.xml` の TextView を編集して、表示されるテキストを「こんにちは！」に変更してください。

<details>
<summary>解答例</summary>

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="こんにちは！" />
```

</details>

### 演習 2: 文字列リソースを使用する

1. `strings.xml` に新しい文字列リソースを追加
2. TextView で `@string/` を使って参照

<details>
<summary>解答例</summary>

**strings.xml:**
```xml
<resources>
    <string name="app_name">JecAndroidJavaTemplate</string>
    <string name="greeting">こんにちは！</string>
</resources>
```

**activity_main.xml:**
```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/greeting" />
```

</details>

### 演習 3: 複数の TextView を追加する

LinearLayout 内に 3 つの TextView を追加し、それぞれ異なるテキストを表示してください。

<details>
<summary>解答例</summary>

```xml
<LinearLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1行目" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="2行目" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="3行目" />

</LinearLayout>
```

</details>

### 演習 4: TextView に ID を付けて Java から操作する

1. TextView に `android:id` を設定
2. MainActivity.java で `findViewById()` を使って TextView を取得
3. `setText()` メソッドでテキストを変更

<details>
<summary>解答例</summary>

**activity_main.xml:**
```xml
<TextView
    android:id="@+id/text_greeting"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!" />
```

**MainActivity.java:**
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    // TextView を取得してテキストを変更
    TextView textGreeting = findViewById(R.id.text_greeting);
    textGreeting.setText("Java から変更しました！");

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });
}
```

※ `import android.widget.TextView;` の追加が必要です。

</details>

---

## まとめ

このテキストでは以下を学びました:

- **プロジェクト構成**: Android アプリの基本的なディレクトリ構造
- **Activity**: 画面を制御するクラスと onCreate ライフサイクル
- **レイアウト XML**: LinearLayout と TextView の基本
- **リソース**: 文字列・色の管理方法
- **ビルド**: アプリの実行方法

次のステップとして、ボタンの追加やユーザー入力の処理を学んでいきましょう。
