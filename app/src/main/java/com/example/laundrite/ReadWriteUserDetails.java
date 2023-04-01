package com.example.laundrite;

public class ReadWriteUserDetails {
    public String fullName, number,email,password;

    public ReadWriteUserDetails(){}

    public ReadWriteUserDetails(String textfullName,String textNumber,String textEmail,String textPassword){
        this.fullName = textfullName;
        this.number=textNumber;
    }
}
