package com.example.myapplication.ui.Utils;

import java.util.ArrayList;

public class CategoryManage {
    private int id;
    private String text;
    public static ArrayList<CategoryManage> categoryManages = new ArrayList<>();


    public CategoryManage(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
