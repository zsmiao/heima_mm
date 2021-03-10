package xyz.itclay.heima_mm.service.impl.system;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.system.Module;
import xyz.itclay.heima_mm.mapper.system.ModuleMapper;
import xyz.itclay.heima_mm.service.system.ModuleService;
import xyz.itclay.heima_mm.utils.MyBatisUtils;
import xyz.itclay.heima_mm.utils.mail.MyAuthenticator;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:52
 **/
public class ModuleServiceImpl implements ModuleService {
    @Override
    public List<Module> findByPage(String pageNum, String pageSize) {
        ModuleMapper mapper = MyBatisUtils.getMapper(ModuleMapper.class);
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
    public List<Map> findAuthorDataByRid(String roleId) {
        ModuleMapper mapper = MyBatisUtils.getMapper(ModuleMapper.class);
        return mapper.findAuthorDataByRid(roleId);
    }

    @Override
    public Module findById(String id) {
        ModuleMapper mapper = MyBatisUtils.getMapper(ModuleMapper.class);
        return mapper.findByPid(id);
    }

    @Override
    public List<Module> findByAll() {
        ModuleMapper mapper = MyBatisUtils.getMapper(ModuleMapper.class);
        return mapper.findAll();
    }
}
