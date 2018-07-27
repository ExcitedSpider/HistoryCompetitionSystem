package qe.entity;

import java.util.List;

public class ExamPaper {
    private int code;
    /** 1 - 成功
     *  0 - 失败
     **/
    private long paperID;

    private List<Question> questions;



    public ExamPaper(int code) {
        this.code = code;
    }

    public ExamPaper(int code, List<Question> questions) {
        this.code = code;
        this.questions = questions;
    }

    public ExamPaper(int code, List<Question> questions, long papaerID) {
        this.code = code;
        this.questions = questions;
        this.paperID = papaerID;
    }

    public long getPaperID() {
        return paperID;
    }

    public void setPaperID(int papaerID) {
        this.paperID = papaerID;
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