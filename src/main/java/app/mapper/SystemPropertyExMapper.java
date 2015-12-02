package app.mapper;

import app.model.SystemProperty;

public interface SystemPropertyExMapper {
    /**
     * 指定したキーに該当する設定値を取得する
     *
     * @param key キー
     * @return 街頭システム設定値
     */
    SystemProperty selectByKey(String key);
}
