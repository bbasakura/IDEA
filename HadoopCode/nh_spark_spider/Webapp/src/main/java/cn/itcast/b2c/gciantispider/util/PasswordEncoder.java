package cn.itcast.b2c.gciantispider.util;

public interface PasswordEncoder {
    /**
     * 加密
     * @param password
     * @return
     */
    String encode(String password);
}
