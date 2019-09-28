package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RealTimeComputData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "real_time_comput_data")
public class RealTimeComputData implements java.io.Serializable {

    // Fields

    private String id;

    private String costTime;

    private String applicationId;

    private String countPerMillis;

    private String applicationUniqueName;

    private Timestamp endTime;

    private Float sourceCount;

    private Set<NhLinkTrafficInformation> nhLinkTrafficInformations = new HashSet<NhLinkTrafficInformation>(0);

    // Constructors

    /** default constructor */
    public RealTimeComputData() {
    }

    /** minimal constructor */
    public RealTimeComputData(String id) {
        this.id = id;
    }

    /** full constructor */
    public RealTimeComputData(String id, String costTime, String applicationId, String countPerMillis, String applicationUniqueName, Timestamp endTime, Float sourceCount,
            Set<NhLinkTrafficInformation> nhLinkTrafficInformations) {
        this.id = id;
        this.costTime = costTime;
        this.applicationId = applicationId;
        this.countPerMillis = countPerMillis;
        this.applicationUniqueName = applicationUniqueName;
        this.endTime = endTime;
        this.sourceCount = sourceCount;
        this.nhLinkTrafficInformations = nhLinkTrafficInformations;
    }

    // Property accessors
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 64)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "cost_time", length = 64)
    public String getCostTime() {
        return this.costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    @Column(name = "application_id", length = 64)
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Column(name = "count_per_millis", precision = 12, scale = 0)
    public String getCountPerMillis() {
        return this.countPerMillis;
    }

    public void setCountPerMillis(String countPerMillis) {
        this.countPerMillis = countPerMillis;
    }

    @Column(name = "application_unique_name", length = 64)
    public String getApplicationUniqueName() {
        return this.applicationUniqueName;
    }

    public void setApplicationUniqueName(String applicationUniqueName) {
        this.applicationUniqueName = applicationUniqueName;
    }

    @Column(name = "end_time", length = 19)
    public Timestamp getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Column(name = "source_count", precision = 12, scale = 0)
    public Float getSourceCount() {
        return this.sourceCount;
    }

    public void setSourceCount(Float sourceCount) {
        this.sourceCount = sourceCount;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "realTimeComputData")
    public Set<NhLinkTrafficInformation> getNhLinkTrafficInformations() {
        return this.nhLinkTrafficInformations;
    }

    public void setNhLinkTrafficInformations(Set<NhLinkTrafficInformation> nhLinkTrafficInformations) {
        this.nhLinkTrafficInformations = nhLinkTrafficInformations;
    }

}