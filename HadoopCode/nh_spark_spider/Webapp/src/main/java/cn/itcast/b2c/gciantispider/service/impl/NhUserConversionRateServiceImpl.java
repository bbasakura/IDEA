package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhUserConversionRateDao;
import cn.itcast.b2c.gciantispider.model.NhUserConversionRate;
import cn.itcast.b2c.gciantispider.service.INhUserConversionRateService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class NhUserConversionRateServiceImpl implements INhUserConversionRateService{
    
    @Autowired
    INhUserConversionRateDao nhUserConversionRateDao;

    @Override
    public List<NhUserConversionRate> queryByDate(String startDate, String userType) {
        String sql = "select ndi.id, ndi.step_type, ndi.conversion_rate FROM nh_user_conversion_rate ndi where 1=1";
        Map<String,Object> map = new HashMap<String,Object>();
        if(!Common.isEmpty(startDate) && !Common.isEmpty(userType)){
            sql = sql + " AND DATE_FORMAT(ndi.record_time,'%Y-%m-%d') =:startDate AND ndi.user_type=:userType";
            map.put("startDate", startDate);
            map.put("userType", userType);
        }
        List<Object[]> nhUserConversionRates = nhUserConversionRateDao.findBySql(sql,map);
        List<NhUserConversionRate> list = new ArrayList<NhUserConversionRate>();
        if (nhUserConversionRates!=null && nhUserConversionRates.size()!=0) {
            for (Object[] obj : nhUserConversionRates) {
                NhUserConversionRate nhUserConversionRate = new NhUserConversionRate();
                nhUserConversionRate.setId(obj[0] + "");
                if (null == obj[1]) {
                    nhUserConversionRate.setStepType(null);
                }else{
                    nhUserConversionRate.setStepType(Integer.valueOf(obj[1]+""));
                }
                
                if (null == obj[2]) {
                    nhUserConversionRate.setConversionValue(null);
                }else{
                    nhUserConversionRate.setConversionValue(Float.valueOf(obj[2]+""));
                }
                
                list.add(nhUserConversionRate);
            }
            
            return list;
        }
        
        return null;
    }
}
