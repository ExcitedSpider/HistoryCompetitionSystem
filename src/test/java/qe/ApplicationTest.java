package qe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qe.entity.CQuestion;
import qe.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    private Log log = LogFactory.getLog(ApplicationTest.class);

    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void contextLoads() throws Exception {
        String q = "问题1";
        List<String> c = new ArrayList<>();
        c.add("选项1");c.add("选项2");c.add("选项3");c.add("选项4");
        int answer = 2;


        CQuestion cq = new CQuestion(q,c,answer);
        int id = questionMapper.insertCQ(cq);
        CQuestion ncq = questionMapper.getOneCQ(id);

        log.info(id);
        log.info(ncq.toString());
    }

}