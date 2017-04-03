package com.tianlai.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_list)
    Button btnList;
    @Bind(R.id.btn_grid)
    Button btnGrid;
    @Bind(R.id.btn_recycler)
    Button btnRecycler;
    @Bind(R.id.btn_header)
    Button btnHeader;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.btn_list, R.id.btn_grid, R.id.btn_recycler, R.id.btn_header,R.id.btn_multi,R.id.btn_complex})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list:
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.btn_grid:
                startActivity(new Intent(this, GridActivity.class));
                break;
            case R.id.btn_recycler:
                startActivity(new Intent(this, RecyclerActivity.class));
                break;
            case R.id.btn_header:
                startActivity(new Intent(this, HeaderFooterActivity.class));
                break;
            case R.id.btn_multi:
                startActivity(new Intent(this, MultiItemActivity.class));
                break;
            case R.id.btn_complex:
                startActivity(new Intent(this, ComplexActivity.class));
                break;
            default:

                break;
        }
    }
}
