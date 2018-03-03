package sax;

import java.io.Serializable;

public class Card implements Serializable{
    private String name;
    private String profession;
    private String workNumber;
    private String mobileNumber;
    private String homeNumber;
    private String email;

    public Card() {
    }

    public Card(String name, String profession, String email) {
        this.name = name;
        this.profession = profession;
        this.email = email;
    }

    public Card(String name, String profession, String workNumber, String mobileNumber, String homeNumber, String email) {
        this.name = name;
        this.profession = profession;
        this.workNumber = workNumber;
        this.mobileNumber = mobileNumber;
        this.homeNumber = homeNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void show(){
        System.out.println("Name: "+ name);
        System.out.println("Profession: "+ profession);
        System.out.println("Work number: "+ workNumber);
        System.out.println("Home number: "+ homeNumber);
        System.out.println("Mobile number: "+ mobileNumber);
        System.out.println("Email: "+ email);
        System.out.println("------------------------------------");
    }
}
