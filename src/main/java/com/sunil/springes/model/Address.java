package com.sunil.springes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Address {

    @JsonProperty("address_id")
    private String addressId;

    @JsonProperty("address_line1")
    private String addressLine1;

    @JsonProperty("address_line2")
    private String addressLine2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state_code")
    private String stateCode;

    @JsonProperty("zip_code")
    private String zipCode;

    public Address() {
    }

    public Address(String addressId, String addressLine1, String addressLine2, String city, String stateCode, String zipCode) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.stateCode = stateCode;
        this.zipCode = zipCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getAddressId(), address.getAddressId()) &&
                Objects.equals(getAddressLine1(), address.getAddressLine1()) &&
                Objects.equals(getAddressLine2(), address.getAddressLine2()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStateCode(), address.getStateCode()) &&
                Objects.equals(getZipCode(), address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressId(), getAddressLine1(), getAddressLine2(), getCity(), getStateCode(), getZipCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
