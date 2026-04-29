# API 仕様書 (v1.0)

## 1. 共通仕様
- **Base URL**: `/api/v1`
- **Content-Type**: `application/json`
- **認証**: セッションベース (ID 11 参照)

## 2. エンドポイント
| メソッド | パス | 説明 |
|:---|:---|:---|
| POST | `/auth/login` | ログイン |
| GET | `/user/goals` | ユーザーの目標栄養素取得 |
| GET | `/meal-plans` | 期間指定での献立リスト取得 |
| POST | `/meal-plans` | 新規献立登録 |
| PUT | `/meal-plans/{id}` | 献立更新 |
| DELETE | `/meal-plans/{id}` | 献立削除 |

## 3. エラーレスポンス形式 (ID 13)
一貫性を保つため、以下のフォーマットで返却する。
```json
{
  "timestamp": "2026-04-29T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "バリデーションエラーが発生しました",
  "path": "/api/v1/meal-plans",
  "details": [
    {"field": "planDate", "message": "未来の日付は指定できません"}
  ]
}
```