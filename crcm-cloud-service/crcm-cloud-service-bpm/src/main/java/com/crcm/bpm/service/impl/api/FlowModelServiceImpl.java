package com.crcm.bpm.service.impl.api;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crcm.bpm.core.common.UserSelectorBean;
import com.crcm.bpm.core.handler.ProcessHandler;
import com.crcm.bpm.service.api.FlowModelService;
import com.crcm.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName ModelServiceImpl
 * @Description：
 * @Copyright：Copyright(c) 2020
 * @Company：中再云图技术有限公司
 * @Author：diaoy
 * @Date：2020/9/17/13:57
 **/
@Slf4j
@Service
public class FlowModelServiceImpl implements FlowModelService {

    @Autowired
    private ProcessHandler processHandler;
    @Autowired
    private RepositoryService repositoryService;

    @Override
    public Deployment deploy(String name, String key, String tenantId, String category, String modelXml) {
        XMLStreamReader reader = null;
        InputStream inputStream = null;
        Deployment deploy = null;

        try {
            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            inputStream = new ByteArrayInputStream(modelXml.getBytes());
            reader = factory.createXMLStreamReader(inputStream);
            BpmnModel model = bpmnXMLConverter.convertToBpmnModel(reader);
            List<Process> processes = model.getProcesses();
            if (CollectionUtils.isEmpty(processes)) {
                log.error("BPMN模型没有配置流程");
                return null;
            }
            processes.stream().forEach(process -> {
                Collection<FlowElement> flowElements = process.getFlowElements();
                for (FlowElement flowElement : flowElements) {
                    if (flowElement instanceof UserTask) {
                        UserTask userTask = (UserTask) flowElement;
                        Map<String, List<ExtensionElement>> extensionElements = userTask.getExtensionElements();
                        List<ExtensionElement> customProperties = extensionElements.get("customProperties");
                        if (!CollectionUtils.isEmpty(customProperties)) {
                            ExtensionElement extensionElement = customProperties.get(0);
                            Map<String, List<ExtensionAttribute>> attributes = extensionElement.getAttributes();
                            // 节点用户
                            List<ExtensionAttribute> nodeUsers = attributes.get("nodeUsers");
                            if (!CollectionUtils.isEmpty(nodeUsers)) {
                                String users = nodeUsers.get(0).getValue();
                                List<UserSelectorBean> userSelectorBeans = JSON.parseArray(users, UserSelectorBean.class);
                                this.setTaskUsers(userSelectorBeans,userTask);
                            }
                            // 节点按钮
                            List<ExtensionAttribute> actionList = attributes.get("actionList");
                            if (!CollectionUtils.isEmpty(actionList)) {
                                String actions = actionList.get(0).getValue();
                                List<ExtensionAttribute> att = new ArrayList<>();
                                ExtensionAttribute ext = new ExtensionAttribute();
                                ext.setName("actions");
                                ext.setValue(actions);
                                att.add(ext);
                                Map<String, List<ExtensionAttribute>> attMap = userTask.getAttributes();
                                if (attMap != null) {
                                    attMap.put("actions",att);
                                } else {
                                    attMap = new HashMap<>();
                                    attMap.put("actions",att);
                                }
                            }
                        }
                    }
                }
            });
            
            Process curProcess = null;
            DeploymentBuilder builder = repositoryService.createDeployment();
            // 流程定义的分类
            DeploymentBuilder deploymentBuilder = repositoryService
                    .createDeployment().category(category)
                    .addBpmnModel(category, model);
            // 部署
            deploy = deploymentBuilder.deploy();
            //deploy = processHandler.deploy( name, key, tenantId, category, inputStream);
            return deploy;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            log.error("BPMN模型创建流程异常",e);
        } finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private UserTask setTaskUsers(List<UserSelectorBean> userSelectors, UserTask userTask) {
        for (UserSelectorBean userSelector : userSelectors) {
            switch (userSelector.getType()) {
                case "user-selector":
                    userTask.setAssignee(userSelector.getValue());
                    break;
                case "fixed-user":
                    userTask.setAssignee(userSelector.getValue());
                    break;
                case "assignee":
                    userTask.setAssignee(null);
                    break;
                case "role":
                    List<String> roleGroups = new ArrayList<>();
                    roleGroups.add(userSelector.getValue());
                    userTask.setCandidateGroups(roleGroups);
                    break;
                case "dept":
                    List<String> deptGroups = new ArrayList<>();
                    deptGroups.add(userSelector.getValue());
                    userTask.setCandidateGroups(deptGroups);
                case "post":
                    List<String> postGroups = new ArrayList<>();
                    postGroups.add(userSelector.getValue());
                    userTask.setCandidateGroups(postGroups);
            }
        }
        return userTask;
    }


    public boolean checkProcessXml(String processXml) {

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
