package cn.itcast.b2c.gciantispider.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.Permission;
import cn.itcast.b2c.gciantispider.service.IPermissionService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.JsonUtil;
/**
 * 权限控制器
 * 
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    
    @Autowired
    private IPermissionService permissionService;
    /**
     * 加载treeTable
     * @param request
     * @return
     */
    @RequestMapping(value="/getAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public String getAllPermission(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            List<Permission> perList = permissionService.getAllPermission();
            //对所查询的结果进行算法排序，历遍插入子节点
            List<Permission> temp = new ArrayList<Permission>();
            Iterator<Permission> it = perList.iterator();
            while(it.hasNext()){
                Permission p = it.next();
                Iterator<Permission> it1 = temp.iterator();
                int l = temp.size();
                final List<Permission> a = new CopyOnWriteArrayList<Permission>(temp);
                while(it1.hasNext()){
                    Permission p1 = it1.next();
                    if(p1.getId().equals(p.getParentid())){
                        int i = temp.indexOf(p1);
                        a.add(i+1, p);
                    }
                }
                if(l == a.size()){
                    temp.add(0,p);
                }else{
                    temp = a;
                }
            }
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
            mapJson.put("perList", temp);
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 获取符合搜索条件的id
     * @param request
     * @return
     */
    @RequestMapping(value="/searchPermission", method = RequestMethod.GET)
    @ResponseBody
    public String searchPermission(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            String seaStr = request.getParameter("seaStr");
            List<Object[]> ttIds =  permissionService.searchPermission(seaStr);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
            mapJson.put("ttIds", ttIds);
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    
    /**
     * 新增权限点
     * @param request
     * @return
     */
    @RequestMapping(value="/newPermission", method = RequestMethod.POST)
    @ResponseBody
    public String newPermission(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            String parentid = request.getParameter("parentid");  //父ID
            String name = request.getParameter("name");          //权限点名称
            String number = request.getParameter("number");      //权限点编号
            String url = request.getParameter("url");            //权限点URL
            String desc = request.getParameter("desc");
            Permission per = new Permission();
            per.setId(UUID.randomUUID().toString());
            if(Common.isEmpty(parentid)){
                per.setParentid("0");
                per.setPerlevel("1");
            }else{
                per.setParentid(parentid);
                Permission per0 = permissionService.getPermissionById(parentid);
                String perlevel = per0.getPerlevel();
                int pe = Integer.valueOf(perlevel) + 1;
                per.setPerlevel(String.valueOf(pe));
            }
            per.setName(name);
//            per.setNumber(number);
            per.setNum(number);
            per.setUrl(url);
            per.setDescription(desc);
                       
            permissionService.newPermission(per);
            mapJson.put("result", "0");
            mapJson.put("msg", "添加成功");
        } catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    
    /**
     * 修改权限点
     * @param request
     * @return
     */
    @RequestMapping(value="/modifyPermission", method = RequestMethod.POST)
    @ResponseBody
    public String modifyPermission(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            String id = request.getParameter("id");              //ID
            String name = request.getParameter("name");          //权限点名称
            String number = request.getParameter("number");      //权限点编号
            String url = request.getParameter("url");            //权限点URL
            String desc = request.getParameter("desc");          //权限点描述
            
            Permission per = permissionService.getPermissionById(id);
            if (null == per) {
                mapJson.put("result", "1");
                mapJson.put("msg", "不存在该权限点。");
                returnJson = JsonUtil.fromObject(mapJson);
                return returnJson;
            }
            per.setName(name);
            per.setNum(number);
            per.setUrl(url);
            per.setDescription(desc);
            
            permissionService.modifyPermission(per);
            mapJson.put("result", "0");
            mapJson.put("msg", "修改成功");
        } catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 通过id获取权限信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="/getPermissionById", method = RequestMethod.GET)
    @ResponseBody
    public String getPermissionById(@RequestParam("id") String id, HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            Permission per = permissionService.getPermissionById(id);
            if (null == per) {
                mapJson.put("result", "1");
                mapJson.put("msg", "不存在该权限点。");
                returnJson = JsonUtil.fromObject(mapJson);
                return returnJson;
            }
            mapJson.put("per", per);           
            mapJson.put("result", "0");
            mapJson.put("msg", "删除成功。");
        } catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 删除权限点
     * @param request
     * @return
     */
    @RequestMapping(value="/delPermission", method = RequestMethod.POST)
    @ResponseBody
    public String delPermission(@RequestParam("ids[]") String[] ids, HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            permissionService.delPermission(ids);
            mapJson.put("result", "0");
            mapJson.put("msg", "删除成功！");
        } catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }

}
