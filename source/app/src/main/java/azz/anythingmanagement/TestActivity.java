package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;

import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class TestActivity extends AppCompatActivity {

    private TextView mTextView;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.test_activity);

        context = super.getApplicationContext();



        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button xmlButton = findViewById(R.id.button9);
        xmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Genre genre = new Genre();
                genre.setGenreName("テストジャンル");
                genre.setColorInfo("テストカラー");

                RegistData registData = new RegistData();
                registData.setTitle("テストタイトル");
                registData.setRegistFlg("テスト登録フラグ");
                registData.setImagePath("テスト画像パス");
                registData.setSystemDate("テストシステム日付");
                registData.setGenre("テストジャンル");
                registData.setEvaluate("テスト評価");
                registData.setMemo("テストメモ");


                Data data = new Data();
                data.registGenre(genre,context);
                data.registData(registData,context);

                Genre testGenre = new Genre();
                testGenre = data.readGenre(testGenre,context);

                String strGenre = testGenre.getGenreName() + testGenre.getColorInfo();
                ((TextView)findViewById(R.id.textView1)).setText(strGenre);
            }
        });
        Button genreListButton = findViewById(R.id.button2);
        genreListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),GenreListActivity.class);
                startActivity(intent);
            }
        });
        Button diaLogButton = findViewById(R.id.button6_1);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"dialog1");
            }
        });
        diaLogButton = findViewById(R.id.button6_2);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"dialog2");
            }
        });

        diaLogButton = findViewById(R.id.button6_3);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"dialog3");
            }
        });

        diaLogButton = findViewById(R.id.button6_4);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"dialog4");
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