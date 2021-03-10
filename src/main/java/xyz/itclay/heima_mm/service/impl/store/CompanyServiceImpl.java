package xyz.itclay.heima_mm.service.impl.store;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.Company;
import xyz.itclay.heima_mm.mapper.store.CompanyMapper;
import xyz.itclay.heima_mm.service.store.CompanyService;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/5 17:03
 **/
public class CompanyServiceImpl implements CompanyService {
    @Override
    public List<Company> findByPage(String pageNum, String pageSize) {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
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
    public void addCompany(Company company) {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
        company.setId(GuidUtil.getUuid());
        mapper.addCompany(company);
    }

    @Override
    public Company findById(String id) {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void updateCompany(Company company) {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
        mapper.updateCompany(company);
    }

    @Override
    public void deleteCompany(String id) {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
        mapper.deleteCompany(id);
    }

    @Override
    public List<Company> findAll() {
        CompanyMapper mapper = MyBatisUtils.getMapper(CompanyMapper.class);
        return mapper.findAll();
    }
}

