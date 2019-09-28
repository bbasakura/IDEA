/**
 * 
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhAgencyCustomerAnalysisDao;
import cn.itcast.b2c.gciantispider.model.NhAgencyCustomerAnalysis;
import cn.itcast.b2c.gciantispider.service.INhAgencyCustomerAnalysisService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;

/**
 * @author itheima
 *
 */

@Service
public class NhAgencyCustomerAnalysisServiceImpl implements INhAgencyCustomerAnalysisService{

	@Autowired
	private INhAgencyCustomerAnalysisDao nhAgencyCustomerAnalysisDao;
	
	
	@Override
	public List<NhAgencyCustomerAnalysis> getNhAgencyCustomerAnalysis(String startDate, String endDate) {
		
		String hql = "from NhAgencyCustomerAnalysis naca where 1=1";
		Map<String,Object> params = new HashMap<String,Object>();
		if(!Common.isEmpty(startDate)){
            hql = hql + " AND naca.recordTime >=:startTime";
            params.put("startTime", DateFormatter.strToDate(startDate));
        }if(!Common.isEmpty(endDate)){
            hql = hql + " AND naca.recordTime <=:endTime";
            params.put("endTime", DateFormatter.strToDate(endDate));
        }
        hql = hql + " ORDER BY naca.recordTime asc";
        
        return nhAgencyCustomerAnalysisDao.find(hql, params);
        
//		String sql = "select nac.id, nac.type, nac.value, nac.create_time FROM nh_agency_customer_analysis nac where 1 = 1";
//		Map<String,Object> params = new HashMap<String,Object>();
//		if(!Common.isEmpty(startDate)){
//            sql = sql + " AND DATE_FORMAT(nac.create_time,'%Y-%m-%d') >=:startDate";
//            params.put("startDate", startDate);
//        }
//        if(!Common.isEmpty(endDate)){
//            sql = sql + " AND DATE_FORMAT(nac.create_time,'%Y-%m-%d') <=:endDate";
//            params.put("endDate", endDate);
//        }
//		sql += " order by nac.create_time asc";
//		List<NhAgencyCustomerAnalysis> nhAgencyCustomerAnalysisList = new ArrayList<NhAgencyCustomerAnalysis>();
//		List<Object[]> result = nhAgencyCustomerAnalysisDao.findBySql(sql, params);
//        if (result!=null &&  0!=result.size()) {
//            int size = result.size();
//            //遍历、循环获取值放入对象并添加到list集合中
//            for(int i=0;i<size;i++){
//                Object[] obj = result.get(i);
//                NhAgencyCustomerAnalysis nhAgencyCustomerAnalysis = new NhAgencyCustomerAnalysis();
//                nhAgencyCustomerAnalysis.setId(obj[0]+"");
//                if(obj[1]==null){
//                    nhAgencyCustomerAnalysis.setCustomerType(null);
//                }else{
//                    nhAgencyCustomerAnalysis.setCustomerType(Integer.valueOf(obj[1]+""));
//                }
//                if(obj[2]==null){
//                    nhAgencyCustomerAnalysis.setValue(null);
//                }else{
//                    nhAgencyCustomerAnalysis.setValue(Float.valueOf(obj[2]+""));
//                }
//                nhAgencyCustomerAnalysis.setRecordTime((Timestamp)obj[3]);
//                nhAgencyCustomerAnalysisList.add(nhAgencyCustomerAnalysis);
//            }
//        }
//        
//        return nhAgencyCustomerAnalysisList;
	}

}
