package com.example.scribbler;

import java.util.ArrayList;

public class UserModel {

    private ArrayList Interest;
    UserModel(){}

    public UserModel(ArrayList interest) {
        Interest = interest;
    }

    public ArrayList getInterest() {
        return Interest;
    }

    public void setInterest(ArrayList interest) {
        Interest = interest;
    }
}
