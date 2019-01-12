package azz.anythingmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import azz.anythingmanagement.xmlData.Genre;

public class GenreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //レイアウトの設定
        setContentView(R.layout.genrelist);
        //ジャンル一覧を取得
        List<Genre> genreList = new ArrayList<Genre>();
        Data data = new Data();
        //（テスト用スタブ使用）
        //genreList = data.readGenre(Genre genre, Context context);


        //ジャンル一覧ボタン生成
        for (int i = 0; i < genreList.size(); i++) {
            String genreName = genreList.get(i).getGenreName();
            String genreColorInfo = genreList.get(i).getColorInfo();
            //String sakujoFlg = genreList.get(i).getSakujoFlg();

            Button btn = new Button(this);
            RelativeLayout.LayoutParams prm = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,	//文字列の幅に合わせる
                    LinearLayout.LayoutParams.WRAP_CONTENT);	//文字列の高さに合わせる
            // ボタン間のマージン　0にしても隙間が埋まらない
            prm.setMargins(0, 0, 0, 0);
            //ボタン名称
            btn.setText(genreName);
            //ボタン背景色
            btn.setBackgroundColor(Color.parseColor(genreColorInfo));
            //ボタンのID
            btn.setId(i + 1);
            RelativeLayout r = (RelativeLayout) findViewById(R.id.rl);
            r.addView(btn, prm);
        }

        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

        });*/

        //ボタンのインスタンス生成
        Button genreButton = findViewById(R.id.genruList_botton1);
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
