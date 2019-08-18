package com.bigdata.itcast.weblog.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


/**
 * 自定义etl以后的数据
 * 	194.237.142.21 									ip			0
- 													-			*
- 													user_id		2
[18/Sep/2013:06:49:18 								s_time		3
		[18/Sep/2013:06:49:18			->   yyyy-MM-dd HH:mm:ss
+0000] 												时区		*
"GET 												类型		*
/wp-content/uploads/2013/07/rstudio-git3.png 		url			6
HTTP/1.1" 											协议		*
304 												状态		8
0 													body_size	9
"-" 												http_ref	10
"Mozilla/4.0 (compatible;)"							user_agent	11~
 * @author 江城子
 *
 */
public class WebLogBean implements Writable {
	
	//define
	private boolean isvalid=true;
	private String  ip;
	private String user_id;
	private String s_time;
	private String url;
	private String status;
	private String body_size;
	private String http_ref;
	private String user_agent;
	
	//构造函数
	public WebLogBean(){
		
	}
	
	public void setAll(boolean isvalid,String  ip,String user_id,String s_time,String url,
			String status,String body_size,String http_ref,String user_agent){
		this.setIsvalid(isvalid);
		this.setIp(ip);
		this.setUser_id(user_id);
		this.setS_time(s_time);
		this.setUrl(url);
		this.setStatus(status);
		this.setBody_size(body_size);
		this.setHttp_ref(http_ref);
		this.setUser_agent(user_agent);
	}
	
	public boolean isIsvalid(boolean isvalid) {
		return isvalid;
	}



	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getS_time() {
		return s_time;
	}



	public void setS_time(String s_time) {
		this.s_time = s_time;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getBody_size() {
		return body_size;
	}



	public void setBody_size(String body_size) {
		this.body_size = body_size;
	}



	public String getHttp_ref() {
		return http_ref;
	}



	public void setHttp_ref(String http_ref) {
		this.http_ref = http_ref;
	}



	public String getUser_agent() {
		return user_agent;
	}



	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body_size == null) ? 0 : body_size.hashCode());
		result = prime * result + ((http_ref == null) ? 0 : http_ref.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + (isvalid ? 1231 : 1237);
		result = prime * result + ((s_time == null) ? 0 : s_time.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user_agent == null) ? 0 : user_agent.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebLogBean other = (WebLogBean) obj;
		if (body_size == null) {
			if (other.body_size != null)
				return false;
		} else if (!body_size.equals(other.body_size))
			return false;
		if (http_ref == null) {
			if (other.http_ref != null)
				return false;
		} else if (!http_ref.equals(other.http_ref))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (isvalid != other.isvalid)
			return false;
		if (s_time == null) {
			if (other.s_time != null)
				return false;
		} else if (!s_time.equals(other.s_time))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user_agent == null) {
			if (other.user_agent != null)
				return false;
		} else if (!user_agent.equals(other.user_agent))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(
				this.isvalid
				+"\001"+this.ip
				+"\001"+this.user_id
				+"\001"+this.s_time
				+"\001"+this.url
				+"\001"+this.status
				+"\001"+this.body_size
				+"\001"+this.http_ref
				+"\001"+this.user_agent
				);
		return sbuilder.toString();
	}

	//反序列化
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.isvalid = in.readBoolean();
		this.ip = in.readUTF();
		this.user_id = in.readUTF();
		this.s_time = in.readUTF();
		this.url = in.readUTF();
		this.status = in.readUTF();
		this.body_size = in.readUTF();
		this.http_ref = in.readUTF();
		this.user_agent = in.readUTF();
	}

	//序列化
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeBoolean(this.isvalid);
		out.writeUTF(this.ip);
		out.writeUTF(this.user_id);
		out.writeUTF(this.s_time);
		out.writeUTF(this.url);
		out.writeUTF(this.status);
		out.writeUTF(this.body_size);
		out.writeUTF(this.http_ref);
		out.writeUTF(this.user_agent);
	}
}
