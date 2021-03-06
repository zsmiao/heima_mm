package xyz.itclay.heima_mm.service.store;


import xyz.itclay.heima_mm.domain.store.Company;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/5 16:27
 **/
public interface CompanyService {
    /**
     * 查询所有公司
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 公司名称集合
     */
    List<Company> findByPage(String pageNum, String pageSize);

    /**
     * 添加企业
     *
     * @param company 新闻对象
     */
    void addCompany(Company company);

    /**
     * 根据id查询企业信息
     *
     * @param id id
     * @return 企业信息
     */
    Company findById(String id);

    /**
     * 修改企业信息
     *
     * @param company 企业信息
     */
    void updateCompany(Company company);

    /**
     * 根据id删除企业信息
     *
     * @param id id
     */
    void deleteCompany(String id);
}
