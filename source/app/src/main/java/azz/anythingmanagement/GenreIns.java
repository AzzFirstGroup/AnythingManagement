package azz.anythingmanagement;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import azz.anythingmanagement.xmlData.Genre;

public class GenreIns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_insret);

        final EditText et = findViewById(R.id.editText);
        final TextView tv = findViewById(R.id.textView);
        final TextView tv2 = findViewById(R.id.textView2);

        final Context cont = super.getApplicationContext();

        final Button bt = findViewById(R.id.button);
        final Button red = findViewById(R.id.ButtonR);
        final Button blue = findViewById(R.id.ButtonB);
        final Button yello = findViewById(R.id.ButtonY);
        final Button green = findViewById(R.id.ButtonG);
        final Button mizblue = findViewById(R.id.ButtonMB);
        final Button puple = findViewById(R.id.ButtonPl);
        final Button yamabuki = findViewById(R.id.ButtonYB);
        final Button skyblue = findViewById(R.id.ButtonSBL);
        final Button vaiolet = findViewById(R.id.ButtonVA);

        // 登録用ボタン
        // ジャンル名、カラーを登録
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString();
                ColorStateList colorlist = et.getTextColors();

                Genre genre = new Genre();

                int colordate = colorlist.getDefaultColor();
                String str = Integer.toHexString(colordate);
                String str2 = str.replaceAll("^..","#");

                Data reg_date = new Data();
                tv.setText(text);
                tv2.setText(str2);
                et.setText("");
                et.setTextColor(Color.BLACK);
                genre.setGenreName(text);
                genre.setColorInfo(str2);
                reg_date.registGenreData(genre,cont);

            }
        });

        // カラー選択
        // デフォルトは白

        // 赤色選択
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
                et.setTextColor(Color.RED);
                // 選択枠の設定

            }
        });

        // 青色選択
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.BLUE);
                et.setTextColor(Color.BLUE);
            }
        });

        // 黄色選択
        yello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.YELLOW);
                et.setTextColor(Color.YELLOW);
            }
        });

        // 緑色選択
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GREEN);
                et.setTextColor(Color.GREEN);
            }
        });

        // 水色選択
        mizblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColormiz);
                v.setBackgroundColor(color_code);
                et.setTextColor(color_code);
            }
        });

        // 紫色選択
        puple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorpoplr);
                v.setBackgroundColor(color_code);
                et.setTextColor(color_code);
            }
        });

        // ヤマブキ色選択
        yamabuki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorgold);
                v.setBackgroundColor(color_code);
                et.setTextColor(color_code);
            }
        });

        // 空色選択
        skyblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorskyblue);
                v.setBackgroundColor(color_code);
                et.setTextColor(color_code);
            }
        });

        // ﾊﾞｲｵﾚｯﾄ色選択
        vaiolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorviolet);
                v.setBackgroundColor(color_code);
                et.setTextColor(color_code);
            }
        });
    }
}
