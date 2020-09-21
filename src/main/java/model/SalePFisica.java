package model;

public class SalePFisica {
    //Fields of Pessoa_Fisica
    private int idPessoaFisica;
    private String nameCustomer;
    private String CPF;
    private String RG;
    private String dateBirth;

    //fields of user
    private String email;

    //Fields of Address
    private String street,number,district,city,state,zipCode;

    //Fields of Phone
    private String phone;

    //Fields of Order
    private String date;

    //Fields payment_form
    private String paymentForm;

    //Fields of Product
    private String color, finishingProcess, cubaType, description, imageLink;
    private String color2, finishingProcess2, cubaType2, description2, imageLink2;
    private String color3, finishingProcess3, cubaType3, description3, imageLink3;
    private double unitaryValue, unitaryValue2, unitaryValue3;

    //Fields of Order_Item
    private int amount, amount2, amount3;

    //Fields of Category
    private String category, category2, category3;
    
    //Fields of Sale
    private double total;


    public int getIdPessoaFisica() {
        return this.idPessoaFisica;
    }

    public void setIdPessoaFisica(int idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public String getNameCustomer() {
        return this.nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
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

    public String getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentForm() {
        return this.paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinishingProcess() {
        return this.finishingProcess;
    }

    public void setFinishingProcess(String finishingProcess) {
        this.finishingProcess = finishingProcess;
    }

    public String getCubaType() {
        return this.cubaType;
    }

    public void setCubaType(String cubaType) {
        this.cubaType = cubaType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return this.imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getColor2() {
        return this.color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getFinishingProcess2() {
        return this.finishingProcess2;
    }

    public void setFinishingProcess2(String finishingProcess2) {
        this.finishingProcess2 = finishingProcess2;
    }

    public String getCubaType2() {
        return this.cubaType2;
    }

    public void setCubaType2(String cubaType2) {
        this.cubaType2 = cubaType2;
    }

    public String getDescription2() {
        return this.description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getImageLink2() {
        return this.imageLink2;
    }

    public void setImageLink2(String imageLink2) {
        this.imageLink2 = imageLink2;
    }

    public String getColor3() {
        return this.color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getFinishingProcess3() {
        return this.finishingProcess3;
    }

    public void setFinishingProcess3(String finishingProcess3) {
        this.finishingProcess3 = finishingProcess3;
    }

    public String getCubaType3() {
        return this.cubaType3;
    }

    public void setCubaType3(String cubaType3) {
        this.cubaType3 = cubaType3;
    }

    public String getDescription3() {
        return this.description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getImageLink3() {
        return this.imageLink3;
    }

    public void setImageLink3(String imageLink3) {
        this.imageLink3 = imageLink3;
    }

    public double getUnitaryValue() {
        return this.unitaryValue;
    }

    public void setUnitaryValue(double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public double getUnitaryValue2() {
        return this.unitaryValue2;
    }

    public void setUnitaryValue2(double unitaryValue2) {
        this.unitaryValue2 = unitaryValue2;
    }

    public double getUnitaryValue3() {
        return this.unitaryValue3;
    }

    public void setUnitaryValue3(double unitaryValue3) {
        this.unitaryValue3 = unitaryValue3;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount2() {
        return this.amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2 = amount2;
    }

    public int getAmount3() {
        return this.amount3;
    }

    public void setAmount3(int amount3) {
        this.amount3 = amount3;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory2() {
        return this.category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return this.category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}