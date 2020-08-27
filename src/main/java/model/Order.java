package model;

import java.sql.Date;

public class Order extends PaymentForm{
    
    private int idColor,paymentFormIdPaymentForm;
    private Date date;


    public int getIdColor() {
        return this.idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getPaymentFormIdPaymentForm() {
        return this.paymentFormIdPaymentForm;
    }

    public void setPaymentFormIdPaymentForm(int paymentFormIdPaymentForm) {
        this.paymentFormIdPaymentForm = paymentFormIdPaymentForm;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}