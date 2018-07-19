package qe.entity;

import java.util.List;

public class ExamPaper {
    private int code;
    /** 1 - 成功
     *  0 - 失败
     **/
    private List<Question> questions;

    public ExamPaper(int code) {
        this.code = code;
    }

    public ExamPaper(int code, List<Question> questions) {
        this.code = code;
        this.questions = questions;
    }


    public int getCode() {
        return code;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
