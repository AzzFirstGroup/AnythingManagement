package azz.anythingmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import azz.anythingmanagement.common.common;
import azz.anythingmanagement.xmlData.RegistData;

/**
 * データ一覧画面Activity
 * <p>
 * ・データ登録画面のタイトル名を表示する。
 * ・ボタン押下で、データ詳細画面へ遷移する。
 * ・アイコンの長押しで削除の確認ダイアログを出力
 *
 * @author c.morita
 * @version 1.0
 */
public class DataListActivity extends AppCompatActivity {

    // Mapのキー
    private final String[] FROM = {"dataIndex", "dataTitle", "isDataReading"};
    // リソースのコントロールID
    private final int[] TO = {R.id.dataIndex, R.id.dataTitle, R.id.isDataReading};
    private Data data;
    private Context context;
    private List<Map<String, Object>> regDataMapList = null;
    private String intentGenreName = "";
    private DataListAdapter adapter;
    private String dataTitle = null;
    private String dataGenreName = null;

    /**
     * 画面一覧表示
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist);
        context = super.getApplicationContext();

        final ListView listView = findViewById(R.id.dataListView);
        final TextView dataGenreNameTextView = findViewById(R.id.dataGenreName);

        // ジャンル名設定
        Intent intent = getIntent();
        intentGenreName = intent.getStringExtra("genre");
        if (intentGenreName == null) {
            dataGenreNameTextView.setText("ジャンル未選択");
        } else {
            dataGenreNameTextView.setText(this.intentGenreName);
        }

        // リストデータの生成
        data = new Data();
        regDataMapList = convertRegistDataListToMapList(intentGenreName, data.getRegistDataList(this));

        // アダプターの設定
        adapter = new DataListAdapter(DataListActivity.this,
                regDataMapList, R.layout.datalist_detail, FROM, TO);
        listView.setAdapter(adapter);

        // ListViewクリックイベント
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // DataListActivity.this.setTitle(String.valueOf(position) + "番目がクリックされました。");
                Log.i("onItemClickNo.", position + " / " + regDataMapList.get(position).get("dataGenre") + " / " + regDataMapList.get(position).get("dataTitle"));
                // 詳細画面移動処理
                String dataTitle = regDataMapList.get(position).get("dataTitle").toString();
                String dataGenreName = regDataMapList.get(position).get("dataGenre").toString();
                Intent intent = new Intent(getApplication(), DataRegistDetailActivity.class);
                intent.putExtra("mode", common.MODE_DETAIL);
                intent.putExtra("genre", dataGenreName);
                intent.putExtra("title", dataTitle);
                startActivity(intent);
            }
        });
        // ListView長押しクリックイベント
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /**
             * @param parent ListView
             * @param view 選択した項目
             * @param position 選択した項目の添え字
             * @param id 選択した項目のID
             */
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("OnItemLongClick No.", position + " / " + regDataMapList.get(position).get("dataGenre") + " / " + regDataMapList.get(position).get("dataTitle"));

                dataTitle = regDataMapList.get(position).get("dataTitle").toString();
                dataGenreName = regDataMapList.get(position).get("dataGenre").toString();

                // 削除ダイアログ表示
                DeleteDateListDialog dialog = new DeleteDateListDialog();
                dialog.show(getSupportFragmentManager(),"DeleteDateList");

                return true;
            }
        });
        // 登録ボタン
        Button dataRegistDetailButton = findViewById(R.id.button_dataListToDataReg);
        dataRegistDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DataRegistDetailActivity.class);
                intent.putExtra("mode", common.MODE_REGIST);
                startActivity(intent);
            }
        });
    }

    public boolean deleteProcess(boolean dialogResult) {
        if(dialogResult) {
          // 削除処理
          RegistData delData = new RegistData();
          delData.setGenre(dataGenreName);
          delData.setTitle(dataTitle);
          data = new Data();
          data.deleteRegistData(delData, context);

          finish();
          startActivity(getIntent());
        }
        return true;
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

        // ジャンル一覧に遷移(メニューボタン)
        if (id == R.id.action_menuList1) {
            Intent intent = new Intent(this, GenreListActivity.class);
            startActivity(intent);
        }

        // ジャンル作成に遷移(メニューボタン)
        if (id == R.id.action_menuList2) {
            Intent intent = new Intent(this, GenreIns.class);
            startActivity(intent);
        }

        // 新規作成に遷移(メニューボタン)
        if (id == R.id.action_menuList3) {
            Intent intent = new Intent(this, DataRegistDetailActivity.class);
            intent.putExtra("mode", common.MODE_REGIST);
            startActivity(intent);
        }

        // データ一覧に遷移(メニューボタン)
        if (id == R.id.action_menuList4) {
            Intent intent = new Intent(this, DataListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * List<Map<String, String>>変換
     *
     * @param regDataList 登録データList
     * @return regDataMapList 登録データMapList
     */
    private List<Map<String, Object>> convertRegistDataListToMapList(String intentGenreName, ArrayList<RegistData> regDataList) {

        List<Map<String, Object>> regDataMapList = new ArrayList<Map<String, Object>>();

        int index = 1;
        if (intentGenreName != null) {
            // ジャンル名がある場合は、ジャンルで絞って表示
            for (RegistData regData : regDataList) {
                if (intentGenreName.equals(regData.getGenre())) {
                    Map<String, Object> regDataMap = new HashMap<String, Object>();
                    regDataMap.put("dataIndex", String.valueOf(index));
                    regDataMap.put("dataGenre", regData.getGenre());
                    regDataMap.put("dataTitle", regData.getTitle());
                    if (common.CHECKED.equals(regData.getTorokuFlg())) {
                        regDataMap.put("isDataReading", true);
                    } else {
                        regDataMap.put("isDataReading", false);
                    }
                    regDataMapList.add(regDataMap);
                    ++index;
                }
            }
        } else {
            // ジャンル名がない場合は、全件表示
            for (RegistData regData : regDataList) {
                Map<String, Object> regDataMap = new HashMap<String, Object>();
                regDataMap.put("dataIndex", String.valueOf(index) + " / " + regData.getGenre());
                regDataMap.put("dataGenre", regData.getGenre());
                regDataMap.put("dataTitle", regData.getTitle());
                if (common.CHECKED.equals(regData.getTorokuFlg())) {
                    regDataMap.put("isDataReading", true);
                } else {
                    regDataMap.put("isDataReading", false);
                }
                regDataMapList.add(regDataMap);
                ++index;
            }
        }

        return regDataMapList;
    }

    // カスタムアダプター
    private class DataListAdapter extends SimpleAdapter {

        // 外部から呼び出し可能なマップ
        public Map<Integer, Boolean> checkList = new HashMap<>();

        public DataListAdapter(Context context, List<? extends Map<String, ?>> data,
                               int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

            // 初期値を設定する
            for (Map<String, ?> mapData : data) {
                //  ジャンル未設定の場合、"1 / 未設定"などの様になっているため
                String index[] = String.valueOf(mapData.get("dataIndex")).split("/");
                // indexは"1"始まりのため -1 する
                checkList.put(Integer.valueOf(index[0].trim()) - 1, Boolean.valueOf(String.valueOf(mapData.get("isDataReading"))));
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            CheckBox ch = view.findViewById(R.id.isDataReading);

            // チェックの状態が変化した場合はマップに記憶する
            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkList.put(position, !isChecked);
                    Log.i("OnChecked No.", position + " / " + regDataMapList.get(position).get("dataGenre") + " / " + regDataMapList.get(position).get("dataTitle"));
                    // 更新処理
                    String dataTitle = regDataMapList.get(position).get("dataTitle").toString();
                    String dataGenreName = regDataMapList.get(position).get("dataGenre").toString();
                    RegistData updData = new RegistData();
                    updData.setGenre(dataGenreName);
                    updData.setTitle(dataTitle);
                    updData.setTorokuFlg(!isChecked ? "1" : "0");
                    data = new Data();
                    data.registRegistData(updData, context);
                }
            });
            return view;
        }
    }
}
