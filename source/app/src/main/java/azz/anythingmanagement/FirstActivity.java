package azz.anythingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトの呼び出し
        setContentView(R.layout.first);

        // ボタンの設定
        findViewById(R.id.bGenreList).setOnClickListener(this);
        findViewById(R.id.bNewCreate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ジャンル一覧画面へ遷移する
            case R.id.bGenreList:
                Intent intent = new Intent (this, GenreListActivity.class);
                startActivity(intent);
                break;
            // 新規登録画面に遷移する
            // TODO:: 仮でMain2Activityを設定しているが、新規登録画面ができたらここを変更する
            case R.id.bNewCreate:
                Intent intent1 = new Intent (this, DataRegistDetailActivity.class);
                startActivity(intent1);
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
        // TODO:: まだ画面がないため、ジャンル一覧画面を仮設定
        if (id == R.id.action_menuList2) {
            Intent intent = new Intent (this, GenreListActivity.class);
            startActivity(intent);
        }

        // 新規作成に遷移(メニューボタン)
        // TODO:: まだ画面がないため、ジャンル一覧画面を仮設定
        if (id == R.id.action_menuList3) {
            Intent intent = new Intent (this, GenreListActivity.class);
            startActivity(intent);
        }

        // データ一覧に遷移(メニューボタン)
        // TODO:: まだ画面がないため、ジャンル一覧画面を仮設定
        if (id == R.id.action_menuList4) {
            Intent intent = new Intent (this, GenreListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
