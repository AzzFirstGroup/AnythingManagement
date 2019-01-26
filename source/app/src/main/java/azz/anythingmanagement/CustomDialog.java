package azz.anythingmanagement;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

    String title1="登録確認ダイアログ";
    String title2="画像取得ダイアログ";
    String title3="削除確認ダイアログ";
    String title4="破棄確認ダイアログ";


    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

        String dialogTag = this.getTag();
        switch(dialogTag) {
            case "dialog1": // 登録確認ダイアログ

                String title = title1;
                TextView textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
//                zdivider.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
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
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "dialog2": // 画像取得ダイアログ

                title = title2;
                textView = new TextView(getActivity());
                // タイトル部分を編集
                textView = TitleStyle(title);
                // タイトル部分を設定
                dialogBuilder.setCustomTitle(textView);
                // 表示する文章設定
                dialogBuilder.setMessage("どのパターンのダイアログを表示しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("カメラ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO カメラ起動処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "カメラ起動", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                // 登録確認ボタン作成
                dialogBuilder.setNegativeButton("フォルダを開く", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO フォルダを開く処理(現在：トーストを出すのみ)
                        Toast toast = Toast.makeText(getActivity(), "フォルダを開きます", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                dialogBuilder.setNeutralButton("キャンセル", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 何もしないで閉じる
                    }
                });
                break;

            case "dialog3": // 削除確認ダイアログ

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
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "dialog4": //破棄確認ダイアログ

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
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        //タイトルテキスト設定
        textView.setText(titleText);
        return textView;
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