package org.example.labs.domain;

public class Region {

    private Long id;

    private String nameRegion;

    private Long countryId;

    private Country country;

    public Region(){}

    public Region(String nameRegion, Country country){
        this.nameRegion = nameRegion;
        this.country = country;
    }

    public Region(String nameRegion, Country country, Long countryId){
        this.nameRegion = nameRegion;
        this.country = country;
        this.countryId = countryId;
    }

    public Region(Long id,String nameRegion, Country country, Long countryId){
        this.id = id;
        this.nameRegion = nameRegion;
        this.country = country;
        this.countryId = countryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country.getFullName();
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", nameRegion='" + nameRegion + '\'' +
                ", country=" + getCountry() +
                '}';
    }
}
