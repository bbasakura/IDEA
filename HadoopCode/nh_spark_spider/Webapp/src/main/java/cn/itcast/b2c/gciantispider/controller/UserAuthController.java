package cn.itcast.b2c.gciantispider.controller;

import java.io.IOException;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.b2c.gciantispider.bean.PermissionBean;
import cn.itcast.b2c.gciantispider.bean.UserForm;
import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.User;
import cn.itcast.b2c.gciantispider.service.IAccountService;
import cn.itcast.b2c.gciantispider.service.IPermissionService;
import cn.itcast.b2c.gciantispider.service.IUserService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.CookieUtil;
import cn.itcast.b2c.gciantispider.util.DateFormatter;
import cn.itcast.b2c.gciantispider.util.RSAUtils;
import cn.itcast.b2c.gciantispider.util.RequestSessionManager;
import cn.itcast.b2c.gciantispider.util.TimeUtil;

@Controller
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private IAccountService accountService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 用户登录
     * 
     * @param request
     * @param response
     * @param user
     * @throws IOException
     */
    @RequestMapping(value = "/login")
    public String login(String username, String source, HttpServletRequest request, Model model) throws IOException {
        String temp = (String) request.getAttribute("LOGIN_SOURCE");
        if (StringUtils.isEmpty(source) && !StringUtils.isEmpty(temp)) {
            source = temp;
        }
        if (!StringUtils.isEmpty(source)) {
            model.addAttribute("source", source);
        }
        if (!StringUtils.isEmpty(username)) {
            model.addAttribute("username", CookieUtil.getValue(request, "userName"));
        }
        Map<String, Object> map = RSAUtils.generateRSAKeys1(request);
        model.addAttribute("_RSA_MODULES", map.get("_RSA_MODULES"));
        model.addAttribute("_RSA_EXPONENT", map.get("_RSA_EXPONENT"));
        return "login";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/logon")
    public String logon(HttpServletRequest request, UserForm user, String source, Model model) throws IOException {
        try {
            String tempKey = (String) request.getSession().getAttribute(Constants.KEY);
            Map<String, KeyPair> temps = (Map<String, KeyPair>) request.getSession().getAttribute("_RSA_KEY");
            KeyPair keyPair = null;
            if (temps != null) {
                keyPair = temps.get(tempKey);
            }
            String password = RSAUtils.decryptPwd(request, tempKey, user.getPassword());
            // 方便二次解密
            temps.put(tempKey, keyPair);
            String userName = RSAUtils.decryptPwd(request, tempKey, user.getUserName());
            Account account = accountService.getByAccountAndPassword(userName, password);

            if (null == account) {
                model.addAttribute("LOGIN_ERROR_MES", "用户名/密码输入有误");
                model.addAttribute("username", CookieUtil.getValue(request, "userName"));
                return "forward:/auth/login";
            }

            account.setLastLogTime(account.getThisLogTime());
            account.setThisLogTime(DateFormatter.yyyy_MM_DDHH24miss(TimeUtil.getCurrTime()));
            accountService.update(account);

            User userBean = userService.queryById(account.getId());
            String curSystem = request.getSession().getServletContext().getInitParameter("curSystem");
            List<PermissionBean> permissionList = accountService.getAccUserPersByAccount(userName, curSystem);
            RequestSessionManager.setSuccessLoginInfo(request, permissionList, account, userBean);
            if (StringUtils.isEmpty(source)) {
                return "redirect:/pages/index.jsp";
            }
            return "redirect:" + source;
        }
        catch (Exception e) {
            model.addAttribute("LOGIN_ERROR_MES", e.getMessage().toString());
            model.addAttribute("username", CookieUtil.getValue(request, "userName"));
            return "forward:/auth/login";
        }
    }

    /**
     * 退出
     * 
     * @param request
     * @param reponse
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse reponse, Model model) {
        // 销毁session
        request.getSession().invalidate();
        // 跳转到登录页面
        return "redirect:/auth/login";
    }

    /**
     * 随机产生密钥。
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/login/key", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request) {
        return RSAUtils.generateRSAKeys1(request);
    }

    /**
     * 获取当前系统的权限url
     */
    @RequestMapping(value = "/getSysPerUrl/{curSystem}")
    @ResponseBody
    public List<String> getSysPerUrl(@PathParam("curSystem") String curSystem, HttpServletRequest request) {
        return permissionService.getSystemPerBySysName(curSystem);
    }

}
