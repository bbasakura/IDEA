package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhProcessNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_process_num", catalog = "gciantispider")
public class NhProcessNum implements java.io.Serializable {

    // Fields

    private Integer processNum;

    private String processId;

    private String processName;

    // Constructors

    /** default constructor */
    public NhProcessNum() {
    }

    /** full constructor */
    public NhProcessNum(String processId, String processName) {
        this.processId = processId;
        this.processName = processName;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "process_num", unique = true, nullable = false)
    public Integer getProcessNum() {
        return this.processNum;
    }

    public void setProcessNum(Integer processNum) {
        this.processNum = processNum;
    }

    @Column(name = "process_id", length = 64)
    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Column(name = "process_name", length = 64)
    public String getProcessName() {
        return this.processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

}