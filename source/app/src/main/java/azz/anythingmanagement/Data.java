package azz.anythingmanagement;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class Data {

    /**
     * ジャンル一覧取得
     *
     * 保存されているジャンルの一覧を取得する
     *
     * @param context activityから取得したContextを設定
     * @return resultList
     *
     */
    public ArrayList<Genre> getGenreList(Context context){
        ArrayList<Genre> resultList = readGenre(context);

        Collections.sort(resultList, new Comparator<Genre>() {
            @Override
            public int compare(Genre o1, Genre o2) {
                return o1.getregistrationOrder().compareTo(o2.getregistrationOrder());
            }
        });

        return resultList;
    }

    /**
     * ジャンル登録
     *
     * Genreクラスに設定した値を保存する
     *
     * @param genre 登録するジャンル
     * @param context activityから取得したContextを設定
     */
    public void registGenreData(Genre genre,Context context){
        String key = genre.getGenreName();
        genre.setSakujoFlg(common.SAKUJO_OFF);
        registGenre(key,genre,context);
    }

    /**
     * ジャンル削除
     *
     * Genreクラスに設定した値に紐づく情報を削除する
     *
     * @param genre 削除するジャンル
     * @param context activityから取得したContextを設定
     */
    public void deleteGenreData(Genre genre,Context context){
        String key = genre.getGenreName();
        genre.setSakujoFlg(common.SAKUJO_ON);
        registGenre(key,genre,context);
    }

    /**
     * 登録データ一覧取得
     *
     * 保存されている登録データの一覧を取得する
     *
     * @param context activityから取得したContextを設定
     */
    public ArrayList<RegistData> getRegistDataList(Context context){
        ArrayList<RegistData> resultList = readRegistData(context);
        return resultList;
    }

    /**
     * 登録データ登録
     *
     * RegistDataクラスに設定した情報を保存する
     *
     * @param data 登録するデータ
     * @param context activityから取得したContextを設定
     */
    public void registRegistData(RegistData data,Context context){
        String key = data.getTitle() + "-" + data.getGenre();
        data.setSakujoFlg(common.SAKUJO_OFF);
        registData(key,data,context);
    }

    /**
     * 登録データ更新
     *
     * タイトル名、ジャンル名が変更されない場合：
     * 　既存の情報を更新する
     * タイトル名、ジャンル名が変更された場合：
     * 　既存の情報を削除し、新規で登録する
     *
     * @param beforeData 変更前登録データ
     * @param afterData 変更後登録データ
     * @param context activityから取得したContextを設定
     * @return true:登録正常終了 false:登録異常終了（キー重複エラー）
     */
    public boolean updateRegistData(RegistData beforeData, RegistData afterData,Context context){

        ArrayList<RegistData> list = getRegistDataList(context);

        // タイトル、ジャンル名が変更されない場合
        if(beforeData.getTitle().equals(afterData.getTitle())
                && beforeData.getGenre().equals(afterData.getGenre())){
            // 既存情報に対して更新
            String key = afterData.getTitle() + "-" + afterData.getGenre();
            afterData.setSakujoFlg(common.SAKUJO_OFF);
            registData(key,afterData,context);
            return true;
        }

        // タイトル、ジャンル名が変更されていて、既存情報と重複となる場合
        for(RegistData data : list){
            if(data.getTitle().equals(afterData.getTitle())
                    && data.getGenre().equals(afterData.getGenre())){
                // 更新せず終了
                return false;
            }
        }

        // それ以外の場合、既存の情報を削除してから、更新情報を新規登録する
        String beforeKey = beforeData.getTitle() + "-" + beforeData.getGenre();
        beforeData.setSakujoFlg(common.SAKUJO_ON);
        registData(beforeKey,beforeData,context);

        String key = afterData.getTitle() + "-" + afterData.getGenre();
        afterData.setSakujoFlg(common.SAKUJO_OFF);
        registData(key,afterData,context);
        return true;
    }

    /**
     * ジャンル削除
     *
     * RegistDataクラスに紐づく情報を削除する
     *
     * @param data 削除する登録データ
     * @param context activityから取得したContextを設定
     */
    public void deleteRegistData(RegistData data,Context context){
        String key = data.getTitle() + "-" + data.getGenre();
        data.setSakujoFlg(common.SAKUJO_ON);
        registData(key,data,context);
    }

    /**
     * ジャンル登録
     *
     * Genreクラスに設定された情報を保存する
     *
     * @param key 登録キー情報（ジャンル名）
     * @param genre 登録するジャンル情報
     * @param context activityから取得したContextを設定
     */
    private void registGenre(String key, Genre genre, Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key,gson.toJson(genre));
        editor.commit();
    }

    /**
     * 登録データ登録
     *
     * RegistDataクラスに設定された情報を保存する
     * （登録キー：タイトル,ジャンル名）
     *
     * @param key 登録キー情報（タイトル名＋"-"＋ジャンル名）
     * @param registData 登録する登録データ情報
     * @param context activityから取得したContextを設定
     */
    private void registData(String key, RegistData registData, Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("RegistData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key,gson.toJson(registData));
        editor.commit();
    }

    /**
     * ジャンル読み出し
     *
     * 登録されているジャンルの一覧を取得する
     *
     * @param context activityから取得したContextを設定
     * @return jsonList
     */
    private ArrayList<Genre> readGenre(Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);

        // ジャンル一覧取得
        Map<String,?> jsonAll = prefs.getAll();
        ArrayList<Genre> jsonList = new ArrayList<>();
        for(Object val : jsonAll.values()){
            String jsonResult = val.toString();
            Genre genreResult = gson.fromJson(jsonResult,Genre.class);
            if(!genreResult.getSakujoFlg().equals(common.SAKUJO_ON)){
                jsonList.add(genreResult);
            }
        }

        return jsonList;
    }

    /**
     * 登録データ全件読み出し
     *
     * 登録されている登録データの一覧を取得する
     *
     * @param context activityから取得したContextを設定
     * @return jsonList
     */
    private ArrayList<RegistData> readRegistData(Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("RegistData", Context.MODE_PRIVATE);

        // 登録データ一覧取得
        Map<String,?> jsonAll = prefs.getAll();
        ArrayList<RegistData> jsonList = new ArrayList<>();
        for(Object val : jsonAll.values()){
            String jsonResult = val.toString();
            RegistData registDataResult = gson.fromJson(jsonResult,RegistData.class);
            if(!registDataResult.getSakujoFlg().equals(common.SAKUJO_ON)){
                jsonList.add(registDataResult);
            }
        }

        return jsonList;
    }

    /**
     * 登録データ1件読み出し
     *
     * キー情報（タイトル名、ジャンル名）に紐づく登録データを取得する
     *
     * @param context activityから取得したContextを設定
     * @param title キー情報のタイトル名
     * @param genre キー情報のジャンル名
     * @return RegistData
     */
    public RegistData getRegistData(Context context, String title, String genre){

        RegistData result = new RegistData();
        // 一覧を取得
        ArrayList<RegistData> dataList = readRegistData(context);

        for(RegistData val : dataList){
            // 一覧からキー情報（タイトル名、ジャンル名）が同じものを取得
            if(title.equals(val.getTitle()) && genre.equals(val.getGenre())){
                result = val;
                break;
            }
        }

        return result;
    }

}
