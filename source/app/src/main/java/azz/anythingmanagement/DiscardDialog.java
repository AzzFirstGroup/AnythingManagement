package azz.anythingmanagement;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.widget.Adapter;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

/*
 破棄確認ダイアログ
 */
public class DiscardDialog extends DialogFragment {

    String title = "作成中です";

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
        dialogBuilder.setMessage("作成中の内容を破棄し、この画面から移動しますか？");

        // 削除確認ボタン作成
        dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO 登録処理(現在：トーストを出すのみ)
                Toast toast = Toast.makeText(getActivity(), "破棄します。", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // NGボタン作成
        dialogBuilder.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 何もしない
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