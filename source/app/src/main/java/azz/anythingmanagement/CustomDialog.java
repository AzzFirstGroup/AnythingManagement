package azz.anythingmanagement;


import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

    String title1="登録確認";
    String title2="画像取得";
    String title3="削除確認";
    String title4="作成中です";
    private static final int REQUEST_CHOOSER = 1000;
    private Uri m_uri;

    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

        String dialogTag = this.getTag();
        boolean resultValue = false;
        switch(dialogTag) {
            case "regist": // 登録確認ダイアログ

                String title = title1;
                TextView textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
                // タイトル部分を設定
                dialogBuilder.setCustomTitle(textView);

                // 表示する文章設定
                dialogBuilder.setMessage("登録しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 登録画面へ遷移(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "登録されました。", Toast.LENGTH_SHORT);
                        toast.show();
                         OkButton();
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "image": // 画像取得ダイアログ

                title = title2;
                textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
                // タイトル部分を設定
                dialogBuilder.setCustomTitle(textView);
                // 表示する文章設定
                dialogBuilder.setMessage("どのパターンのダイアログを表示しますか？");

                // カメラ起動確認ボタン作成
                dialogBuilder.setPositiveButton("カメラ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO カメラ起動処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "カメラ起動", Toast.LENGTH_SHORT);
                        toast.show();

                        //カメラの起動Intentの用意
                        String photoName = System.currentTimeMillis() + ".jpg";
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
                        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        m_uri = getContext().getContentResolver()
                                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

                        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
                        startActivityForResult(intent, REQUEST_CHOOSER);
                    }
                });

                // フォルダ開くボタン作成
                dialogBuilder.setNegativeButton("フォルダを開く", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO フォルダを開く処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "フォルダを開きます", Toast.LENGTH_SHORT);
                        toast.show();

                        Intent intentGallery;
                        if (Build.VERSION.SDK_INT < 19) {
                            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
                            intentGallery.setType("image/*");
                        } else {
                            intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intentGallery.setType("image/jpeg");
                        }
                        Intent intent = Intent.createChooser(intentGallery, "画像の選択");
                        startActivityForResult(intent, REQUEST_CHOOSER);
                    }

                });

                dialogBuilder.setNeutralButton("キャンセル", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 何もしないで閉じる
                    }
                });
                break;

            case "delete": // 削除確認ダイアログ

                title = title3;
                textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
                // タイトル部分を設定
                dialogBuilder.setCustomTitle(textView);
                // 表示する文章設定
                dialogBuilder.setMessage("削除しますか？");

                // 削除確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 削除処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "削除されました。", Toast.LENGTH_SHORT);
                        toast.show();
                        OkButton();
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "discard": //破棄確認ダイアログ

                title = title4;
                textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
                // タイトル部分を設定
                dialogBuilder.setCustomTitle(textView);
                // 表示する文章設定
                dialogBuilder.setMessage("破棄しますか？");

                // 破棄確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 破棄処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "破棄されました。", Toast.LENGTH_SHORT);
                        toast.show();
                        OkButton();
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;
        }

        // dialogBulderを返す
        return dialogBuilder.create();
    }

    private TextView TitleStyle(String titleText){
        // タイトル部分のTextView
        TextView textView = new TextView(getActivity());
        // タイトルの文字色
        textView.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light));
        //文字サイズ
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        //タイトルテキスト設定
        textView.setText(titleText);
        return textView;
    }

    private void OkButton(){
        // editTextの内容を元画面に反映する
        // editTextから値を取得
        boolean returnValue = true;
        // MainActivityのインスタンスを取得
        TestActivity mainActivity = (TestActivity) getActivity();
        mainActivity.setResultView(returnValue);
    }

    private void NgButton(AlertDialog.Builder dialogBuilder){
        // NGボタン作成
        dialogBuilder.setNegativeButton("いいえ", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 何もしないで閉じる
            }
        });
    }
}