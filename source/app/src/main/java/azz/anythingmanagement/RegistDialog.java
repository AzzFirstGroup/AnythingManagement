package azz.anythingmanagement;


import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import azz.anythingmanagement.R;
import azz.anythingmanagement.TestActivity;

/*
 ジャンル作成用登録ダイアログ
 */
public class RegistDialog extends DialogFragment {

    String title="登録確認";

    boolean answer = false;

    private static final int REQUEST_CHOOSER = 1000;
    private Uri m_uri;
    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

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
                boolean returnValue = true;
                // MainActivityのインスタンスを取得
                GenreIns genreInsActivity = (GenreIns) getActivity();
                genreInsActivity.registProcess(returnValue);
            }
        });

        // NGボタン作成
        dialogBuilder.setNegativeButton("いいえ", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean returnValue = false;
                // MainActivityのインスタンスを取得
                GenreIns genreInsActivity = (GenreIns) getActivity();
                genreInsActivity.registProcess(returnValue);
                }
        });
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
}