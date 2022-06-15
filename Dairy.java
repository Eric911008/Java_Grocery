package com.company.final_project_test2;

public class Dairy extends Food {
    private int num;
    private static final int price = 35;

    public Dairy() {
        setName("");
        setNum(0);
    }
    public Dairy(String n, int p) {
        setName(n);
        setNum(p);
    }

    public void setNum(int p) {
        num = p;
    }

    public int getNum() {
        return num;
    }

    public String getItemInfo() {
        return String.format("商品名稱:%s | 數量:%s | 售價:%s",getName(),getNum(),price);
    }

    public int calFee() {
        return num * price;
    }
}