# Android アプリ開発入門

Java ベースの Android アプリ開発を学ぶための授業用テキストです。
このプロジェクトでは、カウンターアプリを題材に Activity とレイアウト、クリック処理を学びます。

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
シンプルなカウンターアプリを題材に、UI 部品の配置とクリック処理、状態更新の流れを理解しましょう。

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
- Button/TextView のクリック処理を実装できるようになる
- アプリのビルドと実行ができるようになる

---

## 2. プロジェクト構成

### 2.1 ディレクトリ構造

```
CounterApp/
├── app/                          # アプリモジュール
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/             # Java ソースコード
│   │   │   │   └── jp/ac/jec/cm0199/jecandroidjavatemplate/
│   │   │   │       └── MainActivity.java
│   │   │   ├── res/              # リソースファイル
│   │   │   │   ├── layout/       # レイアウト XML
│   │   │   │   ├── values/       # 文字列・色・テーマ
│   │   │   │   ├── drawable/     # 画像・図形
│   │   │   │   └── mipmap/       # アプリアイコン
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                 # 単体テスト
│   │   └── androidTest/          # 計測テスト
│   └── build.gradle.kts          # アプリのビルド設定
├── gradle/
│   └── libs.versions.toml        # 依存ライブラリのバージョン管理
├── build.gradle.kts              # プロジェクト全体のビルド設定
└── settings.gradle.kts           # プロジェクト設定
```

### 2.2 主要ファイルの役割

| ファイル | 役割 |
|---------|------|
| `MainActivity.java` | カウンター画面を制御する Activity |
| `activity_main.xml` | カウンター画面のレイアウト定義 |
| `AndroidManifest.xml` | アプリの基本情報と構成を宣言 |
| `strings.xml` | 文字列リソース（多言語対応用） |
| `colors.xml` | UI カラーの定義 |
| `build.gradle.kts` | ビルド設定と依存ライブラリ |

---

## 3. MainActivity.java の解説

### 3.1 ソースコード全体

```java
package jp.ac.jec.cm0199.jecandroidjavatemplate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView txtCount;

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

        txtCount = findViewById(R.id.txt_count);
        Button btnIncrement = findViewById(R.id.btn_increment);
        Button btnDecrement = findViewById(R.id.btn_decrement);
        TextView btnReset = findViewById(R.id.btn_reset);

        btnIncrement.setOnClickListener(v -> {
            count++;
            updateCountDisplay();
        });

        btnDecrement.setOnClickListener(v -> {
            count--;
            updateCountDisplay();
        });

        btnReset.setOnClickListener(v -> {
            count = 0;
            updateCountDisplay();
        });
    }

    private void updateCountDisplay() {
        txtCount.setText(String.valueOf(count));
    }
}
```

### 3.2 パッケージ宣言

```java
package jp.ac.jec.cm0199.jecandroidjavatemplate;
```

- Java クラスが属するパッケージを宣言します
- パッケージ名はアプリを一意に識別するための ID となります

### 3.3 import 文

```java
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
```

| クラス | 役割 |
|--------|------|
| `Bundle` | Activity の状態保存に使用 |
| `Button` | クリック可能なボタン UI |
| `TextView` | テキスト表示 UI |
| `EdgeToEdge` | 画面端までコンテンツを表示する機能 |
| `AppCompatActivity` | 後方互換性のある Activity 基底クラス |
| `Insets` | 画面の余白情報を保持 |
| `ViewCompat` | View 操作の互換性ヘルパー |
| `WindowInsetsCompat` | システムバーの余白情報 |

### 3.4 フィールド

```java
private int count = 0;
private TextView txtCount;
```

- `count` は現在のカウント値を保持します
- `txtCount` は画面中央の数値表示に使用します

### 3.5 onCreate メソッド

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    // ...
}
```

- Activity が作成されたときに呼ばれるメソッドです
- `EdgeToEdge.enable()` でシステムバー領域まで表示を広げます
- `setContentView()` でレイアウトを読み込みます

### 3.6 View の取得

```java
txtCount = findViewById(R.id.txt_count);
Button btnIncrement = findViewById(R.id.btn_increment);
Button btnDecrement = findViewById(R.id.btn_decrement);
TextView btnReset = findViewById(R.id.btn_reset);
```

- XML に定義された View を Java から取得します
- Reset は TextView ですが、クリックイベントを付けてボタンのように扱っています

### 3.7 クリック処理

```java
btnIncrement.setOnClickListener(v -> {
    count++;
    updateCountDisplay();
});
```

- `setOnClickListener()` でクリック時の処理を登録します
- カウントを更新したら `updateCountDisplay()` を呼び、表示を同期します

### 3.8 表示更新メソッド

```java
private void updateCountDisplay() {
    txtCount.setText(String.valueOf(count));
}
```

- `count` を文字列化して `TextView` に反映します
- 表示更新の処理をメソッドに切り出すことで重複を避けます

---

## 4. レイアウトファイルの解説

### 4.1 activity_main.xml 全体

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/background_dark"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center_vertical"
        android:paddingHorizontal="16dp"
        android:paddingTop="8dp">

        <View
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_weight="1" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/app_name"
            android:textColor="@color/white"
            android:textSize="20sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/btn_reset"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="end"
            android:text="@string/reset"
            android:textColor="@color/green_primary"
            android:textSize="18sp"
            android:padding="8dp" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:orientation="vertical"
        android:gravity="center">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/current_count"
            android:textColor="@color/text_secondary"
            android:textSize="14sp"
            android:letterSpacing="0.2" />

        <TextView
            android:id="@+id/txt_count"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="0"
            android:textColor="@color/green_primary"
            android:textSize="120sp"
            android:textStyle="bold"
            android:fontFamily="sans-serif-black" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center"
        android:paddingHorizontal="32dp"
        android:paddingBottom="80dp">

        <Button
            android:id="@+id/btn_decrement"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:text="-"
            android:textSize="48sp"
            android:textColor="@color/green_primary"
            android:backgroundTint="@color/green_dark"
            android:layout_marginEnd="16dp" />

        <Button
            android:id="@+id/btn_increment"
            android:layout_width="0dp"
            android:layout_height="100dp"
            android:layout_weight="1"
            android:text="+"
            android:textSize="48sp"
            android:textColor="@color/background_dark"
            android:backgroundTint="@color/green_primary" />

    </LinearLayout>

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
xmlns:tools="http://schemas.android.com/tools"
```

| 名前空間 | 用途 |
|---------|------|
| `android:` | Android 標準の属性 |
| `tools:` | 開発時のみ使用する属性（実行時は無視） |

### 4.4 ルート LinearLayout

```xml
<LinearLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/background_dark">
```

| 属性 | 値 | 説明 |
|------|-----|------|
| `android:id` | `@+id/main` | インセット処理の対象 View |
| `android:orientation` | `vertical` | 子要素を縦に並べる |
| `android:background` | `@color/background_dark` | 画面全体の背景色 |

### 4.5 上部ヘッダー行

- 左右のスペースは `View` と `layout_weight` を使って確保しています
- `Reset` は右寄せの TextView として配置されています

### 4.6 中央のカウント表示

- "CURRENT COUNT" のラベルと数値を縦に並べています
- 数値は `textSize="120sp"` と `fontFamily="sans-serif-black"` で強調しています

### 4.7 下部のボタン行

- `-` は固定幅、`+` は `layout_weight` で伸縮
- `backgroundTint` でボタンの色を調整しています

### 4.8 layout_width / layout_height の値

| 値 | 説明 |
|----|------|
| `match_parent` | 親要素のサイズに合わせる |
| `wrap_content` | コンテンツのサイズに合わせる |
| `0dp` | 重み付け（`layout_weight`）と併用する |

---

## 5. リソースファイル

### 5.1 strings.xml

`res/values/strings.xml` にアプリで使用する文字列を定義します。

```xml
<resources>
    <string name="app_name">Counter</string>
    <string name="current_count">CURRENT COUNT</string>
    <string name="reset">Reset</string>
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
    <color name="background_dark">#0D1F0D</color>
    <color name="green_primary">#00FF41</color>
    <color name="green_dark">#1A3D1A</color>
    <color name="text_secondary">#80B080</color>
</resources>
```

**色の形式:** `#AARRGGBB`（AA: 透明度, RR: 赤, GG: 緑, BB: 青）

### 5.3 themes.xml

アプリ全体のテーマを定義します。

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Base.Theme.JecAndroidJavaTemplate" parent="Theme.Material3.DayNight.NoActionBar" />
    <style name="Theme.JecAndroidJavaTemplate" parent="Base.Theme.JecAndroidJavaTemplate" />
</resources>
```

### 5.4 AndroidManifest.xml

アプリの基本情報を宣言するファイルです。

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
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
| `android:dataExtractionRules` | 自動バックアップの抽出ルール |
| `android:fullBackupContent` | フルバックアップのルール |
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

### 演習 1: 起動時にカウントを表示する

`onCreate()` の最後で `updateCountDisplay()` を呼び、初期値を画面に反映してください。

<details>
<summary>解答例</summary>

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    // ... 省略 ...

    updateCountDisplay();
}
```

</details>

### 演習 2: 増減幅を 5 にする

`STEP` 定数を追加して、+/- の変化量を 5 に変更してください。

<details>
<summary>解答例</summary>

```java
private static final int STEP = 5;

btnIncrement.setOnClickListener(v -> {
    count += STEP;
    updateCountDisplay();
});

btnDecrement.setOnClickListener(v -> {
    count -= STEP;
    updateCountDisplay();
});
```

</details>

### 演習 3: 0 未満にならないようにする

減算時に 0 未満へ下がらないようガードしてください。

<details>
<summary>解答例</summary>

```java
btnDecrement.setOnClickListener(v -> {
    if (count > 0) {
        count--;
        updateCountDisplay();
    }
});
```

</details>

### 演習 4: 画面回転で値を保持する

`onSaveInstanceState()` を使ってカウントを保存・復元してください。

<details>
<summary>解答例</summary>

```java
import androidx.annotation.NonNull;

private static final String KEY_COUNT = "key_count";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null) {
        count = savedInstanceState.getInt(KEY_COUNT, 0);
    }

    // ... 省略 ...
    updateCountDisplay();
}

@Override
protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(KEY_COUNT, count);
}
```

</details>

---

## まとめ

このテキストでは以下を学びました:

- **プロジェクト構成**: Android アプリの基本的なディレクトリ構造
- **Activity**: 画面を制御するクラスと onCreate ライフサイクル
- **レイアウト XML**: LinearLayout と Button/TextView の基本
- **クリック処理**: カウントの増減と表示更新
- **ビルド**: アプリの実行方法

次のステップとして、保存処理や UI の改善を試してみましょう。
