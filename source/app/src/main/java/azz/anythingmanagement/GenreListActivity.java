package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;

public class GenreListActivity extends AppCompatActivity implements View.OnClickListener {

    private Data data;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set layout
        setContentView(R.layout.genrelist);
        // ジャンル作成ボタンの初期設定
        findViewById(R.id.insert_button).setOnClickListener(this);
        // DB取得のための初期設定
        context = super.getApplicationContext();

        // ジャンル一覧を取得
        data = new Data();
        ArrayList<Genre> genreList = data.getGenreList(context);

        // DBからジャンル情報を取得できない場合は未設定のみボタンを表示する
        Button non_btn = findViewById(R.id.genruList_botton);
        non_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DataListActivity.class);
                startActivity(intent);
            }
        });
        if(genreList.isEmpty()) {
            return;
        }

        // 動的にボタンを生成しているように設定する
        for (int i = 0; i < genreList.size(); i++) {
            // 9ボタン以上は設定しない
            if(i == 9)
            {
                return;
            }

            Button btn;
            int y = i + 1;
            btn = findViewById(R.id.genruList_botton+y);
            // ボタンの非透明化
            btn.setVisibility(View.VISIBLE);
            final String genrename = String.valueOf(genreList.get(i).getGenreName());
            // ボタンに登録したジャンル名を設定
            btn.setText(genrename);
            String genreColorInfo = genreList.get(i).getColorInfo();
            // カラーコードが不正なものはデフォルトカラー
            if(!colorCdCheck(genreColorInfo)){
                genreColorInfo = "#00FA9A";
            }
            // ボタンのカラーコードを設定
            btn.setBackgroundColor(Color.parseColor(genreColorInfo));
            // 各ボタン押下時の挙動設定
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), DataListActivity.class);
                    intent.putExtra("genre", genrename);
                    startActivity(intent);
                }
            });
        }
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

    /**
     * カラーコード文字列チェック
     *
     * 文字列が16進数のカラーコードであるかチェックするメソッド
     * 16進数の文字列の場合、trueを返却
     *
     * @param str 判定文字列
     * @return boolean
     */
    public boolean colorCdCheck(String str){
        boolean result = false;

        // 正規表現文字列
        String matchStr = "#[a-zA-Z0-9]*";

        if(str.length() != 7){
            // 文字列長が7文字以外の場合
            result = false;
        }else if(str.matches(matchStr)){
            // 正規表現と一致する場合、16進数カラーコードと判定
            result = true;
        }

        return result;
    }
}
