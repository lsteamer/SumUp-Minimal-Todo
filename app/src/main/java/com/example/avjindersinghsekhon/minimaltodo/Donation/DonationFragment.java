package com.example.avjindersinghsekhon.minimaltodo.Donation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.sumup.merchant.api.SumUpAPI;
import com.sumup.merchant.api.SumUpLogin;

public class DonationFragment extends AppDefaultFragment implements View.OnClickListener {

    // These are the buttons that will be pressed by donation
    private Button dButton1, dButton2, dButton3, dButton4, dButton5, dButton6;
    private Button loginButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (Button) view.findViewById(R.id.button2);
        loginButton.setOnClickListener(this);

        getButtons(view);


    }

    // Getting the Views & setting an onClickListener to the button variables.
    public void getButtons(View view){
/*
        dButton1 = (Button) view.findViewById(R.id.donation_btn1);
        dButton1.setOnClickListener(this);

        dButton2 = (Button) view.findViewById(R.id.donation_btn2);
        dButton2.setOnClickListener(this);

        dButton3 = (Button) view.findViewById(R.id.donation_btn3);
        dButton3.setOnClickListener(this);

        dButton4 = (Button) view.findViewById(R.id.donation_btn4);
        dButton4.setOnClickListener(this);

        dButton5 = (Button) view.findViewById(R.id.donation_btn5);
        dButton5.setOnClickListener(this);

        dButton6 = (Button) view.findViewById(R.id.donation_btn6);
        dButton6.setOnClickListener(this);*/

    }


    @Override
    public void onClick(View v) {


        Log.d("Wat","oneeeeee");


        switch (v.getId()){

            case R.id.button2:

                // Please go to https://me.sumup.com/developers to get your Affiliate Key by entering the application ID of your app. (e.g. com.sumup.sdksampleapp)
                SumUpLogin sumupLogin = SumUpLogin.builder("72cc96a1-1e06-43c0-8380-5413aa60585e").build();
                SumUpAPI.openLoginActivity(getActivity(), sumupLogin, 1);
                break;

/*            case R.id.donation_btn1:
                Log.d("Wat","oneeeeee");
                break;
            case R.id.donation_btn2:
                Log.d("Wat","two");
                break;
            case R.id.donation_btn3:
                Log.d("Wat","3");
                break;
            case R.id.donation_btn4:
                Log.d("Wat","4");
                break;
            case R.id.donation_btn5:
                Log.d("Wat","5");
                break;
            case R.id.donation_btn6:
                Log.d("Wat","6");
                break;*/
            default:
                break;

        }
    }


    @Override
    protected int layoutRes() {
        return R.layout.fragment_donate;
    }

    public static DonationFragment newInstance() {
        return new DonationFragment();
    }

}
