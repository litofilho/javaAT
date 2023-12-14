package com.example.AT.model;
import lombok.Data;

@Data
public class MyData {
    private String stringValue;
    private int intValue;
    private String[] arrayValue;

    public MyData(String string, int number, String[] array) {
        this.stringValue = string;
        this.intValue = number;
        this.arrayValue = array;
    }
}