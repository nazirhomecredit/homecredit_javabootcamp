package com.homecredit.bankingapp.util;


public class HelpingFun {

    
    public HelpingFun(){

    }
    public int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
