package com.gustavo.citiesmicroservice.entity;

import com.gustavo.citiesmicroservice.annotation.ShowField;
import java.util.Date;

/**
 * 
 * @author Gustavo Cardenas Alba.
 * @version 24/08/2023/A
 */
public class City {

    private Integer geonameId;
    @ShowField
    private String name;
    private String asciiName;
    private String alternateNames;
    @ShowField
    private Double latitude;
    @ShowField
    private Double longitude;
    private Character featureClass;
    private String featureCode;
    private String countryCode;
    private String cc2;
    private String admin1Code;
    private String admin2Code;
    private String admin3Code;
    private String admin4Code;
    private Long population;
    private Integer elevation;
    private Integer dem;
    private String timezone;
    private Date modificationDate;

    @ShowField
    private Float score;

    public City() {
    }

    public City(Integer geonameId, String name, String asciiName, String alternateNames, Double latitude, Double longitude, Character featureClass, String featureCode, String countryCode, String cc2, String admin1Code, String admin2Code, String admin3Code, String admin4Code, Long population, Integer elevation, Integer dem, String timezone, Date modificationDate) {
        this.geonameId = geonameId;
        this.name = name;
        this.asciiName = asciiName;
        this.alternateNames = alternateNames;
        this.latitude = latitude;
        this.longitude = longitude;
        this.featureClass = featureClass;
        this.featureCode = featureCode;
        this.countryCode = countryCode;
        this.cc2 = cc2;
        this.admin1Code = admin1Code;
        this.admin2Code = admin2Code;
        this.admin3Code = admin3Code;
        this.admin4Code = admin4Code;
        this.population = population;
        this.elevation = elevation;
        this.dem = dem;
        this.timezone = timezone;
        this.modificationDate = modificationDate;
    }

    public Integer getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Integer geonameId) {
        this.geonameId = geonameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public String getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(String alternateNames) {
        this.alternateNames = alternateNames;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Character getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(Character featureClass) {
        this.featureClass = featureClass;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1Code() {
        return admin1Code;
    }

    public void setAdmin1Code(String admin1Code) {
        this.admin1Code = admin1Code;
    }

    public String getAdmin2Code() {
        return admin2Code;
    }

    public void setAdmin2Code(String admin2Code) {
        this.admin2Code = admin2Code;
    }

    public String getAdmin3Code() {
        return admin3Code;
    }

    public void setAdmin3Code(String admin3Code) {
        this.admin3Code = admin3Code;
    }

    public String getAdmin4Code() {
        return admin4Code;
    }

    public void setAdmin4Code(String admin4Code) {
        this.admin4Code = admin4Code;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Integer getDem() {
        return dem;
    }

    public void setDem(Integer dem) {
        this.dem = dem;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
    

}
