package com.sjz.partbuilding.constants;


import com.crcm.core.annotation.ChName;

/**
 * @ClassName：YTDicConstant
 * @Description：
 * @Copyright：Copyright(c) 2019
 * @Company：中再云图科技有限公司
 * @Author：Administrator
 * @Date：2019/12/13
 **/
public class YTDicConstant {

    @ChName("性别")
    public interface SEX {
        String CODE = "SEX";
    }

    @ChName("学历")
    public interface EDUCATION {
        String CODE = "EDUCATION";
    }
    @ChName("反馈类型")
    public interface FEED_BACK_TYPE {
        String CODE = "FEED_BACK_TYPE";
    }
    @ChName("设备类型")
    public interface DEVICE_TYPE {
        String CODE = "DEVICE_TYPE";
    }
    @ChName("民族")
    public interface NATION {
        String CODE = "NATION";
    }
    @ChName("政治面貌")
    public interface POLITICAL{
        String CODE="POLITICAL";
    }
}
