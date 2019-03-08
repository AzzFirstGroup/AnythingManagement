package azz.anythingmanagement.xmlData;

public class RegistData {
    /* タイトル */
    private String title;
    /* 登録済フラグ */
    private String torokuFlg;
    /* 画像パス */
    private String imagePath;
    /* システム日付 */
    private String systemDate;
    /* ジャンル */
    private String genre;
    /* 評価 */
    private String evaluate;
    /* メモ */
    private String memo;
    /* 削除フラグ */
    private String sakujoFlg;
    /* テストRegistフラグ */
    private String testRegistFlg;

    /**
     * タイトルgetter
     *
     * @return title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * タイトルsetter
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * 登録済フラグgetter
     *
     * @return torokuFlg
     */
    public String getTorokuFlg(){
        return this.torokuFlg;
    }

    /**
     * 登録済フラグsetter
     *
     * @param torokuFlg
     */
    public void setTorokuFlg(String torokuFlg){
        this.torokuFlg = torokuFlg;
    }

    /**
     * 画像パスgetter
     *
     * @return imagePath
     */
    public String getImagePath(){
        return this.imagePath;
    }

    /**
     * 画像パスsetter
     *
     * @param imagePath
     */
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    /**
     * システム日付getter
     *
     * @return systemDate
     */
    public String getSystemDate(){
        return this.systemDate;
    }

    /**
     * システム日付setter
     *
     * @param systemDate
     */
    public void setSystemDate(String systemDate){
        this.systemDate = systemDate;
    }

    /**
     * ジャンルgetter
     *
     * @return genre
     */
    public String getGenre(){
        return this.genre;
    }

    /**
     * ジャンルsetter
     *
     * @param genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }

    /**
     * 評価getter
     *
     * @return evaluate
     */
    public String getEvaluate(){
        return this.evaluate;
    }

    /**
     * 評価setter
     *
     * @param evaluate
     */
    public void setEvaluate(String evaluate){
        this.evaluate = evaluate;
    }

    /**
     * メモgetter
     *
     * @return memo
     */
    public String getMemo(){
        return this.memo;
    }

    /**
     * メモsetter
     *
     * @param memo
     */
    public void setMemo(String memo){
        this.memo = memo;
    }

    /**
     * 削除済フラグgetter
     *
     * @return sakujoFlg
     */
    public String getSakujoFlg(){
        return this.sakujoFlg;
    }

    /**
     * 削除済フラグsetter
     *
     * @param sakujoFlg
     */
    public void setSakujoFlg(String sakujoFlg){
        this.sakujoFlg = sakujoFlg;
    }

    /**
     * testフラグgetter
     *
     * @return sakujoFlg
     */
    public String getTestRegistFlg(){
        return this.testRegistFlg;
    }

    /**
     * testフラグsetter
     *
     * @param testRegistFlg
     */
    public void setTestRegistFlg(String testRegistFlg){
        this.testRegistFlg = testRegistFlg;
    }

}
