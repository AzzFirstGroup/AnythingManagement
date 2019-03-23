package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import azz.anythingmanagement.common.common;
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
                RegistData registData = new RegistData();
                genre.setGenreName("Genre1");
                genre.setColorInfo("テストカラー");

                registData.setTitle("テストタイトル");
                registData.setTorokuFlg("1");
                registData.setImagePath("テスト画像パス");
                registData.setSystemDate("テストシステム日付");
                registData.setGenre("テストジャンル");
                registData.setEvaluate("4");
                registData.setMemo("テストメモ");

                Data data = new Data();

                data.registGenreData(genre,context);
                data.registRegistData(registData,context);
                //data.deleteGenreData(genre,context);
                //data.deleteRegistData(registData,context);
//                RegistData afterData = new RegistData();
//                afterData.setTitle("変更後タイトル");
//                afterData.setTorokuFlg("0");
//                afterData.setImagePath("変更後画像パス");
//                afterData.setSystemDate("変更後システム日付");
//                afterData.setGenre("変更後ジャンル");
//                afterData.setEvaluate("変更後評価");
//                afterData.setMemo("変更後メモ");
//                data.updateRegistData(registData,afterData,context);

                ArrayList<Genre> arrayList = new ArrayList<>();
                arrayList = data.getGenreList(context);
                Genre testGenre = new Genre();
                if(arrayList.size() > 0){
                    testGenre = arrayList.get(0);
                }

                ArrayList<RegistData> dataList = new ArrayList<>();
                dataList = data.getRegistDataList(context);
                RegistData registdata = new RegistData();
                if(dataList.size() > 0){
                    registdata = dataList.get(0);
                }

                String strGenre = testGenre.getGenreName() + testGenre.getColorInfo() + registdata.getTitle() + registdata.getEvaluate();
                ((TextView)findViewById(R.id.textView1)).setText(strGenre);
            }
        });
        // データ一覧
        Button dataListButton = findViewById(R.id.button5);
        dataListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DataListActivity.class);
                //intent.putExtra("genre", "Genre1");
                startActivity(intent);
            }
        });
        Button ferstButton = findViewById(R.id.button1);
        ferstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FirstActivity.class);
                startActivity(intent);
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

        Button dataRegistDetailButton = findViewById(R.id.button4);
        dataRegistDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DataRegistDetailActivity.class);
                intent.putExtra("mode", common.MODE_REGIST);
                //intent.putExtra("mode", common.MODE_DETAIL);
                //intent.putExtra("title", "ねこ");
                //intent.putExtra("genre", "テストジャンル");
                startActivity(intent);
            }
        });

        Button dataDetailButton = findViewById(R.id.button4);
        dataDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DataRegistDetailActivity.class);
                //intent.putExtra("mode", common.MODE_REGIST);
                intent.putExtra("mode", common.MODE_DETAIL);
                intent.putExtra("title", "ねこ");
                intent.putExtra("genre", "テストジャンル");
                startActivity(intent);
            }
        });
        Button diaLogButton = findViewById(R.id.button6_1);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"regist");
            }
        });
        diaLogButton = findViewById(R.id.button6_2);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"image");
            }
        });

        diaLogButton = findViewById(R.id.button6_3);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"delete");
            }
        });

        diaLogButton = findViewById(R.id.button6_4);
        diaLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getSupportFragmentManager(),"discard");
            }
        });
    }
    public void setResultView(boolean resultValue){
        if(resultValue){
            Intent intent = new Intent(getApplication(),GenreListActivity.class);
            startActivity(intent);
        }else{
            //何もしない
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
