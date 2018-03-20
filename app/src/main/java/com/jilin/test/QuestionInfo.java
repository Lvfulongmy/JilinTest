package com.jilin.test;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ruthout on 2017/8/31.
 */

public class QuestionInfo extends DataSupport implements Serializable {
    private String question_title;

    private String question_type;

    private String question_score;

    private List<AnswerInfo> question_answer;

    private boolean answering = true;

    public boolean isAnswering() {
        return answering;
    }

    public void setAnswering(boolean answering) {
        this.answering = answering;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_title() {
        return this.question_title;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_type() {
        return this.question_type;
    }

    public void setQuestion_score(String question_score) {
        this.question_score = question_score;
    }

    public String getQuestion_score() {
        return this.question_score;
    }

    public void setQuestion_answer(List<AnswerInfo> question_answer) {
        this.question_answer = question_answer;
    }

    public List<AnswerInfo> getQuestion_answer() {
        return this.question_answer;
    }
}
