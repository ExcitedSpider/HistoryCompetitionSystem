package qe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

//判断题
public class TFQuestion implements Question, Serializable{
    private int id;
    private String question;
    private int answer;

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public int getAnswer() {
        return answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public TFQuestion(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public TFQuestion(int id, String question, int answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public Question dropAnswer() {
        return new TFQuestion(question,-1);
    }
}
