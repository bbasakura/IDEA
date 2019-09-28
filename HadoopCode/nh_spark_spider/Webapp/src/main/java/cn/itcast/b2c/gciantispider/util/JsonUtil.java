package cn.itcast.b2c.gciantispider.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.itcast.b2c.gciantispider.model.ServerStatusInfo;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
          
    public static String fromObject(Object obj){
        return JSON.toJSONString(obj, new SimplePropertyFilter());
    }
    
    
	public static List<ServerStatusInfo> analysisJsonGetServerStatusInfoList(String jsonStr){
    	List<ServerStatusInfo> serverStatusInfoList = new ArrayList<ServerStatusInfo>();
    	try {
			JSONArray jsonArray = new JSONArray(jsonStr);
			
			//服务器名称
			String hostname = null;
			//cpu1
		    Float system_p = null;
		    //cpu2
		    Float user_p = null;
		    //内存已使用数据
		    Float actual_used = null;
		    //内存总数
		    Float mem_total = null;
		    //内存使用率
		    Float actual_used_p = null;
		    //硬盘已使用数据
		    Float used = null;
		    //总硬盘大小
		    Float fs_total = null;
		    //时间戳
		    Timestamp timestamp = null;
		    
			for (int i = 0; i < jsonArray.length(); i++) {
				
	            JSONObject roleObject = (JSONObject) jsonArray.get(i);
	            if (!roleObject.isNull("beat.hostname")) { 
	            	hostname = roleObject.getString("beat.hostname");
	            	
	            }
	            if (!roleObject.isNull("cpu.system_p")) { 
	            	system_p = Float.valueOf(roleObject.getString("cpu.system_p"));
	            }
	            if (!roleObject.isNull("cpu.user_p")) { 
	            	user_p = Float.valueOf(roleObject.getString("cpu.user_p"));
	            }
	            if (!roleObject.isNull("mem.actual_used")) { 
	            	actual_used = Float.valueOf(roleObject.getString("mem.actual_used"));
	            }
	            if (!roleObject.isNull("mem.total")) { 
	            	mem_total = Float.valueOf(roleObject.getString("mem.total"));
	            }
	            if (!roleObject.isNull("mem.actual_used_p")) { 
	            	actual_used_p = Float.valueOf(roleObject.getString("mem.actual_used_p"));
	            }
	            if (!roleObject.isNull("fs.used")) { 
	            	used = Float.valueOf(roleObject.getString("fs.used"));
	            }
	            if (!roleObject.isNull("fs.total")) { 
	            	fs_total = Float.valueOf(roleObject.getString("fs.total"));
	            }
	            if (!roleObject.isNull("@timestamp")) { 
	            	String DateTime = roleObject.getString("@timestamp");
	            	DateTime = DateTime.substring(0, DateTime.length()-5);
	            	DateTime = DateTime.split("T")[0]+" "+DateTime.split("T")[1];
	            	timestamp = Timestamp.valueOf(DateTime);
	            }
	            
	            ServerStatusInfo serverStatusInfo = new ServerStatusInfo();
	            java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");
	            serverStatusInfo.setName(hostname);
	            serverStatusInfo.setMemoryRate((actual_used_p*100));
	            serverStatusInfo.setMemoryInfo(((int)(actual_used/1000/1000))+"M/"+((int)(mem_total/1000/1000))+"M");
	            serverStatusInfo.setDiskInfo(((int)(used/1000/10000))+"G/"+((int)(fs_total/1000/10000))+"G");
	            serverStatusInfo.setCpuRate(Float.valueOf(df.format((system_p+user_p)*100)));
	            serverStatusInfo.setDiskRate(Float.valueOf(df.format((used/fs_total)*100)));
	            serverStatusInfo.setStateTime(timestamp);
	            serverStatusInfo.setId(UUID.randomUUID().toString());
	            serverStatusInfoList.add(serverStatusInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return serverStatusInfoList;
    }
    
}
