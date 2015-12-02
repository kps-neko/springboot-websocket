package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.form.PageCondition;
import app.mapper.BulletinBoardDataExMapper;
import app.mapper.BulletinBoardDataMapper;
import app.mapper.SystemPropertyExMapper;
import app.model.BulletinBoardData;
import app.model.BulletinBoardDataExample;
import app.model.SystemProperty;

import java.util.ArrayList;
import java.util.List;

@Service
public class BulletinBoardDataService {

    @Autowired
    private BulletinBoardDataMapper bulletinBoardDataMapper;

    @Autowired
    private BulletinBoardDataExMapper bulletinBoardDataExMapper;

    @Autowired
    private SystemPropertyExMapper systemPropertyExMapper;

    /**
     * 全件取得して返却する
     *
     * 全件取得のため条件は指定せずに検索する
     * ※わかりづらそうだからSQL文書くような形式にしたほうがいいかもしれない
     *
     * @return　List<BulletinBoardData>　検索結果
     */
    public List<BulletinBoardData> getBulletinBoardDataAll() {
        BulletinBoardDataExample exp = new BulletinBoardDataExample();
        return bulletinBoardDataMapper.selectByExample(exp);
    }

    /**
     * 指定した範囲のデータを取得して返却する
     *
     * @return　List<BulletinBoardData>　検索結果
     */
    public List<BulletinBoardData> getSearchNameBulletinBoardData(String name) {
        return bulletinBoardDataExMapper.selectByName(name);
    }

    /**
     * 名前で検索した結果を取得して返却する
     *
     * @return　List<BulletinBoardData>　検索結果
     */
    public List<BulletinBoardData> getSearchNameBulletinBoardData(String name, int page) {
        // システム設定値から画面表示件数取得
        SystemProperty systemProperty = getSystemProperty("DISPLAY_NUMBER");
        int limitCount = systemProperty != null ? Integer.parseInt(systemProperty.getValue()) : 0;
        return bulletinBoardDataExMapper.selectByNamePage(name, calcStartingPosition(page, limitCount), limitCount);
    }

    /**
     * 指定した範囲のデータを取得して返却する
     *
     * @return　List<BulletinBoardData>　検索結果
     */
    public List<BulletinBoardData> getAssignPageData(PageCondition pageCondition) {
        return bulletinBoardDataExMapper.getAssignPageData(pageCondition.getStartingPosition(), pageCondition.getLimitCount());
    }

    /**
     * １件のデータを登録する
     *
     * @return int　件数
     */
    @Transactional(rollbackFor = Exception.class)
    public int addBulletinBoardData(BulletinBoardData record) {
        Integer maxId = bulletinBoardDataExMapper.selectByIdMax();

        int id = 1;
        if (maxId != null) {
            id = maxId.intValue() + 1;
        }

        record.setId(id);
        return bulletinBoardDataMapper.insert(record);
    }

    /**
     * ページ情報を取得する
     *
     * @param page 選択されたページ番号
     * @param paramTotalCount パラメータ総件数(nullの場合はテーブルの全データ数が総件数として設定される)
     * @return　ページ情報
     */
    public PageCondition getPageCondition(int page, Integer paramTotalCount) {
        // システム設定値から画面表示件数取得
        SystemProperty systemProperty = getSystemProperty("DISPLAY_NUMBER");
        int limitCount = systemProperty != null ? Integer.parseInt(systemProperty.getValue()) : 0;
        // システム設定値からページ表示件数取得
        systemProperty = getSystemProperty("DISPLAY_PAGE_NUMBER");
        int displayPage = systemProperty != null ? Integer.parseInt(systemProperty.getValue()) : 0;
        // システム設定値からページング時の遷移先URL取得
        systemProperty = getSystemProperty("PAGING_TRANSITION_URL");
        String pageURL = systemProperty != null ? systemProperty.getValue() : null;

        // 総件数取得
        int totalCount = paramTotalCount == null ? getBulletinBoardDataAll().size() : paramTotalCount;
        // 開始位置の算出
        int startingPosition = calcStartingPosition(page, limitCount);
        // 終了位置の算出
        int endPosition = calcEndPosition(startingPosition, limitCount, totalCount);
        // ページ数の算出
        int totalPage = calcTotalPage(totalCount, limitCount);

        // ページ情報設定
        PageCondition pageCondition = new PageCondition(displayPage);
        pageCondition.setTotalCount(totalCount);
        pageCondition.setLimitCount(limitCount);
        pageCondition.setPage(page);
        pageCondition.setStartingPosition(startingPosition);
        pageCondition.setEndPosition(endPosition);
        pageCondition.setTotalCount(totalCount);
        pageCondition.setTotalPage(totalPage);
        pageCondition.setDisplayPage(displayPage);
        pageCondition.setPageURL(pageURL);

        if (page > displayPage / 2) {
            // 最初に表示するページ番号を算出
            int startPageNumber = page - displayPage / 2 + 1;
            // ページの表示数をページ表示件数分必ず表示させるために最初に表示するページ数を調整
            if (totalPage < startPageNumber + displayPage) {
                startPageNumber = totalPage - displayPage + 1;
            }

            // 表示するページ番号のリストを作成
            List<Integer> pageNumberList = new ArrayList<Integer>();
            for (int i = startPageNumber; i < startPageNumber + displayPage; i++) {
                pageNumberList.add(i);
            }
            pageCondition.setPageNumberList(pageNumberList);
        } else if (totalPage < displayPage) {
            // 総ページ数がページ表示件数未満の場合
            // 表示するページ番号のリストを作成
            List<Integer> pageNumberList = new ArrayList<Integer>();
            for (int i = 1; i <= totalPage; i++) {
                pageNumberList.add(i);
            }
            pageCondition.setPageNumberList(pageNumberList);
        }

        return pageCondition;
    }

    /**
     * 送られてきた情報をもとにページの開始位置の算出
     *
     * @param page ページ数
     * @param limitCount 画面表示件数
     * @return 算出結果
     */
    public int calcStartingPosition(int page, int limitCount) {
        return page != 1 ? (page - 1) * limitCount - 1 : 0;
    }

    /**
     * 送られてきた情報をもとにページの終了位置の算出
     * @param startingPosition ページの開始位置
     * @param totalCount 総件数
     * @param limitCount 画面表示件数
     * @return 算出結果
     */
    public int calcEndPosition(int startingPosition, int totalCount, int limitCount) {
        return startingPosition + limitCount > totalCount ? totalCount : startingPosition + limitCount;
    }

    /**
     * ページ数の算出
     *
     * @param totalCount 総件数
     * @param limitCount 画面表示件数
     * @return
     */
    public int calcTotalPage(int totalCount, int limitCount) {
        int subtotal = (totalCount + limitCount) % limitCount;
        if (subtotal == 0) {
            // 余りが存在しない場合(割り切れた場合)
            return (totalCount + limitCount) / limitCount - 1;
        } else {
            return (totalCount + limitCount) / limitCount;
        }
    }

    /**
     * システム設定値を取得する
     *
     * <pre>
     * キーが送られてきていない、または存在しないキーが送られてきた場合は
     * nullを返却する
     * </pre>
     *
     * @param key 検索キー
     * @return 検索結果
     */
    public SystemProperty getSystemProperty(String key) {

        if (key != null) {
            return systemPropertyExMapper.selectByKey(key);
        }

        return null;
    }
}
