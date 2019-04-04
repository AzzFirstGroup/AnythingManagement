package azz.anythingmanagement;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
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
    String evaluate;

    TextView titleText;
    TextView memoText;
    TextView textDate;
    Spinner genreSpinner;
    ImageButton imageButton;
    CheckBox zumiCheck;

    private Uri m_uri;
    private Uri imageUri;

    private boolean permissionCameraResult = false;
    private boolean permissionReadDataResult = false;

    // 画像選択機能呼び出し時の戻り値確認用ID
    private static final int REQUEST_CHOOSER = 1000;

    private static final String GENRE_DEFAULT = common.GENRE_UNSET;

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
        imageButton = (ImageButton)findViewById(R.id.imageSet);
        zumiCheck = (CheckBox)findViewById(R.id.torokucheckBox);

        // 入力要素があるものの背景色を白に変更
        titleText.setBackgroundColor(Color.WHITE);
        memoText.setBackgroundColor(Color.WHITE);
        genreSpinner.setBackgroundColor(Color.WHITE);
        zumiCheck.setBackgroundColor(Color.WHITE);

        // 現在日時の取得
        Date now = new Date(System.currentTimeMillis());
        // 日時フォーマット作成
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String nowText = sdf.format(now);

        // チェックボックス初期値設定（未設定：false）
        zumiCheck.setChecked(false);

        // 評価値初期設定（=3）
        evaluate = "3" ;

        // 日付表示
        textDate.setText(nowText);

        // ジャンル一覧取得
        Data data = new Data();
        ArrayList<Genre> genreList = data.getGenreList(this);
        ArrayList<String> nameList = new ArrayList();
        nameList.add(GENRE_DEFAULT);
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

        // （テスト用）登録ボタン押下時の処理
        Button registButton = (Button)findViewById(R.id.torokuButton);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleText.getText().toString() == null || titleText.getText().toString().equals("")){
                    Toast.makeText(context,"タイトルを入力してください",Toast.LENGTH_LONG).show();
                } else {
                    RegistData registData = new RegistData();

                    registData.setTitle(titleText.getText().toString());
                    registData.setMemo(memoText.getText().toString());
                    registData.setEvaluate(evaluate);
                    registData.setGenre(genreSpinner.getSelectedItem().toString());
                    if(imageUri != null){
                        registData.setImagePath(imageUri.toString());
                    }

                    if(zumiCheck.isChecked()){
                        registData.setTorokuFlg(common.CHECKED);
                    }else{
                        registData.setTorokuFlg(common.UNCHECKED);
                    }

                    Data data = new Data();
                    data.registRegistData(registData,context);
                }
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

            // 未済チェックボックス設定
            if(registData.getTorokuFlg().equals(common.CHECKED)){
                zumiCheck.setChecked(true);
            }else{
                zumiCheck.setChecked(false);
            }

            // メモ設定
            memoText.setText(registData.getMemo());

            // ジャンル名プルダウン表示
            int index = 0;
            boolean genreSetFlg = false;
            // 遷移元画面から取得したジャンル名からプルダウンに設定したジャンル名リストを比較する
            for(;index < nameList.size(); index++){
                if(nameList.get(index).equals(genre)){
                    genreSetFlg = true;
                    break;
                }
            }
            // 一致するジャンル名をプルダウンの初期値に設定する
            if(genreSetFlg){
                genreSpinner.setSelection(index);
            }else{
                genreSpinner.setSelection(0);
            }


            // 評価値設定
            if(registData.getEvaluate() != null){
                switch (registData.getEvaluate()){
                    case "1":
                        evaluate = "1";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                        break;
                    case "2":
                        evaluate = "2";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                        break;
                    case "3":
                        evaluate = "3";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                        break;
                    case "4":
                        evaluate = "4";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                        break;
                    case "5":
                        evaluate = "5";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_on);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_on);
                        break;
                    default:
                        evaluate = "0";
                        imageButtonStar1.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar2.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar3.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar4.setImageResource(android.R.drawable.btn_star_big_off);
                        imageButtonStar5.setImageResource(android.R.drawable.btn_star_big_off);
                        break;
                }
            }

            // 画像設定
            if(registData.getImagePath() != null){
                imageUri = Uri.parse(registData.getImagePath());
                imageButton.setImageURI(imageUri);
            }

        }

        // 画像クリック時のイベント
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  パーミッションの確認処理
                int permissionCheckCamera = ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA);
                int permissionCheckReadStrage = ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE);

                // カメラ使用か外部ストレージ使用のパーミッションが許可されていない場合、
                if(permissionCheckCamera != PackageManager.PERMISSION_GRANTED || permissionCheckReadStrage != PackageManager.PERMISSION_GRANTED){
                    final int REQUEST_CODE = 1;
                    ActivityCompat.requestPermissions(DataRegistDetailActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE);
                    // カメラ使用と外部ストレージ使用のパーミッションが許可された場合
                    if(permissionCameraResult && permissionReadDataResult){
                        showGallery();
                    }
                }else{
                    showGallery();
                }

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
            imageUri = resultUri;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final int REQUEST_CODE = 1;
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                final String permission = permissions[i];
                final int grantResult = grantResults[i];

                switch (permission) {
                    case Manifest.permission.CAMERA:
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //カメラ使用許可
                            permissionCameraResult = true;
                        }
                        break;
                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //外部ストレージ使用許可
                            permissionReadDataResult = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
    boolean dialogResult = false; // Dialogから取得したboolean用の変数
    public void setResultView(boolean resultValue) {
        Context context = getApplicationContext();
        dialogResult = resultValue; // Dialogからの戻り値を設定
        if (dialogResult) {
            Toast toast = Toast.makeText(context, "登録します。", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //何もしない
        }
    }
}
