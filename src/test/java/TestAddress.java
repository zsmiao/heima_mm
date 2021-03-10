import org.junit.Test;
import xyz.itclay.heima_mm.utils.AddressUtils;

public class TestAddress {
    @Test
    public void test01(){
        String realAddressByIP = AddressUtils.getRealAddressByIP("112.31.210.251");
        System.out.println(realAddressByIP);
    }
}
