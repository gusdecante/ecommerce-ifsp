package model;

public class Address extends Customer{
    
    private int idAdress,customerIdCustomer;
    private String street,number,district,city,state,zipCode;


    public int getIdAdress() {
        return this.idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
    }

    public int getCustomerIdCustomer() {
        return this.customerIdCustomer;
    }

    public void setCustomerIdCustomer(int customerIdCustomer) {
        this.customerIdCustomer = customerIdCustomer;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


}