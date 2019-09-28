package cn.itcast.b2c.gciantispider.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.Permission;
import cn.itcast.b2c.gciantispider.model.SystemRole;
import cn.itcast.b2c.gciantispider.service.IAccountService;
import cn.itcast.b2c.gciantispider.service.IPermissionService;
import cn.itcast.b2c.gciantispider.service.IRefRolePermissionService;
import cn.itcast.b2c.gciantispider.service.IRefUserRoleService;
import cn.itcast.b2c.gciantispider.service.ISystemRoleService;
import cn.itcast.b2c.gciantispider.util.JsonUtil;
/**
 * 角色控制器
 *
 */
@Controller
@RequestMapping("/role")
public class SystemRoleController {
    
    @Autowired
    private ISystemRoleService systemRoleService;
    
    @Autowired
    private IAccountService accountService;
    
    @Autowired
    private IRefUserRoleService refUserRoleService;
    
    @Autowired
    private IPermissionService permissionService;
    
    @Autowired
    private IRefRolePermissionService refRolePermissionService;
    
    /**
     * 分页获取角色列表
     * @param request
     * @param pageNo 页码
     * @param pageSize 每页显示条数
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/getAllRole",method = RequestMethod.GET)
    @ResponseBody
    public String getAllRole(HttpServletRequest request,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize) throws UnsupportedEncodingException{
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        String seaStr = request.getParameter("seaStr"); //搜索条件
        try{
            List<SystemRole> roleList = systemRoleService.getAllRole(pageNo,pageSize,seaStr);
            Long pageAll = systemRoleService.getAllCount(seaStr);
            int pageCount = (int) (pageAll % pageSize == 0 ? pageAll / pageSize : pageAll / pageSize + 1);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
            mapJson.put("roleList", roleList);
            mapJson.put("pageCount", pageCount);
        }catch(Exception e){
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);                      
        return returnJson;
    }
    /**
     * 通过账户信息获取角色信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getRoleByUser",method = RequestMethod.GET)
    @ResponseBody
    public String getRoleByUser(@RequestParam("id") String id){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            List<String> roleList = systemRoleService.getRoleByUser(id);
            mapJson.put("roleList",roleList);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 新增角色
     * @param request
     * @return
     */
    @RequestMapping(value="/newRole",method = RequestMethod.POST)
    @ResponseBody
    public String newRole(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        String name = request.getParameter("name");
        String cnname = request.getParameter("cnname");
        String description = request.getParameter("description");
        try{
            SystemRole systemRole = new SystemRole();
            systemRole.setId(UUID.randomUUID().toString());
            systemRole.setName(name);
            systemRole.setCnname(cnname);
            systemRole.setDescription(description);
            systemRole.setCreatetime(new Date());
            systemRoleService.add(systemRole);
            mapJson.put("result","0");
            mapJson.put("msg", "");
        }catch(Exception e){
            System.out.println(e.getMessage());
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);                      
        return returnJson;
    }
    /**
     * 更新角色
     * @param request
     * @return
     */
    @RequestMapping(value="/editRole",method = RequestMethod.POST)
    @ResponseBody
    public String editRole(HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String cnname = request.getParameter("cnname");
        String description = request.getParameter("description");
        try {
            systemRoleService.modify(id,name,cnname,description);
            mapJson.put("result","0");
            mapJson.put("msg", "");
        }catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 根据id获取角色
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="/getRoleById",method = RequestMethod.GET)
    @ResponseBody
    public String getRoleById(HttpServletRequest request,@RequestParam("roleId") String roleId){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            SystemRole systemRole = systemRoleService.getRoleById(roleId);
            mapJson.put("systemRole", systemRole);
            mapJson.put("result","0");
            mapJson.put("msg", "");
        }catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 删除角色
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value="/delRole",method = RequestMethod.POST)
    @ResponseBody
    public String delRole(HttpServletRequest request,@RequestParam("ids[]") String[] ids){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            systemRoleService.delRole(ids);
            mapJson.put("result","0");
            mapJson.put("msg", "");
        }catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 角色用户列表
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value="/roleUser",method = RequestMethod.POST)
    @ResponseBody
    public String roleUser(HttpServletRequest request,@RequestParam("ids[]") String[] ids,
                @RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            List<Account> list = accountService.roleUser(ids,pageNo,pageSize);
            Long pageAll = accountService.roleUserCount(ids);
            int pageCount = (int) (pageAll % pageSize == 0 ? pageAll / pageSize : pageAll / pageSize + 1);
            mapJson.put("result","0");
            mapJson.put("msg", "");
            mapJson.put("list", list);
            mapJson.put("pageCount", pageCount);
        }catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 根据角色关联表id删除记录
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value="/removeRoleUser",method = RequestMethod.POST)
    @ResponseBody
    public String removeRoleUser(HttpServletRequest request,@RequestParam("id") String id){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            refUserRoleService.delRefUserRoleById(id);
            mapJson.put("result", "0");
            mapJson.put("msg", "操作成功！");
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 根据id获取权限信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getRolePermission",method = RequestMethod.POST)
    @ResponseBody
    public String getDeptPermission(@RequestParam("id") String id){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            List<Permission> perList = permissionService.getRolePermission(id);
            mapJson.put("perList", perList);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    /**
     * 保存权限信息
     * @param ids 权限点id集合
     * @param roleId
     * @return
     */
    @RequestMapping(value="/saveRolePermission",method = RequestMethod.POST)
    @ResponseBody
    public String saveRolePermission(HttpServletRequest request,@RequestParam("roleId") String roleId,
                    @RequestParam(value="ids[]",required=false) String[] ids){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            refRolePermissionService.saveRolePermission(roleId,ids);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    
}
