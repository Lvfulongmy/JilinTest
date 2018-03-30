package com.jilin.test;

import android.os.Bundle;
import android.widget.TextView;

import com.jilin.test.base.BaseToolbarActivity;

import butterknife.Bind;

public class MainActivity extends BaseToolbarActivity {

    @Bind(R.id.first_text)
    protected TextView first_text;
    @Bind(R.id.second_text)
    protected TextView second_text;
    @Bind(R.id.three_text)
    protected TextView three_text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationOnClickListener(v -> {
            this.finish();
        });
        ActionBarHelper helper = createActionBarHelper();
        helper.init();
        helper.setDisplayHomeAsUpEnabled(true);
        mtoolbar_title.setText("考试");
    }

    @Override
    protected void initListeners() {
        first_text.setOnClickListener(v ->{
            TestDetailsActivity.startActivity(this,0,0);
        });
        second_text.setOnClickListener(v ->{
            TestDetailsActivity.startActivity(this,1,0);
        });
        three_text.setOnClickListener(v ->{
            TestDetailsActivity.startActivity(this,2,0);
        });
    }

    @Override
    protected void initData() {
    }
}
