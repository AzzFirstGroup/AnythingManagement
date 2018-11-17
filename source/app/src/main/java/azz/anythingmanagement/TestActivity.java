package azz.anythingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class TestActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.test_activity);

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
        data.registGenre(genre,super.getApplicationContext());
        data.registData(registData,super.getApplicationContext());

        Genre testGenre = new Genre();
        testGenre = data.readGenre(testGenre,super.getApplicationContext());

        String strGenre = testGenre.getGenreName() + testGenre.getColorInfo();
        ((TextView)findViewById(R.id.textView1)).setText(strGenre);

        //set toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button xmlButton = findViewById(R.id.button9);
        xmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
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
