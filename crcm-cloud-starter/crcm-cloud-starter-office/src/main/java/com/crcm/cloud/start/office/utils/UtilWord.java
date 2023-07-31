package com.crcm.cloud.start.office.utils;

import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName
 * @Description word工具类
 * @Copyright Copyright(c) 2019
 * @Company 中再云图科技有限公司
 * @Author diaoy
 * @Date 2019/7/26
 **/
public class UtilWord {


    /**
     * 03版word转HTML
     *
     * @param is
     * @return
     */
    public static String word03ToHtml(final InputStream is) {
        ByteArrayOutputStream bos = null;
        try {
            HWPFDocument wordDocument = null;
            wordDocument = new HWPFDocument(is);
            WordToHtmlConverter converter = null;
            converter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            //对HWPFDocument进行转换
            converter.setPicturesManager(new PicturesManager() {
                @Override
                public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                    String type = pictureType.name();
                    return "data:image/" + type + ";base64," + new String(Base64.encodeBase64(content));
                }
            });
            converter.processDocument(wordDocument);
            Transformer transformer = null;
            transformer = TransformerFactory.newInstance().newTransformer();
            bos = new ByteArrayOutputStream();
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            //是否添加空格
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.transform(
                    new DOMSource(converter.getDocument()),
                    new StreamResult(bos));
            wordDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        } finally {

        }
        return bos.toString();
    }

    /**
     * 07版word转HTML，样式会出一点问题，表格无边框
     *
     * @param is
     * @throws IOException
     */
    public static String word07ToHtml(final InputStream is) throws IOException {
        XWPFDocument document = new XWPFDocument(is);
        List<XWPFPictureData> list = document.getAllPictures();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, outputStream, null);
        String s = new String(outputStream.toByteArray());
        s = setImg(s, list);
        document.close();
        return s;
    }


    private static String setImg(String html, List<XWPFPictureData> list) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("img");
        if (elements != null && elements.size() > 0 && list != null) {
            for (Element element : elements) {
                String src = element.attr("src");
                for (XWPFPictureData data : list) {
                    if (src.contains(data.getFileName())) {
                        String type = src.substring(src.lastIndexOf(".") + 1);
                        String base64 = "data:image/" + type + ";base64," + new String(Base64.encodeBase64(data.getData()));
                        element.attr("src", base64);
                        break;
                    }
                }
            }
        }

        return doc.toString();
    }


}
