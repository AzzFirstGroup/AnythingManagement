package azz.anythingmanagement;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

        editor.putString("genreId",gson.toJson(genre));
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
    public Genre readGenre(Genre genre, Context context){
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences("GenreData", Context.MODE_PRIVATE);
        Genre result = gson.fromJson(prefs.getString("genreId", null), new TypeToken<Genre>(){}.getType());
        //genre.setGenreName(prefs.getString("genreName" , ""));
        //genre.setColorInfo(prefs.getString("colorInfo" , ""));
        return result;
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
}
