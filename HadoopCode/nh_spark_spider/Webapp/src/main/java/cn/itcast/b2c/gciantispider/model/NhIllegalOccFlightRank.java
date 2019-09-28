package cn.itcast.b2c.gciantispider.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NhIllegalOccFlightRank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nh_illegal_occ_flight_rank", catalog = "gciantispider")
public class NhIllegalOccFlightRank implements java.io.Serializable {

    // Fields

    private String id;

    private String flightCode;

    private String depairport;

    private String arrairport;

    private Timestamp flightStartTime;

    private Float price;

    private String shipSpace;

    private Integer frequency;

    private Timestamp recordTime;

    // Constructors

    /** default constructor */
    public NhIllegalOccFlightRank() {
    }

    /** minimal constructor */
    public NhIllegalOccFlightRank(String id) {
        this.id = id;
    }

    /** full constructor */
    public NhIllegalOccFlightRank(String id, String flightCode, String depairport, String arrairport, Timestamp flightStartTime, Float price, String shipSpace, Integer frequency, Timestamp recordTime) {
        this.id = id;
        this.flightCode = flightCode;
        this.depairport = depairport;
        this.arrairport = arrairport;
        this.flightStartTime = flightStartTime;
        this.price = price;
        this.shipSpace = shipSpace;
        this.frequency = frequency;
        this.recordTime = recordTime;
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

    @Column(name = "flight_code", length = 32)
    public String getFlightCode() {
        return this.flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    @Column(name = "depairport", length = 64)
    public String getDepairport() {
        return this.depairport;
    }

    public void setDepairport(String depairport) {
        this.depairport = depairport;
    }

    @Column(name = "arrairport", length = 64)
    public String getArrairport() {
        return this.arrairport;
    }

    public void setArrairport(String arrairport) {
        this.arrairport = arrairport;
    }

    @Column(name = "flight_start_time", length = 19)
    public Timestamp getFlightStartTime() {
        return this.flightStartTime;
    }

    public void setFlightStartTime(Timestamp flightStartTime) {
        this.flightStartTime = flightStartTime;
    }

    @Column(name = "price", precision = 12, scale = 0)
    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Column(name = "ship_space", length = 64)
    public String getShipSpace() {
        return this.shipSpace;
    }

    public void setShipSpace(String shipSpace) {
        this.shipSpace = shipSpace;
    }

    @Column(name = "frequency")
    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Column(name = "record_time", length = 19)
    public Timestamp getRecordTime() {
        return this.recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

}