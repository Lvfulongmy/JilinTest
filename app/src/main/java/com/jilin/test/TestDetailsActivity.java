package com.jilin.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jilin.test.base.BaseToolbarActivity;
import com.jilin.test.util.SPUtils;
import com.jilin.test.util.ToastUtils;
import com.jilin.test.util.rxbus.RxBus;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;

/**
* 考试详情
* @author Lvfl
* created at 2018/3/19 11:52
*/
public class TestDetailsActivity extends BaseToolbarActivity {

    @Bind(R.id.exam_pager)
    protected ViewPager exam_pager;
    @Bind(R.id.exam_progress_text)
    protected TextView exam_progress_text;
    @Bind(R.id.error_counts_text)
    protected TextView error_counts_text;
    private QuestionAdapter mQuestionAdapter;
    private List<QuestionInfo> question_list = new ArrayList<>();
    private List<QuestionInfo> error_question_list = new ArrayList<>();
    public static final String TAG = "TestDetailsActivity";
    public static final String ERROR_TAG = "Error_TestDetailsActivity";
    private Observable<AnswerInfo> type;
    private int total;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (question_list.size() == exam_pager.getCurrentItem() + 1) {
                ToastUtils.showShort("没啦");
            } else {
                exam_pager.setCurrentItem(exam_pager.getCurrentItem() + 1, true);
            }
        }
    };
    private Observable<String> error_type;

    public static void startActivity(Context contenxt){
        Intent intent =new Intent(contenxt,TestDetailsActivity.class);
        contenxt.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details_test_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initToolbar();
        mQuestionAdapter = new QuestionAdapter(getSupportFragmentManager());
        exam_pager.setAdapter(mQuestionAdapter);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationOnClickListener(v -> {
            SPUtils.put_cache(this,SPUtils.POSITION,exam_pager.getCurrentItem());
            this.finish();
        });
        ActionBarHelper helper = createActionBarHelper();
        helper.init();
        helper.setDisplayHomeAsUpEnabled(true);
        mtoolbar_title.setText("考试");
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        error_question_list = DataSupport.select().find(QuestionInfo.class);
        error_counts_text.setText(error_question_list.size()+"");
        type = RxBus.get().register(TAG,AnswerInfo.class);
        type.subscribe(answerInfo -> {
            if (answerInfo.getIs_right()) {
                ToastUtils.showShort("对了");
                total++;
                exam_progress_text.setText(total +  "/"  + question_list.size());
                mHandler.sendEmptyMessageDelayed(0, 500);
                return;
            }
            ToastUtils.showShort("错了");
        });
        error_type = RxBus.get().register(ERROR_TAG,String.class);
        error_type.subscribe(answerInfo -> {
            error_counts_text.setText((Integer.valueOf(error_counts_text.getText().toString())+1) +"");
            ToastUtils.showShort("加入错题本");
        });
        try {
            InputStream in = getAssets().open("test.json");
            ExamInfo anwerInfo = JSON.parseObject(inputStream2String(in), ExamInfo.class);
            question_list.addAll(anwerInfo.getData().getQuestion_list());
            int position = (int) SPUtils.get_cache(this,SPUtils.POSITION,0);
            mQuestionAdapter.notifyDataSetChanged();
            exam_pager.setCurrentItem(position , true);
            exam_progress_text.setText(total +  "/"  + question_list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    class QuestionAdapter extends FragmentStatePagerAdapter {

        public QuestionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return QuestionFragment.newInstance(question_list.get(position));
        }

        @Override
        public int getCount() {
            return question_list.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.put_cache(this,SPUtils.POSITION,exam_pager.getCurrentItem());
        RxBus.get().unregister(TAG,type);
    }
}
