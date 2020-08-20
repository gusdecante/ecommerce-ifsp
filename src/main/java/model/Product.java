package model;

public class Product extends Cuba{
    private int idProduct, stock, cubaIdCuba, FinishingProcessIdFinishing, colorIdColor, categoryIdCategory;
    private String nameProduct, description, imageLink, category, finishingProcess, nameColor;
    private double unitaryValue;


    public int getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCubaIdCuba() {
        return this.cubaIdCuba;
    }

    public void setCubaIdCuba(int cubaIdCuba) {
        this.cubaIdCuba = cubaIdCuba;
    }

    public int getFinishingProcessIdFinishing() {
        return this.FinishingProcessIdFinishing;
    }

    public void setFinishingProcessIdFinishing(int FinishingProcessIdFinishing) {
        this.FinishingProcessIdFinishing = FinishingProcessIdFinishing;
    }

    public int getColorIdColor() {
        return this.colorIdColor;
    }

    public void setColorIdColor(int colorIdColor) {
        this.colorIdColor = colorIdColor;
    }

    public int getCategoryIdCategory() {
        return this.categoryIdCategory;
    }

    public void setCategoryIdCategory(int categoryIdCategory) {
        this.categoryIdCategory = categoryIdCategory;
    }

    public String getNameProduct() {
        return this.nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFinishingProcess() {
        return this.finishingProcess;
    }

    public void setFinishingProcess(String finishingProcess) {
        this.finishingProcess = finishingProcess;
    }

    public String getNameColor() {
        return this.nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public double getUnitaryValue() {
        return this.unitaryValue;
    }

    public void setUnitaryValue(double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }
    
   
}
