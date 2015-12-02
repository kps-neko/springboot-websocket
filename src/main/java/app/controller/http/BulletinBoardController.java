package app.controller.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.exception.SystemErrorException;
import app.form.BulletinBoardForm;
import app.form.PageCondition;
import app.model.BulletinBoardData;
import app.service.BulletinBoardDataService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

@Controller
@EnableAutoConfiguration
@RequestMapping("/bulletinBoard")
public class BulletinBoardController {

    private static final Logger log = LoggerFactory.getLogger(BulletinBoardController.class);

    @Autowired
    BulletinBoardDataService bulletinBoardDataService;

    @ModelAttribute
        // 画面で使うフォームに対応したオブジェクトを初期化して、Modelに追加する(Thymeleafからアクセスさせるために必要)
    BulletinBoardForm setupForm() {
        return new BulletinBoardForm();
    }

    /**
     * 全件取得
     * <p/>
     * DBから全データを取得します
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/serch/list", method = RequestMethod.GET)
    public String serchList(Locale locale, Model model) {
        getPageData(model);
        return "bulletinBoard";
    }

    /**
     * 指定したページのデータを取得する
     *
     * @param page         ページ番号（省略不可）
     * @param serchKeyword 検索条件（省略可　省略時：空文字が設定される）
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/serch/page", method = RequestMethod.GET)
    public String getPageData(@RequestParam int page, @RequestParam(defaultValue = "") String serchKeyword, Locale locale, Model model) {
        if ("".equals(serchKeyword)) {
            // パラメータが存在しない場合
            PageCondition pageCondition = bulletinBoardDataService.getPageCondition(page, null);

            List<BulletinBoardData> list = bulletinBoardDataService.getAssignPageData(pageCondition);
            model.addAttribute("bulletinBoardDataList", list);
            model.addAttribute("pageCondition", pageCondition);
        } else {
            // パラメータで指定された検索キーワードのデータで検索を行いその結果をモデルに設定する
            getSerchKeywordSelectData(model, serchKeyword, page);
        }

        return "bulletinBoard";
    }

    /**
     * 検索条件をクリアして全件を表示する
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/serch/page/serch/clear", method = RequestMethod.GET)
    public String clearSearchConditions(Locale locale, Model model) {
        getPageData(model);
        return "bulletinBoard";
    }

    /**
     * パラメータで指定された名前のデータを検索する
     *
     * @param bulletinBoardForm フォームデータ
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/serch", method = RequestMethod.POST)
    public String serchKeyword(BulletinBoardForm bulletinBoardForm, Locale locale, Model model) {

        // パラメータで指定された名前のデータを検索を行いその結果をモデルに設定する(新規に検索するためページ数は1固定)
        getSerchKeywordSelectData(model, bulletinBoardForm.getName(), 1);

        // 入力された検索情報を設定
        bulletinBoardForm.setSerchKeyword(bulletinBoardForm.getName());
        // フォームの入力データを初期化する
        bulletinBoardForm.initInputData();
        model.addAttribute("bulletinBoardForm", bulletinBoardForm);

        return "bulletinBoard";
    }

    /**
     * 画面で入力されたデータをDBに登録して今回登録したデータを含めて全データを画面に返却する
     * <p/>
     * ※トランザクションの範囲について確認しておく
     *
     * @param bulletinBoardForm フォームデータ
     * @param result
     * @param model
     * @return String
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String registReport(@Validated BulletinBoardForm bulletinBoardForm, BindingResult result, Model model) {

        // バリデータ
        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                log.info("error code = [" + err.getCode() + "]");
            }
            getPageData(model);
            return "bulletinBoard";
        }

        try {
            BulletinBoardData entity = new BulletinBoardData();
            entity.setName(bulletinBoardForm.getName());
            entity.setPostingContent(bulletinBoardForm.getPostingContent());
            entity.setPostingDate(new Timestamp(System.currentTimeMillis()));
            entity.setRegisterDate(new Timestamp(System.currentTimeMillis()));
            bulletinBoardDataService.addBulletinBoardData(entity);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new SystemErrorException();
        }
        // フォームの入力データを初期化する
        bulletinBoardForm.initInputData();
        getPageData(model);
        model.addAttribute("bulletinBoardForm", bulletinBoardForm);
        return "bulletinBoard";
    }

    /**
     * 検索条件が存在する場合
     *
     * @param model
     * @param keyword
     */
    private void getSerchKeywordSelectData(Model model, String keyword, int page) {
        List<BulletinBoardData> list = bulletinBoardDataService.getSearchNameBulletinBoardData(keyword, page);

        // ページ情報取得
        PageCondition pageCondition =
                bulletinBoardDataService.getPageCondition(page, bulletinBoardDataService.getSearchNameBulletinBoardData(keyword).size());

        model.addAttribute("bulletinBoardDataList", list);
        model.addAttribute("pageCondition", pageCondition);
    }

    /**
     * ページ情報初期表示
     *
     * @param model
     */
    private void getPageData(Model model) {
        PageCondition pageCondition = bulletinBoardDataService.getPageCondition(1, null);

        List<BulletinBoardData> list = bulletinBoardDataService.getAssignPageData(pageCondition);
        model.addAttribute("bulletinBoardDataList", list);
        model.addAttribute("pageCondition", pageCondition);
    }
}