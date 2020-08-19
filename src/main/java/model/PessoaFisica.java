package model;

import java.sql.Date;

public class PessoaFisica extends Customer {
    private int idPessoaFisica, customer_id_Customer;
    private String CPF;
    private String RG;
    private Date dateOfBirth;

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public int getIdPessoaFisica() {
        return this.idPessoaFisica;
    }

    public void setIdPessoaFisica(int idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }


    public int getCustomer_id_Customer() {
        return this.customer_id_Customer;
    }

    public void setCustomer_id_Customer(int customer_id_Customer) {
        this.customer_id_Customer = customer_id_Customer;
    }

}