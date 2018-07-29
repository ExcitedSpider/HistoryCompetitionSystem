package qe.service;

import qe.entity.ExamPaper;
import qe.entity.PaperAnswer;
import qe.entity.Question;

import java.util.List;

public interface ExamService {

    ExamPaper getRandomPaper() throws Exception;

    int getFinalScore(PaperAnswer postAnswer);
}
