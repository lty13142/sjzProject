import com.crcm.core.utils.SM4Util;
import org.junit.jupiter.api.Test;

/**
 * @ClassName MyTest
 * @Description 测试
 * @Author GZL
 * @Date 2023/2/10 14:01
 * @Version 1.0
 **/
public class MyTest {

    @Test
    public void SM4UtilTest() {
        System.out.println(SM4Util.decryptStr("1111"));
    }
}
