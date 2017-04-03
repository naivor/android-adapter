package com.tianlai.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplexActivity extends AppCompatActivity {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;
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
    @Bind(R.id.btn_add_header)
    Button btnAddHeader;
    @Bind(R.id.btn_add_footer)
    Button btnAddFooter;
    @Bind(R.id.btn_rm_header)
    Button btnRmHeader;
    @Bind(R.id.btn_rm_footer)
    Button btnRmFooter;
    @Bind(R.id.btn_clear_header)
    Button btnClearHeader;
    @Bind(R.id.btn_clear_footer)
    Button btnClearFooter;

    private TestRecyAdapter adapter;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complex);
        ButterKnife.bind(this);

        inflater = LayoutInflater.from(getApplicationContext());

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recycler   Adapter");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter = new TestRecyAdapter(getApplicationContext());

        rvContent.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvContent.setAdapter(adapter);

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
    public void onTopClick(View v) {
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

    @OnClick({R.id.btn_add_header, R.id.btn_add_footer, R.id.btn_rm_header,
            R.id.btn_rm_footer, R.id.btn_clear_header, R.id.btn_clear_footer})
    public void onBottomClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_header:
                adapter.addHeaderView(createHeader());
                break;
            case R.id.btn_add_footer:
                adapter.addFooterView(createFooter());
                break;
            case R.id.btn_rm_header:
                adapter.removeHeaderView(0);
                break;
            case R.id.btn_rm_footer:
                adapter.removeFooterView(0);
                break;
            case R.id.btn_clear_header:
                adapter.clearHeader();
                break;
            case R.id.btn_clear_footer:
                adapter.clearFooter();
                break;
            default:
                break;

        }
    }

    public View createHeader() {
        View view = inflater.inflate(R.layout.list_item_txt, null);
        ((TextView) find(view, R.id.tv_text)).setText("我是头部");
        return view;
    }

    public View createFooter() {
        View view = inflater.inflate(R.layout.list_item_txt, null);
        ((TextView) find(view, R.id.tv_text)).setText("我是尾部");
        return view;
    }

    /**
     * 获取view
     *
     * @param viewId
     * @return
     */
    public View find(View parent, int viewId) {
        return parent.findViewById(viewId);
    }
}
