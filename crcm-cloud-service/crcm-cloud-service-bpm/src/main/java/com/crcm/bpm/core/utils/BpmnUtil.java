package com.crcm.bpm.core.utils;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONArray;
import com.crcm.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName BpmnUtil
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/22/15:56
 **/
@Slf4j
public class BpmnUtil {

    public static boolean checkProcessXml(String processXml) {

        if (StringUtils.isEmpty(processXml)) {
            return false;
        }
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] bytes = processXml.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(inputStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(in);
            BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(xtr);
            //验证组装bpmnmodel是否正确
            ProcessValidator defaultProcessValidator = new ProcessValidatorFactory().createDefaultProcessValidator();
            List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
            if (validate.size() > 0) {
                log.error("checkProcessXml error {}", JSONArray.toJSONString(validate));
                throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR,JSONArray.toJSONString(validate));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
        } catch (XMLStreamException e) {
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
        }
        return true;
    }
}
