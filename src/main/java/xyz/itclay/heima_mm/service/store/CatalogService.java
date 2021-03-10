package xyz.itclay.heima_mm.service.store;

import xyz.itclay.heima_mm.domain.store.Catalog;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/6 19:35
 **/
public interface CatalogService {
    /**
     * 所有题目类型
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 题目类型集合
     */
    List<Catalog> findByPage(String pageNum, String pageSize);

    /**
     * 根据id查询题目类型
     * @param id id
     * @return 题目类型
     */
    Catalog findById(String id);

    List<Catalog> findAll();

    void addCatalog(Catalog catalog);
}
