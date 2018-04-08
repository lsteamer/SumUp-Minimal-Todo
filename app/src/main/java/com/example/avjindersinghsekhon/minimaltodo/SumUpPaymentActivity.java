package com.example.avjindersinghsekhon.minimaltodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.sumup.merchant.api.SumUpAPI;
import com.sumup.merchant.api.SumUpLogin;
import com.sumup.merchant.api.SumUpPayment;

import java.math.BigDecimal;
import java.util.UUID;

public class SumUpPaymentActivity extends Activity {



    // public static final global variables
    public static final int REQUEST_CODE_LOGIN = 1;
    public static final int REQUEST_CODE_PAYMENT = 2;
    public static final int REQUEST_CODE_PAYMENT_SETTINGS = 3;

    public static final String EXTRA_CODE = "EXTRA_PAYMENT_AMOUNT";


    private Button sumUpButton;

    private boolean loggedIn = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumup);

        sumUpButton = (Button) findViewById(R.id.estebutton);


        final String payableAmount =  getIntent().getExtras().getString(EXTRA_CODE);

        if(!loggedIn){
            sumUpLogin();
        }

        sumUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(loggedIn)
                    makePayment(payableAmount);
                else
                    sumUpLogin();

            }
        });

        Log.d("Payment Information", " "+ loggedIn);
    }


    // Gets the amount from the bundle, as a String, and returns a float
    public float getAmountToFloat(){
        String string =  getIntent().getExtras().getString(EXTRA_CODE);

        return Float.parseFloat(string);
    }

    // Logging in
    public void sumUpLogin(){
        Log.d("SumUp","sumUpLogin area");

        SumUpLogin sumupLogin = SumUpLogin.builder("72cc96a1-1e06-43c0-8380-5413aa60585e").build();
        SumUpAPI.openLoginActivity(this, sumupLogin, REQUEST_CODE_LOGIN);
    }

    public void makePayment(String amount){




        SumUpPayment payment = SumUpPayment.builder()
                // mandatory parameters
                .total(new BigDecimal(amount)) // minimum 1.00
                .currency(SumUpPayment.Currency.EUR)
                // optional: add details
                .title("Donation to Minimal")
                // optional: foreign transaction ID, must be unique!
                .foreignTransactionId(UUID.randomUUID().toString()) // can not exceed 128 chars
                .build();

        SumUpAPI.checkout(this, payment, REQUEST_CODE_PAYMENT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("SumUp","On Result Area");

        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                if (data != null) {
                    Bundle extra = data.getExtras();
                    if(1 == extra.getInt(SumUpAPI.Response.RESULT_CODE)){
                        loggedIn = true;
                    }
                }
                break;
            case REQUEST_CODE_PAYMENT:
                if(data != null) {

                }

        }
    }

    public boolean getLogInStatus(){
        return loggedIn;
    }
}
