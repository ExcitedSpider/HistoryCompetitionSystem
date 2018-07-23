package qe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaperAnswer implements Serializable {
    private List<Integer> answers;
    private int paperID;

    public PaperAnswer(List<Integer> answers, int paperID) {
        this.answers = answers;
        this.paperID = paperID;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public int getPaperID() {
        return paperID;
    }

    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

    @Override
    public String toString() {
        return "PaperAnswer{" +
                "answers=" + answers +
                ", paperID=" + paperID +
                '}';
    }
}
