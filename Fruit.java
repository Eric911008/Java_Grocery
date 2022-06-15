package com.company.final_project_test2;
public class Fruit extends Food {

    private int num;

    private static final int price = 20;
    public Fruit() {
        setName("");
        setNum(0);
    }
    public Fruit(String n, int p) {
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