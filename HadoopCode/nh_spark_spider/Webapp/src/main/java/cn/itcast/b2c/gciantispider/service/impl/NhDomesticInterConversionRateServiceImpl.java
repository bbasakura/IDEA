package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhDomesticInterConversionRateDao;
import cn.itcast.b2c.gciantispider.model.NhDomesticInterConversionRate;
import cn.itcast.b2c.gciantispider.service.INhDomesticInterConversionRateService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class NhDomesticInterConversionRateServiceImpl implements INhDomesticInterConversionRateService {

    @Autowired
    INhDomesticInterConversionRateDao nhDomesticInterConversionRateDao;
    
    @Override
    public List<NhDomesticInterConversionRate> queryByDate(String startDate,String flightType) {
        String sql = "select ndi.id, ndi.step_type, ndi.conversion_rate FROM nh_domestic_inter_conversion_rate ndi where 1=1";
        Map<String,Object> map = new HashMap<String,Object>();
        if(!Common.isEmpty(startDate) && !Common.isEmpty(flightType)){
            sql = sql + " AND DATE_FORMAT(ndi.record_time,'%Y-%m-%d') =:startDate AND ndi.flight_type=:flightType";
            map.put("startDate", startDate);
            map.put("flightType", flightType);
        }
        List<Object[]> nhDomesticInterConversionRates = nhDomesticInterConversionRateDao.findBySql(sql,map);
        List<NhDomesticInterConversionRate> list = new ArrayList<NhDomesticInterConversionRate>();
        if (nhDomesticInterConversionRates!=null && nhDomesticInterConversionRates.size()!=0) {
            for (Object[] obj : nhDomesticInterConversionRates) {
                NhDomesticInterConversionRate nhDomesticInterConversionRate = new NhDomesticInterConversionRate();
                nhDomesticInterConversionRate.setId(obj[0] + "");
                if (null == obj[1]) {
                    nhDomesticInterConversionRate.setStepType(null);
                }else{
                    nhDomesticInterConversionRate.setStepType(Integer.valueOf(obj[1]+""));
                }
                
                if (null == obj[2]) {
                    nhDomesticInterConversionRate.setConversionValue(null);
                }else{
                    nhDomesticInterConversionRate.setConversionValue(Float.valueOf(obj[2]+""));
                }
                
                list.add(nhDomesticInterConversionRate);
            }
            
            return list;
        }
        
        return null;
    }

}
