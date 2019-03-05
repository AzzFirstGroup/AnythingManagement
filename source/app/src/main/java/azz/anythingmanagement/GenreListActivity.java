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

import azz.anythingmanagement.xmlData.Genre;

public class GenreListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.genrelist);


        //ジャンル一覧を取得
        List<Genre> genreList = new ArrayList<Genre>();
        Data data = new Data();
        //（テスト用スタブ使用）
        //genreList = data.readGenre(Genre genre, Context context);
        //テスト用

        for(int i=0; i < 10; i++){
            Genre genre = new Genre();
            genre.setGenreName("ジャンル名 "+i);
            genre.setColorInfo("#FF"+i+i+i+i);
            genre.setSakujoFlg("0");
            genreList.add(genre);
            System.out.println(genre.getGenreName());
            System.out.println(genre.getColorInfo());
            System.out.println(genre.getSakujoFlg()+i);
        }

        //ジャンル一覧ボタン生成
        for (int i = 0; i < genreList.size(); i++) {
            String genreName = genreList.get(i).getGenreName();
            String genreColorInfo = genreList.get(i).getColorInfo();
            int num = i + 1;
            //String sakujoFlg = genreList.get(i).getSakujoFlg();

            Button btn = new Button(this);
            /*ViewGroup.LayoutParams lp = btn.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            mlp.setMargins(20,20, mlp.rightMargin, mlp.bottomMargin);
            //マージンを設定
            btn.setLayoutParams(mlp);*/

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
            RelativeLayout r = (RelativeLayout) findViewById(R.id.rl);
            r.addView(btn,prm);
        }

        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
        });*/

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
    //ここから幅計算
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        // 子供の数を取得
        int l = rl.getChildCount();
        // 無いなら何もしない
        if (l == 0) {
            System.out.println("**************************  "+l+"  *********************************************************************");
            return;
        }
        System.out.println("----------------------------------   "+l+"   ---------------------------------------------------------------------------------");

        // ディスプレイの横幅を取得
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //int max = display.getWidth();
        Point size = new Point();
        display.getSize(size);
        //画面サイズ
        int screenWidth = size.x;
        int screenHeight = size.y;

        int margin = 0;
        // 一番最初は基点となるので何もしない
        View pline = rl.getChildAt(0);
        // 一行全体の長さ
        int total = pline.getWidth() + margin;
        for (int i = 1; i < l; i++) {
            int w = rl.getChildAt(i).getWidth() + margin;
            RelativeLayout.LayoutParams prm = (RelativeLayout.LayoutParams) rl
                    .getChildAt(i).getLayoutParams();
            // 横幅を超えないなら前のボタンの右に出す
            if (screenWidth > total + w) {
                total += w;
                prm.addRule(RelativeLayout.ALIGN_TOP, i);
                prm.addRule(RelativeLayout.RIGHT_OF, i);
            }
            // 超えたら下に出す
            else {
                prm.addRule(RelativeLayout.BELOW, pline.getId());
                // 基点を変更
                pline = rl.getChildAt(i);
                // 長さをリセット
                total = pline.getWidth() + margin;
            }
        }
        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
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
