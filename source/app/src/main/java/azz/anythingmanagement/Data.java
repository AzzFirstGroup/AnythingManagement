package azz.anythingmanagement;

import android.content.Context;
import android.content.SharedPreferences;

import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class Data {

    /**
     * ジャンル登録
     *
     * @param genre
     */
    public void registGenre(Genre genre, Context context){
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("genreName",genre.getGenreName());
        editor.putString("colorInfo",genre.getColorInfo());
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
    public Genre readGenre(Genre genre, Context context){
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
        genre.setGenreName(prefs.getString("genreName" , ""));
        genre.setColorInfo(prefs.getString("colorInfo" , ""));
        return genre;
    }
}
