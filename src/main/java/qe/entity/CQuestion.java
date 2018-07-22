package qe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import qe.handler.ListTypeHandler;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

//选择题
public class CQuestion implements Question, Serializable {
    private int id;
    private String question;
    private List<String> choices;
    private int answer;

    @JsonIgnore
    @Override
    public int getAnswer() {
        return answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public CQuestion(String question, List<String> choices, int answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public CQuestion(int id, String question, String choices, int answer) {
        this.id = id;
        this.question = question;
        this.choices = Arrays.asList(choices.split(","));
        this.answer = answer;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", choices=" + choices +
                ", answer=" + answer +
                '}';
    }

    @Override
    public Question dropAnswer() {
        return new CQuestion(question, choices, -1);
    }
}
