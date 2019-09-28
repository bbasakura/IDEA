package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhUserConversionRate;


public interface INhUserConversionRateService {

    /**
     * 通过日期查询国内外转化率
     * @param date
     * @return
     */
    List<NhUserConversionRate> queryByDate(String startDate,String userType);
}
