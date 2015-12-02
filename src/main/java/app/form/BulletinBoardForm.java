package app.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class BulletinBoardForm {
    // 名前
    @Length(max=40)
    private String name;
    // 投稿内容
    @NotEmpty
    @Length(max=400)
    private String postingContent;
    // 検索キーワード
    private String serchKeyword;

    // コンストラクタ
    public BulletinBoardForm() {
        super();
    }

    /**
     * 入力情報を初期化する
     *
     */
    public void initInputData() {
        this.name = null;
        this.postingContent = null;
    }

    // setter & getter ---------------------

    /**
     * nameを返却
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * nameを設定
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * postingContentを返却
     *
     * @return postingContent
     */
    public String getPostingContent() {
        return postingContent;
    }

    /**
     * postingContentを設定
     *
     * @param postingContent
     */
    public void setPostingContent(String postingContent) {
        this.postingContent = postingContent;
    }

    /**
     * serchKeywordを返却
     *
     * @return serchKeyword
     */
    public String getSerchKeyword() {
        return serchKeyword;
    }

    /**
     * serchKeywordを設定
     *
     * @param serchKeyword
     */
    public void setSerchKeyword(String serchKeyword) {
        this.serchKeyword = serchKeyword;
    }
}
