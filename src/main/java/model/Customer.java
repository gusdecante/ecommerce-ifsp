package model;

public class Customer extends User{
    private int idCustomer, userIdUser;
    
    public int getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getUserIdUser() {
        return this.userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

}
