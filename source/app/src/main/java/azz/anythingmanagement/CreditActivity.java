package azz.anythingmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreditActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトの呼び出し
        setContentView(R.layout.credit);
        // ボタンの設定
        findViewById(R.id.back_button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 各ボタンアクションで使用するintentをここで呼んでおく
        Intent intent;
        if (v.getId() == R.id.back_button1) {
            // TOP覧画面へ遷移する
            intent = new Intent (this, TopActivity.class);
            startActivity(intent);
        }
    }
}
