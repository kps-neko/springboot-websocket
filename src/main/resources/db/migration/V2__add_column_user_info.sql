-- user_infoテーブルにアクティベーション用のカラムを追加する。
ALTER TABLE user_info ADD COLUMN email TEXT;
ALTER TABLE user_info ADD COLUMN activate_token VARCHAR(256);
ALTER TABLE user_info ADD COLUMN activate_limit TIMESTAMP;
ALTER TABLE user_info ADD COLUMN activate_flag BOOLEAN;