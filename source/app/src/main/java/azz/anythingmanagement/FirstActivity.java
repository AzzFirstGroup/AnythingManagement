package azz.anythingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;

public class FirstActivity extends AppCompatActivity {
    private Button bGenreList;
    private Button bNewCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.first);

        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //ボタンの設定
        bGenreList = findViewById(R.id.bGenreList);
        bNewCreate = findViewById(R.id.bNewCreate);

        bGenreList.setOnClickListener((OnClickListener) this);
        bNewCreate.setOnClickListener((OnClickListener) this);

    }

    //ボタン押下時の挙動
    public void onClick(View v) {
        switch (v.getId()) {
            //ジャンル一覧画面へ遷移する
            case R.id.bGenreList:
                Intent intent = new Intent (this, GenreListActivity.class);
                startActivity(intent);
                break;
            //新規登録画面に遷移する(暫定としてMain2に遷移するようにする)
            case R.id.bNewCreate:
                Intent intent1 = new Intent (this, Main2Activity.class);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menuList1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
