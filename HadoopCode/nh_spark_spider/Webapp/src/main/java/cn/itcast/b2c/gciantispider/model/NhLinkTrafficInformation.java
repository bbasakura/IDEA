package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * NhLinkTrafficInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="nh_link_traffic_information",catalog="gciantispider")

public class NhLinkTrafficInformation  implements java.io.Serializable {


    // Fields    

     private String id;
     private RealTimeComputData realTimeComputData;
     private String ipAddress;
     private Float ipValue;
     private Timestamp endTime;


    // Constructors

    /** default constructor */
    public NhLinkTrafficInformation() {
    }

	/** minimal constructor */
    public NhLinkTrafficInformation(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public NhLinkTrafficInformation(String id, RealTimeComputData realTimeComputData, String ipAddress, Float ipValue, Timestamp endTime) {
        this.id = id;
        this.realTimeComputData = realTimeComputData;
        this.ipAddress = ipAddress;
        this.ipValue = ipValue;
        this.endTime = endTime;
    }

   
    // Property accessors
    @Id 
    @Column(name="id", unique=true, nullable=false, length=64)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="flow_id")

    public RealTimeComputData getRealTimeComputData() {
        return this.realTimeComputData;
    }
    
    public void setRealTimeComputData(RealTimeComputData realTimeComputData) {
        this.realTimeComputData = realTimeComputData;
    }
    
    @Column(name="ip_address", length=32)
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Column(name="ip_value", precision=12, scale=0)

    public Float getIpValue() {
        return this.ipValue;
    }
    
    public void setIpValue(Float ipValue) {
        this.ipValue = ipValue;
    }
    
    @Column(name="end_time", length=19)

    public Timestamp getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}