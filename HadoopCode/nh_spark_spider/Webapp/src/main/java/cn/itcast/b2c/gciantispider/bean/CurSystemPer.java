package cn.itcast.b2c.gciantispider.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 封装当前系统的权限点（调用sso获取当前系统的权限点接口返回的json数据）
 * 
 */
public class CurSystemPer implements Serializable {

    private static final long serialVersionUID = -5611461461531887942L;

    /**
     * 存储全部权限点的URL
     */
    private static List<String> perUrList;

    public static List<String> getPerUrList() {
        return perUrList;
    }

    public static void setPerUrList(List<String> perUrList) {
        CurSystemPer.perUrList = perUrList;
    }
}
