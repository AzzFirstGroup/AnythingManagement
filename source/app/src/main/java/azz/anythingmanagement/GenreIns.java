package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;

public class GenreIns extends AppCompatActivity {
    private String nowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_insret);

        final EditText et = findViewById(R.id.editText);
        final TextView tv = findViewById(R.id.textView);
        tv.setTextColor(Color.WHITE);

        final Context cont = super.getApplicationContext();
        final Intent intent = new Intent (this, GenreListActivity.class);

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

        // テキストボックスの背景を白くする
        et.setBackgroundColor(Color.WHITE);
        // 登録用ボタン
        // ジャンル名、カラーを登録
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 入力したデータの取得
                String text = et.getText().toString();
                ColorStateList colorlist = tv.getTextColors();

                // テキストに何も記載が無かったら登録しない
                if(text.isEmpty()) {
                    return;
                }

                int colordate = colorlist.getDefaultColor();
                String str = Integer.toHexString(colordate);
                String str2 = str.replaceAll("^..","#");

                // DBアクセス用の初期化
                Data reg_date = new Data();
                Genre genre = new Genre();

                // DB登録用データ
                genre.setGenreName(text);
                genre.setColorInfo(str2);

                // 現在日時（yyyyMMddhhmmss）取得
                Date now = new Date(System.currentTimeMillis());
                DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                nowDate = sdf.format(now);

                // 登録順序を設定
                ArrayList<Genre> genreList = reg_date.getGenreList(cont);
                genre.setregistrationOrder(nowDate);

                // DBに登録
                reg_date.registGenreData(genre,cont);

                // 入力したデータの初期化
                et.setText("");
                tv.setTextColor(Color.WHITE);

                // 登録後はジャンル一覧画面に遷移
                finish();
                startActivity(intent);
            }
        });

        // カラー選択
        // デフォルトは白

        // 赤色選択
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
                tv.setTextColor(Color.RED);
                tv.setBackgroundColor(Color.RED);
            }
        });

        // 青色選択
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.BLUE);
                tv.setTextColor(Color.BLUE);
                tv.setBackgroundColor(Color.BLUE);
            }
        });

        // 黄色選択
        yello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.YELLOW);
                tv.setTextColor(Color.YELLOW);
                tv.setBackgroundColor(Color.YELLOW);
            }
        });

        // 緑色選択
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GREEN);
                tv.setTextColor(Color.GREEN);
                tv.setBackgroundColor(Color.GREEN);
            }
        });

        // 水色選択
        mizblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColormiz);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // 紫色選択
        puple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorpoplr);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // ヤマブキ色選択
        yamabuki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorgold);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // 空色選択
        skyblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorskyblue);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // ﾊﾞｲｵﾚｯﾄ色選択
        vaiolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.buttonColorviolet);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // ジャンル一覧に遷移(メニューボタン)
        if (id == R.id.action_menuList1) {
            Intent intent = new Intent (this, GenreListActivity.class);
            startActivity(intent);
        }

        // ジャンル作成に遷移(メニューボタン)
        if (id == R.id.action_menuList2) {
            Intent intent = new Intent (this, GenreIns.class);
            startActivity(intent);
        }

        // 新規作成に遷移(メニューボタン)
        if (id == R.id.action_menuList3) {
            Intent intent = new Intent (this, DataRegistDetailActivity.class);
            intent.putExtra("mode", common.MODE_REGIST);
            startActivity(intent);
        }

        // データ一覧に遷移(メニューボタン)
        if (id == R.id.action_menuList4) {
            Intent intent = new Intent (this, DataListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    boolean a = false;
    public void setResultView(boolean resultValue) {
        Context context = getApplicationContext();
        a = resultValue;
        if (a) {
            Toast toast = Toast.makeText(context, "登録します。", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //何もしない
        }
    }
}
