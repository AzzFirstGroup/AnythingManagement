package azz.anythingmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.Genre;
import azz.anythingmanagement.xmlData.RegistData;

public class DataRegistDetailActivity extends AppCompatActivity {

    Context context;
    Context contextThis;

    // 評価値（初期値：3）
    String evaluate = "3";

    TextView titleText;
    TextView memoText;
    TextView textDate;
    Spinner genreSpinner;
    ImageButton imageButton;

    private Uri m_uri;

    // 画像選択機能呼び出し時の戻り値確認用ID
    private static final int REQUEST_CHOOSER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.data_regist_detail);
        context = super.getApplicationContext();
        contextThis = this;

        titleText = (TextView)findViewById(R.id.titleName);
        memoText = (TextView)findViewById(R.id.memo);
        textDate = (TextView)findViewById(R.id.date);
        genreSpinner = (Spinner)findViewById(R.id.genrelist);

        // 現在日時の取得
        Date now = new Date(System.currentTimeMillis());
        // 日時フォーマット作成
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String nowText = sdf.format(now);

        // 日付表示
        textDate.setText(nowText);

        // ジャンル一覧取得
        Data data = new Data();
        ArrayList<Genre> genreList = data.getGenreList(this);
        ArrayList<String> nameList = new ArrayList();
        for (Genre genre : genreList) {
            nameList.add(genre.getGenreName());
        }

        // ドロップダウンリスト設定
        setSpinner(genreSpinner,nameList);

        // 評価の星を押下時のイベント設定
        final ImageButton imageButtonStar1 = (ImageButton)findViewById(R.id.imageStar1);
        final ImageButton imageButtonStar2 = (ImageButton)findViewById(R.id.imageStar2);
        final ImageButton imageButtonStar3 = (ImageButton)findViewById(R.id.imageStar3);
        final ImageButton imageButtonStar4 = (ImageButton)findViewById(R.id.imageStar4);
        final ImageButton imageButtonStar5 = (ImageButton)findViewById(R.id.imageStar5);
        imageButtonStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate = "1";
                imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
            }
        });
        imageButtonStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate = "2";
                imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
            }
        });
        imageButtonStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate = "3";
                imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
            }
        });
        imageButtonStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate = "4";
                imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
            }
        });
        imageButtonStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate = "5";
                imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_on);
            }
        });

        // （テスト用）登録ボタンが押下時の処理
        Button registButton = (Button)findViewById(R.id.torokuButton);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistData registData = new RegistData();

                registData.setTitle(titleText.getText().toString());
                registData.setMemo(memoText.getText().toString());
                registData.setEvaluate(evaluate);
                registData.setGenre(genreSpinner.getSelectedItem().toString());

                Data data = new Data();
                data.registRegistData(registData,context);

            }
        });

        // 遷移元画面で設定した、処理モード情報（登録or詳細）を取得
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        // データ詳細処理の場合
        if(mode.equals(common.MODE_DETAIL)){
            String genre = intent.getStringExtra("genre");
            String title = intent.getStringExtra("title");

            // 登録データ取得
            RegistData registData = data.getRegistData(this,title,genre);

            // タイトル設定
            titleText.setText(registData.getTitle());

            // メモ設定
            memoText.setText(registData.getMemo());

            // ジャンル名プルダウン表示
            int index = 0;
            // 遷移元画面から取得したジャンル名からプルダウンに設定したジャンル名リストを比較する
            for(;index < nameList.size(); index++){
                if(nameList.get(index).equals(genre)){
                    break;
                }
            }
            // 一致するジャンル名をプルダウンの初期値に設定する
            genreSpinner.setSelection(index);

            // 評価値設定
            switch (registData.getEvaluate()){
                case "1":
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                    break;
                case "2":
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                    break;
                case "3":
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                    break;
                case "4":
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                    break;
                case "5":
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_on);
                    break;
                default:
                    imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                    imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                    break;
            }

        }

        // 画像クリック時のイベント
        // TODO 連携準備が出来たら別途作成のダイアログ処理に置き換える
        imageButton = (ImageButton)findViewById(R.id.imageSet);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO パーミッションの確認処理

                showGallery();
            }
        });

    }

    private void showGallery(){
        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー用のIntent作成
        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if(requestCode == REQUEST_CHOOSER) {

            if(resultCode != RESULT_OK) {
                // キャンセル時
                return ;
            }

            Uri resultUri = (resultData != null ? resultData.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );

            // 画像を設定
            imageButton.setImageURI(resultUri);
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

    private void setSpinner(Spinner spinner, ArrayList<String> arr){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
