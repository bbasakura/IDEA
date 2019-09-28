package cn.itcast.b2c.gciantispider.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.JedisCluster;
import cn.itcast.b2c.gciantispider.model.Datacollect;
import cn.itcast.b2c.gciantispider.model.DatacollectView;
import cn.itcast.b2c.gciantispider.pageUtil.LinkJsonVO;
import cn.itcast.b2c.gciantispider.service.IDataCollectService;
import cn.itcast.b2c.gciantispider.util.Constants;
import cn.itcast.b2c.gciantispider.util.JedisConnectionUtil;
import cn.itcast.b2c.gciantispider.util.JsonResolveUtil;

import com.alibaba.fastjson.JSON;
/**
 * @author itheima
 * 数据采集控制器
 *
 */
@Controller
@RequestMapping("/dataCollect")
public class DataCollectController {
	
	private final static Logger logger = Logger.getLogger(DataCollectController.class.getName());
	
	@Autowired
	private IDataCollectService dataCollectService;
	/**
	 * 
	 * 获取所有服务器信息
	 * @return
	 */
	@RequestMapping(value="/getDataCollect",method = RequestMethod.GET)
    @ResponseBody
    public List<DatacollectView> getDataCollect(HttpServletRequest request){
		List<DatacollectView> dataCollectViewList = new ArrayList<DatacollectView>();
		try{
			JedisCluster jedisCluster = JedisConnectionUtil.getJedisCluster();
	        //从redis中拿出所有数据
			List<Datacollect> dataCollectList = dataCollectService.getDataCollect();
			for (Datacollect datacollect : dataCollectList) {
				String last = JedisConnectionUtil.keys(jedisCluster, Constants.CSANTI_MONITOR_LP +"*").last();
				String value = jedisCluster.get(last);
				LinkJsonVO resolveLinkJson = JsonResolveUtil.resolveLinkJson(value);
				Map<String,Integer> map2 = resolveLinkJson.getActiveNumMap();
				Set<String> keySet = map2.keySet();
				for (String key : keySet) {
					if (key.equals(datacollect.getServerName())) {
						DatacollectView datacollectView = new DatacollectView();
						datacollectView.setActiveNum(map2.get(key));
						datacollectView.setBeforeYesterdayNum(datacollect.getBeforeYesterdayNum());
						datacollectView.setId(datacollect.getId());
						
						datacollectView.setLastThreeDaysNum(datacollect.getLastThreeDaysNum());
						datacollectView.setServerName(datacollect.getServerName());
						datacollectView.setYesterdayNum(datacollect.getYesterdayNum());
						dataCollectViewList.add(datacollectView);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return dataCollectViewList;
	}
	/**
	 * 
	 * 删除服务器
	 * @return
	 */
	@RequestMapping(value="/deleteServer",method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> deleteServer(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		try{
			dataCollectService.deleteServer(id);
			map.put("result", "删除成功");
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return map;
	}
	/**
	 * 
	 * 修改脚本
	 * @return
	 */
	@RequestMapping(value="/modifyData",method = RequestMethod.GET)
    @ResponseBody
	public Map<String,Object> modifyData(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String newScript = request.getParameter("newScript");
		try{
			dataCollectService.modifyData(id, newScript);
			map.put("result", "修改成功");
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * 获取脚本(用于修改时前端展示)
	 * @return
	 */
	@RequestMapping(value="/getScript",method = RequestMethod.GET)
    @ResponseBody
    public Datacollect getScript(HttpServletRequest request){
		Datacollect datacollect = null;
		String id = request.getParameter("id");
		try{
			datacollect = dataCollectService.get(id);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return datacollect;
	}
}
