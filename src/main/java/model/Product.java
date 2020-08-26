package model;

public class Product extends Category{
    private int idProduct, categoryIdCategory, stock;
    private String color, finishingProcess, cubaType, description, imageLink;
    private double unitaryValue;

    public int getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCategoryIdCategory() {
        return this.categoryIdCategory;
    }

    public void setCategoryIdCategory(int categoryIdCategory) {
        this.categoryIdCategory = categoryIdCategory;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public double getUnitaryValue() {
        return this.unitaryValue;
    }

    public void setUnitaryValue(double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

   
}
