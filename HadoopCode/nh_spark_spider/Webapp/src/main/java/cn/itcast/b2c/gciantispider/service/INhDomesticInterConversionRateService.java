package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhDomesticInterConversionRate;


public interface INhDomesticInterConversionRateService {
    /**
     * 通过日期查询国内外转化率
     * @param date
     * @return
     */
    List<NhDomesticInterConversionRate> queryByDate(String startDate,String flightType);
    
}
