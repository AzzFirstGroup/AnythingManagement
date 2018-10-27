package azz.anythingmanagement;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_main);

        //set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //main.xmlの内容を読み込む
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView varTextView = (TextView) findViewById(R.id.textView);
        switch (item.getItemId()) {
            case R.id.action_menuList1:
                varTextView.setText(R.string.action_menuList1);
                return true;
            case R.id.action_menuList2:
                varTextView.setText(R.string.action_menuList2);
                return true;
            case R.id.action_menuList3:
                varTextView.setText(R.string.action_menuList3);
                return true;
            case R.id.action_menuList4:
                varTextView.setText(R.string.action_menuList4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
