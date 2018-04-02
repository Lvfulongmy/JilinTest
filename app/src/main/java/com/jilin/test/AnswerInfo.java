package com.jilin.test;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Ruthout on 2017/8/31.
 */

public class AnswerInfo extends DataSupport implements Serializable {
    private String answer_title;

    private String option;

    private String option_title;

    private boolean is_right;

    private int selected = -1;

    public String getAnswer_title() {
        return answer_title;
    }

    public void setAnswer_title(String answer_title) {
        this.answer_title = answer_title;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public boolean is_right() {
        return is_right;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }

    public void setOption_title(String option_title) {
        this.option_title = option_title;
    }

    public String getOption_title() {
        return this.option_title;
    }

    public void setIs_right(boolean is_right) {
        this.is_right = is_right;
    }

    public boolean getIs_right() {
        return this.is_right;
    }
}
