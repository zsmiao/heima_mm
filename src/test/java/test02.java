import org.junit.Test;
import xyz.itclay.heima_mm.domain.system.User;
import xyz.itclay.heima_mm.utils.excel.expose.ExcelTool;

public class test02 {
    @Test
    public void test() {
        ExcelTool excelTool = ExcelTool.$Local(User.class);
        System.out.println(excelTool);
    }
}
