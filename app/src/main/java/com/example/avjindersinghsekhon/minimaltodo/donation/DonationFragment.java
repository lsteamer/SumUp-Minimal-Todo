package com.example.avjindersinghsekhon.minimaltodo.donation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.sumUp.SumUpPaymentActivity;

public class DonationFragment extends AppDefaultFragment implements View.OnClickListener {

    // These are the buttons that will be pressed to make a donation
    private Button dButton1, dButton2, dButton3, dButton4, dButton5, dButton6;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initializing the buttons
        getButtons(view);


    }

    // Getting the Views & setting an onClickListener to the button variables.
    public void getButtons(View view){

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
        dButton6.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        // Starting the intent to call SumUpPayment
        Intent intentPayment = new Intent(getContext(), SumUpPaymentActivity.class);

        // String that will hold the value to be paid
        String value = v.getTag().toString();


        // Depending which button is pressed, we send different values for the payment
        switch (v.getId()){

            case R.id.donation_btn1:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            case R.id.donation_btn2:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            case R.id.donation_btn3:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            case R.id.donation_btn4:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            case R.id.donation_btn5:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            case R.id.donation_btn6:
                intentPayment.putExtra(SumUpPaymentActivity.EXTRA_CODE, value);
                startActivity(intentPayment);
                getActivity().finish();
                break;
            default:
                break;

        }
    }

    // Legacy code.
    @Override
    protected int layoutRes() {
        return R.layout.fragment_donate;
    }

    public static DonationFragment newInstance() {
        return new DonationFragment();
    }

}
