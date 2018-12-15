package com.example.daiki.anythingmanagement;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = (EditText) findViewById(R.id.editText);

        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  String text = et.getText().toString();

                  TextView tv = (TextView) findViewById(R.id.textView);
                  tv.setText(text);
              }
        });
        // カラー選択ボタン
           // 9種類の色を出力
           // タッチができる
           // タッチした色の情報を仮で保持
           // 別の色をタッチしたら、その色の情報を保持
           // デフォルトは白

        // 登録ボタン
          // 入力した文字列がxmlに記述されること
          // 登録対象:ジャンル名,カラー情報

        //
    }
}
