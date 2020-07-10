package com.sunil.springes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Person {

    @JsonProperty("id")
    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("addresses")
    private List<Address> address;
    @JsonProperty("internet_ids")
    private List<String> internetIds;

    public Person() {

    }

    public Person(String id, String firstName, String lastName, List<Address> address, List<String> internetIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.internetIds = internetIds;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<String> getInternetIds() {
        return internetIds;
    }

    public void setInternetIds(List<String> internetIds) {
        this.internetIds = internetIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(getAddress(), person.getAddress()) &&
                Objects.equals(getInternetIds(), person.getInternetIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getInternetIds());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", internetIds=" + internetIds +
                '}';
    }
}
