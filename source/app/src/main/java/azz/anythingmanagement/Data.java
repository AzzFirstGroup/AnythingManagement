package azz.anythingmanagement;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class Data {

    /**
     * ジャンル登録
     *
     * @param genre
     */
    public void registGenre(Genre genre, Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(genre.getGenreName(),gson.toJson(genre));
        //editor.putString("genreId",getRegistGenreId(context));
        //editor.putString("genreName",genre.getGenreName());
        //editor.putString("colorInfo",genre.getColorInfo());
        //editor.putString("sakujoFlg","0");
        editor.commit();
    }

    /**
     * 登録データ登録
     *
     * @param registData
     */
    public void registData(RegistData registData, Context context){
        SharedPreferences prefs = context.getSharedPreferences("RegistData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("title",registData.getTitle());
        editor.putString("registFlg",registData.getRegistFlg());
        editor.putString("imagePath",registData.getImagePath());
        editor.putString("systemDate",registData.getSystemDate());
        editor.putString("genre",registData.getGenre());
        editor.putString("evaluate",registData.getEvaluate());
        editor.putString("memo",registData.getMemo());
        editor.apply();
    }

    /**
     * ジャンル読み出し
     *
     * @param genre
     */
    public ArrayList<Genre> readGenre(Genre genre, Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
//        String json = prefs.getString(genre.getGenreName(),"");
//        ArrayList<Genre> arrayList = new ArrayList<>();
//        if(json.equals("[]")){
//            arrayList = new ArrayList<>();
//        } else {
//            Genre result = gson.fromJson(json, Genre.class);
//            arrayList.add(result);
//            // arrayList = gson.fromJson(json,new TypeToken<ArrayList<Genre>>(){}.getType());
//        }

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

        //Genre result = gson.fromJson(prefs.getString("genreId", null), new TypeToken<Genre>(){}.getType());
        //genre.setGenreName(prefs.getString("genreName" , ""));
        //genre.setColorInfo(prefs.getString("colorInfo" , ""));
        return jsonList;
    }

    /**
     * ジャンルID登録
     *
     */
    public String getRegistGenreId(Context context){
        String genreId = readGenreId(context);
        int i = Integer.valueOf(genreId) + 1 ;

        SharedPreferences prefs = context.getSharedPreferences("GenreIdRegistData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("genreId",String.valueOf(i));

        return String.valueOf(i);
    }

    /**
     * ジャンルID読み出し
     *
     */
    public String readGenreId(Context context){
        SharedPreferences prefs = context.getSharedPreferences("GenreIdRegistData", Context.MODE_PRIVATE);
        return prefs.getString("genreId" , "0");
    }

    /**
     * ジャンル一覧取得（テスト用スタブ）
     *
     */
    public ArrayList<Genre> getGenreList(Context context){
        ArrayList<Genre> resultList = new ArrayList<>();
        Genre genre = new Genre();
        genre.setColorInfo("#FF0000");
        genre.setGenreName("テストジャンル");
        resultList.add(genre);
        return resultList;
    }

    /**
     * ジャンル登録（テスト用スタブ）
     *
     */
    public void registGenreData(Genre genre,Context context){

    }

    /**
     * ジャンル削除（テスト用スタブ）
     *
     */
    public void deleteGenreData(Genre genre,Context context){

    }

    /**
     * 登録データ一覧取得（テスト用スタブ）
     *
     */
    public ArrayList<RegistData> getRegistDataList(Context context){
        ArrayList<RegistData> resultList = new ArrayList<>();
        RegistData registData = new RegistData();
        registData.setTitle("テストタイトル");
        registData.setImagePath("テスト画像パス");
        registData.setSystemDate("2999/12/31");
        registData.setGenre("テストジャンル名");
        registData.setEvaluate("3");
        registData.setMemo("テストメモ");
        resultList.add(registData);
        return resultList;
    }

    /**
     * ジャンル登録（テスト用スタブ）
     *
     */
    public void registRegistData(RegistData registData,Context context){

    }

    /**
     * ジャンル削除（テスト用スタブ）
     *
     */
    public void deleteRegistData(RegistData registData,Context context){

    }
}
