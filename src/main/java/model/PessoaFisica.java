package model;

import java.sql.Date;

public class PessoaFisica extends Customer {
    private int idPessoaFisica, customerIdCustomer;
    private String CPF;
    private String RG;
    private String nameCustomer;

    public int getIdPessoaFisica() {
        return this.idPessoaFisica;
    }

    public void setIdPessoaFisica(int idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public int getCustomerIdCustomer() {
        return this.customerIdCustomer;
    }

    public void setCustomerIdCustomer(int customerIdCustomer) {
        this.customerIdCustomer = customerIdCustomer;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return this.RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getNameCustomer() {
        return this.nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Date getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
    private Date dateBirth;


}