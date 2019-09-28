package cn.itcast.b2c.gciantispider.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.b2c.gciantispider.bean.PermissionBean;
import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.User;

public class RequestSessionManager {
    /**
     * 登陆成功后，保存需要的信息在session中
     * @param request
     */
    public static void setSuccessLoginInfo(HttpServletRequest request , List<PermissionBean>  permissionList, Account account,User user ){
        request.getSession().setAttribute(Constants.USER_PERMISSION, permissionList);           // 当前用户的权限
        request.getSession().setAttribute(Constants.HAVE_LOGIN, "YES");
        request.getSession().setAttribute(Constants.ACCOUNT, account.getName());
        request.getSession().setAttribute(Constants.USER_CNNAME, user.getName());
    }

}
