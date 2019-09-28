package cn.itcast.b2c.gciantispider.controller;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.User;
import cn.itcast.b2c.gciantispider.service.IAccountService;
import cn.itcast.b2c.gciantispider.service.IRefUserRoleService;
import cn.itcast.b2c.gciantispider.service.IUserService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.JsonUtil;
import cn.itcast.b2c.gciantispider.util.PasswordEncoderUtil;
import cn.itcast.b2c.gciantispider.util.RSAUtils;
/**
 * 用户控制器
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
    
    private final static Logger logger = Logger.getLogger(UserController.class.getName());
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IAccountService accountService;
    
    @Autowired
    private IRefUserRoleService refUserRoleService;
    /**
     * 用户列表
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/getAllUser")
    @ResponseBody
    public String getAllUser(HttpServletRequest request,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize) throws UnsupportedEncodingException{
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null; 
        String seaStr = request.getParameter("seaStr"); //搜索条件
        try {
            List<Account> userList = accountService.getAllUser(pageNo,pageSize,seaStr);
            Long pageAll = accountService.getAllCount(seaStr);
            int pageCount = (int) (pageAll % pageSize == 0 ? pageAll / pageSize : pageAll / pageSize + 1);
            mapJson.put("result", "0");
            mapJson.put("msg", "获取数据成功");
            mapJson.put("userList",userList);
            mapJson.put("pageCount", pageCount);
        }
        catch (Exception e) {
            e.printStackTrace();
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
        }
        returnJson = JsonUtil.fromObject(mapJson);
        return returnJson;
    }
    
    /**
     * 新增用户
     * @param request
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/newUser",method = RequestMethod.POST)
    @ResponseBody
    public String newUser(HttpServletRequest request) throws UnsupportedEncodingException  {       
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            String account = request.getParameter("account");    //账号名
            String name = request.getParameter("name");          //用户姓名   
            String tel = request.getParameter("tel");            //电话
            String email = request.getParameter("email");        //邮箱
                       
            //判断账号是否存在
            Account acc = accountService.getByAccount(account);
            if (null != acc) {
                mapJson.put("result", "1");
                mapJson.put("msg", "账号已经存在，不能新增。"); 
                returnJson = JsonUtil.fromObject(mapJson);
                return returnJson;
            } 
            //初始密码统一为 000000，经过SHA1加密后存入数据库中
            String password = "000000";
            password = new PasswordEncoderUtil("SHA1").encode(password);
            
            Account accobj = new Account();
            accobj.setId(UUID.randomUUID().toString());
            accobj.setName(account);
            accobj.setPassword(password);
            
            User user = new User();
            user.setId(accobj.getId());
            user.setName(name);
            user.setEmail(email);
            user.setMobile(tel);
            
            userService.newUser(user, accobj);
            mapJson.put("result", "0");
            mapJson.put("msg", "添加成功"); 
            returnJson = JsonUtil.fromObject(mapJson);
            return returnJson;           
        }
        catch (Exception e) {
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
            returnJson = JsonUtil.fromObject(mapJson);
            return returnJson; 
        }
    }
    /**
     * 删除用户
     * @param ids 账号ID
     * @param request
     * @return
     */
    @RequestMapping(value="/delUser",method = RequestMethod.POST )
    @ResponseBody
    public String delUser(@RequestParam("ids[]") String[] ids,HttpServletRequest request){
        Map<String, Object> mapJson = new HashMap<String, Object>();
        String returnJson = null;
        try {
            //分割ids字符串，以逗号作为分隔符
            userService.delUser(ids);
            mapJson.put("result", "0");
            mapJson.put("msg", "删除成功"); 
            returnJson = JsonUtil.fromObject(mapJson);
            return returnJson;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            mapJson.put("result", "99");
            mapJson.put("msg", "系统异常");
            returnJson = JsonUtil.fromObject(mapJson);
            return returnJson;  
        }
    }
    /**
     * 角色分配
     * @param accountId 账号ID
     * @param roleIds 角色ID集合，用逗号隔开
     * @param request
     * @return
     */
     @RequestMapping(value="/configRole",method = RequestMethod.POST)
     @ResponseBody
     public String configRole(@RequestParam("ids[]") String[] ids,@RequestParam(value="list[]",required=false) String[] roleIds){
         Map<String, Object> mapJson = new HashMap<String, Object>();
         String returnJson = null;
         try {
             refUserRoleService.configRolesByAccountId(ids, roleIds);
             mapJson.put("result", "0");
             mapJson.put("msg", "分配成功"); 
             returnJson = JsonUtil.fromObject(mapJson);
             return returnJson;
         }catch(Exception e){
             logger.error(e.getMessage(),e);
             mapJson.put("result", "99");
             mapJson.put("msg", "系统异常");
             returnJson = JsonUtil.fromObject(mapJson);
             return returnJson;  
         }
     }
     /**
      * 重置密码
      * @param ids 账号ID集合
      * @param request
      * @return
      */
     @RequestMapping(value="/resetPwd",method = RequestMethod.POST)
     @ResponseBody
     public String resetPwd(@RequestParam("ids[]") String[] ids,HttpServletRequest request){
         Map<String, Object> mapJson = new HashMap<String, Object>();
         String returnJson = null;
         try {
             userService.resetPwd(ids);
             mapJson.put("result", "0");
             mapJson.put("msg", "密码重置成功"); 
             returnJson = JsonUtil.fromObject(mapJson);
             return returnJson;
         }catch(Exception e){
             mapJson.put("result", "99");
             mapJson.put("msg", "系统异常");
             returnJson = JsonUtil.fromObject(mapJson);
             return returnJson; 
         }       
     }
     /**
      * 修改密码
      * @param oldPassword 原密码
      * @param newPassword 新密码
      * @return
      */
     @SuppressWarnings("unchecked")
     @RequestMapping(value = "/modifyPassword/pwdkey/{pwdkey}/oldPassword/{oldPassword}/newPassword/{newPassword}", 
             method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
     @ResponseBody
     public String modifyPassword(@PathVariable String oldPassword, @PathVariable String newPassword,
                             @PathVariable String pwdkey,HttpServletRequest request) {
         Map<String, Object> mapJson = new HashMap<String, Object>();
         String returnJson = null;
         String account = (String) request.getSession().getAttribute(Constants.ACCOUNT);; 
         try {
             if (!Common.isEmpty(account)) {
                 Account accobj = accountService.getByAccount(account);
                 Map<String, KeyPair> temps = (Map<String, KeyPair>) request.getSession().getAttribute("_RSA_KEY");
                 KeyPair keyPair = null;
                 if (temps != null)
                 {
                     keyPair = temps.get(pwdkey);
                 }
                 oldPassword = RSAUtils.decryptPwd(request, pwdkey, oldPassword);    //解密
                 temps.put(pwdkey, keyPair);                                         //用于下次解密
                 oldPassword = new PasswordEncoderUtil("SHA1").encode(oldPassword);  //对输入的密码进行SHA1加密，再进行校验
                 if(!(oldPassword.equals(accobj.getPassword()))){
                     mapJson.put("result","1");
                     mapJson.put("msg", "原密码输入有误。");
                     returnJson = JsonUtil.fromObject(mapJson);
                     return returnJson;
                 }else{
                     newPassword = RSAUtils.decryptPwd(request, pwdkey, newPassword);    //解密
                     newPassword = new PasswordEncoderUtil("SHA1").encode(newPassword);  //对新密码进行SHA1加密再保存起来
                     accobj.setPassword(newPassword);             
                     accountService.update(accobj);  
                     mapJson.put("result","0");
                     mapJson.put("msg", "修改成功。");
                     returnJson = JsonUtil.fromObject(mapJson);
                     //销毁session
                     request.getSession().invalidate();
                     return returnJson;
                 }
             }else {
                 mapJson.put("result","2");
                 mapJson.put("msg", "没有登陆");
                 returnJson = JsonUtil.fromObject(mapJson);
                 return returnJson;
             }           
         }
         catch (Exception e) {            
             logger.error(e.getMessage(), e);
             mapJson.put("result", "99");
             mapJson.put("msg", "系统异常");
             returnJson = JsonUtil.fromObject(mapJson);
             return returnJson;
         }        
     }    
}
