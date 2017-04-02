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

public class HeaderFooterActivity extends AppCompatActivity {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private TestRecyAdapter adapter;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_header_footer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recycler   HeaderFooter");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inflater = LayoutInflater.from(getApplicationContext());

        adapter = new TestRecyAdapter(getApplicationContext());

        rvContent.setLayoutManager(new LinearLayoutManager(this));
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

    @OnClick({R.id.btn_add_header, R.id.btn_add_footer, R.id.btn_rm_header,
            R.id.btn_rm_footer, R.id.btn_clear_header, R.id.btn_clear_footer})
    public void onClick(View v) {
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
