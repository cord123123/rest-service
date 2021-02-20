package com.example.restservice;

public class IntegerToRomanException extends RuntimeException{
    IntegerToRomanException(int number){
        super(number +" is not in valid range (1-3999)");
    }
}
