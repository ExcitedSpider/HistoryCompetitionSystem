package qe.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import qe.entity.CQuestion;
import qe.entity.TFQuestion;
import qe.handler.ListTypeHandler;

public interface QuestionMapper {

    @Select("SELECT * FROM CQUESTION WHERE id = #{id}")
    @Results(
            @Result(column = "choices",property = "choices",typeHandler = ListTypeHandler.class)
    )
    @Cacheable("CQ")
    CQuestion getOneCQ(int id);

    @Insert("INSERT INTO CQUESTION (question, choices, answer) VALUES (#{question},#{choices, typeHandler=qe.handler.ListTypeHandler},#{answer})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertCQ(CQuestion question);

    @Select("SELECT * FROM tfquestion WHERE id = #{id}")
    @Cacheable("TFQ")
    TFQuestion getOneTFQ(int id);

    @Insert("INSERT INTO tfquestion (question, answer) VALUES(#{question},#{answer})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertTFQ(TFQuestion question);

    @Select("SELECT count(1) FROM CQUESTION")
    @Cacheable("AmountC")
    int countCQ();

    @Select("SELECT count(1) FROM tfquestion")
    @Cacheable("AmountTF")
    int countTFQ();
}
