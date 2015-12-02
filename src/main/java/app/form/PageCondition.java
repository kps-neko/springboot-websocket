package app.form;

import java.util.ArrayList;
import java.util.List;

/**
 * ページング処理のデータ保管用
 * 検索条件などもここに保管しておいたほうがいいかもしれない
 *
 * @author USER0200
 *
 */
public class PageCondition {

    // 1ページ単位の表示件数
    private int limitCount;
    // 選択されたページ番号
    private int page;
    // 表示するデータの開始位置
    private int startingPosition;
    // 表示するデータの終了位置
    private int endPosition;
    // 検索結果の総件数
    private int totalCount;
    // 検索結果の総ページ数
    private int totalPage;
    // ページング時の遷移先のURL
    private String pageURL;
    // 画面下部の表示ページ数
    private int displayPage;
    // ページリスト
    private List<Integer> pageNumberList;

    public PageCondition(int displayPage) {
        // 初期ページリスト作成
        pageNumberList = new ArrayList<Integer>();
        for (int i = 1; i <= displayPage; i++) {
            pageNumberList.add(i);
        }
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(int displayPage) {
        this.displayPage = displayPage;
    }

    public List<Integer> getPageNumberList() {
        return pageNumberList;
    }

    public void setPageNumberList(List<Integer> pageNumberList) {
        this.pageNumberList = pageNumberList;
    }


}
