package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.NhFlightQueryConversionRate;

public interface INhFlightQueryConversionRateService {
    
    /**
     * 通过日期查询国内外转化率
     * @param date
     * @return
     */
    List<NhFlightQueryConversionRate> queryByDate(String startDate,String querySpiderType);

}
