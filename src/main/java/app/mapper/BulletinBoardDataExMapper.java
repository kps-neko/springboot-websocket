package app.mapper;

import org.apache.ibatis.annotations.Param;
import app.model.BulletinBoardData;

import java.util.List;

/**
 * 自動作成のファイルに追加処理書くともろもろめんどくさそうなので
 * 追加で処理を作りたい場合は、拡張機能扱いで別のDaoとして定義する
 *
 * @author a-numadate
 *
 */
public interface BulletinBoardDataExMapper {

    /**
     * idのMax値を取得して返却する
     *
     * @param example
     * @return idのMax値
     */
    Integer selectByIdMax();

    /**
     * 指定した名前に該当するデータを検索する（全件取得）
     *
     * @param name 名前
     * @return 該当データ(リスト)
     */
    List<BulletinBoardData> selectByName(@Param("name") String name);

    /**
     * 指定した名前に該当するデータを検索する（指定範囲のデータ）
     *
     * @param name 名前
     * @return 該当データ(リスト)
     */
    List<BulletinBoardData> selectByNamePage(@Param("name") String name, @Param("startingPosition") int startingPosition, @Param("displayNum") int displayNum);

    /**
     * 指定したページのデータを取得する
     *
     * @param startingPosition 開始位置
     * @param displayNum 1ページの表示件数
     * @return 該当データ(リスト)
     */
    List<BulletinBoardData> getAssignPageData(@Param("startingPosition") int startingPosition, @Param("displayNum") int displayNum);

}
