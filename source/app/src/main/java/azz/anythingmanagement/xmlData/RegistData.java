package azz.anythingmanagement.xmlData;

public class RegistData {
    /* タイトル */
    private String title;
    /* 登録済フラグ */
    private String registFlg;
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
     * @return registFlg
     */
    public String getRegistFlg(){
        return this.registFlg;
    }

    /**
     * 登録済フラグsetter
     *
     * @param registFlg
     */
    public void setRegistFlg(String registFlg){
        this.registFlg = registFlg;
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

}
