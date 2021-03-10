package xyz.itclay.heima_mm.mapper.store;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.itclay.heima_mm.domain.store.QuestionItem;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/7 9:33
 **/
public interface QuestionItemMapper {
    /**
     * 根据题目id查询选项
     *
     * @param questionId 题目id
     * @return 所有选项
     */
    @Select("select * from st_question_item where question_id=#{questionId}")
    List<QuestionItem> findAll(String questionId);

    @Update("<script>" +
            "update db_heima_mm.st_question_item\n" +
            "        <set>\n" +
            "            <if test=\"questionId != null and questionId != ''\">\n" +
            "                question_id = #{questionId},\n" +
            "            </if>\n" +
            "            <if test=\"content != null and content != ''\">\n" +
            "                content = #{content},\n" +
            "            </if>\n" +
            "            <if test=\"imgUrl != null and imgUrl != ''\">\n" +
            "                img_url = #{imgUrl},\n" +
            "            </if>\n" +
            "            <if test=\"isRight != null and isRight != ''\">\n" +
            "                is_right = #{isRight},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where id = #{id}" +
            "</script>")
    void update(QuestionItem questionItem);

    @Insert("insert into db_heima_mm.st_question_item(id,question_id, content, img_url, is_right)\n" +
            "        values (#{id},#{questionId}, #{content}, #{imgUrl}, #{isRight})")
    void save(QuestionItem questionItem);

    @Select("select * from st_question_item where id=#{id}")
    QuestionItem findById(String id);

    @Delete("  delete\n" +
            "        from db_heima_mm.st_question_item\n" +
            "        where id = #{id} ")
    void deleteQuestionItem(String id);
}
