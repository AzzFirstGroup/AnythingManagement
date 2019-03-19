package azz.anythingmanagement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class DataListActivity extends Activity {

    // Mapのキー
    private final String[] FROM = {"dataIndex", "dataTitle", "isDataReading"};
    // リソースのコントロールID
    private final int[] TO = {R.id.dataIndex, R.id.dataTitle, R.id.isDataReading};
    private Data data;
    private Context context;
    private List<Map<String, Object>> regDataMapList = null;
    private String genreName;

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

        // リストデータの生成
        // TODO :: contextの取得方法を確認する。
        //regDataMapList = convertRegistDataListToMapList(data.getRegistDataList(context));
        regDataMapList = convertRegistDataListToMapList(this.getMockRegistData());

        // ジャンル名設定
        dataGenreNameTextView.setText(this.genreName);

        // アダプターの設定
        MyAdapter adapter = new MyAdapter(DataListActivity.this,
                regDataMapList, R.layout.datalist_detail, FROM, TO);
        listView.setAdapter(adapter);

        // ListViewクリックイベント
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataListActivity.this.setTitle(String.valueOf(position) + "番目がクリックされました。");
                Log.i("onItemClick", position + "番目のクリックイベント dataTitle:" + regDataMapList.get(position).get("dataTitle"));
// ここに詳細画面移動処理を追加する。
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
                Log.i("OnItemLongClickListener", position + "番目の長押しクリックイベント dataTitle:" + regDataMapList.get(position).get("dataTitle"));
                // 削除ダイアログ表示
                CustomDialog dialog = new CustomDialog();

                // 削除処理
                 String dataTitle = regDataMapList.get(position).get("dataTitle").toString();
                 RegistData delData = new RegistData();
                 delData.setGenre(genreName);
                 delData.setTitle(dataTitle);
                 data = new Data();
                 data.deleteRegistData(delData,  context);

                return true;
            }
        });
    }

    /**
     * List<Map<String, String>>変換
     *
     * @param regDataList 登録データList
     * @return regDataMapList 登録データMapList
     */
    private List<Map<String, Object>> convertRegistDataListToMapList(ArrayList<RegistData> regDataList) {

        List<Map<String, Object>> regDataMapList = new ArrayList<Map<String, Object>>();

        int index = 1;
        for (RegistData regData : regDataList) {
            genreName = regData.getGenre();
            Map<String, Object> regDataMap = new HashMap<String, Object>();
            regDataMap.put("dataIndex", String.valueOf(index));
            regDataMap.put("dataTitle", regData.getTitle());
            if ("0".equals(regData.getTorokuFlg())) {
                regDataMap.put("isDataReading", false);
            } else {
                regDataMap.put("isDataReading", true);
            }
            regDataMapList.add(regDataMap);
            ++index;
        }

        return regDataMapList;
    }

    // カスタムアダプター
    private class MyAdapter extends SimpleAdapter {

        // 外部から呼び出し可能なマップ
        public Map<Integer, Boolean> checkList = new HashMap<>();

        public MyAdapter(Context context, List<? extends Map<String, ?>> data,
                         int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

            // 初期値を設定する
            for (int i = 0; i < data.size(); i++) {
                Map map = (Map) data.get(i);
                checkList.put(i, (Boolean) map.get("isDataReading"));
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
                    checkList.put(position, isChecked);
                    Log.i("onItemClick", position + "番目のチェックイベント dataTitle:" + regDataMapList.get(position).get("dataTitle"));
                    // 更新処理
                    String dataTitle = regDataMapList.get(position).get("dataTitle").toString();
                    RegistData updData = new RegistData();
                    updData.setGenre(genreName);
                    updData.setTitle(dataTitle);
                    updData.setTorokuFlg(isChecked ? "1" : "0");
                    data = new Data();
                    data.registRegistData(updData,  context);
                }
            });
            return view;
        }
    }

    // TODO ::  Mockなので後で消す。
    private ArrayList<RegistData> getMockRegistData() {
        ArrayList<RegistData> list = new ArrayList<RegistData>();

        for (int i = 0; i < 8; i++) {
            RegistData data = new RegistData();
            data.setGenre("Genre1");
            data.setTitle("タイトル" + String.valueOf(i));
            if (i == 1 || i == 3) {
                data.setTorokuFlg("1");
            } else {
                data.setTorokuFlg("0");
            }
            list.add(data);
        }

        return list;
    }

}
