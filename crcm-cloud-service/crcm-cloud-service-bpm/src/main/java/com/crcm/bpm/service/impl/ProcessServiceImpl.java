package com.crcm.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crcm.bpm.core.constant.BpmConstant;
import com.crcm.bpm.core.constant.FormFormatConstant;
import com.crcm.bpm.domain.dto.BackFillingDto;
import com.crcm.bpm.domain.dto.ProcessDto;
import com.crcm.bpm.domain.dto.request.ProcessInfoReqDTO;
import com.crcm.bpm.domain.dto.response.GetSortListResponseDto;
import com.crcm.bpm.domain.dto.response.ProcessInfoDTO;
import com.crcm.bpm.domain.dto.response.ProcessStartResDTO;
import com.crcm.bpm.domain.entity.NodeDO;
import com.crcm.bpm.domain.entity.ProcessCollectionDO;
import com.crcm.bpm.domain.entity.ProcessDO;
import com.crcm.bpm.mapper.ProcessMapper;
import com.crcm.bpm.service.NodeService;
import com.crcm.bpm.service.ProcessCollectionService;
import com.crcm.bpm.service.ProcessService;
import com.crcm.core.constant.SystemConstant;
import com.crcm.core.exception.CustomException;
import com.crcm.security.bean.UserAccount;
import com.crcm.security.utils.SecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
@Transactional
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, ProcessDO> implements ProcessService {

    @Autowired
    private NodeService nodeService;

//    @Resource
//    private FeignRoleService feignRoleService;
//
//    @Resource
//    private FeignPostService feignPostService;
//
//    @Resource
//    private FeignEmployeeService feignEmployeeService;

    @Resource
    private ProcessCollectionService processCollectionService;

    @Override
    public int saveProcess(ProcessDO processDO) {
        return this.baseMapper.insert(processDO);
    }

    @Override
    public int updateProcess(ProcessDO processDO) {
        return this.baseMapper.updateById(processDO);
    }

    @Override
    public int deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int realDelete(String id) {
        return this.baseMapper.realDelete(id);
    }

    @Override
    public ProcessDO findById(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ProcessDO> findList(ProcessDO processDO) {
        return this.baseMapper.selectList(createConditionQuery(processDO));
    }

    @Override
    public IPage<ProcessDO> findPage(Page page, ProcessDO processDO) {
        LambdaQueryWrapper<ProcessDO> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ProcessDO::getCompanyId, processDO.getCompanyId() == null ? SecurityUtil.getCurrentUserNoNull().getComId() : processDO.getCompanyId());
        if (!StringUtils.isEmpty(processDO.getProcessName())) {
            queryWrapper.like(ProcessDO::getProcessName, processDO.getProcessName());
        }
        queryWrapper.orderByDesc(ProcessDO::getCreateTime);
        IPage<ProcessDO> pageProcess = this.baseMapper.selectPage(page, queryWrapper);
        return pageProcess;
    }

    @Override
    public ProcessStartResDTO getStartInfo(ProcessInfoReqDTO process) {
        process.setMainVersion(BpmConstant.MAIN_VERSION);
        ProcessStartResDTO flow = this.baseMapper.selectStartInfo(process);
        if (flow == null) {
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, "错误，流程不存在！");
        }
        if (String.valueOf(SystemConstant.ENABLE_STATUS.DISABLE).equals(flow.getModelStatus())) {
            throw new CustomException(HttpStatus.HTTP_INTERNAL_ERROR, "错误，流程已停用！");
        }
        String formJson = formatFormJson(process, flow);
        flow.setFormJson(formJson);
        LambdaQueryWrapper<NodeDO> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(NodeDO::getDefinitionId, flow.getDefinitionId());
        queryWrapper.eq(NodeDO::getTaskType, BpmConstant.TASK_TYPE_START);
        NodeDO nodeDO = nodeService.getOne(queryWrapper);
        flow.setFormEditField(nodeDO.getFormEditField());
        return flow;
    }

    /**
     * 格式化formJson
     *
     * @param process
     * @param flow
     * @return
     */
    private String formatFormJson(ProcessInfoReqDTO process, ProcessStartResDTO flow) {
        String formJson = flow.getFormJson();
        if (formJson != null) {
            // 直接替换
            formJson = directReplacement(process, formJson, flow);
            // 数据回填
            // 获取当前用户最后一次提交该流程的数据
            String oldFormJson = baseMapper.getMaxOneFormJsonByProcessId(process.getProcessId(), SecurityUtil.getCurrentUserNoNull().getUserId());
            if (oldFormJson == null || "".equals(oldFormJson)) {
                return formJson;
            }
            JSONObject formJsonObject = JSONObject.parseObject(formJson);
            // 获取当前所有组件
            JSONArray jsonObjectList = formJsonObject.getJSONArray(FormFormatConstant.FIELDS);
            List<JSONObject> tableAllList = new ArrayList<>();
            LinkedHashMap<String, JSONObject> tableMap = new LinkedHashMap<>(jsonObjectList.size());
            LinkedHashMap<BackFillingDto, JSONObject> backFillingDtoMap = new LinkedHashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            // 遍历组件
            jsonObjectList.forEach(jsonObjectItem -> {
                JSONObject jsonObject = (JSONObject) jsonObjectItem;
                tableAllList.add(jsonObject);
                // 如果没有vModel直接跳过
                if (jsonObject.get(FormFormatConstant.FIELDS_TABLE_VMODEL) != null) {
                    // 将所有组件按vModel加入map进行后续处理
                    tableMap.put(jsonObject.get(FormFormatConstant.FIELDS_TABLE_VMODEL).toString(), jsonObject);
                    BackFillingDto backFillingDto;
                    // 将有回填参数的放入map进行后续处理
                    if (jsonObject.get(FormFormatConstant.BACK_FILLING) != null) {
                        try {
                            backFillingDto = objectMapper.readValue(jsonObject.get(FormFormatConstant.BACK_FILLING).toString(), BackFillingDto.class);
                            if (backFillingDto.getTableVModel() != null && !"".equals(backFillingDto.getTableVModel())) {
                                backFillingDtoMap.put(backFillingDto, jsonObject);
                            }
                        } catch (Exception e) {
                            log.error("BackFillingDto转换失败");
                            throw new CustomException(HttpStatus.HTTP_BAD_METHOD, "获取失败");
                        }
                    }
                }
            });
            // 处理得到的list
            backFillingDtoMap.forEach((backFillingDto, fieldsJsonObject) -> {
                // 将backFillVModel作为key,currentVModel作为value放进map以便后续处理
                LinkedHashMap<String, String> correspondenceDtoMap = new LinkedHashMap<>(backFillingDto.getCorrespondence().size());
                backFillingDto.getCorrespondence().forEach(correspondenceDto -> {
                    correspondenceDtoMap.put(correspondenceDto.getBackFillVModel(), correspondenceDto.getCurrentVModel());
                });
                // 对对应回填组件进行处理
                JSONObject config = fieldsJsonObject.getJSONObject(FormFormatConstant.CONFIG);
                JSONArray rows = config.getJSONArray(FormFormatConstant.ROW);
                LinkedHashMap<String, JSONObject> rowMap = new LinkedHashMap<>(rows.size());
                // 将回填组件模板按vModel加入map进行后续处理
                rows.forEach(rowItem -> {
                    JSONObject row = JSONObject.parseObject(JSON.toJSONString(rowItem));
                    if (row.containsKey(FormFormatConstant.FIELDS_TABLE_VMODEL)) {
                        rowMap.put(row.get(FormFormatConstant.FIELDS_TABLE_VMODEL).toString(), row);
                    } else {
                        rowMap.put(row.get(FormFormatConstant.FIELDS_TABLE_ID).toString(), row);
                    }
                });
                // 使用最后一次提交的该流程的数据进行回填
                JSONObject oldFormJsonObject = JSONObject.parseObject(oldFormJson);
                JSONArray oldFields = oldFormJsonObject.getJSONArray(FormFormatConstant.FIELDS);
                // 遍历旧数据组件
                oldFields.forEach(fieldItem -> {
                    JSONObject field = (JSONObject) fieldItem;
                    JSONObject oldConfig = field.getJSONObject(FormFormatConstant.CONFIG);
                    // 找到对应组件提取数据进行回填
                    if (backFillingDto.getTableVModel().equals(field.get(FormFormatConstant.FIELDS_TABLE_VMODEL))) {
                        // 找到数据的总列数据
                        JSONArray childrenList = oldConfig.getJSONArray(FormFormatConstant.CHILDREN);
                        JSONArray currentChildrenList = config.getJSONArray(FormFormatConstant.CHILDREN);
                        JSONObject currentChildren = (JSONObject) currentChildrenList.get(0);
                        currentChildrenList.clear();
                        // 遍历列获得行
                        childrenList.forEach(childrenItem -> {
                            JSONObject children = (JSONObject) childrenItem;
                            JSONObject childrenConfig = children.getJSONObject(FormFormatConstant.CONFIG);
                            // 找到数据的总行数据
                            JSONArray secondChildrenList = childrenConfig.getJSONArray(FormFormatConstant.CHILDREN);
                            JSONArray rowList = new JSONArray();
                            // 遍历行获得单元组件
                            secondChildrenList.forEach(secondChildrenItem -> {
                                JSONObject secondChildren = (JSONObject) secondChildrenItem;
                                JSONObject secondChildrenConfig = secondChildren.getJSONObject(FormFormatConstant.CONFIG);
                                if (secondChildren.containsKey(FormFormatConstant.FIELDS_TABLE_VMODEL)) {
                                    String secondChildrenVModel = secondChildren.get(FormFormatConstant.FIELDS_TABLE_VMODEL).toString();
                                    // 获取需要填充的单元组件
                                    if (correspondenceDtoMap.get(secondChildrenVModel) != null) {
                                        JSONObject rowJsonObject = rowMap.get(correspondenceDtoMap.get(secondChildrenVModel));
                                        if(rowJsonObject == null){
                                            return;
                                        }
                                        JSONObject rowConfig = rowJsonObject.getJSONObject(FormFormatConstant.CONFIG);
                                        rowConfig.put(FormFormatConstant.DEFAULT_VALUE,
                                                secondChildrenConfig.get(FormFormatConstant.DEFAULT_VALUE) == null ?
                                                        "" : secondChildrenConfig.get(FormFormatConstant.DEFAULT_VALUE));
                                        rowJsonObject.put(FormFormatConstant.CONFIG, rowConfig);
                                        rowMap.put(correspondenceDtoMap.get(secondChildrenVModel), rowJsonObject);
                                    }
                                }
                            });
                            rowMap.forEach((k, v) -> {
                                rowList.add(v);
                            });
                            // 替换行children
                            JSONObject newCurrentChildrenConfig = currentChildren.getJSONObject(FormFormatConstant.CONFIG);
                            newCurrentChildrenConfig.put(FormFormatConstant.CHILDREN, rowList);
                            // 替换行config
                            currentChildren.put(FormFormatConstant.CONFIG, newCurrentChildrenConfig);
                            // 将行添加到列
                            currentChildrenList.add(JSONObject.parse(currentChildren.toString()));
                        });
                        // 替换列children
                        config.put(FormFormatConstant.CHILDREN, currentChildrenList);
                        // 替换列config
                        fieldsJsonObject.put(FormFormatConstant.CONFIG, config);
                    }
                });
            });
            // 清空原始jsonArray
            jsonObjectList.clear();
            // 添加替换后的数据
            tableAllList.forEach(jsonObject -> {
                jsonObjectList.add(jsonObject);
            });
            // 替换fields
            formJsonObject.put(FormFormatConstant.FIELDS, jsonObjectList);
            formJson = JSONArray.toJSONString(formJsonObject, SerializerFeature.DisableCircularReferenceDetect);
        }
        return formJson;
    }

    /**
     * 直接替换
     */
    private String directReplacement(ProcessInfoReqDTO process, String formJson, ProcessStartResDTO flow) {
        UserAccount userDetails = SecurityUtil.getCurrentUser();

        if (flow.getFormJson().contains(BpmConstant.PROCESS_USER_NAME)) {
            formJson = formJson.replaceAll(BpmConstant.REPLACE_PROCESS_USER_NAME, userDetails.getEmpName());
        }
        if (flow.getFormJson().contains(BpmConstant.PROCESS_DEPARTMENT_NAME)) {
            formJson = formJson.replaceAll(BpmConstant.REPLACE_PROCESS_DEPARTMENT_NAME, process.getDepartmentName());
        }
        if (flow.getFormJson().contains(BpmConstant.PROCESS_COMPANY_NAME)) {
            formJson = formJson.replaceAll(BpmConstant.REPLACE_PROCESS_COMPANY_NAME, userDetails.getComName());
        }
        if (flow.getFormJson().contains(BpmConstant.PROCESS_POSITION_NAME)) {
            List<String> posts = new ArrayList<>();
            // TODO 对接远程接口
//            List<EmpPositioanVo> empPostList = feignEmployeeService.findAllPost(userDetails.getComId(), userDetails.getId()).getData();
//            if (CollectionUtil.isNotEmpty(empPostList)) {
//                if (process.getDepartmentId() != null && !"".equals(process.getDepartmentId())) {
//                    empPostList.forEach(empPost -> {
//                        if (process.getDepartmentId().equals(empPost.getDeptId())) {
//                            String param = "{\"label\":\"" + empPost.getPostName() + "\",\"value\":\"" + empPost.getPostName() + "\"}";
//                            posts.add(param);
//                        }
//                    });
//                } else {
//                    empPostList.forEach(empPost -> {
//                        String param = "{\"label\":" + empPost.getPostName() + ",\"value\":" + empPost.getPostName() + "}";
//                        posts.add(param);
//                    });
//                }
//            }
            formJson = formJson.replaceAll(BpmConstant.REPLACE_PROCESS_POSITION_NAME_LABEL_VALUE, posts.toString());
            formJson = formJson.replaceAll(BpmConstant.REPLACE_PROCESS_POSITION_NAME, "");
        }
        return formJson;
    }

    @Override
    public ProcessInfoDTO getProcessInfoById(Long processId) {
        ProcessDO process = this.getById(processId);
        return process == null ? null : BeanUtil.copyProperties(process, ProcessInfoDTO.class);
    }

    @Override
    public List<GetSortListResponseDto> getSortList(String flowType, String processName) {
        UserAccount userDetails = SecurityUtil.getCurrentUser();
        List<String> roleIds = SecurityUtil.getCurrentUserRoles();
        if (roleIds.size() == 0) {
            log.error("employeeId={}未分配角色", userDetails.getUserId());
            throw new CustomException(HttpStatus.HTTP_PRECON_FAILED, "请联系管理员为您分配角色");
        }
        List<GetSortListResponseDto> dtoList = baseMapper.getSortList(flowType, userDetails.getComId(), processName, roleIds);
        // TODO 处理字典的流程类型
//        List list = UtilDic.getDicByCode("FLOW_TYPE");
        Map<String, String> map = new HashMap<>();
//        if (list != null) {
//            list.forEach(obj -> {
//                Map objMap = (Map) obj;
//                map.put(objMap.get("value").toString(), objMap.get("name").toString());
//            });
//        }
        List<Long> processIds = new ArrayList<>();
        List<ProcessCollectionDO> processCollectionDOS = processCollectionService.list(new LambdaQueryWrapper<ProcessCollectionDO>()
                .eq(ProcessCollectionDO::getEmployeeId, userDetails.getUserId()));
        processCollectionDOS.forEach(processCollectionDO -> {
            processIds.add(processCollectionDO.getProcessId());
        });
        dtoList.forEach(dto -> {
            dto.setProcessType(map.get(dto.getProcessTypeValue()));
            dto.getProcessDOList().forEach(processDto -> {
                if(processIds.contains(processDto.getId())){
                    processDto.setIsCollection(true);
                }
            });
        });
        return dtoList;
    }

    private LambdaQueryWrapper<ProcessDO> createConditionQuery(ProcessDO processDO) {
        LambdaQueryWrapper<ProcessDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProcessDO::getDeleted, SystemConstant.YES_OR_NO.NO);
        wrapper.orderByDesc(ProcessDO::getSortOrder).orderByDesc(ProcessDO::getCreateTime);
        if (processDO != null) {
            if (StringUtils.isNotBlank(processDO.getCompanyId())) {
                wrapper.eq(ProcessDO::getCompanyId, processDO.getCompanyId());
            }
            if (StringUtils.isNotBlank(processDO.getProcessName())) {
                wrapper.eq(ProcessDO::getProcessName, processDO.getProcessName());
            }
            if (StringUtils.isNotBlank(processDO.getProcessKey())) {
                wrapper.eq(ProcessDO::getProcessKey, processDO.getProcessKey());
            }
            if (processDO.getProcessStatus() != null) {
                wrapper.eq(ProcessDO::getProcessStatus, processDO.getProcessStatus());
            }
        }
        return wrapper;
    }

    @Override
    public List<String> getUsedModelIdList(String companyId) {
        return baseMapper.getUsedModelIdList(companyId);
    }

    @Override
    public List<ProcessDto> getMyCollection() {
        UserAccount userDetails = SecurityUtil.getCurrentUserNoNull();
        return baseMapper.getMyCollection(userDetails.getUserId(), userDetails.getComId());
    }

    @Override
    public int delete(String processKey) {
        LambdaQueryWrapper<ProcessDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProcessDO::getProcessKey,processKey);
        return this.baseMapper.delete(wrapper);
    }

    @Override
    public List<ProcessDO> selectByProcesskey(String processKey) {
        LambdaQueryWrapper<ProcessDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProcessDO::getProcessKey,processKey);
        return this.baseMapper.selectList(wrapper);
    }
}
