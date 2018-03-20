package com.jilin.test;

import java.util.List;

/**
 * Created by Ruthout on 2017/8/31.
 */

public class ExamInfo {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private List<QuestionInfo> question_list;

        private String errorMsg,user_name;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setQuestion_list(List<QuestionInfo> question_list) {
            this.question_list = question_list;
        }

        public List<QuestionInfo> getQuestion_list() {
            return this.question_list;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return this.errorMsg;
        }
    }
}
