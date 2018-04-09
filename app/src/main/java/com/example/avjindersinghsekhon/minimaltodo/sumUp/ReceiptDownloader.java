package com.example.avjindersinghsekhon.minimaltodo.sumUp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.Receipt;
import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.TransactionData;

import java.lang.reflect.Field;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
*  CLASS THAT'S NOT NEEDED
* */

public class ReceiptDownloader {

    private final static String TAG = "ReceiptDownloader.class";

    // The built retrofit
    private Retrofit retrofit;

    // baseURL & transactionCode are passed by the construction.
    // serverResponse is the server code given back by the retrofit call
    private String baseURL, transactionCode, serverResponse;

    // static merchant code for the API call
    private static String PUBLIC_MERCHANT_CODE = "MDMQTMEM";

    // TransactionData. The needed data for the Receipt
    private TransactionData traDaReceipt;

    // Activity that's instantiating the class
    private Activity activity;


    ReceiptDownloader(Activity activity, String url, String tCode){
        this.baseURL = url;
        this.transactionCode = tCode;
        this.activity = activity;
        retrofitStarter();
        retrofitCall();
        traDaReceipt = new TransactionData();

    }

    // Fixing the retrofit call
    public void retrofitStarter(){


        retrofit = new Retrofit.Builder()
                .baseUrl( baseURL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    // Making the call
    public void retrofitCall(){

        // Creating the full baseURL for the REST API call
        String fullBaseURL = baseURL + transactionCode + "?mid=" + PUBLIC_MERCHANT_CODE;


        SumUpAPI sumUpAPI = retrofit.create(SumUpAPI.class);
        Call<Receipt> call = sumUpAPI.getData(fullBaseURL);
        call.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {

                Log.d(TAG, " " + response.body().getTransactionData());
                serverResponse = response.toString();

                traDaReceipt = response.body().getTransactionData();
            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable throwable) {
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public TransactionData getReceipt() {
        return traDaReceipt;
    }

    public String getProductName(){
        return traDaReceipt.getProducts().get(0).getName();
    }
}
