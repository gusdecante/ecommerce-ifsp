package model;

public class Order extends PaymentForm{
    
    private int idOrder, paymentFormIdPaymentForm;
    private String date;

    public int getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getPaymentFormIdPaymentForm() {
        return this.paymentFormIdPaymentForm;
    }

    public void setPaymentFormIdPaymentForm(int paymentFormIdPaymentForm) {
        this.paymentFormIdPaymentForm = paymentFormIdPaymentForm;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}