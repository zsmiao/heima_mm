package xyz.itclay.heima_mm.service.impl.store;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.mapper.store.CatalogMapper;
import xyz.itclay.heima_mm.service.store.CatalogService;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.util.List;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/6 19:40
 **/
public class CatalogServiceImpl implements CatalogService {
    @Override
    public List<Catalog> findByPage(String pageNum, String pageSize) {
        CatalogMapper mapper = MyBatisUtils.getMapper(CatalogMapper.class);
        Integer pageN = 1;
        Integer pageS = 10;
        if (StringUtils.isNotBlank(pageNum)) {
            pageN = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)) {
            pageS = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(pageN, pageS);
        return mapper.findAll();
    }

    @Override
    public Catalog findById(String id) {
        CatalogMapper mapper = MyBatisUtils.getMapper(CatalogMapper.class);
        return mapper.findById(id);
    }
}
