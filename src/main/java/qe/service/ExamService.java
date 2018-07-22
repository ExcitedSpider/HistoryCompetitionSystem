package qe.service;

import qe.entity.ExamPaper;
import qe.entity.Question;

import java.util.List;

public interface ExamService {

    ExamPaper getRandomPaper();

    int getFinalScore();
}
