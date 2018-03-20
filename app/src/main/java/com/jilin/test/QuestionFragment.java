package com.jilin.test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jilin.test.base.adapter.recycleview.CommonAdapter;
import com.jilin.test.base.adapter.recycleview.MultiItemTypeAdapter;
import com.jilin.test.base.adapter.recycleview.base.ViewHolder;
import com.jilin.test.util.ToastUtils;
import com.jilin.test.util.rxbus.RxBus;

/**
 * a simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    private static final String TAG = "QuestionFragment";
    private TextView check_answer;
    private TextView join_error;

    public static QuestionFragment newInstance(QuestionInfo questionInfo) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(TAG, questionInfo);
        fragment.setArguments(args);
        return fragment;
    }

    private QuestionInfo mQuestionInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuestionInfo = (QuestionInfo) getArguments().getSerializable(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    private int[] options = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    private TextView question_content_text;
    private RecyclerView answer_recycler;
    private CommonAdapter<AnswerInfo> mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        question_content_text = (TextView) view.findViewById(R.id.question_content_text);
        question_content_text.setText(mQuestionInfo.getQuestion_title());

        check_answer = (TextView) view.findViewById(R.id.check_answer);
        join_error = (TextView) view.findViewById(R.id.join_error);
        answer_recycler = (RecyclerView) view.findViewById(R.id.answer_recycler);
        answer_recycler.setVisibility(View.GONE);
        answer_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonAdapter<AnswerInfo>(getContext(), R.layout.answer_item, mQuestionInfo.getQuestion_answer()) {
            @Override
            protected void convert(ViewHolder holder, AnswerInfo answerInfo, int position) {
                holder.setText(R.id.answer_title_text, answerInfo.getOption_title());
                holder.setTag(R.id.option_image, answerInfo.getOption());
                if (mQuestionInfo.isAnswering()) {
                    holder.setBackgroundRes(R.id.option_image, options[position]);
                } else {
                    if (-1 == answerInfo.getSelected()) {
                        holder.setBackgroundRes(R.id.option_image, options[position]);
                    } else {
                        holder.setBackgroundRes(R.id.option_image, answerInfo.getIs_right() ? R.drawable.answer_right : R.drawable.answer_error);
                    }
                }
            }
        };
        answer_recycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mQuestionInfo.isAnswering()) {
                    mQuestionInfo.setAnswering(false);
                    AnswerInfo answerInfo = mQuestionInfo.getQuestion_answer().get(position);
                    answerInfo.setSelected(position);
                    if (answerInfo.getIs_right()) {
                        answer_recycler.findViewWithTag(mQuestionInfo.getQuestion_answer().get(position).getOption()).setBackgroundResource(R.drawable.answer_right);
                    } else {
                        answer_recycler.findViewWithTag(mQuestionInfo.getQuestion_answer().get(position).getOption()).setBackgroundResource(R.drawable.answer_error);
                        for (int i = 0; i < mQuestionInfo.getQuestion_answer().size(); i++) {
                            if (mQuestionInfo.getQuestion_answer().get(i).getIs_right()) {
                                answer_recycler.findViewWithTag(mQuestionInfo.getQuestion_answer().get(i).getOption()).setBackgroundResource(R.drawable.answer_right);
                                mQuestionInfo.getQuestion_answer().get(i).setSelected(i);
                                break;
                            }
                        }
                    }
                    RxBus.get().post(TestDetailsActivity.TAG, answerInfo);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        check_answer.setOnClickListener(v -> {
            answer_recycler.setVisibility(View.VISIBLE);
        });
        join_error.setOnClickListener(v -> {
            ToastUtils.showShort("加入错题本");
        });

    }
}
