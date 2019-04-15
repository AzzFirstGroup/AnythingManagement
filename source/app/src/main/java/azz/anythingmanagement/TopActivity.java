package azz.anythingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class TopActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウトの呼び出し
        setContentView(R.layout.top);

        // ボタンの設定
        findViewById(R.id.start_button1).setOnClickListener(this);
        findViewById(R.id.credit_button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 各ボタンアクションで使用するintentをここで呼んでおく
        Intent intent;
        switch (v.getId()) {
            // 機能画面へ遷移する
            case R.id.start_button1:
                intent = new Intent (this, FirstActivity.class);
                startActivity(intent);
                break;
            // クレジット画面に遷移する
            case R.id.credit_button1:
                intent = new Intent (this, CreditActivity.class);
                startActivity(intent);
        }
    }
}
