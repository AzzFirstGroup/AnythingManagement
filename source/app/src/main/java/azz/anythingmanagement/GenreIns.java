package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;

public class GenreIns extends AppCompatActivity {
    private String nowDate;
    private String text;
    private ColorStateList colorlist;
    private Context cont;

    private EditText et;
    private TextView tv;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_insret);

        cont = super.getApplicationContext();
        et = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);
        tv.setTextColor(Color.WHITE);
        intent = new Intent(this, GenreListActivity.class);
        final Button bt = findViewById(R.id.button);
        final Button genreColor1 = findViewById(R.id.ButtonGenreColor1);
        final Button genreColor2 = findViewById(R.id.ButtonGenreColor2);
        final Button genreColor3 = findViewById(R.id.ButtonGenreColor3);
        final Button genreColor4 = findViewById(R.id.ButtonGenreColor4);
        final Button genreColor5 = findViewById(R.id.ButtonGenreColor5);
        final Button genreColor6 = findViewById(R.id.ButtonGenreColor6);
        final Button genreColor7 = findViewById(R.id.ButtonGenreColor7);
        final Button genreColor8 = findViewById(R.id.ButtonGenreColor8);
        final Button genreColor9 = findViewById(R.id.ButtonGenreColor9);

        // テキストボックスの背景を白くする
        et.setBackgroundColor(Color.WHITE);
        // 登録用ボタン
        // ジャンル名、カラーを登録
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 入力したデータの取得
                text = et.getText().toString();
                colorlist = tv.getTextColors();

                // テキストに何も記載が無かったら登録せず注意喚起する
                if (text.isEmpty()) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "ジャンルを入力してください", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 650);
                    toast.show();
                } else {
                    // 登録ダイアログ表示
                    RegistDialog dialog = new RegistDialog();
                    dialog.show(getSupportFragmentManager(), "DetailRegist");
                }

            }
        });

        // カラー選択
        // デフォルトは白

        // カラーボタン１
        genreColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor1);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン２
        genreColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor2);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン３
        genreColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor3);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン４
        genreColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor4);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン５
        genreColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor5);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン６
        genreColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor6);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン７
        genreColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor7);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン８
        genreColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor8);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });

        // カラーボタン９
        genreColor9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                int color_code = res.getColor(R.color.genreColor9);
                v.setBackgroundColor(color_code);
                tv.setTextColor(color_code);
                tv.setBackgroundColor(color_code);
            }
        });
    }

    public void registProcess(boolean dialogResult) {
        if (dialogResult) {
            int colordate = colorlist.getDefaultColor();
            String str = Integer.toHexString(colordate);
            String str2 = str.replaceAll("^..", "#");

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
            genre.setregistrationOrder(nowDate);

            // DBに登録
            reg_date.registGenreData(genre, cont);

            // 入力したデータの初期化
            et.setText("");
            tv.setTextColor(Color.WHITE);

            // 登録後はジャンル一覧画面に遷移
            finish();
            startActivity(intent);
        }
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
            Intent intent = new Intent(this, GenreListActivity.class);
            startActivity(intent);
        }

        // ジャンル作成に遷移(メニューボタン)
        if (id == R.id.action_menuList2) {
            Intent intent = new Intent(this, GenreIns.class);
            finish();
            startActivity(intent);
        }

        // 新規作成に遷移(メニューボタン)
        if (id == R.id.action_menuList3) {
            Intent intent = new Intent(this, DataRegistDetailActivity.class);
            intent.putExtra("mode", common.MODE_REGIST);
            startActivity(intent);
        }

        // データ一覧に遷移(メニューボタン)
        if (id == R.id.action_menuList4) {
            Intent intent = new Intent(this, DataListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
