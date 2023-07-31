import cn.hutool.core.lang.UUID;
import com.crcm.admin.constants.CharGenerateRule;
import com.crcm.admin.utils.CharGenerateUtil;
import com.crcm.core.utils.SM4Util;
import org.junit.jupiter.api.Test;

/**
 * @ClassName NormalTest
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/11/29
 **/
public class NormalTest {
    @Test
    public void genUUID() {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        System.out.println(CharGenerateUtil.generateSpecialChar(7));
        System.out.println(CharGenerateUtil.generateRoleString(CharGenerateRule.LETTER_NUMBER_CHARACTER ,3,4,1));
        System.out.println(SM4Util.encryptBase64("zzyt@123"));
        System.out.println(SM4Util.encryptBase64("gzl"));
        System.out.println(SM4Util.decryptStr("qC+Tc9UFPpKu5KLd0dGQm5x5FD4KNV01TKHN4hooo03ooV29LvL7Zj7KyS7aVObq1MMcyPR3X4SawrelqSu92IvAAIfiILSALNqlLEykBgR3KF33jdYRyVRqmEV9v8zcoRAIlm461mn2vCCedW2hEG2YyoCdUydPtL2s/ZIzBhDUBAJPmKmel+F4GnF3zeOiRnonWfo7s5TCR148HQscIrsRan0JBw3rlqzKJTa2o/PuKTUVN7G386VSuqKtLEt2aL9AjQ4syXqpcGSexjbuB64jXiItBpzyeJWzkyFsD5AvChN5g1mikv7krojWe+FHumNyu/eVMLTEFfTtfBvC8K1CjmQY65uQJANqxuZ5OxtwGo9V4Fto1/99ihSrRywGAQxstwkbyKdnEbPwJar4nJav02/NJ2wMYPWWS5EK81b+5mowoUYmm8hz1v2PkSD+hm2f1f7Kl9T7kZ0B6zEQqwL2yDaBDoTaOZ0Ck6yxpr8FSjPqk5E9VExddHEG8OTgydBG7ihDxN1UvGfgvGLP/7NDMI31//lq0dpWNxfBo/sm+kiS6dVQXv4dKmO6TOUa3h8Sp/PRtYfX4RhIhh1SJ0kqynS4+ISiXf4LCblr16/jbmkuHHOoVTTvPuFMMvaQx6BW4aI3yATx4IOtqoiDXVWCL+WSCdxyWcKwPHN3D1JKRFZKnEhGfhgQMBBGq61ORZl+pMy2d/L7jVjpgjU1d0ZBdu2QTb1EW+K45FoHwj/IjNuR/b24A08JaYZwUf2aj9XjCPlbZtMq4U5MQBAtKbe5I7m8Uwxx89Tw1XKJWLIujO2onhXo1WioHcP01zj5fdS9rPEJF2L71I+2/rSQT8w8WjkoJB1oeLtn5oNHX4xg7Ku4yjQK9mAI3ZOREAE7sCNaTcxfZnQU+35ICfN8qMKfzpi1Px0aFg6Qiqsy8N8OWOUU/9w29SoOPsDXI1l3DmMp6nr864bXNN5bBrRETHlF73f8CPplWkSEoTth9EYfBakbBm8EFecdDxGgep/kSMn4oUWMFqr/0BL6K3pDzLy0mg2pX1SRt8myodA57TiGDEC6dfNqOn21A3QXlGmKTy+bvmpN4t6ilxQHSEahcN3WjoD0EhxkPA3Kmd61U+uNjKaEIajmUqCIpuZui3pWcPL/DRZEal4hZ8wIDfSG1F4TPWMiIalWJ08v3stwvq5Ksc654w1Zj0Xfg/hA1dcoAztY4mLQCLQiqgXNYwuX8g=="));
    }
}
