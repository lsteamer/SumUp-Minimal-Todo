package com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("total_price")
    @Expose
    private String total_price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", total_price='" + total_price + '\'' +
                '}';
    }
}
