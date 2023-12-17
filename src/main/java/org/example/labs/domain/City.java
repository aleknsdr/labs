package org.example.labs.domain;

public class City {

    private Long id;

    private String nameCity;

    private Region region;

    private Long regionId;

    public City(){}

    public City(String nameCity, Region region){
        this.nameCity = nameCity;
        this.region = region;
    }

    public City(String nameCity, Region region, Long regionId){
        this.nameCity = nameCity;
        this.region = region;
        this.regionId = regionId;
    }

    public City(Long id,String nameCity, Region region, Long regionId){
        this.id = id;
        this.nameCity = nameCity;
        this.region = region;
        this.regionId = regionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getRegion() {
        return region.getNameRegion();
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", nameCity='" + nameCity + '\'' +
                ", region=" + getRegion() +
                '}';
    }
}
