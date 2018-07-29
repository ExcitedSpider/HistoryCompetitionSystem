package qe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaperAnswer implements Serializable {

    private long paperID;
    /**
     * 先20选择后10判断，选择4分一题判断2分一题
     */
    private List<Integer> answers;


    public PaperAnswer(List<Integer> answers, long paperID) {
        this.answers = answers;
        this.paperID = paperID;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public long getPaperID() {
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
