package com.example.avjindersinghsekhon.minimaltodo.sumUp;

import com.example.avjindersinghsekhon.minimaltodo.sumUp.dataModel.Receipt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SumUpAPI {

    @GET
    Call<Receipt> getData(@Url String url);
}
