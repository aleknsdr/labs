package org.example.labs.domain;

public class Address {

    private Long id;

    private String person;

    private String street;

    private int building;

    private int office;

    private City city;

    private Long cityId;

    public Address(){}

    public Address(String person, String street, int building, int office, City city){
        this.person = person;
        this.street = street;
        this.building = building;
        this.office = office;
        this.city = city;
    }

    public Address(String person, String street, int building, int office, City city, Long cityId){
        this.person = person;
        this.street = street;
        this.building = building;
        this.office = office;
        this.city = city;
        this.cityId = cityId;
    }

    public Address(Long id,String person, String street, int building, int office, City city, Long cityId){
        this.id = id;
        this.person = person;
        this.street = street;
        this.building = building;
        this.office = office;
        this.city = city;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public String getCity() {
        return city.getNameCity();
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", office=" + office +
                ", city=" + city.getNameCity() +
                '}';
    }
}
