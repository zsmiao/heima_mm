package xyz.itclay.heima_mm.service.system;

import xyz.itclay.heima_mm.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/9 15:41
 **/
public interface ModuleService {
    List<Module> findByPage(String pageNum, String pageSize);

    List<Map> findAuthorDataByRid(String roleId);

    Module findById(String id);

    List<Module> findByAll();
}
