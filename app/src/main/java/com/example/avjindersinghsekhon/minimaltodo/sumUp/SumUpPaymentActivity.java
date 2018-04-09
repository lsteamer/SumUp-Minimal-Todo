package com.example.avjindersinghsekhon.minimaltodo.sumUp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.Receipt;
import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.TransactionData;
import com.sumup.merchant.api.SumUpAPI;
import com.sumup.merchant.api.SumUpLogin;
import com.sumup.merchant.api.SumUpPayment;

import java.math.BigDecimal;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SumUpPaymentActivity extends Activity {

    // public static final global variables
    public static final int REQUEST_CODE_LOGIN = 1;
    public static final int REQUEST_CODE_PAYMENT = 2;
    public static final int REQUEST_CODE_PAYMENT_SETTINGS = 3;

    // static String for the amount in the bundle.
    public static final String EXTRA_CODE = "EXTRA_PAYMENT_AMOUNT";

    // debug TAG
    public static final String TAG = "SumUpPaymentActivity";

    // Base URL for the API call
    private static final String BASE_URL = "https://receipts-ng.sumup.com/v0.1/receipts/";
    // static merchant code for the API call
    private static final String PUBLIC_MERCHANT_CODE = "MDMQTMEM";
    // static affiliate key
    private static final String AFFILIATE_KEY = "72cc96a1-1e06-43c0-8380-5413aa60585e";



    // serverResponse is the server code given back by the retrofit call
    private String serverResponse;


    // Modifiable TextViews in the Dialog / Receipt
    private TextView amountReceipt, currencyReceipt, nameReceipt, statusReceipt, numberReceipt;


    // Button to Log/in Make the payment
    // & Buttons in the dialog
    private Button sumUpSignIn, sumupPayment, stayLogged, logOff;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumup);


        // get the Amount to be paid in a string
        final String payableAmount =  getIntent().getExtras().getString(EXTRA_CODE);


        // Button login
        sumUpSignIn = (Button) findViewById(R.id.loginButton);
        sumUpSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Logging in
                sumUpLogin();
            }
        });

        // Button Payment
        sumupPayment = (Button) findViewById(R.id.paymentButton);
        sumupPayment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // Making the payment
                makePayment(payableAmount);
            }
        });



    }


    // Logging in
    public void sumUpLogin(){

        SumUpLogin sumupLogin = SumUpLogin.builder(AFFILIATE_KEY).build();
        SumUpAPI.openLoginActivity(this, sumupLogin, REQUEST_CODE_LOGIN);
    }

    // Make the payment
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

    // Results for the SumUp operations
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_LOGIN:
                if (data != null) {
                    Bundle extra = data.getExtras();
                    if(1 == extra.getInt(SumUpAPI.Response.RESULT_CODE)){

                        Toast.makeText(this, "Logged in.", Toast.LENGTH_SHORT).show();
                    }
                    else if(11 == extra.getInt(SumUpAPI.Response.RESULT_CODE)){
                        Toast.makeText(this, "Already logged in.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Error. Not logged in.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case REQUEST_CODE_PAYMENT:
                if(data != null) {

                    Bundle extra = data.getExtras();
                    if(1 == extra.getInt(SumUpAPI.Response.RESULT_CODE)){

                        // Retrofit Call and Dialog Receipt to follow
                        String txCode = extra.getString(SumUpAPI.Response.TX_CODE);
                        retrofitStarter(txCode);

                    }
                    else if(8 == extra.getInt(SumUpAPI.Response.RESULT_CODE)) {
                        Toast.makeText(this, "You need to log in.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Payment was not completed.", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

        }
    }



    // Retrofit call
    // From the response, the dialog will be shown
    public void retrofitStarter(String transactionCode){



        // The built retrofit
        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        // Creating the full baseURL for the REST API call
        String fullBaseURL = BASE_URL + transactionCode + "?mid=" + PUBLIC_MERCHANT_CODE;

        // Interface, Different file
        com.example.avjindersinghsekhon.minimaltodo.sumUp.SumUpAPI sumUpAPI = retrofit.create(com.example.avjindersinghsekhon.minimaltodo.sumUp.SumUpAPI.class);

        // Call
        Call<Receipt> call = sumUpAPI.getData(fullBaseURL);

        call.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {

                Log.d(TAG, " " + response.body().getTransactionData());
                serverResponse = response.toString();
                TransactionData transactionData = response.body().getTransactionData();


                // Getting the data from the Call
                String amount = transactionData.getAmount();
                String currency = transactionData.getCurrency();
                String receiptNumber = transactionData.getReceiptNumber();
                String status = transactionData.getStatus();
                String timestamp = transactionData.getTimestamp();
                String name = transactionData.getProducts().get(0).getName();

                // And calling the method that will construct the Receipt - dialog
                showDialog(amount, currency, name, status, receiptNumber, timestamp);

            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Constructs the dialog that shows the receipt
    public void showDialog(String... receiptStrings){
        /*receiptStrings order is:
         * 0- amount
         * 1- currency
         * 2- name
         * 3- status
         * 4- receiptNumber
         * 5- timestamp
         * */

        // Building the dialog
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SumUpPaymentActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_receipt, null);


        // TextViews within the Dialog that will be changed for the receipt
        amountReceipt = (TextView) mView.findViewById(R.id.amountReceiptPaymentSuccessful);
        amountReceipt.setText(receiptStrings[0]);

        currencyReceipt = (TextView) mView.findViewById(R.id.currencyReceiptPaymentSuccessful);
        currencyReceipt.setText(receiptStrings[1]);

        nameReceipt = (TextView) mView.findViewById(R.id.productMotivePaymentSuccessful);
        nameReceipt.setText(receiptStrings[2]);

        statusReceipt = (TextView) mView.findViewById(R.id.statusReceiptPaymentSuccessful);
        statusReceipt.setText(receiptStrings[3]);

        numberReceipt = (TextView) mView.findViewById(R.id.receiptNumberReceiptPaymentSuccessful);
        numberReceipt.setText(receiptStrings[4]);

        // Start the dialog
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();



        // The two buttons of the dialog below

        // If user wants to stay Logged in SumUp
        stayLogged = (Button) mView.findViewById(R.id.signedInReceiptButton);
        stayLogged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

        // And if user wants to sign off
        logOff = (Button) mView.findViewById(R.id.signOffReceiptButton);
        logOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SumUpAPI.logout();
                dialog.dismiss();
                finish();
            }
        });

    }


    // Gets the amount from the bundle, as a String, and returns a float
    public float getAmountToFloat(){

        String string =  getIntent().getExtras().getString(EXTRA_CODE);
        return Float.parseFloat(string);
    }


}
