package com.tianlai.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.naivor.adapter.AdapterOperator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MultiItemActivity extends AppCompatActivity {
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MultiRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Multi Items");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter = new MultiRecyAdapter(getApplicationContext());
        adapter.setInnerListener(new AdapterOperator.InnerListener<SimpleItem>() {
            @Override
            public void onClick(View view, SimpleItem itemData, int postition) {
                Toast.makeText(getApplicationContext(),"我是编号："+postition+"  "+itemData.getContent(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, SimpleItem itemData, int postition) {
                Toast.makeText(getApplicationContext(),"我是编号："+postition+"  "+itemData.getContent()+"我被长按了",Toast.LENGTH_SHORT).show();
            }
        });

        rvContent.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvContent.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        List<SimpleItem> datas = new ArrayList<>();

        SimpleItem itemA = new SimpleItem();
        itemA.setContent("我是 A");
        itemA.setType(SimpleItem.Type.A);
        SimpleItem itemB = new SimpleItem();
        itemB.setContent("我是 B");
        itemB.setType(SimpleItem.Type.B);
        SimpleItem itemS = new SimpleItem();
        itemS.setContent("我是 S");
        itemS.setType(SimpleItem.Type.S);
        for (int i = 0; i < 10; i++) {
            datas.add(itemA);
            datas.add(itemB);
            datas.add(itemS);
        }

        adapter.setItems(datas);
    }

}
