package com.crcm.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class MapGeocoderUtil {
    //百度地理编码
    private String baiDu_Geocoder = "http://api.map.baidu.com/geocoder/v2/?address=&city=重庆市&output=json&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
    //百度逆地理编码；location=30.574984062978588,104.06938771026837
    private String baiDu_NGeocoder = "http://api.map.baidu.com/geocoder/v2/?location=&output=json&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
    //百度逆向地理编码;根据坐标解析地址，可返回POI点;POIS=1时表示100米;location=30.574984062978588,104.06938771026837
    private String baiDu_NGeocoderPOI = "http://api.map.baidu.com/geocoder/v2/?location=&output=json&pois=1&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
    //百度POI点，根据地址解析周边POI点;query=康田国际
    private String baiDu_GeocoderPlacePOI = "http://api.map.baidu.com/place/v2/search?query=&page_size=10&page_num=0&scope=1&region=重庆市&output=json&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
    //百度地址模糊搜索
    private String baiDu_PlaceSuggestion = "http://api.map.baidu.com/place/v2/suggestion?query=&region=重庆市&output=json&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";
    //百度坐标转换 coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924
    private String baidu_Coords = "http://api.map.baidu.com/geoconv/v1/?coords=&from=1&to=5&ak=z8XKeiFNsOpWUL6II3nkHoBu7H3hoBn7";

    /**
     * 百度逆向地理编码;根据坐标解析地址<br/>
     * <p>
     * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     *
     * @param x 白度坐标x
     * @param y 白度坐标y
     * @return string json
     */
    public String getCoordToAddress(Double x, Double y) {
        StringBuilder result = new StringBuilder("");
        String tempAddressCodeUrl = "";
        try {
            String yx = y + "," + x;
            tempAddressCodeUrl = baiDu_NGeocoder.replace("=&", "=" + yx + "&");
            URL baidu_url = new URL(tempAddressCodeUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(baidu_url.openStream(), Charset.forName("UTF-8")));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                result.append(strTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 百度地理编码;根据地址解析坐标，返回百度坐标
     *
     * @param address 地址
     * @return string 百度坐标
     * @ http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     */
    @SuppressWarnings("unchecked")
    public String getBaiDuAddressToCoord(String address) {
        StringBuilder result = new StringBuilder("");
        String tempAddressCodeUrl;
        try {
            tempAddressCodeUrl = baiDu_Geocoder.replace("=&", "=" + address + "&");
            URL baidu_url = new URL(tempAddressCodeUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(baidu_url.openStream(), Charset.forName("UTF-8")));
            String strTemp;
            while (null != (strTemp = br.readLine())) {
                result.append(strTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject objCoord = JSON.parseObject(result.toString());
        if (objCoord.getString("status").equals("1")) {
            //无返回结果
            return "";
        }
        Map<String, Object> array = (Map<String, Object>) JSON.parse(objCoord.getString("result"));
        array = (Map<String, Object>) array.get("location");
        String point = array.get("lng") + "," + array.get("lat");
        return point;
    }

    /**
     * 百度没提供‘百度坐标转wgs84坐标’服务
     * 百度坐标和wgs1984转换
     *
     * @param coords 百度经纬度字符串 114.21892734521,29.575429778924;114.21892734521,29.575429778924
     * @return json 坐标
     */
    public String getWGSCoordsToBaiduCoords(String coords) {
        String[] baidu_coord = coords.split(";");//百度坐标
        String[] wgs_coord = null;//wgs坐标
        //通过服务转换成坐标
        StringBuilder result = new StringBuilder("");
        String tempAddressCodeUrl = "";
        try {
            //将百度坐标看成是WGS坐标，通过WGS转换成百度坐标(这里需要通过服务转换一次)
            tempAddressCodeUrl = baidu_Coords.replace("=&", "=" + coords + "&");
            URL baidu_url = new URL(tempAddressCodeUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(baidu_url.openStream(), Charset.forName("UTF-8")));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                result.append(strTemp);
            }
            JSONObject objCoord = JSON.parseObject(result.toString());
            JSONArray array = JSON.parseArray(objCoord.getString("result"));
            String from_coords = "";
            for (Object objectArray : array) {
                JSONObject object = JSON.parseObject(objectArray.toString());
                String x = object.getString("x");
                String y = object.getString("y");
                String content = x + "," + y;
                from_coords = (from_coords == "") ? from_coords + content : from_coords + ";" + content;
            }
            wgs_coord = from_coords.split(";");//得到伪WGS，这里获取的不是最终的WGS坐标
            result = new StringBuilder();
            result.append("");
            //坐标算法转换，把百度坐标和伪WGS坐标通过算法转换成最终WGS坐标
            for (int c = 0; baidu_coord.length > c; c++) {
                String[] xy1 = baidu_coord[c].split(",");
                String[] xy2 = wgs_coord[c].split(",");
                double x1 = Double.parseDouble(xy1[0]);
                double y1 = Double.parseDouble(xy1[1]);
                double x2 = Double.parseDouble(xy2[0]);
                double y2 = Double.parseDouble(xy2[1]);
                double x = 2 * x1 - x2;
                double y = 2 * y1 - y2;
                result.append("" + x + "," + y + "");
                if (baidu_coord.length - 1 != c) {
                    result.append(";");
                }
            }
            result.append("");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
