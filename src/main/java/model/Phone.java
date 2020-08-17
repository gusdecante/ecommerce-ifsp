package model;

public class Phone extends Customer{
    
    private int idPhone,customerIdCustomer;
    private String phone;


    public int getIdPhone() {
        return this.idPhone;
    }

    public void setIdPhone(int idPhone) {
        this.idPhone = idPhone;
    }

    public int getCustomerIdCustomer() {
        return this.customerIdCustomer;
    }

    public void setCustomerIdCustomer(int customerIdCustomer) {
        this.customerIdCustomer = customerIdCustomer;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}