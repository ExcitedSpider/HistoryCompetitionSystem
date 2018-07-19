package qe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

//选择题
public class CQuestion implements Question, Serializable {
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


    @Override
    public Question dropAnswer() {
        return new CQuestion(question, choices, -1);
    }
}
