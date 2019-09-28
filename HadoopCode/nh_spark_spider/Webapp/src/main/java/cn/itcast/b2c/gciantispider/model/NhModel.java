package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * NhModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_model")
public class NhModel implements java.io.Serializable {

    // Fields

    private String id;

    private NhProcessInfo nhProcessInfo;

    private Integer type;

    private Integer splitWay;

    private Integer treeDepth;

    private Integer minTreeDepth;

    private Integer minInstanceNum;

    private Integer clusterNum;

    private Integer iterNum;

    // Constructors

    /** default constructor */
    public NhModel() {
    }

    /** minimal constructor */
    public NhModel(String id, NhProcessInfo nhProcessInfo) {
        this.id = id;
        this.nhProcessInfo = nhProcessInfo;
    }

    /** full constructor */
    public NhModel(String id, NhProcessInfo nhProcessInfo, Integer type, Integer splitWay, Integer treeDepth, Integer minTreeDepth, Integer minInstanceNum, Integer clusterNum, Integer iterNum) {
        this.id = id;
        this.nhProcessInfo = nhProcessInfo;
        this.type = type;
        this.splitWay = splitWay;
        this.treeDepth = treeDepth;
        this.minTreeDepth = minTreeDepth;
        this.minInstanceNum = minInstanceNum;
        this.clusterNum = clusterNum;
        this.iterNum = iterNum;
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

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonBackReference
    public NhProcessInfo getNhProcessInfo() {
        return this.nhProcessInfo;
    }

    public void setNhProcessInfo(NhProcessInfo nhProcessInfo) {
        this.nhProcessInfo = nhProcessInfo;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "split_way")
    public Integer getSplitWay() {
        return this.splitWay;
    }

    public void setSplitWay(Integer splitWay) {
        this.splitWay = splitWay;
    }

    @Column(name = "tree_depth")
    public Integer getTreeDepth() {
        return this.treeDepth;
    }

    public void setTreeDepth(Integer treeDepth) {
        this.treeDepth = treeDepth;
    }

    @Column(name = "min_tree_depth")
    public Integer getMinTreeDepth() {
        return this.minTreeDepth;
    }

    public void setMinTreeDepth(Integer minTreeDepth) {
        this.minTreeDepth = minTreeDepth;
    }

    @Column(name = "min_instance_num")
    public Integer getMinInstanceNum() {
        return this.minInstanceNum;
    }

    public void setMinInstanceNum(Integer minInstanceNum) {
        this.minInstanceNum = minInstanceNum;
    }

    @Column(name = "cluster_num")
    public Integer getClusterNum() {
        return this.clusterNum;
    }

    public void setClusterNum(Integer clusterNum) {
        this.clusterNum = clusterNum;
    }

    @Column(name = "iter_num")
    public Integer getIterNum() {
        return this.iterNum;
    }

    public void setIterNum(Integer iterNum) {
        this.iterNum = iterNum;
    }

}