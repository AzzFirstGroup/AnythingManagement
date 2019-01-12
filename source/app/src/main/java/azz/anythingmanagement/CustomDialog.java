package azz.anythingmanagement;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

        String dialogTag = this.getTag();
        switch(dialogTag) {
            case "dialog1":
                // タイトル設定
                dialogBuilder.setTitle("登録確認ダイアログ");
                // 表示する文章設定
                dialogBuilder.setMessage("登録しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // トーストを出す
                        Toast toast = Toast.makeText(getActivity(), "登録されました。", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "dialog2":
                // タイトル設定
                dialogBuilder.setTitle("画像取得ダイアログ");
                // 表示する文章設定
                dialogBuilder.setMessage("どのパターンのダイアログを表示しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("カメラ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // トーストを出す
                        Toast toast = Toast.makeText(getActivity(), "カメラ起動", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                // 登録確認ボタン作成
                dialogBuilder.setNegativeButton("フォルダを開く", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // トーストを出す
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

            case "dialog3":
                // タイトル設定
                dialogBuilder.setTitle("削除確認ダイアログ");
                // 表示する文章設定
                dialogBuilder.setMessage("削除しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // トーストを出す
                        Toast toast = Toast.makeText(getActivity(), "削除されました。", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                // NGボタン作成
                NgButton(dialogBuilder);

                break;

            case "dialog4":
                // タイトル設定
                dialogBuilder.setTitle("破棄確認ダイアログ");
                // 表示する文章設定
                dialogBuilder.setMessage("破棄しますか？");

                // 登録確認ボタン作成
                dialogBuilder.setPositiveButton("はい", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // トーストを出す
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