package com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel;

import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.products.Products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TransactionData {


    @SerializedName("products")
    @Expose
    private ArrayList<Products> products;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("receipt_no")
    @Expose
    private String receiptNumber;

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String toString() {
        return "TransactionData{" +
                "products=" + products +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                ", receiptNumber='" + receiptNumber + '\'' +
                '}';
    }
}
