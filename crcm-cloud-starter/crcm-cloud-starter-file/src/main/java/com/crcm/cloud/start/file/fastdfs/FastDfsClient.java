package com.crcm.cloud.start.file.fastdfs;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @ClassName FastDfsClient
 * @Description
 * @Copyright Copyright(c) 2020
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2020/4/17
 **/
@Slf4j
@Data
@Builder
public class FastDfsClient {

    private String uploadUrl;
    private String deleteUrl;
    private String basePath;
    private static final int CACHE_SIZE = 8192;

    public static FastDfsClient init(FastDfsConfig fastDfsConfig) {
        StringBuilder url = new StringBuilder();
        url.append(fastDfsConfig.getHost());
        if (StringUtils.isNotBlank(fastDfsConfig.getGroup())) {
            url.append(fastDfsConfig.getGroup().startsWith("/") ? fastDfsConfig.getGroup() : "/" + fastDfsConfig.getGroup());
        }
        return FastDfsClient.builder()
                .basePath(url + fastDfsConfig.getBasePath())
                .uploadUrl(url + fastDfsConfig.getUploadUrl())
                .deleteUrl(url + fastDfsConfig.getDeleteUrl())
                .build();
    }

    /**
     * 文件上传
     *
     * @param fileName 文件名称
     * @param stream   文件流
     * @param path     存储目录
     * @return
     * @throws IOException
     */
    public FastDfsUploadResult doUpload(String fileName, InputStream stream, String path) throws IOException {
        String result = "";
        StringBuffer savePath = new StringBuffer(this.basePath);
        if (StringUtils.isNotBlank(path)) {
            savePath.append(path.startsWith("/") ? path : "/" + path);
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(200000)
                .setSocketTimeout(2000000)
                .build();
        HttpPost httpPost = new HttpPost(this.uploadUrl);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("UTF-8"))
                .addTextBody("output", "json")
                .addTextBody("scene", savePath.toString())
                .addBinaryBody("file", stream,
                        ContentType.DEFAULT_BINARY, fileName);
        httpPost.setEntity(multipartEntityBuilder.build());
        httpResponse = httpClient.execute(httpPost);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String respStr = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(respStr);
            if (respStr != null) {
                return JSON.parseObject(respStr, FastDfsUploadResult.class);
            }
        }

        httpClient.close();
        httpResponse.close();
        return null;
    }


    public void doDownload(String path, String fileFullName, HttpServletResponse response) {
        OutputStream os = null;
        //得到输入流
        InputStream is = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            is = conn.getInputStream();
            String downloadName = URLEncoder.encode(fileFullName, "UTF-8");
            // 设置强制下载不打开 MIME
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + downloadName);
            //将文件直接读取到响应体中
            os = response.getOutputStream();
            byte[] buffer = new byte[CACHE_SIZE];
            int byteRead = 0;
            while ((byteRead = is.read(buffer, 0, CACHE_SIZE)) != -1) {
                os.write(buffer, 0, byteRead);
            }
            os.flush();
            is.close();
            System.out.println("info:" + url + " download success");
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

    public FastDfsDeleteResult doDelete(String filePath) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(200000)
                .setSocketTimeout(2000000)
                .build();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.deleteUrl);
        builder.queryParam("path", filePath);

        HttpGet httpGet = new HttpGet(builder.build().encode().toString());
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

        httpResponse = httpClient.execute(httpGet);

        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String respStr = EntityUtils.toString(httpResponse.getEntity());
            return JSON.parseObject(respStr, FastDfsDeleteResult.class);
        }
        httpClient.close();
        httpResponse.close();
        return null;
    }

    /**
     * 获取文件URI
     * @param fileFullName
     * @return
     */
    public String getUri(String fileFullName) {
        return basePath + fileFullName;
    }
}
