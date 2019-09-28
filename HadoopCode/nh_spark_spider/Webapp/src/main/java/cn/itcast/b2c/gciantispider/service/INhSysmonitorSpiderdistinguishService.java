package cn.itcast.b2c.gciantispider.service;

import cn.itcast.b2c.gciantispider.pageUtil.SpiderVO;

public interface INhSysmonitorSpiderdistinguishService {
    /**
     * 系统监控:
     * 获取当天爬虫的数量以及累积的数量
     * @param date
     * @return
     */
    public SpiderVO getSysmonitorSpiderdistinguish();
}
