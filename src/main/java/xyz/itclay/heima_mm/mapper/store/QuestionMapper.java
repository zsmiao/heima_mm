package xyz.itclay.heima_mm.mapper.store;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.domain.store.Company;
import xyz.itclay.heima_mm.domain.store.Question;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/7 9:33
 **/
public interface QuestionMapper {

    @Select("select * from st_question order by create_time desc")
    @Results(id = "questionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "company", javaType = Company.class, column = "company_id",
                    one = @One(select = "xyz.itclay.heima_mm.mapper.store.CompanyMapper.findById")),
            @Result(property = "catalog", javaType = Catalog.class, column = "catalog_id",
                    one = @One(select = "xyz.itclay.heima_mm.mapper.store.CatalogMapper.findById"))
    })
    List<Question> findAll();


    @Select("select * from st_question where id=#{id}")
    @ResultMap(value = "questionMap")
    Question findById(String id);

    @Update("<script> " +
            "update db_heima_mm.st_question\n" +
            "        <set>\n" +
            "            <if test=\"catalogId != null and catalogId != ''\">\n" +
            "                catalog_id = #{catalogId},\n" +
            "            </if>\n" +
            "            <if test=\"catalogName != null and catalogName != ''\">\n" +
            "                catalog_name = #{catalogName},\n" +
            "            </if>\n" +
            "            <if test=\"courseId != null and courseId != ''\">\n" +
            "                course_id = #{courseId},\n" +
            "            </if>\n" +
            "            <if test=\"courseName != null and courseName != ''\">\n" +
            "                course_name = #{courseName},\n" +
            "            </if>\n" +
            "            <if test=\"number != null and number != ''\">\n" +
            "                number = #{number},\n" +
            "            </if>\n" +
            "            <if test=\"subject != null and subject != ''\">\n" +
            "                subject = #{subject},\n" +
            "            </if>\n" +
            "            <if test=\"type != null and type != ''\">\n" +
            "                type = #{type},\n" +
            "            </if>\n" +
            "            <if test=\"difficulty != null and difficulty != ''\">\n" +
            "                difficulty = #{difficulty},\n" +
            "            </if>\n" +
            "            <if test=\"analysis != null and analysis != ''\">\n" +
            "                analysis = #{analysis},\n" +
            "            </if>\n" +
            "            <if test=\"analysisVideo != null and analysisVideo != ''\">\n" +
            "                analysis_video = #{analysisVideo},\n" +
            "            </if>\n" +
            "            <if test=\"remark != null and remark != ''\">\n" +
            "                remark = #{remark},\n" +
            "            </if>\n" +
            "            <if test=\"isClassic != null and isClassic != ''\">\n" +
            "                is_classic = #{isClassic},\n" +
            "            </if>\n" +
            "            <if test=\"state != null and state != ''\">\n" +
            "                state = #{state},\n" +
            "            </if>\n" +
            "            <if test=\"reviewStatus != null and reviewStatus != ''\">\n" +
            "                review_status = #{reviewStatus},\n" +
            "            </if>\n" +
            "            <if test=\"createBy != null and createBy != ''\">\n" +
            "                create_by = #{createBy},\n" +
            "            </if>\n" +
            "            <if test=\"createDept != null and createDept != ''\">\n" +
            "                create_dept = #{createDept},\n" +
            "            </if>\n" +
            "            <if test=\"createTime != null\">\n" +
            "                create_time = #{createTime},\n" +
            "            </if>\n" +
            "            <if test=\"updateBy != null and updateBy != ''\">\n" +
            "                update_by = #{updateBy},\n" +
            "            </if>\n" +
            "            <if test=\"updateTime != null\">\n" +
            "                update_time = #{updateTime},\n" +
            "            </if>\n" +
            "            <if test=\"companyId != null and companyId != ''\">\n" +
            "                company_id = #{companyId},\n" +
            "            </if>\n" +
            "            <if test=\"companyName != null and companyName != ''\">\n" +
            "                company_name = #{companyName},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where id = #{id}" +
            "</script>")
    void updateQuestion(Question question);


    @Insert("insert into db_heima_mm.st_question(id,catalog_id, catalog_name, course_id, course_name, number, subject, type,\n" +
            "                                            difficulty, analysis, analysis_video, remark, is_classic, state,\n" +
            "                                            review_status, create_by, create_dept, create_time, update_by, update_time,\n" +
            "                                            company_id, company_name)\n" +
            "        values (#{id},#{catalogId}, #{catalogName}, #{courseId}, #{courseName}, #{number}, #{subject}, #{type}, #{difficulty},\n" +
            "                #{analysis}, #{analysisVideo}, #{remark}, #{isClassic}, #{state}, #{reviewStatus}, #{createBy},\n" +
            "                #{createDept}, #{createTime}, #{updateBy}, #{updateTime}, #{companyId}, #{companyName})")
    void addQuestion(Question question);
}
