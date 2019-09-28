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

import cn.itcast.b2c.gciantispider.dao.INhFourFlowNumDao;
import cn.itcast.b2c.gciantispider.model.NhFourFlowNum;
import cn.itcast.b2c.gciantispider.service.INhFourFlowNumService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;

/**
 * @author itheima
 *
 */

@Service
public class NhFourFlowNumServiceImpl implements INhFourFlowNumService{
	
	@Autowired
	private INhFourFlowNumDao nhFourFlowNumDao;

	@Override
	public List<NhFourFlowNum> getNhFourFlowNum(String startDate, String endDate) {
		
		
		String hql = "from NhFourFlowNum nffn where 1=1";
		Map<String,Object> params = new HashMap<String,Object>();
		if(!Common.isEmpty(startDate)){
            hql = hql + " AND nffn.recordTime >=:startTime";
            params.put("startTime", DateFormatter.strToDate(startDate));
        }if(!Common.isEmpty(endDate)){
            hql = hql + " AND nffn.recordTime <=:endTime";
            params.put("endTime", DateFormatter.strToDate(endDate));
        }
        hql = hql + " ORDER BY nffn.recordTime asc";
        
        return nhFourFlowNumDao.find(hql, params);
//		String sql = "select nff.id, nff.type, nff.value, nff.create_time from nh_four_flow_num nff where 1=1";
//		Map<String,Object> params = new HashMap<String,Object>();
//		if(!Common.isEmpty(startDate)){
//			sql += " AND DATE_FORMAT(nff.create_time,'%Y-%m-%d') >=:startDate";
//			params.put("startDate", startDate);
//		}
//		if(!Common.isEmpty(endDate)){
//            sql = sql + " AND DATE_FORMAT(nff.create_time,'%Y-%m-%d') <=:endDate";
//            params.put("endDate", endDate);
//        }
//		sql += " order by nff.create_time asc";
//		List<NhFourFlowNum> nhFourFlowNumList = new ArrayList<NhFourFlowNum>();
//		List<Object[]> result = nhFourFlowNumDao.findBySql(sql, params);
//        if (result!=null &&  0!=result.size()) {
//            int size = result.size();
//            //遍历、循环获取值放入对象并添加到list集合中
//            for(int i=0;i<size;i++){
//                Object[] obj = result.get(i);
//                NhFourFlowNum nhFourFlowNum = new NhFourFlowNum();
//                nhFourFlowNum.setId(obj[0]+"");
//                if (null == obj[1]) {
//                    nhFourFlowNum.setType(null);
//                }else{
//                    nhFourFlowNum.setType(Integer.valueOf(obj[1]+""));
//                }
//                if (null == obj[2]) {
//                    nhFourFlowNum.setValue(null);
//                }else{
//                    nhFourFlowNum.setValue(Integer.valueOf(obj[2]+""));
//                }
//                nhFourFlowNum.setRecordTime((Timestamp)obj[3]);
//                nhFourFlowNumList.add(nhFourFlowNum);
//            }
//        }
//		return nhFourFlowNumList;
	}

}
