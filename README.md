# JecAndroidJavaTemplate

Java ベースの Android アプリ テンプレートです。最小限の構成で、UI とテストの雛形を含みます。

## 必要環境
- Android Studio Panda 1（2025.3.1）以降 または Gradle 実行環境
- JDK 11
- Android SDK（minSdk 30 / compileSdk 36 / targetSdk 36）
- Android Gradle Plugin 8.13.2

## セットアップ
1. このリポジトリを取得
2. Android Studio で `JecAndroidJavaTemplate` を開く
3. SDK パスは `local.properties` に自動生成されます

## 主要コマンド
```sh
./gradlew assembleDebug
```
デバッグ APK を生成します。

```sh
./gradlew installDebug
```
接続端末へインストールします。

```sh
./gradlew test
```
JVM ユニットテストを実行します。

```sh
./gradlew connectedAndroidTest
```
計測テスト（実機/エミュレータ）を実行します。

```sh
./gradlew lint
```
Android Lint を実行します。

## プロジェクト構成
- `app/` : アプリ本体モジュール
- `app/src/main/java/` : Java ソース（例: `jp/ac/jec/cm0199/jecandroidjavatemplate/`）
- `app/src/main/res/` : レイアウト/画像/文字列などのリソース
- `app/src/test/` : JVM ユニットテスト
- `app/src/androidTest/` : 計測テスト

## 主要依存ライブラリ
- AndroidX AppCompat 1.7.1
- AndroidX Activity 1.12.2
- AndroidX ConstraintLayout 2.2.1
- Material Components 1.13.0
- JUnit 4.13.2 / AndroidX Test JUnit 1.3.0 / Espresso 3.7.0

## GitHub Actions（Claude Code Review）
`.github/workflows/claude-review.yml` を使うには、GitHub Secrets の設定が必要です。

設定手順:
1. GitHub のリポジトリ画面で `Settings` → `Secrets and variables` → `Actions` → `New repository secret`
2. Name: `ANTHROPIC_API_KEY`
3. Value: Anthropic の API キー

動作:
- PR 作成/更新で自動実行
- コメントで `@claude` を付けるとレビュー応答（Owner/Member/Collaborator のみ）

## 学習資料
- `TUTORIAL.md` : Android アプリ開発入門の授業用テキスト（演習問題付き）
- `SPEC.md` : アプリ仕様書

## 貢献
開発方針や規約は `AGENTS.md` を参照してください。

## ライセンス
未設定です。必要に応じて追加してください。
