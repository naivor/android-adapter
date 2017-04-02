package com.tianlai.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    @Bind(R.id.lv_content)
    ListView lvContent;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_adds)
    Button btnAdds;
    @Bind(R.id.btn_replace)
    Button btnReplace;
    @Bind(R.id.btn_remove)
    Button btnRemove;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private TestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ListView   Adapter");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter=new TestListAdapter(getApplicationContext());

        lvContent.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        List<SimpleItem> datas = new ArrayList<>();

        SimpleItem item = new SimpleItem();
        item.setContent("我是RecyclerView 的 Item");
        for (int i = 0; i < 10; i++) {
            datas.add(item);
        }

        adapter.setItems(datas);
    }

    @OnClick({R.id.btn_add, R.id.btn_adds, R.id.btn_remove,
            R.id.btn_replace, R.id.btn_clear})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                adapter.addItem(0, createNewItem());
                break;
            case R.id.btn_adds:
                adapter.addItems(0, createNewItems());
                break;
            case R.id.btn_replace:
                adapter.replaceItem(0, createReplaceItem());
                break;
            case R.id.btn_remove:
                adapter.removeItem(0);
                break;
            case R.id.btn_clear:
                adapter.clearItems();
                break;

            default:
                break;

        }
    }

    public SimpleItem createNewItem() {
        SimpleItem simpleItem = new SimpleItem();
        simpleItem.setContent("我是 新的item");
        return simpleItem;
    }

    public List<SimpleItem> createNewItems() {
        List<SimpleItem> items = new ArrayList<>();

        SimpleItem simpleItem = new SimpleItem();
        simpleItem.setContent("我是 新的item");

        for (int i = 0; i < 10; i++) {
            items.add(simpleItem);
        }
        return items;
    }

    public SimpleItem createReplaceItem() {
        SimpleItem simpleItem = new SimpleItem();
        simpleItem.setContent("我是 用来替换的 item");
        return simpleItem;
    }
}
