package app.mapper;

import app.model.UserRolesKey;

import java.util.List;

public interface UserRolesExMapper {
    /**
     * 指定した名前に該当するデータを検索する
     *
     * @param name 名前
     * @return 該当データ(リスト)
     */
    List<UserRolesKey> selectByUserId(String name);
}
