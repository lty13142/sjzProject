package com.crcm.bpm.core.utils;

import org.dom4j.*;

import java.util.*;

/**
 * @author zwj
 * @date 2020-11-03
 */
public class XmlUtils {
    public static void main(String[] args) {
        String xmlResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:ns0=\"undefined\" targetNamespace=\"http://www.flowable.org/processdef\">\n" +
                "    <process id=\"Process_0g45t0p\" name=\"请假申请测试模型\" isExecutable=\"true\">\n" +
                "        <documentation>请假申请测试模型</documentation>\n" +
                "        <startEvent id=\"StartTask_0cq54fx\" name=\"开始节点\" />\n" +
                "        <userTask id=\"UserTask_03ibrvs\" name=\"发起人\" flowable:formKey=\"2\">\n" +
                "            <extensionElements>\n" +
                "                <flowable:customProperties userSelectors=\"promoter\" formEditField=\"field104\" userIdList=\"\" userNameList=\"\" assigneeField=\"bpm_UserTask_03ibrvs_assignee_field\" handlerStrategy=\"skip\" roleIds=\"\" roleNames=\"\" findUserType=\"1\" actionList=\"start,draft\" taskType=\"start\" sequential=\"parallel\" proportion=\"100\" selectFormKey=\"2\" selectPath=\"0\" roleCode=\"\" />\n" +
                "            </extensionElements>\n" +
                "            <outgoing>SequenceFlow_15neqt4</outgoing>\n" +
                "        </userTask>\n" +
                "        <sequenceFlow id=\"SequenceFlow_010mho6\" sourceRef=\"StartTask_0cq54fx\" targetRef=\"UserTask_03ibrvs\" />\n" +
                "        <userTask id=\"UserTask_1ww2iz7\" name=\"部门负责人审核\" flowable:formKey=\"2\">\n" +
                "            <extensionElements>\n" +
                "                <flowable:customProperties userSelectors=\"deptHead\" formEditField=\"\" userIdList=\"\" userNameList=\"\" assigneeField=\"bpm_UserTask_1ww2iz7_assignee_field\" handlerStrategy=\"skip\" roleIds=\"\" roleNames=\"\" findUserType=\"1\" actionList=\"pass,reject\" taskType=\"approve\" selectFormKey=\"2\" selectPath=\"0\" />\n" +
                "            </extensionElements>\n" +
                "            <incoming>SequenceFlow_15neqt4</incoming>\n" +
                "            <outgoing>SequenceFlow_0n9q8ve</outgoing>\n" +
                "        </userTask>\n" +
                "        <sequenceFlow id=\"SequenceFlow_15neqt4\" sourceRef=\"UserTask_03ibrvs\" targetRef=\"UserTask_1ww2iz7\" />\n" +
                "        <userTask id=\"UserTask_0r1ynga\" name=\"发起人确认\" flowable:formKey=\"2\">\n" +
                "            <extensionElements>\n" +
                "                <flowable:customProperties userSelectors=\"promoter\" formEditField=\"field104\" userIdList=\"\" userNameList=\"\" assigneeField=\"bpm_UserTask_0r1ynga_assignee_field\" handlerStrategy=\"skip\" roleIds=\"\" roleNames=\"\" findUserType=\"1\" actionList=\"pass,reject\" taskType=\"approve\" selectFormKey=\"2\" selectPath=\"0\" />\n" +
                "            </extensionElements>\n" +
                "            <incoming>SequenceFlow_0n9q8ve</incoming>\n" +
                "            <outgoing>SequenceFlow_1p6c70z</outgoing>\n" +
                "        </userTask>\n" +
                "        <sequenceFlow id=\"SequenceFlow_0n9q8ve\" sourceRef=\"UserTask_1ww2iz7\" targetRef=\"UserTask_0r1ynga\" />\n" +
                "        <endEvent id=\"EndEvent_00ou801\" name=\"结束节点\">\n" +
                "            <incoming>SequenceFlow_1p6c70z</incoming>\n" +
                "        </endEvent>\n" +
                "        <sequenceFlow id=\"SequenceFlow_1p6c70z\" sourceRef=\"UserTask_0r1ynga\" targetRef=\"EndEvent_00ou801\" />\n" +
                "    </process>\n" +
                "    <bpmndi:BPMNDiagram id=\"BPMNDiagram_Process_0g45t0p\">\n" +
                "        <bpmndi:BPMNPlane id=\"BPMNPlane_Process_0g45t0p\" bpmnElement=\"Process_0g45t0p\">\n" +
                "            <bpmndi:BPMNEdge id=\"SequenceFlow_1p6c70z_di\" bpmnElement=\"SequenceFlow_1p6c70z\">\n" +
                "                <omgdi:waypoint x=\"720\" y=\"218\" />\n" +
                "                <omgdi:waypoint x=\"802\" y=\"218\" />\n" +
                "            </bpmndi:BPMNEdge>\n" +
                "            <bpmndi:BPMNEdge id=\"SequenceFlow_0n9q8ve_di\" bpmnElement=\"SequenceFlow_0n9q8ve\">\n" +
                "                <omgdi:waypoint x=\"540\" y=\"218\" />\n" +
                "                <omgdi:waypoint x=\"620\" y=\"218\" />\n" +
                "            </bpmndi:BPMNEdge>\n" +
                "            <bpmndi:BPMNEdge id=\"SequenceFlow_15neqt4_di\" bpmnElement=\"SequenceFlow_15neqt4\">\n" +
                "                <omgdi:waypoint x=\"360\" y=\"218\" />\n" +
                "                <omgdi:waypoint x=\"440\" y=\"218\" />\n" +
                "            </bpmndi:BPMNEdge>\n" +
                "            <bpmndi:BPMNEdge id=\"BPMNEdge_SequenceFlow_010mho6\" bpmnElement=\"SequenceFlow_010mho6\">\n" +
                "                <omgdi:waypoint x=\"186\" y=\"218\" />\n" +
                "                <omgdi:waypoint x=\"260\" y=\"218\" />\n" +
                "            </bpmndi:BPMNEdge>\n" +
                "            <bpmndi:BPMNShape id=\"BPMNShape_StartTask_0cq54fx\" bpmnElement=\"StartTask_0cq54fx\">\n" +
                "                <omgdc:Bounds x=\"150\" y=\"200\" width=\"36\" height=\"36\" />\n" +
                "                <bpmndi:BPMNLabel>\n" +
                "                    <omgdc:Bounds x=\"146\" y=\"243\" width=\"43\" height=\"14\" />\n" +
                "                </bpmndi:BPMNLabel>\n" +
                "            </bpmndi:BPMNShape>\n" +
                "            <bpmndi:BPMNShape id=\"BPMNShape_UserTask_03ibrvs\" bpmnElement=\"UserTask_03ibrvs\">\n" +
                "                <omgdc:Bounds x=\"260\" y=\"178\" width=\"100\" height=\"80\" />\n" +
                "            </bpmndi:BPMNShape>\n" +
                "            <bpmndi:BPMNShape id=\"UserTask_1ww2iz7_di\" bpmnElement=\"UserTask_1ww2iz7\">\n" +
                "                <omgdc:Bounds x=\"440\" y=\"178\" width=\"100\" height=\"80\" />\n" +
                "            </bpmndi:BPMNShape>\n" +
                "            <bpmndi:BPMNShape id=\"UserTask_0r1ynga_di\" bpmnElement=\"UserTask_0r1ynga\">\n" +
                "                <omgdc:Bounds x=\"620\" y=\"178\" width=\"100\" height=\"80\" />\n" +
                "            </bpmndi:BPMNShape>\n" +
                "            <bpmndi:BPMNShape id=\"EndEvent_00ou801_di\" bpmnElement=\"EndEvent_00ou801\">\n" +
                "                <omgdc:Bounds x=\"802\" y=\"200\" width=\"36\" height=\"36\" />\n" +
                "                <bpmndi:BPMNLabel>\n" +
                "                    <omgdc:Bounds x=\"799\" y=\"243\" width=\"43\" height=\"14\" />\n" +
                "                </bpmndi:BPMNLabel>\n" +
                "            </bpmndi:BPMNShape>\n" +
                "        </bpmndi:BPMNPlane>\n" +
                "    </bpmndi:BPMNDiagram>\n" +
                "</definitions>";
        parseXml(xmlResult);
        /*String xpath = "process/userTask/extensionElements";
        String attrName = "actionList";
        xmlparse(xmlResult, xpath, attrName);*/
    }

    public static void parseXml(String xmlResult) {
        // xml是字符串格式
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            // 将xml格式字符串转化为DOM对象
            org.dom4j.Document document = DocumentHelper.parseText(xmlResult);
            // 获取根结点对象
            Element rootElement = document.getRootElement();
            Iterator process = rootElement.elementIterator("process");
            while (process.hasNext()) {
                Element next = (Element) process.next();
                Iterator userTask = next.elementIterator("userTask");
                while (userTask.hasNext()) {
                    Map<String, Object> map = new HashMap<>();
                    Element next1 = (Element) userTask.next();
                    map.put("nodeId", next1.attributeValue("id"));
                    resultList.add(map);
                    Iterator extensionElements = next1.elementIterator("extensionElements");
                    while (extensionElements.hasNext()) {
                        Element next3 = (Element) extensionElements.next();
                        List content = next3.content();
                        for (Object o : content) {
                            try {
                                Element ele = (Element) o;
                                String actionList = ele.attributeValue("actionList");
                                map.put("actionList", actionList);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultList);
    }

    private static void xmlparse(String xmlResult, String xpath, String attrName) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            //获取xpath字符串
            String[] pName = xpath.split("/");
            List<String> pNameList = Arrays.asList(pName);
            // 将xml格式字符串转化为DOM对象
            Document document = DocumentHelper.parseText(xmlResult);
            Element rootElement = document.getRootElement();
            int i = 0;
            ddd(rootElement, pNameList, i, resultList);
            System.out.println(resultList);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void ddd(Element element, List<String> pNameList, int i, List<Map<String, Object>> resultList) {
        Map<String, Object> map=new HashMap<>();
        Iterator iterator = element.elementIterator(pNameList.get(i));
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            System.out.println(next.getName());
            if ("userTask".equals(next.getName())) {
                map.put("nodeId", next.attributeValue("id"));
                resultList.add(map);
            }
            if (++i < pNameList.size()) {
                ddd(next, pNameList, i, resultList);
            }
        }
    }
}

