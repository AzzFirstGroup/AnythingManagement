package azz.anythingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import azz.anythingmanagement.common.common;

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
        // 各ボタンアクションで使用するintentをここで呼んでおく
        Intent intent;
        switch (v.getId()) {
            // ジャンル一覧画面へ遷移する
            case R.id.bGenreList:
            case R.id.imageButton:
                intent = new Intent (this, GenreListActivity.class);
                startActivity(intent);
                break;
            // 新規登録画面に遷移する
            case R.id.bNewCreate:
            case R.id.imageButton2:
                intent = new Intent (this, DataRegistDetailActivity.class);
                intent.putExtra("mode", common.MODE_REGIST);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        finish();
        return false;
    }
}
