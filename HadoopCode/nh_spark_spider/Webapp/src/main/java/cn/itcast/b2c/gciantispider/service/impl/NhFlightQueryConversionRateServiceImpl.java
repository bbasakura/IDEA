package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhFlightQueryConversionRateDao;
import cn.itcast.b2c.gciantispider.model.NhFlightQueryConversionRate;
import cn.itcast.b2c.gciantispider.service.INhFlightQueryConversionRateService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class NhFlightQueryConversionRateServiceImpl implements INhFlightQueryConversionRateService {

    @Autowired
    INhFlightQueryConversionRateDao nhFlightQueryConversionRateDao;
    
    @Override
    public List<NhFlightQueryConversionRate> queryByDate(String startDate,String querySpiderType) {
        String sql = "select ndi.id, ndi.step_type, ndi.conversion_rate FROM nh_flight_query_conversion_rate ndi where 1=1";
        Map<String,Object> map = new HashMap<String,Object>();
        if(!Common.isEmpty(startDate) && !Common.isEmpty(querySpiderType)){
            sql = sql + " AND DATE_FORMAT(ndi.record_time,'%Y-%m-%d') =:startDate AND ndi.query_spider_type=:querySpiderType";
            map.put("startDate", startDate);
            map.put("querySpiderType", querySpiderType);
        }
        List<Object[]> nhFlightQueryConversionRates = nhFlightQueryConversionRateDao.findBySql(sql,map);
        List<NhFlightQueryConversionRate> list = new ArrayList<NhFlightQueryConversionRate>();
        if (nhFlightQueryConversionRates!=null && nhFlightQueryConversionRates.size()!=0) {
            for (Object[] obj : nhFlightQueryConversionRates) {
                NhFlightQueryConversionRate nhFlightQueryConversionRate = new NhFlightQueryConversionRate();
                nhFlightQueryConversionRate.setId(obj[0] + "");
                
                if (null == obj[1]) {
                    nhFlightQueryConversionRate.setStepType(null);
                }else{
                    nhFlightQueryConversionRate.setStepType(Integer.valueOf(obj[1]+""));
                }
                
                if (null == obj[2]) {
                    nhFlightQueryConversionRate.setConversionValue(null);
                }else{
                    nhFlightQueryConversionRate.setConversionValue(Float.valueOf(obj[2]+""));
                }
                
                list.add(nhFlightQueryConversionRate);
            }
            return list;
        }
        return null;
    }

}
