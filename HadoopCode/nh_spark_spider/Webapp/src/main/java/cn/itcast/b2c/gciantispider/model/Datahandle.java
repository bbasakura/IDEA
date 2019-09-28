package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Datahandle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "datahandle", catalog = "gciantispider")
public class Datahandle implements java.io.Serializable {

    // Fields

    private String id;

    private String sourceData;

    private String requestMethod;

    private String request;

    private String remoteAddr;

    private String httpUserAgent;

    private String timeIso8601;

    private String serverAddr;

    private String criticalCookie;

    private String highFrqIpgroup;

    private String flightType;

    private String behaviorType;

    private String travelType;

    private String flightDate;

    private String depcity;

    private String arrcity;

    private String cookieValueJsessionId;

    private String cookieValueUserId;

    private String queryRequestDataStr;

    private String bookRequestDataStr;

    private String httpReferrer;

    // Constructors

    /** default constructor */
    public Datahandle() {
    }

    /** minimal constructor */
    public Datahandle(String id) {
        this.id = id;
    }

    /** full constructor */
    public Datahandle(String id, String sourceData, String requestMethod, String request, String remoteAddr, String httpUserAgent, String timeIso8601, String serverAddr, String criticalCookie,
            String highFrqIpgroup, String flightType, String behaviorType, String travelType, String flightDate, String depcity, String arrcity, String cookieValueJsessionId,
            String cookieValueUserId, String queryRequestDataStr, String bookRequestDataStr, String httpReferrer) {
        this.id = id;
        this.sourceData = sourceData;
        this.requestMethod = requestMethod;
        this.request = request;
        this.remoteAddr = remoteAddr;
        this.httpUserAgent = httpUserAgent;
        this.timeIso8601 = timeIso8601;
        this.serverAddr = serverAddr;
        this.criticalCookie = criticalCookie;
        this.highFrqIpgroup = highFrqIpgroup;
        this.flightType = flightType;
        this.behaviorType = behaviorType;
        this.travelType = travelType;
        this.flightDate = flightDate;
        this.depcity = depcity;
        this.arrcity = arrcity;
        this.cookieValueJsessionId = cookieValueJsessionId;
        this.cookieValueUserId = cookieValueUserId;
        this.queryRequestDataStr = queryRequestDataStr;
        this.bookRequestDataStr = bookRequestDataStr;
        this.httpReferrer = httpReferrer;
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

    @Column(name = "source_data", length = 65535)
    public String getSourceData() {
        return this.sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    @Column(name = "request_method", length = 16)
    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Column(name = "request", length = 16)
    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "remote_addr", length = 16)
    public String getRemoteAddr() {
        return this.remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Column(name = "httpUser_agent", length = 16)
    public String getHttpUserAgent() {
        return this.httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    @Column(name = "time_Iso8601", length = 64)
    public String getTimeIso8601() {
        return this.timeIso8601;
    }

    public void setTimeIso8601(String timeIso8601) {
        this.timeIso8601 = timeIso8601;
    }

    @Column(name = "server_addr", length = 16)
    public String getServerAddr() {
        return this.serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    @Column(name = "critical_cookie", length = 16)
    public String getCriticalCookie() {
        return this.criticalCookie;
    }

    public void setCriticalCookie(String criticalCookie) {
        this.criticalCookie = criticalCookie;
    }

    @Column(name = "highFrqIPGroup", length = 16)
    public String getHighFrqIpgroup() {
        return this.highFrqIpgroup;
    }

    public void setHighFrqIpgroup(String highFrqIpgroup) {
        this.highFrqIpgroup = highFrqIpgroup;
    }

    @Column(name = "flight_type", length = 16)
    public String getFlightType() {
        return this.flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    @Column(name = "behavior_type", length = 16)
    public String getBehaviorType() {
        return this.behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    @Column(name = "travel_type", length = 16)
    public String getTravelType() {
        return this.travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    @Column(name = "flight_date", length = 64)
    public String getFlightDate() {
        return this.flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    @Column(name = "depcity", length = 64)
    public String getDepcity() {
        return this.depcity;
    }

    public void setDepcity(String depcity) {
        this.depcity = depcity;
    }

    @Column(name = "arrcity", length = 64)
    public String getArrcity() {
        return this.arrcity;
    }

    public void setArrcity(String arrcity) {
        this.arrcity = arrcity;
    }

    @Column(name = "cookieValue_jsessionID", length = 1024)
    public String getCookieValueJsessionId() {
        return this.cookieValueJsessionId;
    }

    public void setCookieValueJsessionId(String cookieValueJsessionId) {
        this.cookieValueJsessionId = cookieValueJsessionId;
    }

    @Column(name = "cookieValue_userID", length = 64)
    public String getCookieValueUserId() {
        return this.cookieValueUserId;
    }

    public void setCookieValueUserId(String cookieValueUserId) {
        this.cookieValueUserId = cookieValueUserId;
    }

    @Column(name = "queryRequestDataStr", length = 1024)
    public String getQueryRequestDataStr() {
        return this.queryRequestDataStr;
    }

    public void setQueryRequestDataStr(String queryRequestDataStr) {
        this.queryRequestDataStr = queryRequestDataStr;
    }

    @Column(name = "bookRequestDataStr", length = 1024)
    public String getBookRequestDataStr() {
        return this.bookRequestDataStr;
    }

    public void setBookRequestDataStr(String bookRequestDataStr) {
        this.bookRequestDataStr = bookRequestDataStr;
    }

    @Column(name = "http_referrer", length = 16)
    public String getHttpReferrer() {
        return this.httpReferrer;
    }

    public void setHttpReferrer(String httpReferrer) {
        this.httpReferrer = httpReferrer;
    }

}