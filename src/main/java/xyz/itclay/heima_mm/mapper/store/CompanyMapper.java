package xyz.itclay.heima_mm.mapper.store;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.itclay.heima_mm.domain.store.Company;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/5 17:05
 **/
public interface CompanyMapper {
    /**
     * 查询所有企业信息
     *
     * @return 所有企业
     */
    @Select("select * from st_company")
    List<Company> findAll();

    /**
     * 添加企业
     *
     * @param company 企业对象
     */
    @Insert(" insert into db_heima_mm.st_company(id,name, expiration_date, address, license_id, representative, phone,\n" +
            "                                           company_size, industry, remarks, state, city)\n" +
            "        values (#{id},#{name}, #{expirationDate}, #{address}, #{licenseId}, #{representative}, #{phone}, #{companySize},\n" +
            "                #{industry}, #{remarks}, #{state}, #{city})")
    void addCompany(Company company);

    /**
     * 根据id查询企业信息
     *
     * @param id id
     * @return 企业信息
     */
    @Select("select * from st_company where id=#{id}")
    Company findById(String id);

    /**
     * 根据id修改企业信息
     *
     * @param company 企业信息
     */
    @Update("<script>" +
            "update db_heima_mm.st_company\n" +
            "        <set>\n" +
            "            <if test=\"name != null and name != ''\">\n" +
            "                name = #{name},\n" +
            "            </if>\n" +
            "            <if test=\"expirationDate != null\">\n" +
            "                expiration_date = #{expirationDate},\n" +
            "            </if>\n" +
            "            <if test=\"address != null and address != ''\">\n" +
            "                address = #{address},\n" +
            "            </if>\n" +
            "            <if test=\"licenseId != null and licenseId != ''\">\n" +
            "                license_id = #{licenseId},\n" +
            "            </if>\n" +
            "            <if test=\"representative != null and representative != ''\">\n" +
            "                representative = #{representative},\n" +
            "            </if>\n" +
            "            <if test=\"phone != null and phone != ''\">\n" +
            "                phone = #{phone},\n" +
            "            </if>\n" +
            "            <if test=\"companySize != null and companySize != ''\">\n" +
            "                company_size = #{companySize},\n" +
            "            </if>\n" +
            "            <if test=\"industry != null and industry != ''\">\n" +
            "                industry = #{industry},\n" +
            "            </if>\n" +
            "            <if test=\"remarks != null and remarks != ''\">\n" +
            "                remarks = #{remarks},\n" +
            "            </if>\n" +
            "            <if test=\"state != null\">\n" +
            "                state = #{state},\n" +
            "            </if>\n" +
            "            <if test=\"city != null and city != ''\">\n" +
            "                city = #{city},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where id = #{id}" +
            "</script>")
    void updateCompany(Company company);

    /**
     * 根据id删除企业信息
     *
     * @param id id
     */
    @Delete("delete\n" +
            "        from db_heima_mm.st_company\n" +
            "        where id = #{id}")
    void deleteCompany(String id);
}
