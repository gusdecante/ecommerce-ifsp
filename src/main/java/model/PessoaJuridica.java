package model;

public class PessoaJuridica extends Customer{
    private String cnpj;
    private String razaoSocial;
    private int customerIdCustomer;

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getCustomerIdCustomer() {
        return this.customerIdCustomer;
    }

    public void setCustomerIdCustomer(int customerIdCustomer) {
        this.customerIdCustomer = customerIdCustomer;
    }

}