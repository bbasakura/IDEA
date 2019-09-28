/**
 * 
 */
package cn.itcast.b2c.gciantispider.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.INhIllegalOccFlightRankDao;
import cn.itcast.b2c.gciantispider.model.NhIllegalOccFlightRank;
import cn.itcast.b2c.gciantispider.service.INhIllegalOccFlightRankService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;

/**
 * @author itheima
 *
 */
@Service
public class NhIllegalOccFlightRankServiceImpl implements
		INhIllegalOccFlightRankService {
	
	@Autowired
	private INhIllegalOccFlightRankDao nhIllegalOccFlightRankDao;

	@Override
	public List<NhIllegalOccFlightRank> getNhIllegalOccFlightRank(int pages,
			int rows, String startTime, String endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT i.flight_code,i.depairport,i.arrairport,i.flight_start_time,i.ship_Space,i.price,i.frequency from nh_illegal_occ_flight_rank i WHERE 1=1";
        if (!Common.isEmpty(startTime)) {
            sql = sql + " AND DATE_FORMAT(i.record_time,'%Y-%m-%d') >=:startTime";
            params.put("startTime", startTime);
        }
        if (!Common.isEmpty(endTime)) {
            sql = sql + " AND DATE_FORMAT(i.record_time,'%Y-%m-%d') <=:endTime";
            params.put("endTime", endTime);
        }
        sql = sql + " ORDER BY i.frequency desc";
        List<Object[]> seatList = new ArrayList<Object[]>();
        List<NhIllegalOccFlightRank> nhIllegalOccFlightRankList = new ArrayList<NhIllegalOccFlightRank>();
        seatList = nhIllegalOccFlightRankDao.findBySql(sql, params, pages, rows);
        for (int i = 0; i < seatList.size(); i++) {
        	NhIllegalOccFlightRank nhIllegalOccFlightRank = new NhIllegalOccFlightRank();
            Object[] o = seatList.get(i);
            nhIllegalOccFlightRank.setFlightCode((String) o[0]);
            nhIllegalOccFlightRank.setDepairport((String) o[1]);
            nhIllegalOccFlightRank.setArrairport((String) o[2]);
            nhIllegalOccFlightRank.setFlightStartTime((Timestamp) o[3]);
            nhIllegalOccFlightRank.setShipSpace((String) o[4]);
            nhIllegalOccFlightRank.setPrice((Float) o[5]);
            nhIllegalOccFlightRank.setFrequency((Integer) o[6]);
            nhIllegalOccFlightRankList.add(nhIllegalOccFlightRank);
        }
        
		return nhIllegalOccFlightRankList;
	}

	@Override
	public BigInteger getAllCount(String startTime, String endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT count(*) FROM nh_illegal_occ_flight_rank i WHERE 1=1";
        if (!Common.isEmpty(startTime)) {
            params.put("startTime", startTime);
            sql = sql + " AND i.record_time >=:startTime";
        }
        if (!Common.isEmpty(endTime)) {
            sql = sql + " AND i.record_time <=:endTime";
            params.put("endTime", endTime);
        }
        return nhIllegalOccFlightRankDao.countBySql1(sql, params);
	}
	
    

}
