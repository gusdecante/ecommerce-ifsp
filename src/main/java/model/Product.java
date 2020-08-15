package model;

public class Product extends Cuba{
    private int idProd;
    private String name;
    private String description;
    private String linkImage;
    private int stock;
    private double unitaryValue;

    public int getIdProd() {
        return this.idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkImage() {
        return this.linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getUnitaryValue() {
        return this.unitaryValue;
    }

    public void setUnitaryValue(double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }  
}
