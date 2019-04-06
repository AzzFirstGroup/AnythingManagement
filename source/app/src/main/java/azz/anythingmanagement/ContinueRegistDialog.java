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

import azz.anythingmanagement.DataRegistDetailActivity;
import azz.anythingmanagement.R;

/*
 データ登録用(連続登録確認)ダイアログ
 */
public class ContinueRegistDialog extends DialogFragment {

    String title = "登録しました";

    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);

        TextView textView;
        // タイトル部分を編集
        textView = TitleStyle(title);
        // タイトル部分を設定
        dialogBuilder.setCustomTitle(textView);
        // 表示する文章設定
        dialogBuilder.setMessage("続けて登録しますか？");

        // 登録確認ボタン作成
        dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean resultValue = true;
                // 選択結果をDataRegistDetailActivityへ渡す
                DataRegistDetailActivity dataRegistDetailActivity = (DataRegistDetailActivity) getActivity();
                dataRegistDetailActivity.continueProcess(resultValue);
            }
        });

        // NGボタン作成
        dialogBuilder.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean resultValue = false;
                // 選択結果をDataRegistDetailActivityへ渡す
                DataRegistDetailActivity dataRegistDetailActivity = (DataRegistDetailActivity) getActivity();
                dataRegistDetailActivity.continueProcess(resultValue);
            }
        });


        // dialogBulderを返す
        return dialogBuilder.create();
    }

    private TextView TitleStyle(String titleText) {
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