import com.crcm.core.utils.RSAUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @ClassName NormalTest
 * @Description
 * @Copyright Copyright(c) 2021
 * @Company 中再云图技术有限公司
 * @Author diaoy
 * @Date 2021/6/11
 **/
public class NormalTest {
    @Test
    public void testKey() {
        try {
            Map<String, Object> map = RSAUtils.genKeyPair();
            System.out.println(RSAUtils.getPublicKey(map));
            System.out.println(RSAUtils.getPrivateKey(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecode() {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDT+DeESB8GTwh7KBhS344MAK6qA7HoqhwacYzkkOwlK2aig0zatLY6iHNpNGp2CO/XebR3CvrudqBEhMj+53Om7JxwGcyyv/aShEksIqBweCoUja/e5Flm6c2GSb/+AlcGxEMW2zs2ee0+6/6dEE81leIL8hcvZQsMTRg1a8RdCQIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANP4N4RIHwZPCHsoGFLfjgwArqoDseiqHBpxjOSQ7CUrZqKDTNq0tjqIc2k0anYI79d5tHcK+u52oESEyP7nc6bsnHAZzLK/9pKESSwioHB4KhSNr97kWWbpzYZJv/4CVwbEQxbbOzZ57T7r/p0QTzWV4gvyFy9lCwxNGDVrxF0JAgMBAAECgYEAlFVxVEq8G0gXYUfvrZ7v4opfsG4hg/X28LJhrFmWR3SVwEgt8ibgFxoB2a9qiOs8HAf5OA0hCNtO0GQz293QWcrs7VtleiySo+BQ+9bAx/N2ejXipDfEMzKJCGO9g1WwyEtDFe8eaNQWFLJlq+Tm3PvCmhfYaN8tBJGahw0vfpkCQQD7CtPRLyTXzG9fH9DshxEphkobSkiQG3NbsmwLp48sByG9aDgVLyWg1AkpPduDjS3cQg0wvM/sJraovPJCSpeTAkEA2CfaYCfPj8lSOrEka5T/aF7J1LDA3IGtc+RFdCyONlUWAK9NSMChpw6CtgYmPsjrQOxElUlEC3a3pYt/flRicwJAD7h12zugiwL6VnKfmrA6mucV7O+R67RMXQmnSnOLu64Wps3H9Uumh5O/nx2IiYxlus1IFi08uAUUmyx53zzckQJAPqPN9O+UhuagncGX/FVBbs2JT8mpGj26NkuAlRoUXEpsBebHTr4EGpVp67X8f1VaV4YgmeKRhYDx11eFquZpYQJAQHzOPDgmo7hNbqNCP0vPywB3w6thfZ3DWEaxjM4XUuPG4G30oc1LYt2EoJ5bfL5M6i2EcB/8uWHVW25eqVGO+w==";
        String hello_word = RSAUtils.encryptedDataOnJava("hello word", publicKey);
        System.out.println(hello_word);
        String s = RSAUtils.decryptDataOnJava(hello_word, privateKey);
        System.out.println(s);
    }

    @Test
    public void testSign() {
        try {
            Map<String, Object> map = RSAUtils.genKeyPair();
            String params = "a=1&b=2&c=3";
            String sign = RSAUtils.sign(params.getBytes(), RSAUtils.getPrivateKey(map));
            System.out.println(sign);
            System.out.println(RSAUtils.verify(params.getBytes(), RSAUtils.getPublicKey(map), sign));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
