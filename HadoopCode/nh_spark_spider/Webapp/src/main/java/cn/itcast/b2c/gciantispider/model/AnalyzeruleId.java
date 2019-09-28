package cn.itcast.b2c.gciantispider.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnalyzeruleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AnalyzeruleId implements java.io.Serializable {

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
    public AnalyzeruleId() {
    }

    /** full constructor */
    public AnalyzeruleId(String id, String flightType, String behaviorType, String requestMatchExpression, String requestMethod, String isNormalGet, String isNormalForm, String isApplicationJson,
            String isTextXml, String isJson, String isXml, String formDataField, String bookBookUserId, String bookBookUnUserId, String bookPsgName, String bookPsgType, String bookIdType,
            String bookIdCard, String bookContractName, String bookContractPhone, String bookDepCity, String bookArrCity, String bookFlightDate, String bookCabin, String queryDepCity,
            String queryArrCity, String queryFlightDate, String queryAdultNum, String queryChildNum, String queryInfantNum, String queryCountry, String queryTravelType, String bookPsgFirName) {
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

    @Column(name = "id", length = 64)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Column(name = "requestMatchExpression", length = 64)
    public String getRequestMatchExpression() {
        return this.requestMatchExpression;
    }

    public void setRequestMatchExpression(String requestMatchExpression) {
        this.requestMatchExpression = requestMatchExpression;
    }

    @Column(name = "requestMethod", length = 16)
    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Column(name = "isNormalGet", length = 16)
    public String getIsNormalGet() {
        return this.isNormalGet;
    }

    public void setIsNormalGet(String isNormalGet) {
        this.isNormalGet = isNormalGet;
    }

    @Column(name = "isNormalForm", length = 16)
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

    @Column(name = "isJson", length = 16)
    public String getIsJson() {
        return this.isJson;
    }

    public void setIsJson(String isJson) {
        this.isJson = isJson;
    }

    @Column(name = "isXML", length = 16)
    public String getIsXml() {
        return this.isXml;
    }

    public void setIsXml(String isXml) {
        this.isXml = isXml;
    }

    @Column(name = "formDataField", length = 16)
    public String getFormDataField() {
        return this.formDataField;
    }

    public void setFormDataField(String formDataField) {
        this.formDataField = formDataField;
    }

    @Column(name = "book_bookUserId", length = 16)
    public String getBookBookUserId() {
        return this.bookBookUserId;
    }

    public void setBookBookUserId(String bookBookUserId) {
        this.bookBookUserId = bookBookUserId;
    }

    @Column(name = "book_bookUnUserId", length = 16)
    public String getBookBookUnUserId() {
        return this.bookBookUnUserId;
    }

    public void setBookBookUnUserId(String bookBookUnUserId) {
        this.bookBookUnUserId = bookBookUnUserId;
    }

    @Column(name = "book_psgName", length = 16)
    public String getBookPsgName() {
        return this.bookPsgName;
    }

    public void setBookPsgName(String bookPsgName) {
        this.bookPsgName = bookPsgName;
    }

    @Column(name = "book_psgType", length = 16)
    public String getBookPsgType() {
        return this.bookPsgType;
    }

    public void setBookPsgType(String bookPsgType) {
        this.bookPsgType = bookPsgType;
    }

    @Column(name = "book_idType", length = 16)
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

    @Column(name = "book_contractName", length = 16)
    public String getBookContractName() {
        return this.bookContractName;
    }

    public void setBookContractName(String bookContractName) {
        this.bookContractName = bookContractName;
    }

    @Column(name = "book_contractPhone", length = 16)
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

    @Column(name = "book_flightDate", length = 16)
    public String getBookFlightDate() {
        return this.bookFlightDate;
    }

    public void setBookFlightDate(String bookFlightDate) {
        this.bookFlightDate = bookFlightDate;
    }

    @Column(name = "book_cabin", length = 16)
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

    @Column(name = "query_flightDate", length = 16)
    public String getQueryFlightDate() {
        return this.queryFlightDate;
    }

    public void setQueryFlightDate(String queryFlightDate) {
        this.queryFlightDate = queryFlightDate;
    }

    @Column(name = "query_adultNum", length = 16)
    public String getQueryAdultNum() {
        return this.queryAdultNum;
    }

    public void setQueryAdultNum(String queryAdultNum) {
        this.queryAdultNum = queryAdultNum;
    }

    @Column(name = "query_childNum", length = 16)
    public String getQueryChildNum() {
        return this.queryChildNum;
    }

    public void setQueryChildNum(String queryChildNum) {
        this.queryChildNum = queryChildNum;
    }

    @Column(name = "query_infantNum", length = 16)
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

    @Column(name = "query_travelType", length = 16)
    public String getQueryTravelType() {
        return this.queryTravelType;
    }

    public void setQueryTravelType(String queryTravelType) {
        this.queryTravelType = queryTravelType;
    }

    @Column(name = "book_psgFirName", length = 16)
    public String getBookPsgFirName() {
        return this.bookPsgFirName;
    }

    public void setBookPsgFirName(String bookPsgFirName) {
        this.bookPsgFirName = bookPsgFirName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AnalyzeruleId))
            return false;
        AnalyzeruleId castOther = (AnalyzeruleId) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
                && ((this.getFlightType() == castOther.getFlightType()) || (this.getFlightType() != null && castOther.getFlightType() != null && this.getFlightType().equals(castOther.getFlightType())))
                && ((this.getBehaviorType() == castOther.getBehaviorType()) || (this.getBehaviorType() != null && castOther.getBehaviorType() != null && this.getBehaviorType().equals(
                        castOther.getBehaviorType())))
                && ((this.getRequestMatchExpression() == castOther.getRequestMatchExpression()) || (this.getRequestMatchExpression() != null && castOther.getRequestMatchExpression() != null && this
                        .getRequestMatchExpression().equals(castOther.getRequestMatchExpression())))
                && ((this.getRequestMethod() == castOther.getRequestMethod()) || (this.getRequestMethod() != null && castOther.getRequestMethod() != null && this.getRequestMethod().equals(
                        castOther.getRequestMethod())))
                && ((this.getIsNormalGet() == castOther.getIsNormalGet()) || (this.getIsNormalGet() != null && castOther.getIsNormalGet() != null && this.getIsNormalGet().equals(
                        castOther.getIsNormalGet())))
                && ((this.getIsNormalForm() == castOther.getIsNormalForm()) || (this.getIsNormalForm() != null && castOther.getIsNormalForm() != null && this.getIsNormalForm().equals(
                        castOther.getIsNormalForm())))
                && ((this.getIsApplicationJson() == castOther.getIsApplicationJson()) || (this.getIsApplicationJson() != null && castOther.getIsApplicationJson() != null && this
                        .getIsApplicationJson().equals(castOther.getIsApplicationJson())))
                && ((this.getIsTextXml() == castOther.getIsTextXml()) || (this.getIsTextXml() != null && castOther.getIsTextXml() != null && this.getIsTextXml().equals(castOther.getIsTextXml())))
                && ((this.getIsJson() == castOther.getIsJson()) || (this.getIsJson() != null && castOther.getIsJson() != null && this.getIsJson().equals(castOther.getIsJson())))
                && ((this.getIsXml() == castOther.getIsXml()) || (this.getIsXml() != null && castOther.getIsXml() != null && this.getIsXml().equals(castOther.getIsXml())))
                && ((this.getFormDataField() == castOther.getFormDataField()) || (this.getFormDataField() != null && castOther.getFormDataField() != null && this.getFormDataField().equals(
                        castOther.getFormDataField())))
                && ((this.getBookBookUserId() == castOther.getBookBookUserId()) || (this.getBookBookUserId() != null && castOther.getBookBookUserId() != null && this.getBookBookUserId().equals(
                        castOther.getBookBookUserId())))
                && ((this.getBookBookUnUserId() == castOther.getBookBookUnUserId()) || (this.getBookBookUnUserId() != null && castOther.getBookBookUnUserId() != null && this.getBookBookUnUserId()
                        .equals(castOther.getBookBookUnUserId())))
                && ((this.getBookPsgName() == castOther.getBookPsgName()) || (this.getBookPsgName() != null && castOther.getBookPsgName() != null && this.getBookPsgName().equals(
                        castOther.getBookPsgName())))
                && ((this.getBookPsgType() == castOther.getBookPsgType()) || (this.getBookPsgType() != null && castOther.getBookPsgType() != null && this.getBookPsgType().equals(
                        castOther.getBookPsgType())))
                && ((this.getBookIdType() == castOther.getBookIdType()) || (this.getBookIdType() != null && castOther.getBookIdType() != null && this.getBookIdType().equals(castOther.getBookIdType())))
                && ((this.getBookIdCard() == castOther.getBookIdCard()) || (this.getBookIdCard() != null && castOther.getBookIdCard() != null && this.getBookIdCard().equals(castOther.getBookIdCard())))
                && ((this.getBookContractName() == castOther.getBookContractName()) || (this.getBookContractName() != null && castOther.getBookContractName() != null && this.getBookContractName()
                        .equals(castOther.getBookContractName())))
                && ((this.getBookContractPhone() == castOther.getBookContractPhone()) || (this.getBookContractPhone() != null && castOther.getBookContractPhone() != null && this
                        .getBookContractPhone().equals(castOther.getBookContractPhone())))
                && ((this.getBookDepCity() == castOther.getBookDepCity()) || (this.getBookDepCity() != null && castOther.getBookDepCity() != null && this.getBookDepCity().equals(
                        castOther.getBookDepCity())))
                && ((this.getBookArrCity() == castOther.getBookArrCity()) || (this.getBookArrCity() != null && castOther.getBookArrCity() != null && this.getBookArrCity().equals(
                        castOther.getBookArrCity())))
                && ((this.getBookFlightDate() == castOther.getBookFlightDate()) || (this.getBookFlightDate() != null && castOther.getBookFlightDate() != null && this.getBookFlightDate().equals(
                        castOther.getBookFlightDate())))
                && ((this.getBookCabin() == castOther.getBookCabin()) || (this.getBookCabin() != null && castOther.getBookCabin() != null && this.getBookCabin().equals(castOther.getBookCabin())))
                && ((this.getQueryDepCity() == castOther.getQueryDepCity()) || (this.getQueryDepCity() != null && castOther.getQueryDepCity() != null && this.getQueryDepCity().equals(
                        castOther.getQueryDepCity())))
                && ((this.getQueryArrCity() == castOther.getQueryArrCity()) || (this.getQueryArrCity() != null && castOther.getQueryArrCity() != null && this.getQueryArrCity().equals(
                        castOther.getQueryArrCity())))
                && ((this.getQueryFlightDate() == castOther.getQueryFlightDate()) || (this.getQueryFlightDate() != null && castOther.getQueryFlightDate() != null && this.getQueryFlightDate().equals(
                        castOther.getQueryFlightDate())))
                && ((this.getQueryAdultNum() == castOther.getQueryAdultNum()) || (this.getQueryAdultNum() != null && castOther.getQueryAdultNum() != null && this.getQueryAdultNum().equals(
                        castOther.getQueryAdultNum())))
                && ((this.getQueryChildNum() == castOther.getQueryChildNum()) || (this.getQueryChildNum() != null && castOther.getQueryChildNum() != null && this.getQueryChildNum().equals(
                        castOther.getQueryChildNum())))
                && ((this.getQueryInfantNum() == castOther.getQueryInfantNum()) || (this.getQueryInfantNum() != null && castOther.getQueryInfantNum() != null && this.getQueryInfantNum().equals(
                        castOther.getQueryInfantNum())))
                && ((this.getQueryCountry() == castOther.getQueryCountry()) || (this.getQueryCountry() != null && castOther.getQueryCountry() != null && this.getQueryCountry().equals(
                        castOther.getQueryCountry())))
                && ((this.getQueryTravelType() == castOther.getQueryTravelType()) || (this.getQueryTravelType() != null && castOther.getQueryTravelType() != null && this.getQueryTravelType().equals(
                        castOther.getQueryTravelType())))
                && ((this.getBookPsgFirName() == castOther.getBookPsgFirName()) || (this.getBookPsgFirName() != null && castOther.getBookPsgFirName() != null && this.getBookPsgFirName().equals(
                        castOther.getBookPsgFirName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getFlightType() == null ? 0 : this.getFlightType().hashCode());
        result = 37 * result + (getBehaviorType() == null ? 0 : this.getBehaviorType().hashCode());
        result = 37 * result + (getRequestMatchExpression() == null ? 0 : this.getRequestMatchExpression().hashCode());
        result = 37 * result + (getRequestMethod() == null ? 0 : this.getRequestMethod().hashCode());
        result = 37 * result + (getIsNormalGet() == null ? 0 : this.getIsNormalGet().hashCode());
        result = 37 * result + (getIsNormalForm() == null ? 0 : this.getIsNormalForm().hashCode());
        result = 37 * result + (getIsApplicationJson() == null ? 0 : this.getIsApplicationJson().hashCode());
        result = 37 * result + (getIsTextXml() == null ? 0 : this.getIsTextXml().hashCode());
        result = 37 * result + (getIsJson() == null ? 0 : this.getIsJson().hashCode());
        result = 37 * result + (getIsXml() == null ? 0 : this.getIsXml().hashCode());
        result = 37 * result + (getFormDataField() == null ? 0 : this.getFormDataField().hashCode());
        result = 37 * result + (getBookBookUserId() == null ? 0 : this.getBookBookUserId().hashCode());
        result = 37 * result + (getBookBookUnUserId() == null ? 0 : this.getBookBookUnUserId().hashCode());
        result = 37 * result + (getBookPsgName() == null ? 0 : this.getBookPsgName().hashCode());
        result = 37 * result + (getBookPsgType() == null ? 0 : this.getBookPsgType().hashCode());
        result = 37 * result + (getBookIdType() == null ? 0 : this.getBookIdType().hashCode());
        result = 37 * result + (getBookIdCard() == null ? 0 : this.getBookIdCard().hashCode());
        result = 37 * result + (getBookContractName() == null ? 0 : this.getBookContractName().hashCode());
        result = 37 * result + (getBookContractPhone() == null ? 0 : this.getBookContractPhone().hashCode());
        result = 37 * result + (getBookDepCity() == null ? 0 : this.getBookDepCity().hashCode());
        result = 37 * result + (getBookArrCity() == null ? 0 : this.getBookArrCity().hashCode());
        result = 37 * result + (getBookFlightDate() == null ? 0 : this.getBookFlightDate().hashCode());
        result = 37 * result + (getBookCabin() == null ? 0 : this.getBookCabin().hashCode());
        result = 37 * result + (getQueryDepCity() == null ? 0 : this.getQueryDepCity().hashCode());
        result = 37 * result + (getQueryArrCity() == null ? 0 : this.getQueryArrCity().hashCode());
        result = 37 * result + (getQueryFlightDate() == null ? 0 : this.getQueryFlightDate().hashCode());
        result = 37 * result + (getQueryAdultNum() == null ? 0 : this.getQueryAdultNum().hashCode());
        result = 37 * result + (getQueryChildNum() == null ? 0 : this.getQueryChildNum().hashCode());
        result = 37 * result + (getQueryInfantNum() == null ? 0 : this.getQueryInfantNum().hashCode());
        result = 37 * result + (getQueryCountry() == null ? 0 : this.getQueryCountry().hashCode());
        result = 37 * result + (getQueryTravelType() == null ? 0 : this.getQueryTravelType().hashCode());
        result = 37 * result + (getBookPsgFirName() == null ? 0 : this.getBookPsgFirName().hashCode());
        return result;
    }

}