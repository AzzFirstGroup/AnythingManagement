package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;

public class GenreListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set layout
        setContentView(R.layout.genrelist);

        // ジャンル作成ボタンの初期設定
        findViewById(R.id.insert_button).setOnClickListener(this);

        // ジャンル一覧を取得
        List<Genre> genreList = new ArrayList<Genre>();
        // ここでDBからジャンル情報を取得なければ未設定のみを表示するようにする
        if(genreList.isEmpty()) {
            return;
        }

        // ここでDBの件数分ボタンを設定する。
        //ジャンル一覧ボタン生成
        for (int i = 0; i < genreList.size(); i++) {
            String genreName = genreList.get(i).getGenreName();
            String genreColorInfo = genreList.get(i).getColorInfo();

            // ボタンの生成
            Button btn = new Button(this);
            RelativeLayout.LayoutParams prm = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,	//文字列の幅に合わせる
                    LinearLayout.LayoutParams.WRAP_CONTENT);	//文字列の高さに合わせる
            // ボタン間のマージン
            prm.setMargins(20, 20, 0, 0);
            //ボタン名称
            btn.setText(genreName);
            //ボタン背景色
            btn.setBackgroundColor(Color.parseColor(genreColorInfo));
            //ボタンのID
            btn.setId(i + 1);
            btn.setWidth(80);
            btn.setHeight(80);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), testDataListActivity.class);
                    startActivity(intent);
                }
            });
        }

        //ボタンのインスタンス生成
        Button genreButton = findViewById(R.id.genruList_botton);
        //ボタンクリック時のアプリ内アクティビティの呼び出し
        genreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), testDataListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        // 各ボタンアクションで使用するintentをここで呼んでおく
        Intent intent;
        int id = v.getId();
        // ジャンル登録画面へ遷移する
        if(id == R.id.insert_button) {
            intent = new Intent(this, GenreIns.class);
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
}
