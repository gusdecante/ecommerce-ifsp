package model;

public class User {
    private int idUser;
    private String email;
    private String password;
    private int typeOfUser;//add token
    private int token;

    
    public int getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getTypeOfUser() {
        return this.typeOfUser;
    }
    
    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }
    
    public int getToken() {
        return this.token;
    }

    public void setToken(int token) {
        this.token = token;
    }
    
}