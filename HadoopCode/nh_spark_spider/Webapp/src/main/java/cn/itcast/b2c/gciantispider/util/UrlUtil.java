package cn.itcast.b2c.gciantispider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.b2c.gciantispider.bean.CurSystemPer;
import cn.itcast.b2c.gciantispider.bean.PermissionBean;

/**
 * URL工具类
 * 
 */
public class UrlUtil {
    
    /**
     * 校验权限的URL是否具备权限
     * @param requestUrl 请求的URL
     * @param listUrl
     * @return true表示有权限，false表示无权限
     */
    public static boolean validatePerUrl(String reqUrl, List<PermissionBean> permissionList){
        if (null==permissionList) {
            return false;
        }
        List<String> sysPerUrlList = ListUtil.normalList(CurSystemPer.getPerUrList());
        List<String> curUserSysPerUrlList = ListUtil.normalList(getUserPerUrl(permissionList));
        if (regExValidate(reqUrl,sysPerUrlList,curUserSysPerUrlList)) {  //与当前系统限制的url进行匹配
            return true;
        }else {
            return false;
        }       
    }
    /**
     * 正则校验
     * @param reqUrl 当前请求的url
     * @param sysPerUrlList 当前系统需要限制的权限URL
     * @param curUserSysPerUrlList 当前用户拥有的在本系统所拥有的限制权限URL
     * @return
     */
    public static boolean regExValidate(String reqUrl, List<String> sysPerUrlList, List<String> curUserSysPerUrlList){
        if (null==sysPerUrlList || 0==sysPerUrlList.size() ) {       //系统没有需要限制的权限URL里面，无需限制
            return true;
        }
        for (String perUrl : sysPerUrlList) {
            //先进行uri验证
            String[]  perUrls = perUrl.split(":");
            String[]  reqUrls = reqUrl.split(":");
            Pattern pattern = Pattern.compile(perUrls[1]);
            Matcher mat = pattern.matcher(reqUrls[1]);
            if (mat.matches()) {        //请求URL在系统需要限制的权限URL里面 
                //对当前用户所拥有该系统的权限URL进行判断
                if (null==curUserSysPerUrlList || 0==curUserSysPerUrlList.size() ) {   
                    return false;
                }
                //进行请求方法验证
                pattern = Pattern.compile(perUrls[0]);
                mat = pattern.matcher(reqUrls[0]);
                if (mat.matches()) {
                    for (String userPerUrl : curUserSysPerUrlList){
                        String[] userPerUrls = userPerUrl.split(":");
                        pattern = Pattern.compile(userPerUrls[1]);
                        mat = pattern.matcher(reqUrls[1]);
                        if (mat.matches()) {
                            pattern = Pattern.compile(userPerUrls[0]);
                            mat = pattern.matcher(reqUrls[0]);
                            if (mat.matches()) {
                                return true;
                            }
                        }else {
                            continue; 
                        }
                    }
                    return false;
                } 
               
            }else {                 //结束本次循环,进入下一次循环
                continue; 
            }
        }
        return true;
    }
    /**
     * 组装当前请求的URL
     * @param request
     * @return
     */
    public static String constructReqUrl(HttpServletRequest request) {
        String url2 = request.getRequestURI();
        StringBuffer url = new StringBuffer();
        url.append(request.getMethod().toUpperCase());
        url.append(":");
        url.append(url2);
        if (request.getQueryString() != null) { 
            url.append('?'); 
            url.append(request.getQueryString()); 
        } 
        return url.toString(); 
    }    
    /**
     * 组装服务端的权限URL
     * @param perUrl
     * @return
     */
    public static String constructServicePerUrl(String perUrl){               
        //去掉空格
        String temUrl = perUrl.replaceAll(" ", ""); 
        String[] strs = temUrl.split(":");
        String finalUrl = "";
        for (int i = 1; i < strs.length; i++) {
            finalUrl += strs[i];
        }        
        finalUrl = strs[0].toUpperCase() + ":" + finalUrl;
        return finalUrl;
    }
    /**
     * 获取用户的权限url
     */
    public static List<String> getUserPerUrl(List<PermissionBean> permissionList){
        if (null == permissionList) {
            return null;
        }
        List<String> userPerList = new ArrayList<String>();
        for(PermissionBean permissionBean : permissionList){
            userPerList.add(permissionBean.getPerUrl());
        }
        return userPerList;
    }
    
    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("GET|POST:/sso/userInfo/modifyPassword/pwdkey/.*/oldPassword/.*/newPassword/.*");
//        Matcher mat = pattern.matcher("POST:/sso/userInfo/modifyPassword/pwdkey/123/oldPassword/45/newPassword/56");
//        Pattern pattern = Pattern.compile("GET|POST:/gciantispider/base/to/manage/roleManage.*");
//        Matcher mat = pattern.matcher("GET:/gciantispider/base/to/manage/roleManage");
        Pattern pattern = Pattern.compile("GET|POST");
        Matcher mat = pattern.matcher("POST");
        boolean b= mat.matches();
        System.out.println(b);
    }
}
