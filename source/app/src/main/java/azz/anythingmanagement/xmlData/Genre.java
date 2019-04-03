package azz.anythingmanagement.xmlData;

public class Genre {

    /* ジャンル名 */
    private String genreName;
    /* カラー情報 */
    private String colorInfo;
    /* 登録順情報 */
    private String registrationOrder;
    /* 削除フラグ */
    private String sakujoFlg;

    /**
     * ジャンル名getter
     *
     * @return genreName
     */
    public String getGenreName(){
        return this.genreName;
    }

    /**
     * ジャンル名setter
     *
     * @param genreName
     */
    public void setGenreName(String genreName){
        this.genreName = genreName;
    }

    /**
     * カラー情報getter
     *
     * @return colorInfo
     */
    public String getColorInfo(){
        return this.colorInfo;
    }

    /**
     * カラー情報setter
     *
     * @param colorInfo
     */
    public void setColorInfo(String colorInfo){
        this.colorInfo = colorInfo;
    }

    /**
     * 登録順情報getter
     *
     * @return registrationOrder
     */
    public String getregistrationOrder(){
        return this.registrationOrder;
    }

    /**
     * 登録順情報setter
     *
     * @param registrationOrder
     */
    public void setregistrationOrder(String registrationOrder){ this.registrationOrder = registrationOrder; }

    /**
     * 削除フラグgetter
     *
     * @return sakujoFlg
     */
    public String getSakujoFlg(){
        return this.sakujoFlg;
    }

    /**
     * 削除フラグsetter
     *
     * @param sakujoFlg
     */
    public void setSakujoFlg(String sakujoFlg){
        this.sakujoFlg = sakujoFlg;
    }
}
