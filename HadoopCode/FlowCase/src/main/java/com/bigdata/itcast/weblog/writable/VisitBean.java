package com.bigdata.itcast.weblog.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class VisitBean implements Writable {
	
	private String session;
	private String ip;
	private String inTime;
	private String outTime;
	private String inPage;
	private String outPage;
	private String refere;
	private int pageNum;
	
	public void set(String session,String ip,String inTime,String outTime,String inPage
			,String outPage,String refere,int pageNum){
		this.setSession(session);
		this.setIp(ip);
		this.setInTime(inTime);
		this.setOutTime(outTime);
		this.setInPage(inPage);
		this.setOutPage(outPage);
		this.setRefere(refere);
		this.setPageNum(pageNum);
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

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getInPage() {
		return inPage;
	}

	public void setInPage(String inPage) {
		this.inPage = inPage;
	}

	public String getOutPage() {
		return outPage;
	}

	public void setOutPage(String outPage) {
		this.outPage = outPage;
	}

	public String getRefere() {
		return refere;
	}

	public void setRefere(String refere) {
		this.refere = refere;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(session);
		out.writeUTF(ip);
		out.writeUTF(inTime);
		out.writeUTF(outTime);
		out.writeUTF(inPage);
		out.writeUTF(outPage);
		out.writeUTF(refere);
		out.writeInt(pageNum);
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.session = in.readUTF();
		this.ip = in.readUTF();
		this.inTime = in.readUTF();
		this.outTime = in.readUTF();
		this.inPage = in.readUTF();
		this.outPage = in.readUTF();
		this.refere = in.readUTF();
		this.pageNum = in.readInt();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return session +"\001" +ip+"\001" +inTime+"\001" +outTime+"\001" +
				inPage+"\001" +outPage+"\001" +refere+"\001" +pageNum;
	}

}
