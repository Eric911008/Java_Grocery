package com.company.final_project_test2;

public abstract class Food {
    private String name;

    public Food() {
        setName("");
    }
    public Food(String n) {
        setName(n);
    }

    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }

    public abstract String getItemInfo();

    public abstract int calFee();

}