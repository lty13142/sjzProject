package com.crcm.core.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @ClassName: UtilFilePath
 * @Description 文件工具
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/12/6
 **/
public class FileUtil {
    // 默认连接超时时间
    public static final int connectTimeout = 15*1000;
    // 默认缓存大小
    private static final int CACHE_SIZE = 8192;

    /**
     * 通过URL获取文件输入流
     * @param fileUrl 文件地址
     * @throws IOException
     */
    public static InputStream getWebFileImputStream(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为15秒
        conn.setConnectTimeout(15*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        return conn.getInputStream();
    }

    /**
     * 通过URL下载在线文件，返回输入流
     * @param fileUrl 文件地址
     * @return
     * @throws IOException
     */
    public static InputStream downloadWebFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为5秒
        conn.setConnectTimeout(connectTimeout);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        return conn.getInputStream();
    }

    /**
     * 通过URL下载在线文件，保存到本地
     * @param fileUrl 文件地址
     * @param savePath 保存路径
     * @param fileName 文件名称
     * @throws IOException
     */
    public static void downloadWebFile(String fileUrl, String savePath, String fileName) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为5秒
        conn.setConnectTimeout(15*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //创建保存路径
        File saveDir = new File(savePath);
        if(!saveDir.exists()) {
            saveDir.mkdir();
        }
        if (StringUtils.isBlank(fileName)) {
            fileName = fileUrl.substring(fileUrl.lastIndexOf("/"), fileUrl.indexOf("?"));
        }
        //创建文件
        File file = new File(saveDir+File.separator + fileName);
        //文件输出流
        FileOutputStream fos = new FileOutputStream(file);
        //输出文件
        byte[] buff = new byte[4096];
        int len = -1;
        while ((len = inputStream.read(buff)) != -1) {
            fos.write(buff,0,len);
        }
        //关闭输出输入流
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }


    public static void download(InputStream is, HttpServletResponse response , String fileName) {
        OutputStream os = null;
        //得到输入流
        try {

            String downloadName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/force-download");// 设置强制下载不打开 MIME
            response.addHeader("Content-Disposition", "attachment;fileName=" + downloadName);// 设置文件名
            //将文件直接读取到响应体中
            os = response.getOutputStream();
            byte[] buffer = new byte[CACHE_SIZE];
            int byteRead = 0;
            while ((byteRead = is.read(buffer,0, CACHE_SIZE)) != -1) {
                os.write(buffer, 0 ,byteRead);
            }
            os.flush();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
