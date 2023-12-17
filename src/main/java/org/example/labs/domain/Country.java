package org.example.labs.domain;

public class Country {

    private Long id;

    private String fullName;

    private String shortName;

    public Country(){}

    public Country(String fullName, String shortName){
        this.fullName = fullName;
        this.shortName = shortName;
    }
    public Country(Long id,String fullName, String shortName){
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
