# AI Meal Planner (献立管理アプリ)

AIを活用して1週間の献立計画と栄養バランス（PFC）の管理を支援するアプリケーションです。

## 1. 開発環境・技術スタック

### バックエンド
- **Runtime**: Java / Spring Boot 3.x
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA

### フロントエンド
- **Language**: Vanilla JavaScript (ES6+)
- **Styling**: Tailwind CSS (CDN/Utility-first)
- **Template Engine**: Thymeleaf (予定)
*※注: React等のSPAライブラリは使用せず、軽量かつ保守性の高いVanilla JS構成を採用しています。*

### 開発ツール
- **IDE**: Visual Studio Code
- **CLI**: Git Bash, GitHub CLI (`gh`)
- **Environment**: Windows (改行コード LF 固定設定)

## 2. プロジェクトの進捗状況（要件定義工程まで）

以下の作業を完了し、AI-Nativeな開発フローの土台を構築しました。

1.  **プロジェクト基盤の構築**:
    - AI-Native開発に適したディレクトリ構造（`docs`, `mocks`, `src`, `tests`, `.ai`）の作成。
    - 改行コード（LF）をプロジェクト全体で統一するための `.gitattributes` の設定。
2.  **モック・プロトタイプ作成**:
    - 献立データの基本構造を定義した `meal-plan.json` の作成。
    - UIの骨組みを定義したワイヤーフレーム (`wireframe.md`) の作成。
    - Vanilla JS による動的な週間ビュー・プロトタイプの作成。
3.  **アーキテクチャ設計**:
    - DDD（ドメイン駆動設計）に基づくドメイン定義（Entity, Value Object）の作成。
    - PostgreSQL 用のテーブル定義 DDL (`table_definition.sql`) の作成。
4.  **要件定義の確定**:
    - バックエンド（Spring Boot）とフロントエンド（Vanilla JS）の役割を明確にした「要件定義書 v2.0」の作成。
5.  **タスク・Issue管理の自動化**:
    - セルフレビューによる課題抽出と、GitHub CLI を用いた一括 Issue 登録スクリプトの作成。

## 3. ディレクトリ構成

```text
├── .ai/                # AIへの指示書・ログ管理
├── docs/               # 要件定義・アーキテクチャ設計・Issue管理
├── mocks/              # プロトタイプ、ワイヤーフレーム、モックデータ
├── src/                # 実装コード（今後開発）
├── scripts/            # 開発支援スクリプト
└── README.md           # プロジェクト概要（本ファイル）
```

## 4. 今後の予定
- Phase 1: Spring Boot Entity および Repository 層の実装。