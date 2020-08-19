package model;

public class PessoaJuridica extends Customer{
    private int idPessoaJuridica;
    private String cnpj;
    private String razaoSocial;

    public int getIdPessoaJuridica() {
        return this.idPessoaJuridica;
    }

    public void setIdPessoaJuridica(int idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

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
}