package com.bigdata.itcast.weblog.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class PageViewBean implements Writable {
	
	private String session;
	private String ip;
	private String time;
	private String request;
	private int step;
	private String length;
	private String http_ref;
	private String user_agent;
	private String body_size;
	private String status;
	
	public void set(String session,String ip,String time,String request,int step,String length
			,String http_ref,String user_agent,String body_size,String status){
		this.setSession(session);
		this.setIp(ip);
		this.setTime(time);
		this.setRequest(request);
		this.setStep(step);
		this.setLength(length);
		this.setHttp_ref(http_ref);
		this.setUser_agent(user_agent);
		this.setBody_size(body_size);
		this.setStatus(status);
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
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

	public String getBody_size() {
		return body_size;
	}

	public void setBody_size(String body_size) {
		this.body_size = body_size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(session);
		out.writeUTF(ip);
		out.writeUTF(time);
		out.writeUTF(request);
		out.writeInt(step);
		out.writeUTF(length);
		out.writeUTF(http_ref);
		out.writeUTF(user_agent);
		out.writeUTF(body_size);
		out.writeUTF(status);
		
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.session = in.readUTF();
		this.ip = in.readUTF();
		this.time = in.readUTF();
		this.request = in.readUTF();
		this.step = in.readInt();
		this.length = in.readUTF();
		this.http_ref = in.readUTF();
		this.user_agent = in.readUTF();
		this.body_size = in.readUTF();
		this.status = in.readUTF();
	}

	@Override
	public String toString() {
		return this.session+"\001"+
				this.ip+"\001"+
				this.time+"\001"+
				this.request+"\001"+
				this.step+"\001"+
				this.length+"\001"+
				this.http_ref+"\001"+
				this.user_agent+"\001"+
				this.body_size+"\001"+
				this.status;
	}
}
