package com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Receipt {

    @SerializedName("transaction_data")
    @Expose
    private TransactionData transactionData;

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "transactionData=" + transactionData +
                '}';
    }
}
