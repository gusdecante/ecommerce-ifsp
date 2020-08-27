package model;

public class OrderItem extends Product{

    private int idOrderItem,amount,productIdProduct,orderIdItem;

    public int getIdOrderItem() {
        return this.idOrderItem;
    }

    public void setIdOrderItem(int idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProductIdProduct() {
        return this.productIdProduct;
    }

    public void setProductIdProduct(int productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    public int getOrderIdItem() {
        return this.orderIdItem;
    }

    public void setOrderIdItem(int orderIdItem) {
        this.orderIdItem = orderIdItem;
    }

}