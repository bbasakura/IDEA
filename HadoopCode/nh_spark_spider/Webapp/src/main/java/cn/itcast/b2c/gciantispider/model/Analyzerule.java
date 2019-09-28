package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Analyzerule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "analyzerule")
public class Analyzerule implements java.io.Serializable {

    // Fields

    private String id;

    private String flightType;

    private String behaviorType;

    private String requestMatchExpression;

    private String requestMethod;

    private String isNormalGet;

    private String isNormalForm;

    private String isApplicationJson;

    private String isTextXml;

    private String isJson;

    private String isXml;

    private String formDataField;

    private String bookBookUserId;

    private String bookBookUnUserId;

    private String bookPsgName;

    private String bookPsgType;

    private String bookIdType;

    private String bookIdCard;

    private String bookContractName;

    private String bookContractPhone;

    private String bookDepCity;

    private String bookArrCity;

    private String bookFlightDate;
    
    private String bookFlightNo;

    private String bookCabin;

    private String queryDepCity;

    private String queryArrCity;

    private String queryFlightDate;

    private String queryAdultNum;

    private String queryChildNum;

    private String queryInfantNum;

    private String queryCountry;

    private String queryTravelType;

    private String bookPsgFirName;

    // Constructors

    /** default constructor */
    public Analyzerule() {
    }

    /** minimal constructor */
    public Analyzerule(String id) {
        this.id = id;
    }

    /** full constructor */
	public Analyzerule(String id, String flightType, String behaviorType,
			String requestMatchExpression, String requestMethod,
			String isNormalGet, String isNormalForm, String isApplicationJson,
			String isTextXml, String isJson, String isXml,
			String formDataField, String bookBookUserId,
			String bookBookUnUserId, String bookPsgName, String bookPsgType,
			String bookIdType, String bookIdCard, String bookContractName,
			String bookContractPhone, String bookDepCity, String bookArrCity,
			String bookFlightDate, String bookFlightNo, String bookCabin,
			String queryDepCity, String queryArrCity, String queryFlightDate,
			String queryAdultNum, String queryChildNum, String queryInfantNum,
			String queryCountry, String queryTravelType, String bookPsgFirName) {
		super();
		this.id = id;
		this.flightType = flightType;
		this.behaviorType = behaviorType;
		this.requestMatchExpression = requestMatchExpression;
		this.requestMethod = requestMethod;
		this.isNormalGet = isNormalGet;
		this.isNormalForm = isNormalForm;
		this.isApplicationJson = isApplicationJson;
		this.isTextXml = isTextXml;
		this.isJson = isJson;
		this.isXml = isXml;
		this.formDataField = formDataField;
		this.bookBookUserId = bookBookUserId;
		this.bookBookUnUserId = bookBookUnUserId;
		this.bookPsgName = bookPsgName;
		this.bookPsgType = bookPsgType;
		this.bookIdType = bookIdType;
		this.bookIdCard = bookIdCard;
		this.bookContractName = bookContractName;
		this.bookContractPhone = bookContractPhone;
		this.bookDepCity = bookDepCity;
		this.bookArrCity = bookArrCity;
		this.bookFlightDate = bookFlightDate;
		this.bookFlightNo = bookFlightNo;
		this.bookCabin = bookCabin;
		this.queryDepCity = queryDepCity;
		this.queryArrCity = queryArrCity;
		this.queryFlightDate = queryFlightDate;
		this.queryAdultNum = queryAdultNum;
		this.queryChildNum = queryChildNum;
		this.queryInfantNum = queryInfantNum;
		this.queryCountry = queryCountry;
		this.queryTravelType = queryTravelType;
		this.bookPsgFirName = bookPsgFirName;
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

    @Column(name = "flight_type", length = 64)
    public String getFlightType() {
        return this.flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    @Column(name = "behavior_type", length = 64)
    public String getBehaviorType() {
        return this.behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    @Column(name = "requestMatchExpression", length = 64)
    public String getRequestMatchExpression() {
        return this.requestMatchExpression;
    }

    public void setRequestMatchExpression(String requestMatchExpression) {
        this.requestMatchExpression = requestMatchExpression;
    }

    @Column(name = "requestMethod", length = 64)
    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Column(name = "isNormalGet", length = 64)
    public String getIsNormalGet() {
        return this.isNormalGet;
    }

    public void setIsNormalGet(String isNormalGet) {
        this.isNormalGet = isNormalGet;
    }

    @Column(name = "isNormalForm", length = 64)
    public String getIsNormalForm() {
        return this.isNormalForm;
    }

    public void setIsNormalForm(String isNormalForm) {
        this.isNormalForm = isNormalForm;
    }

    @Column(name = "isApplicationJson", length = 1024)
    public String getIsApplicationJson() {
        return this.isApplicationJson;
    }

    public void setIsApplicationJson(String isApplicationJson) {
        this.isApplicationJson = isApplicationJson;
    }

    @Column(name = "isTextXml", length = 1024)
    public String getIsTextXml() {
        return this.isTextXml;
    }

    public void setIsTextXml(String isTextXml) {
        this.isTextXml = isTextXml;
    }

    @Column(name = "isJson", length = 64)
    public String getIsJson() {
        return this.isJson;
    }

    public void setIsJson(String isJson) {
        this.isJson = isJson;
    }

    @Column(name = "isXML", length = 64)
    public String getIsXml() {
        return this.isXml;
    }

    public void setIsXml(String isXml) {
        this.isXml = isXml;
    }

    @Column(name = "formDataField", length = 64)
    public String getFormDataField() {
        return this.formDataField;
    }

    public void setFormDataField(String formDataField) {
        this.formDataField = formDataField;
    }

    @Column(name = "book_bookUserId", length = 64)
    public String getBookBookUserId() {
        return this.bookBookUserId;
    }

    public void setBookBookUserId(String bookBookUserId) {
        this.bookBookUserId = bookBookUserId;
    }

    @Column(name = "book_bookUnUserId", length = 64)
    public String getBookBookUnUserId() {
        return this.bookBookUnUserId;
    }

    public void setBookBookUnUserId(String bookBookUnUserId) {
        this.bookBookUnUserId = bookBookUnUserId;
    }

    @Column(name = "book_psgName", length = 64)
    public String getBookPsgName() {
        return this.bookPsgName;
    }

    public void setBookPsgName(String bookPsgName) {
        this.bookPsgName = bookPsgName;
    }

    @Column(name = "book_psgType", length = 64)
    public String getBookPsgType() {
        return this.bookPsgType;
    }

    public void setBookPsgType(String bookPsgType) {
        this.bookPsgType = bookPsgType;
    }

    @Column(name = "book_idType", length = 64)
    public String getBookIdType() {
        return this.bookIdType;
    }

    public void setBookIdType(String bookIdType) {
        this.bookIdType = bookIdType;
    }

    @Column(name = "book_idCard", length = 64)
    public String getBookIdCard() {
        return this.bookIdCard;
    }

    public void setBookIdCard(String bookIdCard) {
        this.bookIdCard = bookIdCard;
    }

    @Column(name = "book_contractName", length = 64)
    public String getBookContractName() {
        return this.bookContractName;
    }

    public void setBookContractName(String bookContractName) {
        this.bookContractName = bookContractName;
    }

    @Column(name = "book_contractPhone", length = 64)
    public String getBookContractPhone() {
        return this.bookContractPhone;
    }

    public void setBookContractPhone(String bookContractPhone) {
        this.bookContractPhone = bookContractPhone;
    }

    @Column(name = "book_depCity", length = 64)
    public String getBookDepCity() {
        return this.bookDepCity;
    }

    public void setBookDepCity(String bookDepCity) {
        this.bookDepCity = bookDepCity;
    }

    @Column(name = "book_arrCity", length = 64)
    public String getBookArrCity() {
        return this.bookArrCity;
    }

    public void setBookArrCity(String bookArrCity) {
        this.bookArrCity = bookArrCity;
    }

    @Column(name = "book_flightDate", length = 64)
    public String getBookFlightDate() {
        return this.bookFlightDate;
    }

    public void setBookFlightDate(String bookFlightDate) {
        this.bookFlightDate = bookFlightDate;
    }

    @Column(name = "book_flightNo", length = 64)
	public String getBookFlightNo() {
		return bookFlightNo;
	}

	
	public void setBookFlightNo(String bookFlightNo) {
		this.bookFlightNo = bookFlightNo;
	}

	@Column(name = "book_cabin", length = 64)
    public String getBookCabin() {
        return this.bookCabin;
    }

    public void setBookCabin(String bookCabin) {
        this.bookCabin = bookCabin;
    }

    @Column(name = "query_depCity", length = 64)
    public String getQueryDepCity() {
        return this.queryDepCity;
    }

    public void setQueryDepCity(String queryDepCity) {
        this.queryDepCity = queryDepCity;
    }

    @Column(name = "query_arrCity", length = 64)
    public String getQueryArrCity() {
        return this.queryArrCity;
    }

    public void setQueryArrCity(String queryArrCity) {
        this.queryArrCity = queryArrCity;
    }

    @Column(name = "query_flightDate", length = 64)
    public String getQueryFlightDate() {
        return this.queryFlightDate;
    }

    public void setQueryFlightDate(String queryFlightDate) {
        this.queryFlightDate = queryFlightDate;
    }

    @Column(name = "query_adultNum", length = 64)
    public String getQueryAdultNum() {
        return this.queryAdultNum;
    }

    public void setQueryAdultNum(String queryAdultNum) {
        this.queryAdultNum = queryAdultNum;
    }

    @Column(name = "query_childNum", length = 64)
    public String getQueryChildNum() {
        return this.queryChildNum;
    }

    public void setQueryChildNum(String queryChildNum) {
        this.queryChildNum = queryChildNum;
    }

    @Column(name = "query_infantNum", length = 64)
    public String getQueryInfantNum() {
        return this.queryInfantNum;
    }

    public void setQueryInfantNum(String queryInfantNum) {
        this.queryInfantNum = queryInfantNum;
    }

    @Column(name = "query_country", length = 64)
    public String getQueryCountry() {
        return this.queryCountry;
    }

    public void setQueryCountry(String queryCountry) {
        this.queryCountry = queryCountry;
    }

    @Column(name = "query_travelType", length = 64)
    public String getQueryTravelType() {
        return this.queryTravelType;
    }

    public void setQueryTravelType(String queryTravelType) {
        this.queryTravelType = queryTravelType;
    }

    @Column(name = "book_psgFirName", length = 64)
    public String getBookPsgFirName() {
        return this.bookPsgFirName;
    }

    public void setBookPsgFirName(String bookPsgFirName) {
        this.bookPsgFirName = bookPsgFirName;
    }

}