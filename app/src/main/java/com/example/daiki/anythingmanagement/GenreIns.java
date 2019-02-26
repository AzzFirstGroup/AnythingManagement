package com.example.daiki.anythingmanagement;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GenreIns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_insret);

        final EditText et = (EditText) findViewById(R.id.editText);
        final TextView tv = (TextView) findViewById(R.id.textView);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);

        Button bt = (Button) findViewById(R.id.button);
        Button red = (Button) findViewById(R.id.ButtonR);
        Button blue = (Button) findViewById(R.id.ButtonB);
        Button yello = (Button) findViewById(R.id.ButtonY);
        Button green = (Button) findViewById(R.id.ButtonG);
        Button mizblue = (Button) findViewById(R.id.ButtonMB);
        Button puple = (Button) findViewById(R.id.ButtonPl);
        Button yamabuki = (Button) findViewById(R.id.ButtonYB);
        Button skyblue = (Button) findViewById(R.id.ButtonSBL);
        Button vaiolet = (Button) findViewById(R.id.ButtonVA);

        // 登録用ボタン
        bt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String text = et.getText().toString();
             ColorStateList colorlist = et.getTextColors();

             int colordate = colorlist.getDefaultColor();
             String str = Integer.toHexString(colordate);
             String str2 = str.replaceAll("^..","#");

             tv.setText(text);
             tv2.setText(str2);
             et.setText("");
             et.setTextColor(Color.BLACK);
         }
        });


        // 赤色選択
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
                et.setTextColor(Color.RED);
            }
        });

        // 青色選択
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
         // デフォルトは白

          // 登録ボタン
          // 入力した文字列,選択したカラーがxmlに記述されること
          // 登録対象:ジャンル名,カラー情報

          //
    }
}
