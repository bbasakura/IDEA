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

import cn.itcast.b2c.gciantispider.dao.INhFlowInfoDao;
import cn.itcast.b2c.gciantispider.model.NhFlowInfo;
import cn.itcast.b2c.gciantispider.model.NhFlowQueryRate;
import cn.itcast.b2c.gciantispider.service.INhFlowInfoService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;


/**
 * @author itheima
 *
 */

@Service
public class NhFlowInfoServiceImpl implements INhFlowInfoService{
	
	@Autowired
	private INhFlowInfoDao nhFlowInfoDao;

	@Override
	public List<NhFlowInfo> getNhFlowInfo(String startDate,String endDate) {
		String hql = "from NhFlowInfo nfi where 1=1";
		// String sql = "select nfi.id, nfi.type, nfi.value, nfi.create_time from nh_flow_info nfi where 1=1";
		Map<String,Object> params = new HashMap<String,Object>();
		if(!Common.isEmpty(startDate)){
            hql = hql + " AND nfi.recordTime >=:startTime";
            params.put("startTime", DateFormatter.strToDate(startDate));
        }if(!Common.isEmpty(endDate)){
            hql = hql + " AND nfi.recordTime <=:endTime";
            params.put("endTime", DateFormatter.strToDate(endDate));
        }
        hql = hql + " ORDER BY nfi.recordTime asc";
		
        return nhFlowInfoDao.find(hql, params);
		/*if(!Common.isEmpty(startDate)){
			sql += " AND DATE_FORMAT(nfi.create_time,'%Y-%m-%d') >=:startDate";
			params.put("startDate", startDate);
		}
		if(!Common.isEmpty(endDate)){
            sql = sql + " AND DATE_FORMAT(nfi.create_time,'%Y-%m-%d') <=:endDate";
            params.put("endDate", endDate);
        }
		sql += " order by nfi.create_time asc";
		List<NhFlowInfo> nhFlowInfoList = new ArrayList<NhFlowInfo>();
		List<Object[]> result = nhFlowInfoDao.findBySql(sql, params);
        if (result!=null &&  0!=result.size()) {
            int size = result.size();
            //遍历、循环获取值放入对象并添加到list集合中
            for(int i=0;i<size;i++){
                Object[] obj = result.get(i);
                NhFlowInfo nhFlowInfo = new NhFlowInfo();
                nhFlowInfo.setId(obj[0]+"");
                if(obj[1]==null){
                	nhFlowInfo.setFlowType(null);
                }else{
                	nhFlowInfo.setFlowType(Integer.valueOf(obj[1]+""));
                }
                if(obj[2]==null){
                	nhFlowInfo.setFlowValue(null);
                }else{
                	nhFlowInfo.setFlowValue(Float.valueOf(obj[2]+""));
                }
                nhFlowInfo.setRecordTime((Timestamp)obj[3]);
                nhFlowInfoList.add(nhFlowInfo);
            }
        }*/
	}

}
