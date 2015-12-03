-- ************************************
-- テーブル作成
-- ************************************
-- BulletinBoardData 掲示板データを管理するテーブル
create table bulletin_board_data (
  id            serial     primary key,
  name          varchar(40) not null,
  posting_date          timestamp(6) not null,
  posting_content          varchar(400) not null,
  register_date          timestamp(6) not null,
  update_date          timestamp(6)
);

-- UserInfo ユーザーの情報を管理するテーブル
create table user_info (
  user_id            varchar(255)     primary key,
  password          varchar(255) not null,
  organization_name  varchar(255) not null,
  enabled           boolean not null
);

-- UserRoles ユーザーの権限を管理するテーブル
create table user_roles (
  user_id            varchar(255),
  role          varchar(255) not null,
  PRIMARY KEY (user_id, role)
);

-- SystemProperty システムで利用する設定値を管理するためのテーブル
create table system_property (
  key            varchar(255) primary key,
  value          varchar(400) not null
);

-- ************************************
-- データ作成
-- ************************************
-- password:user　※passwordにはsha256で「user」を暗号化したものを設定
INSERT INTO user_info (user_id, password, organization_name, enabled) VALUES('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', '一般', true);
-- password:admin　※passwordにはsha256で「admin」を暗号化したものを設定
INSERT INTO user_info (user_id, password, organization_name, enabled) VALUES('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '管理者', true);

INSERT INTO user_roles (user_id, role) VALUES('user', 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES('admin', 'ROLE_ADMIN');

INSERT INTO system_property (key, value) VALUES('DISPLAY_NUMBER', '10');
INSERT INTO system_property (key, value) VALUES('DISPLAY_PAGE_NUMBER', '10');
INSERT INTO system_property (key, value) VALUES('PAGING_TRANSITION_URL', '/bulletinBoard/serch/page');


