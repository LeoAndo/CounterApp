# Repository Guidelines

この ドキュメント は この リポジトリ の 参加者 向け ガイド です。Android Java テンプレート として の 作業 を 迅速 に 始める ため、構成 と 基本 ルール を まとめます。

## プロジェクト構成とモジュール
- ルート は Gradle Kotlin DSL 構成 で、`settings.gradle.kts` と `build.gradle.kts` が 入口。
- アプリ 本体 は 単一 モジュール `app/`。Java ソース は `app/src/main/java/jp/ac/jec/cm0199/jecandroidjavatemplate/`。
- リソース は `app/src/main/res/`、マニフェスト は `app/src/main/AndroidManifest.xml`。
- 共有 設定 は `gradle/` と `gradle/libs.versions.toml` で 管理。

## ビルド・テスト・開発コマンド
- `./gradlew assembleDebug` : デバッグ APK を 生成。
- `./gradlew assembleRelease` : リリース APK を 生成（minify は 無効）。
- `./gradlew installDebug` : 接続 端末 に インストール。
- `./gradlew test` : JVM ユニット テスト（JUnit4）。
- `./gradlew connectedAndroidTest` : 実機 / エミュレータ 向け 計測 テスト。
- `./gradlew lint` : Android Lint を 実行。

## コーディング規約と命名
- Java 11 を 前提。インデント は 4 スペース、タブ 禁止。
- クラス は PascalCase、メソッド / 変数 は lowerCamelCase。
- リソース 名 は lower_snake_case。例: `activity_main.xml`, `@+id/btn_submit`。
- 画面実装 は View System を採用する。
- レイアウト は ConstraintLayout を 避け、必要 に 応じて LinearLayout など を 使用する。
- ロジックも含めて処理 は Activityにまとめる。
- EdgeToEdge を 有効 に し、システム バー の インセット を 処理する。
- ダークテーマは非対応。

## テスト方針
- 単体 テスト は `app/src/test/java/`、計測 テスト は `app/src/androidTest/java/`。
- 新規 修正 には 再現 テスト を 追加 し、`*Test.java` 命名 を 維持。
- UI 変更 は Espresso の 追加 を 検討。

## コミット & PR
- `.git` が 無いため 既存 ルール を 確認 できない。短い 命令形 + 変更 内容（例: "Add settings screen"）を 推奨。
- PR には 目的、変更 点、実行 した コマンド を 記載。UI 変更 は スクリーンショット を 添付。

## 設定・環境
- `local.properties` は SDK パス 用。個人 環境 の 値 は 共有 しない。
- 依存 バージョン 変更 は 影響 範囲 を 記録 し、`gradle/libs.versions.toml` を 更新。

## ドキュメント
- `README.md` : プロジェクト概要とセットアップ手順。
- `SPEC.md` : アプリ仕様書。画面一覧と技術仕様を記載。
- `TUTORIAL.md` : 授業用テキスト。Android アプリ開発入門の解説と演習問題。
