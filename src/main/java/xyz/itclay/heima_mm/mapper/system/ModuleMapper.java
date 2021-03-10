package xyz.itclay.heima_mm.mapper.system;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:41
 **/
public interface ModuleMapper {
    @Select("select * from ss_module")
    @Results(id = "moduleMap", value = {
            @Result(id = true, column = "module_id", property = "moduleId"),
            @Result(property = "module", column = "parent_id", javaType = Module.class,
                    one = @One(select = "xyz.itclay.heima_mm.mapper.system.ModuleMapper.findByPid"))
    })
    List<Module> findAll();


    @Select("select * from ss_module where module_id=#{id}")
    @ResultMap("moduleMap")
    Module findByPid(String id);


    @Select("select\n" +
            "            module_id as id,\n" +
            "            parent_id as pId,\n" +
            "            name as name,\n" +
            "            case\n" +
            "                when module_id in (select module_id from ss_role_module where role_id = #{roleId})\n" +
            "                    then 'true'\n" +
            "                    else 'false'\n" +
            "                    end\n" +
            "                as checked\n" +
            "        from\n" +
            "            ss_module")
    List<Map> findAuthorDataByRid(String roleId);
}
