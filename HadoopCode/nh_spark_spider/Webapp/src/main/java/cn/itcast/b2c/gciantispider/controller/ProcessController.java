package cn.itcast.b2c.gciantispider.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.NhModel;
import cn.itcast.b2c.gciantispider.model.NhProcessInfo;
import cn.itcast.b2c.gciantispider.model.NhProcessNum;
import cn.itcast.b2c.gciantispider.model.NhRule;
import cn.itcast.b2c.gciantispider.model.NhStrategy;
import cn.itcast.b2c.gciantispider.service.INhModelService;
import cn.itcast.b2c.gciantispider.service.INhProcessInfoService;
import cn.itcast.b2c.gciantispider.service.INhProcessNumService;
import cn.itcast.b2c.gciantispider.service.INhRuleService;
import cn.itcast.b2c.gciantispider.service.INhStrategyService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.RedisFlagUtils;

import com.alibaba.fastjson.JSON;

/**
 * 流程管理
 * 
 * Title: JIESAI_NH <br>
 * Description: <br>
 * 
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

    private final static Logger logger = Logger.getLogger(ProcessController.class.getName());

    private static final String PROCESS = "/process/process";

    @Autowired
    private INhProcessInfoService nhProcessInfoService;

    @Autowired
    private INhModelService nhModelService;

    @Autowired
    private INhRuleService nhRuleService;

    @Autowired
    private INhStrategyService nhStrategyService;
    
    @Autowired
    INhProcessNumService nhProcessNumService;
    
    @RequestMapping(value = "/processMain")
    public String process(HttpServletRequest request, HttpServletResponse response, Model mode) throws Exception {
        return PROCESS;
    }
    /**
     * 根据id删除相关流程信息
     * 
     * @param id
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/deleteProcess", method = RequestMethod.POST)
    @ResponseBody
    public Map deleteProcess(@RequestParam("id") String id, HttpServletRequest request) {
        Map<String, Object> mapS = new HashMap<String, Object>();
        try {
            if (!Common.isEmpty(id)) {
                nhProcessInfoService.deleteProcess(id);
                //删除流程维表里面的数据
                NhProcessNum nhProcessNum = nhProcessNumService.getNhProcessNum(id);
                if (null!=nhProcessNum) {
                    nhProcessNumService.delNhProcessNum(nhProcessNum);
                }
                //向redis存入信息改变标识
                RedisFlagUtils.setChangeProcessFlag();
                mapS.put("code", "1");
                mapS.put("msg", "操作成功");
            }
            else {
                mapS.put("code", "2");
                mapS.put("msg", "请求参数异常");
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            mapS.put("code", "0");
            mapS.put("msg", "删除失败");
        }
        return mapS;
    }

    /**
     * 根据id获取相关流程信息
     * 
     * @param id
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/getNhProcessInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Map getNhProcessInfoById(@RequestParam("id") String id, HttpServletRequest request) {
        Map<String, Object> mapS = new HashMap<String, Object>();
        try {
            NhProcessInfo nhProcessInfo = nhProcessInfoService.getNhProcessInfoById(id);
            System.err.println("nhProcessInfo:"+ nhProcessInfo);
            Set<NhRule> nhRules = nhProcessInfo.getNhRules();
            int ruleType;
            for (NhRule nhRule : nhRules) {
                // 获取规则类型方便前端判断
                ruleType = nhRule.getRuleType();
                mapS.put("ruleType", ruleType);
            }
            mapS.put("nhProcessInfo", nhProcessInfo);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            System.err.println(e.getMessage());
        }
        return mapS;
    }

    /**
     * 根据流程id查询所有相关的规则
     * 
     * @param id
     * @param request
     * @return
     */
    @SuppressWarnings({ "unused", "rawtypes" })
    @RequestMapping(value = "/findNhRuleById", method = RequestMethod.POST)
    @ResponseBody
    public Map findNhRuleById(@RequestParam("id") String id, HttpServletRequest request) {
        Map<String, Object> mapS = new HashMap<String, Object>();
        List<NhRule> nhRules = new ArrayList<NhRule>();
        try {
            nhRules = nhRuleService.findNhRuleById(id);
            // mapS.put("nhRules", nhRules);
            // mapS = ProcessUtils.getRuleInfo(nhRules);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
        }
        return mapS;
    }

    /**
     * 查询所有流程信息
     * 
     * @param strategy
     * @param type
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/findAllProcessInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map findAllProcessInfo(HttpServletRequest request) {
        Map<String, Object> mapS = new HashMap<String, Object>();
        List listInfo = new ArrayList();
        try {
            listInfo = nhProcessInfoService.findAllProcessInfo();
            mapS.put("listInfo", listInfo);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
        }
        return mapS;
    }

    /**
     * 新建流程
     * 
     * @param jsonProcessInfo
     * @param jsonRules
     * @param jsonModel
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/saveProcess", method = RequestMethod.POST)
    @ResponseBody
    public Map saveProcess(@RequestParam("jsonProcessInfo") String jsonProcessInfo, 
                    @RequestParam("jsonRules") String jsonRules, 
                    @RequestParam("jsonModel") String jsonModel, 
                    @RequestParam("jsonNhStrategy") String jsonNhStrategy, 
                    HttpServletRequest request) {
        // 解析前段传过来的json数据
        NhProcessInfo nhProcessInfo = JSON.parseObject(jsonProcessInfo, NhProcessInfo.class);
        List<NhRule> nhRules = JSON.parseArray(jsonRules, NhRule.class);
        NhModel nhModel = JSON.parseObject(jsonModel, NhModel.class);
        NhStrategy nhStrategy = JSON.parseObject(jsonNhStrategy, NhStrategy.class);
        Map<String, Object> mapS = new HashMap<String, Object>();
        if (Common.isEmpty(nhProcessInfo.getProcessName())) {
            mapS.put("code", "2");
            mapS.put("msg", "名称不能为空，请仔细检查。");
            return mapS;
        }
        // 校验流程名称，判断该名称是否已存在
        NhProcessInfo nhProcessInf1 = nhProcessInfoService.getNhProcessInfoByName(nhProcessInfo.getProcessName());
        if (nhProcessInf1 != null) {
            mapS.put("code", "3");
            mapS.put("msg", "该名称已存在，请重新填写!");
            return mapS;
        }
        try {
            // 保存流程信息
            nhProcessInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            nhProcessInfo.setCreatePerson((String)request.getSession().getAttribute(Constants.USER_CNNAME));
            nhProcessInfo.setId(UUID.randomUUID().toString());
            nhProcessInfo.setStatus(0);
            nhProcessInfoService.saveNhProcessInfo(nhProcessInfo);
            //保存流程维表信息
            NhProcessNum nhProcessNum = new NhProcessNum();
            nhProcessNum.setProcessName(nhProcessInfo.getProcessName());
            nhProcessNum.setProcessId(nhProcessInfo.getId());
            nhProcessNumService.insertNhProcessNum(nhProcessNum);
            // 保存模型信息
            nhModel.setId(nhProcessInfo.getId());
            nhModelService.saveNhModel(nhModel);
            // 循环保存规则信息
            for (NhRule nhRule : nhRules) {
                nhRule.setId(UUID.randomUUID().toString());
                nhRule.setNhProcessInfo(nhProcessInfo);
                nhRuleService.saveNhRule(nhRule);
            }
            // 保存策略信息
            nhStrategy.setId(nhProcessInfo.getId());
            nhStrategyService.savaNhStrategy(nhStrategy);
            //向redis存入信息改变标识
            RedisFlagUtils.setChangeProcessFlag();
            mapS.put("code", "0");
            mapS.put("msg", "信息保存成功");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            mapS.put("code", "1");
            mapS.put("msg", "信息保存失败");
        }
        return mapS;
    }

    /**
     * 修改流程信息
     * 
     * @param jsonProcessInfo
     * @param jsonRules
     * @param jsonModel
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
    @ResponseBody
    public Map updateProcess(@RequestParam("jsonProcessInfo") String jsonProcessInfo,
                        @RequestParam("jsonRules") String jsonRules, 
                        @RequestParam("jsonModel") String jsonModel, 
                        @RequestParam("jsonNhStrategy") String jsonNhStrategy, 
                        HttpServletRequest request) {
        // 将前段传的json数据解析成相对应的实体
        NhProcessInfo nhProcessInfo = JSON.parseObject(jsonProcessInfo, NhProcessInfo.class);
        List<NhRule> nhRules = JSON.parseArray(jsonRules, NhRule.class);
        NhModel nhModel = JSON.parseObject(jsonModel, NhModel.class);
        NhStrategy nhStrategy = JSON.parseObject(jsonNhStrategy, NhStrategy.class);
        Map<String, Object> mapS = new HashMap<String, Object>();
        int status;
        String id = nhProcessInfo.getId();
        // 校验前段传过来的id
        if (!Common.isEmpty(id)) {
            // 根据id重新获取流程运行信息
            NhProcessInfo oldNhProcessInfo = nhProcessInfoService.getNhProcessInfoById(id);
            status = oldNhProcessInfo.getStatus();
            // 修改数据前删除该id关联的所有数据（防止垃圾数据产生）
            nhProcessInfoService.deleteProcess(id);
        }else {
            mapS.put("code", "2");
            mapS.put("msg", "请求参数异常");
            return mapS;
        }
        NhProcessInfo nhProcessInf1 = nhProcessInfoService.getNhProcessInfoByName(nhProcessInfo.getProcessName());
        if (nhProcessInf1 != null) {
            mapS.put("code", "3");
            mapS.put("msg", "该名称已存在，请重新填写!");
            return mapS;
        }
        try {
            // 保存流程信息
            nhProcessInfo.setId(id);
            nhProcessInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            nhProcessInfo.setCreatePerson((String)request.getSession().getAttribute(Constants.USER_CNNAME));
            nhProcessInfo.setStatus(status);
            nhProcessInfoService.saveNhProcessInfo(nhProcessInfo);
            //更新维表的相关数据
            NhProcessNum nhProcessNum = nhProcessNumService.getNhProcessNum(id);
            nhProcessNum.setProcessId(id);
            nhProcessNum.setProcessName(nhProcessInfo.getProcessName());
            nhProcessNumService.updateNhProcessNum(nhProcessNum);
            // 保存模型信息
            nhModel.setId(id);
            nhModelService.saveNhModel(nhModel);
            // 循环保存规则信息
            for (NhRule nhRule : nhRules) {
                nhRule.setId(UUID.randomUUID().toString());
                nhRule.setNhProcessInfo(nhProcessInfo);
                nhRuleService.saveNhRule(nhRule);
            }
            // 保存策略信息
            nhStrategy.setId(id);
            nhStrategyService.savaNhStrategy(nhStrategy);
            //向redis存入信息改变标识
            RedisFlagUtils.setChangeProcessFlag();
            mapS.put("code", "0");
            mapS.put("msg", "信息修改成功");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            mapS.put("code", "1");
            mapS.put("msg", "信息修改失败");
        }
        return mapS;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/updateStatusbyId", method = RequestMethod.POST)
    @ResponseBody
    public Map updateStatusbyId(@RequestParam("id") String id, HttpServletRequest request) {
        Map<String, Object> mapS = new HashMap<String, Object>();
        try {
            if (!Common.isEmpty(id)) {
                nhProcessInfoService.updateStatusbyId(id);
                //向redis存入信息改变标识
                RedisFlagUtils.setChangeProcessFlag();
                mapS.put("code", "1");
                mapS.put("msg", "流程运行状态更新成功");
            }
            else {
                mapS.put("code", "2");
                mapS.put("msg", "请求参数异常");
                return mapS;
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            mapS.put("code", "0");
            mapS.put("msg", "信息更新失败");
        }
        return mapS;
    }
}
